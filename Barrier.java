/**
This is a class that draws the barriers or the platforms in the game.
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

import java.awt.Color;
import java.awt.Graphics2D;

public class Barrier implements Obstacle, DrawingObject {
    private double x, y, h, w;
    private boolean activate;
/** 
*  instantiates the values used in the class
*/
    public Barrier(double x, double y, double w, double h) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
    }
/** 
*  draws the necessary shapes
*/
    public void draw(Graphics2D g2d) {

        g2d.drawRect((int) x, (int) y, (int) w, (int) h);
    }
/** 
*  returns the width
*/
    @Override
    public double getWidth() {
        return w;

    }
/** 
*  returns the height
*/
    @Override
    public double getHeight() {
        return h;

    }
/** 
*  sets new x-coordinate
*/
    @Override
    public void setX(int n) {

    }
/** 
*  sets new y-coordinate
*/
    @Override
    public void setY(int n) {

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
*  gets color
*/
    @Override
    public Color getColor() { 
        Color c = new Color(0,0,0);
        return c;
    }
/** 
*  changes if activated or not
*/
    @Override
    public void changeActivate(boolean b) {
        
    }
/** 
*  gets the boolean activate
*/
    @Override
    public boolean getActivate() {
        return activate;
    }


}
