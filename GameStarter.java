/**
This is a class that is ran by the client to start the game.
It instantitates a start menu first for the client.
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
public class GameStarter {
    public static void main(String[] args) {
        MenuFrame mf = new MenuFrame(1920, 1080);
        mf.setUpGUI();
        // GameFrame gf = new GameFrame(1920, 1080);
        // gf.connectToServer();
        // gf.setUpGUI();
    }
}
