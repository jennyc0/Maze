
public abstract class Maze implements IMaze{
    private ICell[][] maze;

    public Maze(){
        this(5, 5);

    }

    public Maze(int rows, int cols){
        maze = new ICell[rows][cols];
        for (int row = 0; row < rows; row++){
            for (int col = 0; col < cols; col++){
                maze[row][col] = new Cell();
            }
        }

        for (int row = 0; row < rows; row++){
            for (int col = 0; col < cols; col++){
                maze[row][col].setAdj(getAdjacent(maze[row][col]));
            }
        }
        generateMaze();
    }
    //all code methods from IMaze go here
    //Returns the maze as an Array of Cells
    public ICell[][] getMaze(){
        return maze;
    }

    //Returns a specific cell in the maze
    public ICell getCell(int row, int col){
        return maze[row][col];
    }

    public ICell getCell(int[] coords){
        return getCell(coords[0], coords[1]);
    }
    //Creates a link between two cells in the maze| Must be two adjacent cells
    public void createLink(ICell from, ICell to) throws IllegalArgumentException{
        ICell[] fromAdj = from.getAdj();
        for (int i = 0; i < fromAdj.length; i++){
            if (fromAdj[i] == to){
                from.setOpen(intToDir(i));
                to.setOpen(intToDir((i+2) % 4));
            }
        }
    }
    //Returns all cells adjacent (intention is NESW)
    public ICell[] getAdjacent(ICell cell){
        ICell[] adj = new ICell[4];
        if (getRow(cell) > 0){
            adj[0] = maze[getRow(cell)-1][getCol(cell)]; 
        }
        if (getCol(cell)<maze[0].length-1){
            adj[1] = maze[getRow(cell)][getCol(cell) + 1];
        }
        if (getRow(cell) < maze.length -1){
            adj[2] = maze[getRow(cell) + 1][getCol(cell)];
        }
        if (getCol(cell) > 0){
            adj[3] = maze[getRow(cell)][getCol(cell) - 1];
        }
        return adj;
    }
    //Helper method for the generate maze to ensure all ICells are linked
    //NESW is what I used to keep everything organized
    public boolean allVisited(){
        for (int row = 0; row < maze.length; row++){
            for (int col = 0; col < maze[row].length; col++){
                if (!maze[row][col].getVisited()){
                    return false;
                }
            }
        }
        return true;
    }
    //returns a random ICell 
    public ICell getRandomCell(){
        int row = (int) (Math.random() * maze.length);
        int col = (int) (Math.random() * maze[row].length);
        return maze[row][col];

    }
    //returns coordinates of the cell passed in {row, col} format
    public int[] getCoords(ICell cell){
        for (int  row = 0; row < maze.length; row++){
            for (int col = 0; col < maze[row].length; col++){
                if (cell == maze[row][col]){
                    return new int[] {row, col};
                }
            }
        }
        return null;
    }
    //returns the row of the passed cell
    public int getRow(ICell cell){
        return getCoords(cell)[0];
    }
    //returns the column of the passed cell
    public int getCol(ICell cell){
        return getCoords(cell)[1];
    }
    //precondition: int must be [0, 3]
    public Direction intToDir(int n){
        return n == 0 ? Direction.NORTH : n == 1 ? Direction.EAST : n == 2? Direction.SOUTH : Direction.WEST;
    }

    public String toString(){
        String st = "";
        //top row 
        for (int col = 0; col < maze[0].length; col ++){
            st +=  "+---";
        }
        st+= "+\n";

        for (int row = 0; row < maze.length; row ++){
            st += "|";

            //spaces and pipes for open/close east 
            for (int col = 0; col < maze[row].length; col ++){
                //st += "   ";
                st += " ";
                int dist = maze[row][col].getDistance();
                st+= Math.abs(dist);
                if (dist/10>0){
                    if ( maze[row][col].getOpenArray()[1]){ //east open
                        st+= " ";
                    } else {
                        st+= "|";
                    }
                }
                else {
                    if (maze[row][col].getOpenArray()[1]){//east
                        st += "  ";
                    } else {
                        st += " |";
                    }

                }

            }
            st += "\n";
            st += "+";
            for (int col = 0; col < maze[row].length; col ++){

                //check if south is open, + for corners 
                if (maze[row][col].getOpenArray()[2]){ // south
                    st += "   ";
                } else {
                    st += "---";
                }
                st += "+";
            }
            st += "\n";
        }
        return st;

    }
    //All Children of Maze must implement the generateMaze() method.      
    public abstract void generateMaze();
    public ICell getStart(){
        return null;
    }
    public ICell getEnd(){
        return null;
    }
}
