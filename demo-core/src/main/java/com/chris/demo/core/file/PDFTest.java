package com.chris.demo.core.file;

import com.chris.demo.core.util.NoBorderCell;
import com.itextpdf.forms.PdfAcroForm;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.*;
import com.itextpdf.kernel.pdf.canvas.draw.SolidLine;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.Style;
import com.itextpdf.layout.borders.DottedBorder;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.LineSeparator;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.property.HorizontalAlignment;
import com.itextpdf.layout.property.TextAlignment;
import com.itextpdf.layout.property.UnitValue;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.Map;

public class PDFTest {
    private static final String DEST2 = "E:\\temp\\testDetail.pdf";//文件路径
    private static final String SRC = "E:\\temp\\testDetail.pdf";//文件路径
    private static final String DEST = "E:\\temp\\dest.pdf";//文件路径

    private static  PdfFont sysFont;//中文字体

    static {
        try {
            sysFont = PdfFontFactory.createFont("STSongStd-Light", "UniGB-UCS2-H", false);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void test(String dest) throws Exception {
        detailReci(dest);
//        batchReci();
    }

    private static void batchReci() throws Exception {
        String dest = "E:\\temp\\testBatch.pdf";
        PdfDocument pdfDoc = new PdfDocument(new PdfWriter(dest));
        Document doc = new Document(pdfDoc);//构建文档对象
        doc.setFont(sysFont);
        doc.add(new Paragraph("叧叨呓囊囋囍囎囏囐嘱囒啮囔囕囖").setTextAlignment(TextAlignment.CENTER));
        doc.add(new Paragraph("犇").setTextAlignment(TextAlignment.CENTER));
        doc.add(new LineSeparator(new SolidLine()).setMarginTop(10f));
        Table batchTable = new Table(new float[]{6, 6})
                .setWidth(UnitValue.createPercentValue(80))
                .setHorizontalAlignment(HorizontalAlignment.CENTER)
                .setMarginTop(20f);
        batchTable.addCell(new NoBorderCell("回单编号:"));
        batchTable.addCell(new NoBorderCell("批次号:"));
        batchTable.addCell(new NoBorderCell("工资表名称:"));
        batchTable.addCell(new NoBorderCell("工资月份:"));
        batchTable.addCell(new NoBorderCell("发薪企业名称:"));
        batchTable.addCell(new NoBorderCell("用人单位名称:"));
        batchTable.addCell(new NoBorderCell("合计笔数:"));
        batchTable.addCell(new NoBorderCell("合计金额:"));
        batchTable.addCell(new NoBorderCell("申请时间:"));
        batchTable.addCell(new NoBorderCell("完成时间:"));
        Table table = new Table(new float[]{2f, 2.5f, 3.5f, 2f, 2f})
                .setWidth(UnitValue.createPercentValue(80))
                .setHorizontalAlignment(HorizontalAlignment.CENTER)
                .setMarginTop(20f);
        table.addHeaderCell("姓名");//向表格添加内容
        table.addHeaderCell("工号");
        table.addHeaderCell("工资卡");
        table.addHeaderCell("实发金额");
        table.addHeaderCell("交易结果");
        for (int i = 0; i < 100; i++) {
            table.addCell("姓名" + i);
            table.addCell("1234567");
            table.addCell("6222020202020202000");
            table.addCell("实发金额" + i);
            table.addCell("交易结果" + i);
        }
        doc.add(batchTable);
        doc.add(table);//将表格添加入文档并页面居中
        //盖章
        addSeal(doc);
        doc.close();
    }


    private static void readAndReplace() throws Exception {
        PdfDocument pdfDoc = new PdfDocument(new PdfReader(SRC), new PdfWriter(DEST));
        PdfReader reader = new PdfReader(SRC);

        PdfAcroForm acroForm = PdfAcroForm.getAcroForm(pdfDoc, true);
        Map fields = acroForm.getFormFields();
        fields.forEach((o, o2) -> {
            System.out.println(o);
            System.out.println(o2);
        });

        PdfPage page = pdfDoc.getFirstPage();
        PdfDictionary dict = page.getPdfObject();
        PdfObject object = dict.get(PdfName.Contents);
        if (object instanceof PdfStream) {
            PdfStream stream = (PdfStream) object;
            byte[] data = stream.getBytes();
            String content = new String(data);
            stream.setData(content.replace("批次号", "HELLO WORLD").getBytes(StandardCharsets.UTF_8));
        }
        pdfDoc.close();
    }

    private static void detailReci(String dest) throws Exception {
        long startDt = new Date().getTime();
        System.out.println(startDt);
        PdfDocument pdfDoc = new PdfDocument(new PdfWriter(dest));
        PdfFont sysFont = PdfFontFactory.createFont("STSong-Light", "UniGB-UCS2-H", false);//中文字体
        Document doc = new Document(pdfDoc);//构建文档对象
        doc.setFont(sysFont);
        doc.add(new Paragraph("abcdefghi").setTextAlignment(TextAlignment.CENTER));
        doc.add(new LineSeparator(new SolidLine()).setMarginTop(10f));
        Style boldStyle = new Style()
                .setBorderTop(new DottedBorder(1.0f))
                .setBold();
        Table table = new Table(new float[]{6, 6})
                .setWidth(UnitValue.createPercentValue(80))
                .setHorizontalAlignment(HorizontalAlignment.CENTER)
                .setMarginTop(20f);

        table.addCell(new NoBorderCell("回单编号:"));
        table.addCell(new NoBorderCell("批次号:"));
        table.addCell(new NoBorderCell(1, 2, "付款方信息").addStyle(boldStyle));
        table.addCell(new NoBorderCell("户名"));
        table.addCell(new NoBorderCell("账号:"));
        table.addCell(new NoBorderCell(1, 2, "开户单位:"));
        table.addCell(new NoBorderCell(1, 2, "收款方信息").addStyle(boldStyle));
        table.addCell(new NoBorderCell("户名:"));
        table.addCell(new NoBorderCell("账号:"));
        table.addCell(new NoBorderCell(1, 2, "开户银行:"));
        table.addCell(new NoBorderCell(1, 2, "付款金额").addStyle(boldStyle));
        table.addCell(new NoBorderCell("小写:"));
        table.addCell(new NoBorderCell("大写:"));
        table.addCell(new NoBorderCell(1, 2, "交易信息").addStyle(boldStyle));
        table.addCell(new NoBorderCell("交易类型:实时代付"));
        table.addCell(new NoBorderCell("付款用途:"));
        table.addCell(new NoBorderCell("申请时间:"));
        table.addCell(new NoBorderCell("成功时间:"));
        table.addCell(new NoBorderCell(1, 2, "交易备注:"));
        doc.add(table);
        //盖章
        addSeal(doc);
        doc.close();
        long endDt = new Date().getTime();
        System.out.println(endDt - startDt);

    }

    private static void addSeal(Document doc) throws MalformedURLException {
        Image maru = new Image(ImageDataFactory.create("E:\\temp\\walletseal.PNG"));
        maru.scaleAbsolute(100, 80);                  // 控制图片大小
        maru.setMarginTop(-100);
        maru.setMarginLeft(400);
        doc.add(maru);
    }

    public static void main(String[] args) throws Exception {
        test(DEST2);
        readAndReplace();
    }

}
