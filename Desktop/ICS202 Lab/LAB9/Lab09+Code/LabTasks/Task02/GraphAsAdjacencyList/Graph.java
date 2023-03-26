import java.util.*; 

class Graph { 
        int numVertices; 
          
        LinkedList<String>[] adjacencyList;
        String[] array;
          
        Graph(int numVertices,String[]array) {
            this.numVertices = numVertices; 
            adjacencyList = new LinkedList[numVertices]; 
              this.array = array;
            for (int i = 0; i < adjacencyList.length; i++) 
                adjacencyList[i] = new LinkedList<String>();
              
        } 
          
        //To add a directed edge to graph 
        void addDirectedEdge(int v, int w)  { 
            adjacencyList[v].add(this.array[w]); // Add w to v’s list.
        }

         //To add undirected edge to graph 
        void addUndirectedEdge(int v, int w) {          
            adjacencyList[v].add(this.array[w]);
            adjacencyList[w].add(this.array[v]);
        } 
        
        void displayGraph(){
           for (int i = 0; i < adjacencyList.length; i++){  
               System.out.println(this.array[i] + " ----> " + adjacencyList[i]);
            } 
            System.out.println(); 
        
       } 
   }

