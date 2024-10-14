/*
 * select random cell to mark visited 
 * select a new cell to be the head of a path 
 * make random path from head until visited 
 *  if loop, backtrack 
 * 
 * repeat
 */
import java.util.*;

public class Wilson extends Maze {
    public Wilson(){
        super();
    }

    public Wilson(int a, int b){
        super(a, b);
    }

    public void generateMaze(){
        Stack<ICell> s = new Stack<>();
        ICell[][] maze = getMaze();
        Random rand = new Random();
        ICell end = getRandomCell();
        end.setVisited();
        ICell start = getRandomCell();
        while (start == end){
            start = getRandomCell();
        }
        s.push(start);
        while (start!=end){
            start.setVisited();
            ICell[] adj = getAdjacent(start);
            ICell next = adj[rand.nextInt(4)];

            while (next == null){
                next = adj[rand.nextInt(4)];
            }

            s.push(next);
            if (next.getVisited() && next!=end){
                //clear loop
                ICell dup = s.pop();
            
                while (s.peek() != next){
                    ICell remove = s.pop();
                    remove.setVisited(false);
                }
                start = s.peek();
            } else{
                start = next;
            }
            
        }

        //found path. create links 
        while (!s.isEmpty()){
            ICell remove = s.pop();
            createLink(start, remove);
            start = remove;
        }

        s.clear();
        while (!allVisited()){
            ICell curr = getRandomCell();
            while (curr.getVisited()){
                curr = getRandomCell();
            }
            curr.setVisited();
            s.push(curr);       
            boolean currPath = true;
            //until you reach a visited cell 
            while (currPath){
                ICell[] adj = getAdjacent(curr);
                ICell next = adj[rand.nextInt(4)];
                while (next == null){
                    next = adj[rand.nextInt(4)];
                }
                s.push(next);
                boolean reachedEnd = false;
                if (next.getVisited()){ 
                    // checking if loop or reached end 
                    for (boolean side:next.getOpenArray()){
                        if (side) reachedEnd = true;
                        // is already part of open maze 
                    } 

                    if (!reachedEnd){
                        //made a loop - clear loop 
                        ICell r = s.pop();
                        if (s.isEmpty()){
                            s.push(r);
                            curr = r;
                        } else{
                            boolean noDup = true;
                            while (noDup && !s.isEmpty()){
                                ICell remove = s.pop();
                                remove.setVisited(false);
                                if (remove == next){
                                    noDup = false;
                                    s.push(remove);
                                    remove.setVisited();
                                    curr = remove;

                                }
                            }
                        }

                    }
                }
                if (reachedEnd){
                    currPath = false;
                    //create links after path is found 
                    while (!s.isEmpty()){
                        ICell a = s.pop();
                        if (!s.isEmpty()){
                            ICell b = s.peek();
                            createLink(a, b);
                        }

                    }
                } else {
                    next.setVisited();
                    curr = next;
                }

            }

        }
    }
}