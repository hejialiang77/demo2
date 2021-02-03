package com.chris.demo.core.security;

import javafx.util.Pair;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

import javax.crypto.Cipher;
import java.io.*;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.*;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.RSAPrivateKeySpec;
import java.security.spec.RSAPublicKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Enumeration;

public class RSAUtil {
    private static Provider provider = new BouncyCastleProvider();

    private RSAPublicKey publicKey;
    private RSAPrivateKey privateKey;

    public RSAUtil(RSAPublicKey publicKey, RSAPrivateKey privateKey) {
        this.publicKey = publicKey;
        this.privateKey = privateKey;
    }

    public void setPrivateKey(RSAPrivateKey privateKey) {
        this.privateKey = privateKey;
    }

    public String encrypt(String data) throws Exception {
        return Base64.encode(encrypt(this.privateKey, data.getBytes()));
    }

    public static String encrypt(RSAPublicKey publicKey, String data) throws Exception {
        return Base64.encode(encrypt(publicKey, data.getBytes()));
    }

    public String decrypt(String data) throws Exception {
        return new String(decrypt(this.publicKey, Base64.decode(data)));
    }

    public static String decrypt(RSAPrivateKey privateKey, String data) throws Exception {
        return new String(decrypt(privateKey, Base64.decode(data)));
    }

    public byte[] encrypt(byte[] data) throws Exception {
        return encrypt(this.privateKey, data);
    }

    public byte[] decrypt(byte[] data) throws Exception {
        return decrypt(this.publicKey, data);
    }

    public String sign(String text) throws Exception {
        return RSAUtil.sign(this.privateKey, text);
    }

    public boolean verify(String text, String sign) throws Exception {
        return RSAUtil.verify(publicKey, text, sign);
    }

    public static String sign(PrivateKey privateKey, String text) throws Exception {
        Signature signature = Signature.getInstance("SHA1WithRSA", provider);
        signature.initSign(privateKey);
        signature.update(text.getBytes(StandardCharsets.UTF_8));
        byte[] data = signature.sign();
        return Base64.encode(data);
    }

    public static boolean verify(PublicKey publicKey, String text, String sign) throws Exception {
        Signature signature = Signature.getInstance("SHA1WithRSA", provider);
        signature.initVerify(publicKey);
        signature.update(text.getBytes(StandardCharsets.UTF_8));
        byte[] signed = Base64.decode(sign);
        return signature.verify(signed);
    }

    public static PrivateKey loadPrivateKey(String alias, String path, String password) throws Exception {
        try (InputStream ksfis = new FileInputStream(path)) {
            KeyStore ks = KeyStore.getInstance("pkcs12");
            char[] storePwd = password.toCharArray();
            char[] keyPwd = password.toCharArray();
            ks.load(ksfis, storePwd);
            // 从密钥仓库得到私钥
            return (PrivateKey) ks.getKey(alias, keyPwd);
        }
    }

    public static PublicKey loadPublicKey(String alias, String path, String password) throws Exception {
        try (InputStream ksfis = new FileInputStream(path)) {
            KeyStore ks = KeyStore.getInstance("pkcs12");
            char[] storePwd = password.toCharArray();
            ks.load(ksfis, storePwd);
            // 从密钥仓库得到私钥
            return ks.getCertificate(alias).getPublicKey();
        }
    }

    /**
     * 生成密钥对
     *
     * @return KeyPair
     * @throws Exception
     */
    public static KeyPair generateKeyPair() throws Exception {
        KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance("RSA", provider);
        final int KEY_SIZE = 1024;
        keyPairGen.initialize(KEY_SIZE, new SecureRandom());
        return keyPairGen.genKeyPair();
    }

    /**
     * 生成公钥
     *
     * @param modulus
     * @param publicExponent
     * @return RSAPublicKey
     * @throws Exception
     */
    public static RSAPublicKey generateRSAPublicKey(byte[] modulus, byte[] publicExponent) throws Exception {
        KeyFactory keyFac = KeyFactory.getInstance("RSA", provider);
        RSAPublicKeySpec pubKeySpec = new RSAPublicKeySpec(new BigInteger(modulus), new BigInteger(publicExponent));
        return (RSAPublicKey) keyFac.generatePublic(pubKeySpec);
    }

    /**
     * 生成私钥
     *
     * @param modulus
     * @param privateExponent
     * @return RSAPrivateKey
     * @throws Exception
     */
    public static RSAPrivateKey generateRSAPrivateKey(byte[] modulus, byte[] privateExponent) throws Exception {
        KeyFactory keyFac = KeyFactory.getInstance("RSA", provider);
        RSAPrivateKeySpec priKeySpec = new RSAPrivateKeySpec(new BigInteger(modulus),
                new BigInteger(privateExponent));
        return (RSAPrivateKey) keyFac.generatePrivate(priKeySpec);
    }

