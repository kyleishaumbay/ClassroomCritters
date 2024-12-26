/**
This is a class that draws the background of the game and the players.
It uses the JComponent to access the Graphics object.
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
import javax.swing.*;
import java.awt.*;

public class GameCanvas extends JComponent {
    protected Map bg;
    protected PlayerSprite me;
    protected PlayerSprite enemy;
    protected GameFrame pfr;
/** 
*  instantiates the game map
*/
    public GameCanvas() {
        bg = new Map();
    }
/** 
*  adds the players
*/
    public void addPlayer(PlayerSprite ps, PlayerSprite  ps2, int playerID) {
        if (playerID == 1) {
            me = ps;
            enemy = ps2;

        } else {
            me = ps2;
            enemy = ps;

        }

    }
/** 
*  draws Graphics objects
*/
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        bg.draw(g2d);
        enemy.drawSprite(g2d);
        me.drawSprite(g2d);


    }


}
