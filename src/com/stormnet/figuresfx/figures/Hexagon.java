package com.stormnet.figuresfx.figures;

import com.stormnet.figuresfx.drawutils.Drawable;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Hexagon extends Figure implements Drawable {

    private double radius;
    private double x1, x2, x3, x4, x5, x6;
    private double y1, y2, y3, y4, y5, y6;

    public Hexagon(double cx, double cy, double lineWidth, Color color) {
        super(FIGURE_TYPE_HEXAGON, cx, cy, lineWidth, color);
    }

    public Hexagon(double cx, double cy, double lineWidth, Color color, double radius) {
        this(cx, cy, lineWidth, color);
        this.radius = radius;

        double offsetx = 1.5 * (radius * Math.sqrt(3) / 3);
        double offsety = radius / 2;

        x1 = cx;
        y1 = cy + radius;
        x2 = cx + offsetx;
        y2 = cy + offsety;
        x3 = cx + offsetx;
        y3 = cy - offsety;
        x4 = cx;
        y4 = cy - radius;
        x5 = cx - offsetx;
        y5 = cy - offsety;
        x6 = cx - offsetx;
        y6 = cy + offsety;
    }

    @Override
    public void draw(GraphicsContext gc) {
        double nx[] = new double[]{x1, x2, x3, x4, x5, x6};
        double ny[] = new double[]{y1, y2, y3, y4, y5, y6};

        gc.setStroke(color);
        gc.strokePolygon(nx, ny, 6);

    }
}
