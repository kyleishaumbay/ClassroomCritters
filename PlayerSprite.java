/**
This is a class that draws the players of the game.
It implements the interfaces as assets of the game.
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

public class PlayerSprite {
    protected double x, y;
    protected double s;
    protected Color c;
    double checkpointX;
    double checkpointY;
    private Image img;

    public PlayerSprite(double a, double b, double size, Color c, String filename) {
        x = a;
        y = b;
        s = size;
        this.c = c;
        img = Toolkit.getDefaultToolkit().getImage(filename);
    }

    public void drawSprite(Graphics2D g2d) {
        Rectangle2D.Double sq = new Rectangle2D.Double(x, y, s, s);
        g2d.setColor(c);
        //g2d.fill(sq);
        g2d.drawImage(img, (int) x, (int) y, null);

    }

    public void moveH(double n) {
        x += n;
    }

    public void moveV(double n) {
        y += n;
    }

    public void setX(double n) {
        x = n;
    }

    public void setY(double n) {
        y = n;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public boolean isColliding(Obstacle other) {
        return !(this.x + this.s <= other.getX() ||
                this.x >= other.getX() + other.getWidth() ||
                this.y + this.s <= other.getY() ||
                this.y >= other.getY() + other.getHeight());
    }
    public boolean isTouchin(PlayerSprite other) {
        return !(this.x + this.s <= other.getX() ||
                this.x >= other.getX() + other.getSize() ||
                this.y + this.s <= other.getY() ||
                this.y >= other.getY() + other.getSize());
    }
    

    public void respawn() {
        this.x = checkpointX;
        this.y = checkpointY;
    }

    public void setCheckpoint(double a, double b) {
        checkpointX = a;
        checkpointY = b;
    }

    public double getSize() {
        return s;
    }
    public Color getColor(){
        return c;
    }
    
}