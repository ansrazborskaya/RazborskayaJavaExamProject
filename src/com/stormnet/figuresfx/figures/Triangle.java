package com.stormnet.figuresfx.figures;

import com.stormnet.figuresfx.drawutils.Drawable;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.Objects;

public class Triangle extends Figure implements Drawable {

    private double height;
    private double x1, x2, x3;
    private double y1, y2, y3;

    public Triangle(double cx, double cy, double lineWidth, Color color) {
        super(FIGURE_TYPE_TRIANGLE, cx, cy, lineWidth, color);
    }

    public Triangle(double cx, double cy, double lineWidth, Color color, double height) {
        this(cx, cy, lineWidth, color);
        this.height = height;

        x1 = cx;
        y1 = cy + height;
        x2 = cx + (3 * height / Math.sqrt(3)) / 2;
        y2 = cy - height / 2;
        x3 = cx - (3 * height / Math.sqrt(3)) / 2;
        y3 = cy - height / 2;

    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public double getX1() {
        return x1;
    }

    public void setX1(double x1) {
        this.x1 = x1;
    }

    public double getX2() {
        return x2;
    }

    public void setX2(double x2) {
        this.x2 = x2;
    }

    public double getX3() {
        return x3;
    }

    public void setX3(double x3) {
        this.x3 = x3;
    }

    public double getY1() {
        return y1;
    }

    public void setY1(double y1) {
        this.y1 = y1;
    }

    public double getY2() {
        return y2;
    }

    public void setY2(double y2) {
        this.y2 = y2;
    }

    public double getY3() {
        return y3;
    }

    public void setY3(double y3) {
        this.y3 = y3;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Triangle triangle = (Triangle) o;
        return Double.compare(triangle.height, height) == 0 &&
                Double.compare(triangle.x1, x1) == 0 &&
                Double.compare(triangle.x2, x2) == 0 &&
                Double.compare(triangle.x3, x3) == 0 &&
                Double.compare(triangle.y1, y1) == 0 &&
                Double.compare(triangle.y2, y2) == 0 &&
                Double.compare(triangle.y3, y3) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(height, x1, x2, x3, y1, y2, y3);
    }

    @Override
    public void draw(GraphicsContext gc) {
        double[] nx = new double[]{x1, x2, x3};
        double[] ny = new double[]{y1, y2, y3};

        gc.setStroke(color);
        gc.strokePolygon(nx, ny, 3);

    }
}
