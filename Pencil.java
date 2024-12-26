/**
This is a class that draws the pencil in the game.
It implements the interfaces for the assets of the game.
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

public class Pencil implements Obstacle, DrawingObject {
    private int x;
    private int y;
    private double width;
    private double height;
    private Color color;
    private Image img;
    private boolean activate = true;
    public Pencil(int x, int y, double width, double height, Color color, String filename) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.color = color;
        img = Toolkit.getDefaultToolkit().getImage(filename);
    }

    public void draw(Graphics2D g2d) {
        Rectangle2D.Double sq = new Rectangle2D.Double(x, y, width, height);
        g2d.setColor(color);
        //g2d.fill(sq);
        g2d.drawImage(img, x, y, null);
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

    }

    @Override
    public boolean getActivate() {
        return activate;
    }

}
