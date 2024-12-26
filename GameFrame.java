
/**
This is a frame class that contains the methods for the client.
This is also where animation and the game interface is done.
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
import java.io.*;
import java.net.*;

public class GameFrame extends JFrame {
    private int w, h;
    private Container cp;
    private PlayerSprite me;
    private PlayerSprite enemy;
    protected GameCanvas gc;
    private Timer t;
    private boolean up;
    private boolean down;
    private boolean left;
    private boolean right;
    private Socket socket;
    private int playerID;
    private ReadFromServer rfsRunnable;
    private WriteToServer wtsRunnable;
    private Map bg;

    /**
     * puts values and instantiates
     */
    public GameFrame(int w, int h) {
        this.w = w;
        this.h = h;
        up = false;
        down = false;
        left = false;
        right = false;
        bg = new Map();
    }

    /**
     * sets up the GUI
     */
    public void setUpGUI() {
        cp = this.getContentPane();
        this.setTitle("Player" + playerID);
        cp.setPreferredSize(new Dimension(w, h));
        gc = new GameCanvas();

        createSprite();

        cp.add(gc);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setVisible(true);
        setUpAnimationTimer();
        setUpKeyListener();
    }

    /**
     * creates the player sprites
     */
    private void createSprite() {

        if (playerID == 1) {
            me = new PlayerSprite(151, 927, 70, Color.BLUE, "BlueCat.png");
            enemy = new PlayerSprite(131, 927, 70, Color.RED, "RedCat.png");
            gc.addPlayer(me, enemy, 1);

        } else {
            me = new PlayerSprite(131, 927, 70, Color.RED, "RedCat.png");
            enemy = new PlayerSprite(151, 927, 70, Color.BLUE, "BlueCat.png");
            gc.addPlayer(enemy, me, 2);

        }
        gc.me.setCheckpoint(131, 927);
        gc.enemy.setCheckpoint(131, 927); // find out bakit pag di sinet yung isa di na gagana yung buogn program
    }

    /**
     * contains all animation
     */
    private void setUpAnimationTimer() {
        int interval = 10;

        ActionListener al = new ActionListener() {
            boolean hasJumped = false;
            double speed = 5;
            double p1yspeed = 0;
            double p2yspeed = 0;
            final double acceleration = 1;
            final double gravity = 0.3;

            public void actionPerformed(ActionEvent e) {
                boolean buttonpressed = false;

                // player.setCheckpoint(starting position)

                if (up && !hasJumped)// !hasjumped
                {
                    hasJumped = true;
                    gc.me.moveV(p1yspeed - 50);
                }
                if (down) {
                    // gc.me.moveV(speed);
                    // not need in final code
                }
                if (right) {
                    gc.me.moveH(speed);
                    speed += acceleration;
                    if (speed > 7) {
                        speed = 7;
                    }

                }
                if (left) {
                    gc.me.moveH(-speed);
                    speed += acceleration;
                    if (speed > 7) {
                        speed = 7;
                    }

                }
                // button collision testing
                for (int i = 0; i < Map.obstacles.size(); i++) {
                    if (gc.me.isColliding(Map.obstacles.get(i)) || gc.enemy.isColliding(Map.obstacles.get(i))) {

                        if (Map.obstacles.get(i) instanceof Button) {
                            buttonpressed = true;

                        } else if (!(gc.me.isColliding(Map.obstacles.get(i)))
                                && !(gc.enemy.isColliding(Map.obstacles.get(i)))) {
                            buttonpressed = false;
                        }

                        if (Map.obstacles.get(i) instanceof Spikes) {
                            if (gc.bg.rs.activate && gc.bg.bs.activate) {
                                if (gc.me.isColliding((Map.obstacles.get(i)))) {
                                    if (((gc.me.getColor().equals(Color.RED)
                                            && Map.obstacles.get(i).getColor().equals(Color.BLUE)))
                                            || ((gc.me.getColor().equals(Color.BLUE)
                                                    && Map.obstacles.get(i).getColor().equals(Color.RED)))) {
                                        gc.me.respawn();
                                    }
                                }
                                if (gc.enemy.isColliding((Map.obstacles.get(i)))) {
                                    if (((gc.enemy.getColor().equals(Color.RED)
                                            && Map.obstacles.get(i).getColor().equals(Color.BLUE)))
                                            || ((gc.enemy.getColor().equals(Color.BLUE)
                                                    && Map.obstacles.get(i).getColor().equals(Color.RED)))) {
                                        gc.enemy.respawn();
                                    }
                                }
                                // System.out.println(Map.obstacles.get(i).getActivate());
                            }

                        }
                    }

                }

                for (int i = 0; i < Map.obstacles.size(); i++) {
                    if (gc.me.isColliding(Map.obstacles.get(i))) {
                        if (Map.obstacles.get(i) instanceof Barrier || Map.obstacles.get(i) instanceof Pencil
                                || Map.obstacles.get(i) instanceof Chair) {
                            if (!(gc.me.getX() + gc.getWidth() <= Map.obstacles.get(i).getX() || gc.me
                                    .getX() >= Map.obstacles.get(i).getX() + Map.obstacles.get(i).getWidth()
                                    || gc.me.getY() >= Map.obstacles.get(i).getY()
                                            + Map.obstacles.get(i).getHeight())) {
                                p1yspeed = 0;
                                hasJumped = false;

                                gc.me.setY(Map.obstacles.get(i).getY() - gc.me.getSize());
                            } // if player on top

                            else if (!(gc.me.getX() + gc.getWidth() <= Map.obstacles.get(i).getX() || gc.me
                                    .getX() >= Map.obstacles.get(i).getX() + Map.obstacles.get(i).getWidth()
                                    || Map.obstacles.get(i).getY() >= gc.me.getY() + gc.me.getSize())) {

                                gc.me.setY(Map.obstacles.get(i).getY() + Map.obstacles.get(i).getHeight());
                                // System.out.println("print from blue colliding barrier ");

                            }

                            if (!(gc.me.getX() >= Map.obstacles.get(i).getX() + Map.obstacles.get(i).getWidth()
                                    || gc.getY() + gc.me.getSize() <= Map.obstacles.get(i).getY()
                                    || gc.me.getY() >= Map.obstacles.get(i).getY()
                                            + Map.obstacles.get(i).getHeight())) {
                                gc.me.setX(Map.obstacles.get(i).getX() - gc.me.getSize());

                            } else if (!(gc.me.getX() + gc.me.getSize() <= Map.obstacles.get(i).getX()
                                    || gc.me.getY() + gc.me.getSize() <= Map.obstacles.get(i).getY()
                                    || gc.me.getY() >= Map.obstacles.get(i).getY()
                                            + Map.obstacles.get(i).getHeight())) {
                                gc.me.setX(Map.obstacles.get(i).getX() + Map.obstacles.get(i).getWidth());
                                /// p roblema
                            }
                        }
                        if (Map.obstacles.get(i) instanceof Checkpoint) {
                            if (gc.me.isColliding(Map.obstacles.get(i)))
                                gc.me.setCheckpoint(Map.obstacles.get(i).getX(), Map.obstacles.get(i).getY());

                        }

                        if (Map.obstacles.get(i) instanceof Exit) {
                            System.out.println("A player has completed the game");
                            System.exit(0);
                        }

                    }

                }

                // enemy collision testing
                for (int i = 0; i < Map.obstacles.size(); i++) {
                    if (gc.enemy.isColliding(Map.obstacles.get(i))) {
                        if (Map.obstacles.get(i) instanceof Barrier || Map.obstacles.get(i) instanceof Pencil
                                || Map.obstacles.get(i) instanceof Chair) {

                            if (!(gc.enemy.getX() + gc.getWidth() <= Map.obstacles.get(i).getX() || gc.enemy
                                    .getX() >= Map.obstacles.get(i).getX() + Map.obstacles.get(i).getWidth()
                                    || gc.enemy.getY() >= Map.obstacles.get(i).getY()
                                            + Map.obstacles.get(i).getHeight())) {// if above
                                // barrier
                                p2yspeed = 0;
                                gc.enemy.setY(Map.obstacles.get(i).getY() - gc.enemy.getSize());
                            } // ontop

                            else if (!(gc.enemy.getX() + gc.getWidth() <= Map.obstacles.get(i).getX() || gc.enemy
                                    .getX() >= Map.obstacles.get(i).getX() + Map.obstacles.get(i).getWidth()
                                    || Map.obstacles.get(i).getY() >= gc.enemy.getY() + gc.enemy.getSize())) {

                                gc.enemy.setY(Map.obstacles.get(i).getY() + Map.obstacles.get(i).getHeight());
                                // player below barrier

                            }
                            if (!(gc.enemy.getX() >= Map.obstacles.get(i).getX()
                                    + Map.obstacles.get(i).getWidth()
                                    || gc.getY() + gc.enemy.getSize() <= Map.obstacles.get(i).getY()
                                    || gc.enemy.getY() >= Map.obstacles.get(i).getY()
                                            + Map.obstacles.get(i).getHeight())) {

                                gc.enemy.setX(Map.obstacles.get(i).getX() - gc.enemy.getSize());

                            }

                            else if (!(gc.enemy.getX() + gc.enemy.getSize() <= Map.obstacles.get(i).getX()
                                    || gc.enemy.getY() + gc.enemy.getSize() <= Map.obstacles.get(i).getY()
                                    || gc.enemy.getY() >= Map.obstacles.get(i).getY()
                                            + Map.obstacles.get(i).getHeight())) {
                                gc.enemy.setX(Map.obstacles.get(i).getX() + Map.obstacles.get(i).getWidth());
                            }

                            if (Map.obstacles.get(i) instanceof Checkpoint) {
                                if (gc.enemy.isColliding(Map.obstacles.get(i)))
                                    gc.enemy.setCheckpoint(Map.obstacles.get(i).getX(), Map.obstacles.get(i).getY());

                            }

                            if (Map.obstacles.get(i) instanceof Exit) {
                                System.out.println("A player has completed the game");

                                System.exit(0);
                            }

                        }
                    }
                }

                for (int k = 0; k < Map.obstacles.size(); k++) {
                    if (buttonpressed) {
                        gc.bg.rs.activate = false;
                        gc.bg.bs.activate = false;

                    } else {
                        gc.bg.rs.activate = true;
                        gc.bg.bs.activate = true;
                    }

                }

                if (me.getX() <= 0) {
                    me.setX(0);
                }
                if (me.getX() + me.getSize() >= w)
                    me.setX(w - me.getSize());
                if (enemy.getX() <= 0) {
                    enemy.setX(0);
                }
                if (enemy.getX() + me.getSize() >= w)
                    enemy.setX(w - me.getSize());
                if (me.getY() >= h) {
                    me.respawn();
                }
                if (enemy.getY() >= h) {
                    enemy.respawn();
                }

                gc.bg.rc.moveY(gravity);
                gc.bg.rc1.moveY(gravity);
                gc.bg.bc.moveY(gravity);
                p1yspeed += gravity;
                p2yspeed += gravity;
                gc.me.moveV(p1yspeed);
                gc.enemy.moveV(p2yspeed);
                if ((!left && !right) || (left && right)) {
                    speed = 0;
                }
                gc.repaint();

            }
        };
        t = new Timer(interval, al);
        t.start();
    }

    private void setUpKeyListener() {
        KeyListener kl = new KeyListener() {
            public void keyTyped(KeyEvent e) {

            }

            public void keyPressed(KeyEvent e) {
                int keyCode = e.getKeyCode();

                switch (keyCode) {
                    case KeyEvent.VK_W:
                        up = true;
                        break;

                    case KeyEvent.VK_S:
                        down = true;
                        break;
                    case KeyEvent.VK_A:
                        left = true;
                        break;
                    case KeyEvent.VK_D:
                        right = true;
                        break;
                }
            }

            public void keyReleased(KeyEvent e) {
                int keyCode = e.getKeyCode();
                switch (keyCode) {
                    case KeyEvent.VK_W:
                        up = false;
                        break;
                    case KeyEvent.VK_S:
                        down = false;
                        break;
                    case KeyEvent.VK_A:
                        left = false;
                        break;
                    case KeyEvent.VK_D:
                        right = false;
                        break;

                }
            }
        };
        cp.addKeyListener(kl);
        cp.setFocusable(true);
    }

    /**
     * creates client socket to connect to server
     */
    protected void connectToServer(String ip, int port) {

        try {
            socket = new Socket(ip, port);
            DataInputStream in = new DataInputStream(socket.getInputStream());
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            playerID = in.readInt();
            System.out.println("You are Player #" + playerID);
            if (playerID == 1) {
                System.out.println("Waiting for Player #2 to connect.");
            }

            rfsRunnable = new ReadFromServer(in);
            wtsRunnable = new WriteToServer(out);
            rfsRunnable.waitForStartMsg();
        } catch (IOException e) {
            // TODO: handle exception
        }
    }

    private class ReadFromServer implements Runnable {
        private DataInputStream dataIn;

        private ReadFromServer(DataInputStream in) {
            dataIn = in;

        }

        public void run() {
            try {
                while (true) {
                    if (enemy != null) {
                        enemy.setX(dataIn.readDouble());
                        enemy.setY(dataIn.readDouble());

                    }
                }
            } catch (IOException e) {
                // TODO: handle exception
            }
        }

        public void waitForStartMsg() {
            try {
                String startMsg = dataIn.readUTF();
                System.out.println("Message from server " + startMsg);
                Thread readThread = new Thread(rfsRunnable);
                Thread writeThread = new Thread(wtsRunnable);
                readThread.start();
                writeThread.start();
            } catch (IOException e) {
                // TODO: handle exception
            }
        }
    }

    private class WriteToServer implements Runnable {
        private DataOutputStream dataOut;

        private WriteToServer(DataOutputStream out) {
            dataOut = out;

        }

        @Override
        public void run() // dito issend yung coordinates
        {
            try {

                while (true) {
                    if (me != null) {
                        dataOut.writeDouble(me.getX());
                        dataOut.writeDouble(me.getY());
                        dataOut.flush();
                    }
                    try {
                        Thread.sleep(25); // maooverwhelm yung server pag di inisleep
                    } catch (InterruptedException e) {
                        System.out.println("interrupted exception from wts run()");
                    }
                }

            } catch (IOException e) {
                // TODO: handle exception
            }
        }

    }

}