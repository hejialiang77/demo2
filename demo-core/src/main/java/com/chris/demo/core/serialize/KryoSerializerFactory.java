package com.chris.demo.core.serialize;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.esotericsoftware.kryo.pool.KryoFactory;
import com.esotericsoftware.kryo.pool.KryoPool;
import org.objenesis.strategy.StdInstantiatorStrategy;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

public class KryoSerializerFactory {


    private static volatile KryoPool kryoPool = null;

    public static Kryo get() {
        return getKryoPool().borrow();
    }

    public static void releaseKryo(Kryo kryo) {
        getKryoPool().release(kryo);
    }

    private static KryoPool getKryoPool() {
        if (kryoPool == null) {

            synchronized(KryoSerializerFactory.class) {

                if (kryoPool == null) {

                    KryoFactory factory = () -> {
                        Kryo kryo = new Kryo();
                        kryo.setInstantiatorStrategy(new Kryo.DefaultInstantiatorStrategy(new StdInstantiatorStrategy()));
                        // configure kryo instance, customize settings
                        return kryo;
                    };
                    // Build pool with SoftReferences enabled (optional)
                    kryoPool = new KryoPool.Builder(factory).softReferences().build();
                }

                return kryoPool;
            }
        }

        return kryoPool;
    }

    /**
     * 进行发序列化对象.
     *
     * @param bytes
     * @return
     */
    public static Object deSerialization(byte[] bytes, Class clazz) {
        ByteArrayInputStream inputStream=new ByteArrayInputStream(bytes);
        Input input = new Input(inputStream);
        Kryo kryo = KryoSerializerFactory.get();
        try{
            return kryo.readObject(input, clazz);
        } finally {
            input.close();
            KryoSerializerFactory.releaseKryo(kryo);
        }
    }

    /**
     * 进行序列化对象.
     *
     * @param object
     * @return
     */
    public static byte[] serialization(Object object) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

        Output output = new Output(byteArrayOutputStream);

        Kryo kryo = KryoSerializerFactory.get();

        try {
            kryo.writeObject(output, object);
        } finally {
            output.flush();
            output.close();
        }

        return byteArrayOutputStream.toByteArray();
    }
}
