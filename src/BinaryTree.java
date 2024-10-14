public class BinaryTree extends Maze{
    public BinaryTree(){
        super();
    }
    public BinaryTree(int a, int b){
        super(a, b);
    }
    
    public void generateMaze(){
        ICell[][] maze = getMaze();
        for (int row = 0; row<maze.length-1; row++){
            for (int col = 0; col<maze[row].length-1; col++){
                if (Math.random()<.5){
                    //east 
                    createLink(maze[row][col], maze[row][col+1]);
                } else {
                    //South 
                    createLink(maze[row][col], maze[row+1][col]);
                }
            }
            
            //far right col
            createLink(maze[row][maze[row].length-1], maze[row+1][maze[row].length-1]);
        }
        //bottom row 
        int row = maze.length - 1;
        for (int col= 0; col < maze[0].length - 1; col++){
            createLink(maze[row][col], maze[row][col+1]);
        }
        
    }
    
}