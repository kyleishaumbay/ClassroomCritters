/**
This is a class that shows a menu frame for the client to enter necessary details.
This runs before the game itself.
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
import javax.swing.*;
import java.awt.event.*;

public class MenuFrame extends JFrame {
     private int w, h;
     private Container cp;
     private JLabel s;
     private JTextField tf1;
     private JTextField tf2;
     private String ip;
     private int port;

     public MenuFrame(int w, int h) {
          this.w = w;
          this.h = h;
          tf1 = new JTextField();
          tf2 = new JTextField();
          s = new JLabel();
          ip = "";
          port = 0;
     }

     public void setUpGUI() {
          cp = this.getContentPane();
          cp.setPreferredSize(new Dimension(w, h));
          this.setLayout(null);
          s.setIcon(new ImageIcon("Menu.png"));
          s.setBounds(0, 0, 1920, 1080);
          tf1.setBounds(1008, 524, 531, 95); // https://www.tutorialspoint.com/what-is-the-use-of-setbounds-method-in-java
          tf2.setBounds(1008, 631, 531, 95);
          cp.add(tf1);
          cp.add(tf2);
          cp.add(s);
          this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
          this.pack();
          this.setVisible(true);
          setUpMouseListener();
     }

     private void setUpMouseListener() {
          MouseListener ml = new MouseListener() {
               @Override
               public void mouseClicked(MouseEvent e) {
                    double x = e.getX();
                    double y = e.getY();
                    if ((x > 504.2 && x < 911.6 + 504.2) && (y > 800.2 && y < 800.2 + 197.2)) {
                         // if((x > 0 && x < 300) && (y > 0 && y < 300)) {
                         ip = tf1.getText();
                         port = Integer.parseInt(tf2.getText());
                         cp.remove(s);
                         cp.remove(tf1);
                         cp.remove(tf2);
                         GameFrame gf = new GameFrame(1920, 1080);
                         gf.connectToServer(ip, port);
                         gf.setUpGUI();
                    }
               }

               @Override
               public void mousePressed(MouseEvent e) {

               }

               @Override
               public void mouseReleased(MouseEvent e) {

               }

               @Override
               public void mouseEntered(MouseEvent e) {

               }

               @Override
               public void mouseExited(MouseEvent e) {

               }
          };
          cp.addMouseListener(ml);

     }

     public int getPort() {
          return port;
     }

     public String getIP() {
          return ip;
     }

}