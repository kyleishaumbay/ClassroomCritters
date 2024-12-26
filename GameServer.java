/**
This is a class that contains the program for the server of the game.
It contains necessary network elements for the program to work.
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
import java.io.*;
import java.net.*;

public class GameServer {
    private ServerSocket ss;
    private int numPlayers;
    private int maxPlayers;
    private Socket s1;
    private Socket s2;
    private readFromClient p1ReadRunnable;
    private readFromClient p2ReadRunnable;
    private writeToClient p1WriteRunnable;
    private writeToClient p2WriteRunnable;
    private double p1x, p1y, p2x, p2y;


    public GameServer() {
        System.out.println("===== GAME SERVER =====");
        numPlayers = 0;
        maxPlayers = 2;

        try {
            ss = new ServerSocket(12345);
        } catch (IOException e) {
            System.out.println("IOException from GameServer");
        }
    }

    public void acceptConnections() {
        try {
            System.out.println("Waiting for Connections");
            while (numPlayers < maxPlayers) {
                Socket s = ss.accept();
                DataInputStream in = new DataInputStream(s.getInputStream());
                DataOutputStream out = new DataOutputStream(s.getOutputStream());
                numPlayers++;
                out.writeInt(numPlayers);


                System.out.println("Player #" + numPlayers + " has connected.");
                readFromClient rfc = new readFromClient(numPlayers, in);
                writeToClient wtc = new writeToClient(numPlayers, out);
                if (numPlayers == 1) {
                    s1 = s;
                    p1ReadRunnable = rfc;
                    p1WriteRunnable = wtc;

                } else {
                    s2 = s;
                    p2ReadRunnable = rfc;
                    p2WriteRunnable = wtc;
                    p1WriteRunnable.sendStartMsg();
                    p2WriteRunnable.sendStartMsg();
                    Thread readThread1 = new Thread(p1ReadRunnable);
                    Thread readThread2 = new Thread(p2ReadRunnable);
                    Thread writeThread1 = new Thread(p1WriteRunnable);
                    Thread writeThread2 = new Thread(p2WriteRunnable);
                    readThread1.start();
                    readThread2.start();
                    writeThread1.start();
                    writeThread2.start();

                }
            }

            System.out.println("No longer accepting connections");
        } catch (IOException e) {
            // TODO: handle exception
        }

    }

    private class readFromClient implements Runnable {
        private int playerID;
        private DataInputStream dataIn;

        public readFromClient(int pid, DataInputStream in) {
            playerID = pid;
            dataIn = in;
            System.out.println("RFC " + playerID + " Runnable created");
        }

        public void run() {
            try {
                while (true) {
                    if (playerID == 1) {
                        p1x = dataIn.readDouble();
                        p1y = dataIn.readDouble();
                    } else {
                        p2x = dataIn.readDouble();
                        p2y = dataIn.readDouble();
                    }
                }
            } catch (IOException e) {
                System.out.println("ioexception from rfs run()");
            }

        }

    }

    private class writeToClient implements Runnable {
        private int playerID;
        private DataOutputStream dataOut;

        public writeToClient(int pid, DataOutputStream out) {
            playerID = pid;
            dataOut = out;
            System.out.println("WTC " + playerID + " Runnable created");
        }

        public void run() {
            try {
                while (true) {
                    if (playerID == 1) {
                        dataOut.writeDouble(p2x);
                        dataOut.writeDouble(p2y);
                        dataOut.flush();
                    } else {
                        dataOut.writeDouble(p1x);
                        dataOut.writeDouble(p1y);
                        dataOut.flush();
                    }
                    try {
                        Thread.sleep(25);
                    } catch (InterruptedException e) {
                        // TODO: handle exception
                    }

                }
            } catch (IOException e) {
                // TODO: handle exception
            }
        }

        public void sendStartMsg() {
            try {
                dataOut.writeUTF("We now have 2 players. GO!");
            } catch (IOException e) {
                // TODO: handle exception
            }
        }

    }

    public static void main(String[] args) {
        GameServer gs = new GameServer();
        gs.acceptConnections();

    }
}