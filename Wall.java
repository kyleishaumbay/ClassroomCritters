/**
This is a class that draws the wall in the game.
It implements the interfaces that are used by the assets in the game.
@author Gershwin Colin C. Hierro (223144) & Marie Kyleisha E. Umbay (226508)
@version May 15, 2023
**/
/*
I have not discussed the Java language code in my program
with anyone other than my instructor or the teaching assistants
assigned to this course.
I have not used Java language code obtained from another student,
or any other unauthorized source, either modified or unmodified.
If any Java language code or documentation used in my program
was obtained from another source, such as a textbook or website,
that has been clearly noted with a proper citation in the comments
of my program.
*/
import java.awt.*;
import java.awt.geom.*;

public class Wall implements Obstacle, DrawingObject {
    private int x;
    private int y;
    private double width;
    private double height;
    private Color color;

    public Wall(int x, int y, double width, double height, Color color) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public void draw(Graphics2D g2d) {
        Rectangle2D.Double sq = new Rectangle2D.Double(x, y, width, height);
        g2d.setColor(color);
        g2d.fill(sq);
    }

    @Override
    public double getX() {
        return x;
    }
    @Override
    public double getY() {
        return y;
    }
    @Override
    public double getWidth(){
        return width;
    }

    public double getHeight(){
        return height;
    }

    @Override
    public void setX(int n) {

    }

    @Override
    public void setY(int n) {

    }

    @Override
    public Color getColor() {
        return color;
    }

    @Override
    public void changeActivate(boolean b) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'changeActivate'");
    }

    @Override
    public boolean getActivate() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getActivate'");
    }

}
