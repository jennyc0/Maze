
public class Dijkstra{
    
    
    public static void dijkstra(IMaze maze){
        dijkstra(maze, maze.getMaze()[0][0]);
    }

    public static void dijkstra(IMaze maze, ICell start){
        start.setDistance(0);
        ICell[] adj = start.getAdj();
        for (int i = 0; i < 4; i++){
            dijkstra(adj[i], 1, start.getOpenArray()[i]);
        }
    }

    public static void dijkstra(ICell current, int level, boolean open){

        if(current!=null && open){
            current.setDistance(level);
            ICell[] adj = current.getAdj();
            for (int i = 0; i < 4; i++){
                if (adj[i]!=null && adj[i].getDistance()==-1){
                    dijkstra(adj[i], level+1, current.getOpenArray()[i]);
                }

            }
        }
    }
    public static void resetDistance(){
        
    }
    
    public static ICell[] getLongestPath(IMaze maze){
        ICell start = maze.getRandomCell();
        
        //return getLongestPath(maze, 0);
        
        return null;
    }
    private static ICell[] getLongestPath(IMaze maze, int max){
        //run dijkstra from current start 
        //get end point (max num)
        //if end == start && distance == max 
        return null;
        
    }
}