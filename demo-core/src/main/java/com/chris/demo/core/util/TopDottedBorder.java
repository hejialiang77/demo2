package com.chris.demo.core.util;

import com.itextpdf.kernel.colors.Color;
import com.itextpdf.kernel.geom.Point;
import com.itextpdf.kernel.pdf.canvas.PdfCanvas;
import com.itextpdf.layout.borders.Border;
import com.itextpdf.layout.borders.DottedBorder;

import static javafx.geometry.Side.TOP;

/**
 * todo
 *
 * @ClassName TopDttedBorder
 * @Author jlhe
 * @Date 2019/3/4
 * @Version 1.0
 */
public class TopDottedBorder extends DottedBorder {

    /**
     * The modifier to be applied on the width to have the initial gap size
     */
    private static final float GAP_MODIFIER = 1.5f;

    public TopDottedBorder(float width) {
        super(width);
    }

    public TopDottedBorder(Color color, float width) {
        super(color, width);
    }

    public TopDottedBorder(Color color, float width, float opacity) {
        super(color, width, opacity);
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public void drawCellBorder(PdfCanvas canvas, float x1, float y1, float x2, float y2, Side defaultSide) {

        float initialGap = width * GAP_MODIFIER;
        float dx = x2 - x1;
        float dy = y2 - y1;
        double borderLength = Math.sqrt(dx * dx + dy * dy);

        float adjustedGap = getDotsGap(borderLength, initialGap + width);
        if (adjustedGap > width) {
            adjustedGap -= width;
        }
        Border.Side borderSide = getBorderSide(x1, y1, x2, y2, defaultSide);
        if (!borderSide.equals(Side.TOP)) {
            return;
        }
        canvas
                .saveState()
                .setLineWidth(width)
                .setStrokeColor(transparentColor.getColor());
        transparentColor.applyStrokeTransparency(canvas);
        canvas
                .setLineDash(width, adjustedGap, width + adjustedGap / 2)
                .moveTo(x1, y1)
                .lineTo(x2, y2)
                .stroke()
                .restoreState();
    }
}
