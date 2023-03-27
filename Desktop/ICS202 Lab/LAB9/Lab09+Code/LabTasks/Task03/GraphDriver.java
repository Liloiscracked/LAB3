import java.util.List;
import java.util.Arrays;
import java.util.Scanner;

public class GraphDriver{
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        List<Edge> edges = Arrays.asList(Edge.getEdge(0, 3),  Edge.getEdge(1, 0),
                                             Edge.getEdge(1, 2),  Edge.getEdge(1, 4),
                                             Edge.getEdge(2, 7),  Edge.getEdge(3, 4),
                                             Edge.getEdge(3, 5), Edge.getEdge(4, 3),
                                             Edge.getEdge(4, 6), Edge.getEdge(5, 6),
                                             Edge.getEdge(6, 7));
 
        // Number of nodes in the graph (labelled from 0 to N-1)
        int numVertices = 8;
        // To be completed by students
        Graph mygrapgh = new Graph(edges,numVertices);
        System.out.println(mygrapgh.isReachable(2,3));

    }
}