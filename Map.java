/**
This is a class that draws the whole map of the game.
This is where all the obstacles and other game assets are added.
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
import java.awt.geom.Rectangle2D;
import java.util.*;

public class Map implements DrawingObject {
    private Image img;
    public Barrier b1;
    public Barrier b2;
    public Barrier b3;
    public Barrier b4;
    public Barrier b5;
    public Barrier b6;
    public Barrier b7;
    public Barrier b8;
    public Barrier b9;
    public Barrier b10;
    public Barrier b11;
    public Barrier b12;
    public Barrier b13;
    public Barrier b14;
    public Barrier b15;
    public Checkpoint cp1;
    public Checkpoint cp2;
    public Pencil rp;
    public Pencil lp;
    public Pencil p;
    public Spikes rs;
    public Spikes bs;
    public Button rb;
    public Button bb;
    public Button rb1;
    public Button bb1;
    public Chair rc;
    public Chair rc1;
    public Chair bc;
    public Wall w;
    public Exit e;

    public static ArrayList<Obstacle> obstacles = new ArrayList<>();

    public Map() {
        img = Toolkit.getDefaultToolkit().getImage("Background.png");
        b1 = new Barrier(0, 1008, 1008, 72);
        b2 = new Barrier(1111.5, 1008, 808.5, 72);
        b3 = new Barrier(0, 796.5235, 1008, 40.4765);
        b4 = new Barrier(1111.5, 796.5, 688.5, 40.4765);
        b5 = new Barrier(0, 607.5, 360, 40.4765);
        b6 = new Barrier(468, 607.5, 423, 40.4765);
        b7 = new Barrier(1363.5, 607.5235, 436.5, 40.4765);
        b8 = new Barrier(0, 418.482, 576, 40.4765);
        b9 = new Barrier(685, 418.5235, 432, 40.4765);
        b10 = new Barrier(1224, 418.5235, 432, 40.4765);
        b11 = new Barrier(1764, 418.5, 156, 40.4765);
        b12 = new Barrier(1620, 229.5235, 300, 40.4765);
        b13 = new Barrier(612, 229.5, 898.5, 40.4765);
        b14 = new Barrier(180, 229.5, 324, 40.4765);
        b15 = new Barrier(0, 309.8039, 108, 108.6961);
        cp1 = new Checkpoint(297, 707, 45, 90, Color.RED);
        cp2 = new Checkpoint(1400, 518, 45, 90, Color.RED);
        p = new Pencil(999, 612, 256.5, 31.5, Color.red, "Pencil.png");
        rs = new Spikes(432, 932, 292.5, 76.5, Color.RED, "RedSpikes.png");
        bs = new Spikes(1251, 936, 225, 72, Color.BLUE, "BlueSpikes.png");
        rb = new Button(801, 968, 76.5, 40.5, Color.RED, "RedButton.png");
        bb = new Button(1548, 968, 76.5, 40.5, Color.RED, "BlueButton.png");
        // rb1 = new Button(1035, 189, 76.5, 40.5, Color.RED, "RedButton.png");
        // bb1 = new Button(1301, 189, 76.5, 40.5, Color.RED, "BlueButton.png");
        rc = new Chair(1828, 941, 63, 67.5, Color.red, "RedChair.png");
        rc1 = new Chair(1681, 540, 63, 67.5, Color.red, "RedChair.png");
        bc = new Chair(383, 729, 63, 67.5, Color.red, "BlueChair.png");
        e = new Exit(1813, 108, 76.5, 121.5);
       // w = new Wall(1197, 72, 18, 157.5, Color.red);

        obstacles.add(b1);
        obstacles.add(b2);
        obstacles.add(b3);
        obstacles.add(b4);
        obstacles.add(b5);
        obstacles.add(b6);
        obstacles.add(b7);
        obstacles.add(b8);
        obstacles.add(b9);
        obstacles.add(b10);
        obstacles.add(b11);
        obstacles.add(b12);
        obstacles.add(b13);
        obstacles.add(b14);
        obstacles.add(b15);
        //obstacles.add(w);
        obstacles.add(bc);
        obstacles.add(rc1);
        obstacles.add(rc);
       // obstacles.add(bb1);
        //obstacles.add(rb1);
        obstacles.add(bb);
        obstacles.add(rb);
        obstacles.add(bs);
        obstacles.add(rs);
        obstacles.add(p);
        // obstacles.add(lp);
        // obstacles.add(rp);
        obstacles.add(cp1);
        obstacles.add(cp2);
        obstacles.add(e);

    }

    public void draw(Graphics2D g2d) {
        g2d.setColor(Color.GRAY);
        g2d.fillRect(0, 0, 1920, 1080);
        g2d.drawImage(img, 0, 0, null);
        g2d.setColor(Color.red);
        b1.draw(g2d);
        b2.draw(g2d);
        b3.draw(g2d);
        b4.draw(g2d);
        b5.draw(g2d);
        b6.draw(g2d);
        b7.draw(g2d);
        b8.draw(g2d);
        b9.draw(g2d);
        b10.draw(g2d);
        b11.draw(g2d);
        b12.draw(g2d);
        b13.draw(g2d);
        b14.draw(g2d);
        b15.draw(g2d);
        rb.draw(g2d);
        cp1.draw(g2d);
        cp2.draw(g2d);
        // rp.draw(g2d);
        // lp.draw(g2d);
        p.draw(g2d);
        rs.draw(g2d);
        bs.draw(g2d);
        bb.draw(g2d);
        //rb1.draw(g2d);
      //  bb1.draw(g2d);
        rc.draw(g2d);
        rc1.draw(g2d);
        bc.draw(g2d);
        //w.draw(g2d);
    }

}