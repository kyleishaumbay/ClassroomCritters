import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

/**
 * This is a class that draws the bexit of the game.
 * This will determine if the players have reached the exit of the game.
 * 
 * @author Gershwin Colin C. Hierro (223144) & Marie Kyleisha E. Umbay (226508)
 * @version May 15, 2023
 **/
/*
 * I have not discussed the Java language code in my program
 * with anyone other than my instructor or the teaching assistants
 * assigned to this course.
 * I have not used Java language code obtained from another student,
 * or any other unauthorized source, either modified or unmodified.
 * If any Java language code or documentation used in my program
 * was obtained from another source, such as a textbook or website,
 * that has been clearly noted with a proper citation in the comments
 * of my program.
 */
public class Exit implements Obstacle, DrawingObject {
    private int x;
    private int y;
    private double width;
    private double height;

/** 
*  instantiates the values used in the class
*/
    public Exit(int x, int y, double width, double height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }
/** 
*  draw the shapes and images
*/
    @Override
    public void draw(Graphics2D g2d) {
        Rectangle2D.Double sq = new Rectangle2D.Double(x, y, width, height);
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

    }
/** 
*  sets y-coordinate
*/
    @Override
    public void setY(int n) {

    }
/** 
*  gets activate
*/
    @Override
    public boolean getActivate() {
        return activate;
    }
/** 
*  gets color
*/
    @Override
    public Color getColor() {
        Color c = Color.blue;
        return c;
    }
/** 
*  changes to activate or not
*/
    @Override
    public void changeActivate(boolean b) {
    
    }
}
