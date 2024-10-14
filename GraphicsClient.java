import javax.swing.*;
import java.awt.*;


public class GraphicsClient{
    public static final int ROWS = 30;
    public static final int COLS = 40;
    public static final int SIZE = 20;
    
    public static void main(String[] args){
        JFrame frame = new JFrame ("Maze");
        Maze m = new Wilson(ROWS, COLS);
        Dijkstra.dijkstra(m);
        JPanel p = new MazePanel(m, SIZE);
        
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.add(p);
        frame.pack();
        frame.setVisible(true);
    }
    
    
}