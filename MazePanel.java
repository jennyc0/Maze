
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;


public class MazePanel extends JPanel implements KeyListener{
    IMaze maze;
    int size;//size of each cell 
    //for navigator
    private int dotRow;
    private int dotCol;
    
    public MazePanel(IMaze maze, int size){
        this.maze = maze;
        setPreferredSize(new Dimension(size*maze.getMaze()[0].length, size*maze.getMaze().length));
        this.size = size;
        
        addKeyListener(this);
        setFocusable(true); //tells it to focus on this panel? 
        dotRow = 0;
        dotCol = 0;
    }
    
    @Override 
    public void paintComponent(Graphics g){
        g.clearRect(0, 0, getWidth(), getHeight());
        ICell[][] cells = maze.getMaze();
        for (int row = 0; row < cells.length; row++){
            for (int col = 0; col < cells[row].length; col++){
                int dist = cells[row][col].getDistance();
                g.setColor(new Color(cells[row][col].getDistance(),255-cells[row][col].getDistance(), 255-cells[row][col].getDistance()));
                g.fillRect((col-1)*size + (size), (row-1)*size +(size), size, size);
                boolean[] open = cells[row][col].getOpenArray();
                g.setColor(Color.BLACK);
                if (!open[0]){ // north 
                    g.drawLine(col*size, row*size, (col+1)*size, row*size);
                }
                if (!open[1]){//east
                    g.drawLine((col+1)*size, row*size, (col+1)*size, (row+1)*size);
                }
                if (!open[2]){//south
                    g.drawLine(col*size, (row+1)*size, (col+1)*size, (row+1)*size);
                }
                if (!open[3]){//west 
                    g.drawLine(col*size, row*size, col*size, (row+1)*size);
                }
                
            }
        }
        g.setColor(Color.RED);
        g.fillOval(dotCol*size, dotRow*size, size, size);
    }
    public void move(Direction d){
        ICell current = maze.getCell(dotRow, dotCol);
        if (current.canMove(d)){
            int dRow = d == Direction.NORTH? -1 : d == Direction.SOUTH? 1 : 0;
            int dCol = d == Direction.WEST? -1 : d == Direction.EAST? 1 : 0;
            dotRow += dRow;
            dotCol += dCol;
        }
        
        repaint();
    }
    //for KeyListener 
    public void keyPressed(KeyEvent ke){
        //arrow keys 
        if (ke.getKeyCode() == KeyEvent.VK_UP){
            move(Direction.NORTH);
        }
        if (ke.getKeyCode() == KeyEvent.VK_RIGHT){
            move(Direction.EAST);
        }
        if (ke.getKeyCode() == KeyEvent.VK_DOWN){
            move(Direction.SOUTH);
        }
        if (ke.getKeyCode() == KeyEvent.VK_LEFT){
            move(Direction.WEST);
        }
        
    
    }
    public void keyReleased(KeyEvent ke){}
    public void keyTyped(KeyEvent ke){}
    
    
    
    
}