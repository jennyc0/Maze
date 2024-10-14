
import java.util.*;
public class SideWinder extends Maze{
    public SideWinder(){
        super();
    }
    public SideWinder(int rows, int cols){
        super(rows, cols);
    }
    public void generateMaze(){
        
        ICell[][] maze = getMaze();
        ArrayList<int[]> list = new ArrayList<>();
        
        for (int row = 0; row<maze.length-1; row++){
            for (int col = 0; col<maze[row].length-1; col++){
                //remember cell in list 
                list.add(new int[] {row, col});
                if (Math.random()<.5){ // Heads/East 
                    createLink(maze[row][col], maze[row][col+1]);
                    
                } else {// Tails, South
                    //randomly pick cell from list 
                    //reset the list 
                    int rand = (int)(Math.random() * list.size());
                    int[] cell = list.get(rand);
                    createLink(maze[cell[0]][cell[1]], maze[cell[0] + 1][cell[1]]);
                    list.clear();
                }
            }
            //add cell in last col into list 
            //far right must pick tails 
            list.add(new int[] {row, maze[row].length -1});
            int rand = (int)(Math.random() * list.size());
            int [] cell = list.get(rand);
            createLink(maze[cell[0]][cell[1]], maze[cell[0] + 1][cell[1]]);
            list.clear();
        }
        //bottom row - must carve east 
        int row = maze.length - 1;
        for (int col= 0; col < maze[0].length - 1; col++){
            createLink(maze[row][col], maze[row][col+1]);
        }    
        
        
        
    }
    
    
}