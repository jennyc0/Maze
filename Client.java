public class Client{
    
    public static void main(String[] args){
        
        Maze m = new Wilson(20, 20);
        Dijkstra.dijkstra(m);
        System.out.println(m);
        
    }
}