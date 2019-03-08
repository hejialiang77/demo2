package com.chris.demo.core.util;

import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.canvas.draw.SolidLine;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.Style;
import com.itextpdf.layout.borders.DottedBorder;
import com.itextpdf.layout.element.*;
import com.itextpdf.layout.property.HorizontalAlignment;
import com.itextpdf.layout.property.TextAlignment;
import com.itextpdf.layout.property.UnitValue;

import java.net.MalformedURLException;
import java.util.Date;

public class PDFTest {
    private static final String DEST2 = "E:\\temp\\testDetail.pdf";//文件路径

    public static void test(String dest) throws Exception {
        detailReci(dest);
//        batchReci(dest);
    }

    private static void batchReci(String dest) throws Exception {
        PdfDocument pdfDoc = new PdfDocument(new PdfWriter(dest));
        Date startDt = new Date();
        System.out.println(startDt);
        PdfFont sysFont = PdfFontFactory.createFont("STSongStd-Light", "UniGB-UCS2-H", false);//中文字体
        Document doc = new Document(pdfDoc);//构建文档对象
        doc.setFont(sysFont);
        doc.add(new Paragraph("连连银通电子支付明细回单").setTextAlignment(TextAlignment.CENTER));
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
        Date date1 = new Date();
        System.out.println(date1.getTime() - startDt.getTime());
        Table table = new Table(new float[]{2f, 2.5f, 3.5f, 2f, 2f})
                .setWidth(UnitValue.createPercentValue(80))
                .setHorizontalAlignment(HorizontalAlignment.CENTER)
                .setMarginTop(20f);
        table.addHeaderCell("姓名");//向表格添加内容
        table.addHeaderCell("工号");
        table.addHeaderCell("工资卡");
        table.addHeaderCell("实发金额");
        table.addHeaderCell("交易结果");
        for (int i = 0; i < 10000; i++) {
            table.addCell("姓名" + i);
            table.addCell("1234567");
            table.addCell("6222020202020202000");
            table.addCell("实发金额" + i);
            table.addCell("交易结果" + i);
        }
        doc.add(batchTable);
        doc.add(table);//将表格添加入文档并页面居中
        Date date2 = new Date();
        System.out.println(date2.getTime() - startDt.getTime());
        //盖章
        addSeal(doc);
        doc.close();
        Date endDt = new Date();
        System.out.println(endDt);
        System.out.println(endDt.getTime() - startDt.getTime());
    }

    private static void detailReci(String dest) throws Exception {
        PdfDocument pdfDoc = new PdfDocument(new PdfWriter(dest));
        PdfFont sysFont = PdfFontFactory.createFont("STSong-Light", "UniGB-UCS2-H", false);//中文字体
        Document doc = new Document(pdfDoc);//构建文档对象
        doc.setFont(sysFont);
        doc.add(new Paragraph("连连银通电子支付凭证").setTextAlignment(TextAlignment.CENTER));
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
        table.addCell(new NoBorderCell(1, 2,"开户单位:"));
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
    }
   private static void addSeal( Document doc) throws MalformedURLException {
//        Image maru  = new Image(ImageDataFactory.create("E:\\temp\\walletseal.PNG"));
//        maru.setFixedPosition(400, 100);           // 控制图片位置
//        maru.scaleAbsolute(100, 80);                  // 控制图片大小
//        doc.add(maru);
    }

    public static void main(String[] args) throws Exception {
        test(DEST2);
    }

}
