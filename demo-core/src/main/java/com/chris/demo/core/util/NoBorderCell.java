package com.chris.demo.core.util;

import com.itextpdf.layout.borders.Border;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;

/**
 * todo
 *
 * @ClassName NoBorderCell
 * @Author jlhe
 * @Date 2019/2/13
 * @Version 1.0
 */
public class NoBorderCell extends Cell {

    public NoBorderCell() {
        super();
        this.setBorder(Border.NO_BORDER)
                .setPaddings(0, 0, 10f, 0);
    }

    public NoBorderCell(String content) {
        super();
        this.add(new Paragraph(content)).setBorder(Border.NO_BORDER)
                .setPaddings(0, 0, 10f, 0);
    }

    public NoBorderCell(int rowspan, int colspan, String content) {
        super(rowspan, colspan);
        this.add(new Paragraph(content)).setBorder(Border.NO_BORDER)
                .setPaddings(0, 0, 10f, 0);
    }
}
