package com.stormnet.figuresfx.figures;

import com.stormnet.figuresfx.drawutils.Drawable;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.Objects;

public class Circle extends Figure implements Drawable {

    private double radius;

    private Circle(double cx, double cy, double lineWidth, Color color) {
        super(FIGURE_TYPE_CIRCLE, cx, cy, lineWidth, color);
    }

    public Circle(double cx, double cy, double lineWidth, Color color, double radius) {
        this(cx, cy, lineWidth, color);
        this.radius = radius < 10 ? 50 : radius;
    }

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Circle circle = (Circle) o;
        return Double.compare(circle.radius, radius) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(radius);
    }

    @Override
    public void draw(GraphicsContext gc) {

        gc.setLineWidth(lineWidth);
        gc.setStroke(color);
        gc.strokeOval(cx - radius, cy - radius, radius * 2, radius * 2);

    }
}
