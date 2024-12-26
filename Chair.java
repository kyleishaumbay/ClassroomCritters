/**
This is a class that draws the chairs in the game.
It contains methods to access its elements and implements interfaces for the drawing and the actions.
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

public class Chair implements Obstacle, DrawingObject {
    protected int x;
    protected int y;
    protected double width;
    protected double height;
    protected Color color;
    protected Image img;
    protected boolean activate;
/** 
*  instantiates the values used in the class
*/
    public Chair(int x, int y, double width, double height, Color color, String filename) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.color = color;
        img = Toolkit.getDefaultToolkit().getImage(filename);
    }
/** 
*  draw the shapes and images
*/
    public void draw(Graphics2D g2d) {
        Rectangle2D.Double sq = new Rectangle2D.Double(x, y, width, height);
        g2d.setColor(color);
       // g2d.fill(sq);
        g2d.drawImage(img, x, y, null);
    }
/** 
*  gets x-coordinate
*/
    @Override
    public double getX() {
        return x;
    }
/** 
*  gets y-coordinate
*/
    @Override
    public double getY() {
        return y;
    }
/** 
*  gets width
*/
    @Override
    public double getWidth() {
        return width;
    }
/** 
*  gets height
*/
    public double getHeight() {
        return height;
    }
/** 
*  sets x-coordinate
*/
    @Override
    public void setX(int n) {
        x = n;
    }
/** 
*  sets y-coordinate
*/
    @Override
    public void setY(int n) {
        y = n;
    }
/** 
*  gets color
*/
    @Override
    public Color getColor() {
        return color;
    }

    /** 
*  changes to activate or not
*/
@Override
    public void changeActivate(boolean b) {

    }
/** 
*  gets activate
*/
    @Override
    public boolean getActivate() {
        return activate;
    }
/** 
*  changes y -coordinate
*/
    public void moveY(double n) {
        this.y += n;
    }
/** 
*  checks if colliding
*/
    public boolean isColliding(Obstacle other) {
        return !(this.x + this.width <= other.getX() ||
                this.x >= other.getX() + other.getWidth() ||
                this.y + this.height <= other.getY() ||
                this.y >= other.getY() + other.getHeight());
    }
}
