/*
1)  Pick any random cell to start
2)  Pick any random direction to go
3)  If the next cell hasn't yet been visited, link the two cells
3.5) if it has been visited, move to that cell 
4) Continue to link cells until all cells have been visited
 */

import java.util.*;
public class AldousBroder extends Maze{
    public AldousBroder(){
        super();
    }
    public AldousBroder(int a, int b){
        super(a, b);
    }
    public void generateMaze(){
        ICell[][] maze = getMaze();
        ICell curr = getRandomCell();
        while (!allVisited()){
            curr.setVisited();
            ICell[] adj = getAdjacent(curr);
            Random rand = new Random();
            int dir = rand.nextInt(4);
            if (adj[dir]!=null && adj[dir].getVisited()){
                curr = adj[dir];
            } else if (adj[dir] != null){
                createLink(curr, adj[dir]);
                curr = adj[dir];
            }
        }
    }
}