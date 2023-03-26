public class Graph {
      private boolean adjacencyMatrix[][];
      private int numberOfVertices;

      public Graph(int numberOfVertices) {
            this.numberOfVertices = numberOfVertices;
            adjacencyMatrix = new boolean[numberOfVertices][numberOfVertices];
      }

      public void addEdge(int i, int j) {
          // To be completed by students   
          this.adjacencyMatrix[i][j] = true;
          this.adjacencyMatrix[j][i] = true;
      }

      public void removeEdge(int i, int j) {
            // To be completed by students
          this.adjacencyMatrix[i][j] = false;
          this.adjacencyMatrix[j][i] = false;
      }

      public boolean isEdge(int i, int j) {
            // to be completed by students
          return this.adjacencyMatrix[i][j];
      }
      
      public void displayGraph(){
        System.out.printf("%10s", " ");
        for(int i = 0; i < numberOfVertices; i++)
          System.out.printf("%10s", i);
          System.out.println();
        for(int i = 0; i < numberOfVertices; i++){
          System.out.printf("%10s", i);
          for(int j = 0; j < numberOfVertices; j++){
            System.out.printf("%10s", adjacencyMatrix[i][j]);
           }
           System.out.println();
       }
       
   }
 }
