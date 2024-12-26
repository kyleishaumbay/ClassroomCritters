/**
This is an interface that is used by the classes to get the method draw. 
This is used for easier access of the method in multiple classes.
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

public interface DrawingObject {
    /** 
*  draws Graphics2D objects
*/
    public void draw(Graphics2D g2d);
}
