public class GraphDriver{
    public static void main(String[] args){
    // Create an undirected graph with 4 vertices
    // To be completed by students
        Graph mygrapgh = new Graph(4);
    
    // Add the 5 edges of the graph
    // To be completed by students
        mygrapgh.addEdge(0,3);
        mygrapgh.addEdge(0,2);
        mygrapgh.addEdge(0,1);
        mygrapgh.addEdge(1,3);
        mygrapgh.addEdge(2,3);
    
    // Display the graph before deleting edge 2---3
    System.out.println("Before deleting edge 2---3 the graph is: \n");
    // To be completed by students
        mygrapgh.displayGraph();
    
    
    // Display the graph after deleting edge 2---3
    // To be completed by students
        mygrapgh.removeEdge(2,3);
    System.out.println("\nAfter deleting edge 2---3 the graph is: \n");
    // To be completed by students
        mygrapgh.displayGraph();
  }
}