    /**
     * 加密
     *
     * @param key  加密的密钥
     * @param data 待加密的明文数据
     * @return 加密后的数据
     * @throws Exception
     */
    public static byte[] encrypt(Key key, byte[] data) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding", provider);
        cipher.init(Cipher.ENCRYPT_MODE, key);
        int blockSize = cipher.getBlockSize();//获得加密块大小，如：加密前数据为128个byte，而key_size=1024 加密块大小为 127 byte,加密后为128个byte;因此共有2个加密块，第一个127 byte第二个为1个byte
        int outputSize = cipher.getOutputSize(data.length);//获得加密块加密后块大小
        int leavedSize = data.length % blockSize;
        int blocksSize = leavedSize != 0 ? data.length / blockSize + 1 : data.length / blockSize;
        byte[] raw = new byte[outputSize * blocksSize];
        int i = 0;
        while (data.length - i * blockSize > 0) {
            cipher.doFinal(data, i * blockSize, Math.min(data.length - i * blockSize, blockSize), raw, i * outputSize);
            // 这里面doUpdate方法不可用，查看源代码后发现每次doUpdate后并没有什么实际动作除了把byte[]放到 ByteArrayOutputStream中，而最后doFinal的时候才将所有的byte[]进行加密，可是到了此时加密块大小很可能已经超出了 OutputSize所以只好用dofinal方法。
            i++;
        }
        return raw;
    }

    /**
     * 解密
     *
     * @param key 解密的密钥
     * @param raw 已经加密的数据
     * @return 解密后的明文
     * @throws Exception
     */
    public static byte[] decrypt(Key key, byte[] raw) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding", provider);
        cipher.init(Cipher.DECRYPT_MODE, key);
        int blockSize = cipher.getBlockSize();
        ByteArrayOutputStream bout = new ByteArrayOutputStream(64);
        int j = 0;
        while (raw.length - j * blockSize > 0) {
            bout.write(cipher.doFinal(raw, j * blockSize, blockSize));
            j++;
        }
        return bout.toByteArray();
    }

    public static String getPublicKeyString(RSAPublicKey key) throws Exception {
        String exponent = RSAUtil.byte2hex(key.getPublicExponent().toByteArray());
        String modulus = RSAUtil.byte2hex(key.getModulus().toByteArray());
        return modulus + " " + exponent;
    }

    public static String getPrivateKeyString(RSAPrivateKey key) throws Exception {
        String exponent = RSAUtil.byte2hex(key.getPrivateExponent().toByteArray());
        String modulus = RSAUtil.byte2hex(key.getModulus().toByteArray());
        return modulus + " " + exponent;
    }

    public static RSAPublicKey getPublicKey(String keyString) throws Exception {
        String[] part = keyString.split(" ");
        if (part.length != 2)
            throw new Exception("密钥文件错误。");

        byte[] bytes = RSAUtil.hex2byte(part[0]);
        BigInteger modulus = new BigInteger(bytes);
        bytes = RSAUtil.hex2byte(part[1]);
        BigInteger publicExponent = new BigInteger(bytes);

        return RSAUtil.generateRSAPublicKey(modulus.toByteArray(), publicExponent.toByteArray());
    }

    public static RSAPrivateKey getPrivateKey(String keyString) throws Exception {
        String[] part = keyString.split(" ");
        if (part.length != 2)
            throw new Exception("密钥文件错误。");
        byte[] bytes = RSAUtil.hex2byte(part[0]);
        BigInteger modulus = new BigInteger(bytes);
        bytes = RSAUtil.hex2byte(part[1]);
        BigInteger privateExponent = new BigInteger(bytes);

        return RSAUtil.generateRSAPrivateKey(modulus.toByteArray(), privateExponent.toByteArray());
    }

    //字节码转换成16进制字符串
    public static String byte2hex(byte[] bytes) {
        StringBuilder retString = new StringBuilder();
        for (byte aByte : bytes) {
            retString.append(Integer.toHexString(0x0100 + (aByte & 0x00FF))
                    .substring(1).toUpperCase());
        }
        return retString.toString();
    }

    public static String byte2hex(byte[] bytes, int index, int len) {
        StringBuilder retString = new StringBuilder();
        for (int i = index; i < len; ++i) {
            retString.append(Integer.toHexString(0x0100 + (bytes[i] & 0x00FF))
                    .substring(1).toUpperCase());
        }
        return retString.toString();
    }

    //将16进制字符串转换成字节码
    public static byte[] hex2byte(String hex) {
        byte[] bts = new byte[hex.length() / 2];
        for (int i = 0; i < bts.length; i++) {
            bts[i] = (byte) Integer.parseInt(hex.substring(2 * i, 2 * i + 2),
                    16);
        }
        return bts;
    }


    public static PrivateKey loadPrivateKey(String path, String password) throws Exception {
        FileInputStream ksfis = null;
        try {
            KeyStore ks = KeyStore.getInstance("pkcs12");

            ksfis = new FileInputStream(path);
            char[] storePwd = password.toCharArray();
            char[] keyPwd = password.toCharArray();

            ks.load(ksfis, storePwd);
            String keyAlias = null;
            Enumeration<String> enumas = ks.aliases();
            if (enumas.hasMoreElements()) {
                keyAlias = (String) enumas.nextElement();
            }
            // 从密钥仓库得到私钥
            return (PrivateKey) ks.getKey(keyAlias, keyPwd);
        } finally {
            if (ksfis != null)
                ksfis.close();
        }
    }

    /**
     * 装载公钥
     *
     * @param path
     * @return
     * @throws Exception
     */
    public static PublicKey loadPublicKey(String path) throws Exception {
        try (InputStream inStream = new FileInputStream(new File(path))) {
            // 创建X509工厂类
            CertificateFactory cf = CertificateFactory.getInstance("X.509");
            // 创建证书对象
            X509Certificate oCert = (X509Certificate) cf.generateCertificate(inStream);
            return oCert.getPublicKey();
        }
    }


    public static Pair<String,String> convertToNCKey(String llPublicKey, String llPrivateKey) throws Exception {
        byte[] pubKeyByte = Base64.decode(llPublicKey);
        X509EncodedKeySpec x509ek = new X509EncodedKeySpec(pubKeyByte);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        RSAPublicKey publicKey = (RSAPublicKey) keyFactory.generatePublic(x509ek);
        String ncPub =getPublicKeyString(publicKey);
        System.out.println("publicKey:");
        System.out.println(ncPub);

        byte[] priKeyByte = Base64.decode(llPrivateKey);
        PKCS8EncodedKeySpec s8ek = new PKCS8EncodedKeySpec(priKeyByte);
        keyFactory = KeyFactory.getInstance("RSA");
        RSAPrivateKey privateKey = (RSAPrivateKey) keyFactory.generatePrivate(s8ek);
        String ncPri = getPrivateKeyString(privateKey);
        System.out.println("privateKey:");
        System.out.println(ncPri);
        return new Pair<>(ncPub, ncPri);
    }


    public static void main(String[] args) throws Exception {

        String pub = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDPHR35XPLV+C5H/hVH7rSncZDI\n" +
                "BxCKK0kTmBKrcDtXvedPcUBspK6opjkCpqnFEbgpoFUi8pomQoiY8fFQLV/SdP8C\n" +
                "y50WopuT+noxFbq0Z9XrPbzCij+iX5D6dWRKWJgKY3UFh854AY3UC4ZPf0X9/vvz\n" +
                "FeqSNFnK+vG+SGX3FQIDAQAB";

        String pri = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAM8dHflc8tX4Lkf+\n" +
                "FUfutKdxkMgHEIorSROYEqtwO1e9509xQGykrqimOQKmqcURuCmgVSLymiZCiJjx\n" +
                "8VAtX9J0/wLLnRaim5P6ejEVurRn1es9vMKKP6JfkPp1ZEpYmApjdQWHzngBjdQL\n" +
                "hk9/Rf3++/MV6pI0Wcr68b5IZfcVAgMBAAECgYAQ3cUoHnqkFB7ou/dNsVHC5Quu\n" +
                "Tmz5Y4cC8npqvawHfC6PGKDveAXFNCwmXB4tL0E5GpZ0Tx9giECJfAJzzg4kduEw\n" +
                "m7dy3f5yzofdiddz/x3dtAQyEpSan39tsK1pvXoacgjoIr5QLs541thyXas6kA7P\n" +
                "vdbD3oUef7pSdHAHgQJBAOug7R+DgIxH5QRpybI0x04n4tEysNd+rKB+3tt2eqLA\n" +
                "/ttC5fgN1ehs5aTEf7eEBHZit1hRjDPrmsF/S9Ub+GECQQDhBRUZ5Mtnwn8v4UwD\n" +
                "flrq3t8519HE2lMiHsMY2EEPySNpuEdkFLkRkP/MVmdBLoNUP6P9Xp0ViVY4vHOQ\n" +
                "q2s1AkA1lctXN1isjk0Oy+AKnuYUaA209Yox96Taev/DeRY6nRlYU8ZouvzCdsFi\n" +
                "zy0cVvGM2rZHZ4Qy6omvJ3vfYxUBAkBrwLpNDwHUaw/qxrFWzvAMnrM4iL+7y2yZ\n" +
                "3y8/u3bUb1YU7xjItR+2aMAFgq6pSPO8qUkHMIJ6aKywOJYChgOtAkEA5HnilrEt\n" +
                "CBVtstQAZR2GqjNRxFCmoF4DlsSyp/Dk6jvaRz6SM/asn+QkfI61nWlrP8GMYJLm\n" +
                "Ldnhqjov//GthA==";

        Pair<String,String>  keys =  convertToNCKey(pub,pri);
        String ori = "测试一下";
        System.out.println("---------------------\n原文:\n"+ori);
        String miwen = encrypt(getPublicKey(keys.getKey()), ori);
        System.out.println("---------------------\n密文:\n"+miwen);
        String card_no = decrypt(getPrivateKey(keys.getValue()),miwen);
        System.out.println("---------------------\n明文:\n" + card_no);

    }
}
