

public class Cell implements ICell{
    private boolean visited;
    private boolean[] open;
    private ICell[] adj;
    
    public Cell(){
        visited = false;
        open = new boolean[4];
        distance_from_start = -1;
    }
    //Letting the generated know if a cell had been visited or not
    public void setVisited(){
        visited = true;
    }
    public void setVisited(boolean b){
        visited = b;
    }
    
    public boolean getVisited(){
        return visited;
    }
    //changes a direction from a barrier (default) to a non barrier
    public void setOpen(Direction d){
        open[convDir(d)] = true;
    }
    //checking if a direction is a barrier or not
    public boolean canMove(Direction d){
        return open[convDir(d)];
    }
    //get an array of booleans indicating if the ordinal directions are open
    //NESW is what is intended
    public boolean[] getOpenArray(){
        return open;
    }
    //Sets the adjacent cells.  
    //NESW is what is intended
    public void setAdj(ICell[] adj){
        this.adj = adj;
    }
    public ICell[] getAdj(){
        return adj;
    }
    private int convDir(Direction d){
        return d == Direction.NORTH ? 0 : d == Direction.EAST ? 1 : d == Direction.SOUTH ? 2 : 3;
    }
    
    //for Dijkstra's Algorithm
    private int distance_from_start;
    public void setDistance(int d){
        distance_from_start = d;
    }
    public int getDistance(){
        return distance_from_start;
    }
}