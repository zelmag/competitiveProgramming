import java.util.Scanner; //to be able to read input from the keyboard...breaks the input into tokens using a delimiter
import java.util.*;
//(whitespace)

public class videogame {
    private static Map<Integer, Node> graph = new HashMap<>(); //to keep track of all nodes
    private static Edge[] edges;
    static int numRooms;
    static int startHealth;

    static void findMostHealthyPath() {
        // Step 2: Relax all edges |V| - 1 times. A simple
        // shortest path from src to any other vertex can
        // have at-most |V| - 1 edges
        for (int i = 1; i < numRooms; i++) {
          for(int e=0; e<edges.length; e++){
                Node from = edges[e].getFrom();
                Node to = edges[e].getTo();
                int healthChange = edges[e].getChangeHealth();
                if (from.getDist() != Integer.MIN_VALUE && from.getDist() + healthChange > to.getDist())
                    to.setDist(from.getDist()+healthChange);
            }
        }
        //look for positive health cycles
        // Step 3: check for negative-weight cycles. The above
        // step guarantees shortest distances if graph doesn't
        // contain negative weight cycle. If we get a shorter
        // path, then there is a cycle.
        for (int j = 0; j < edges.length; j++) {
            Node from = edges[j].getFrom();
            Node to = edges[j].getTo();
            int healthChange = edges[j].getChangeHealth();
            if (from.getDist() != Integer.MIN_VALUE && from.getDist() + healthChange > to.getDist()) {
                System.out.println("infinity");
                return;
            }
        }
        Node dest = graph.get(new Integer(numRooms));
        if(dest.getDist()==Integer.MIN_VALUE){
            System.out.println("-1");
        }else{
            System.out.println(dest.getDist()+startHealth);
        }
    }
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        numRooms = input.nextInt();
        int numEdges = input.nextInt();
        startHealth=input.nextInt();
        edges = new Edge[numEdges];
        int countLines = 0;
        while (input.hasNextLine() && countLines < numEdges) {
            input.nextLine();
            int startNode = input.nextInt();
            int endNode = input.nextInt();
            int healthChange = input.nextInt();
            if (!graph.containsKey(startNode)) {
                graph.put(startNode, new Node(startNode));
            }
            if (!graph.containsKey(endNode)) {
                graph.put(endNode, new Node(endNode));
            }
            Node nodeA = graph.get(startNode);
            Node nodeB = graph.get(endNode);
            Edge newEdge = new Edge(nodeA,nodeB,healthChange);
            edges[countLines]=newEdge;
            countLines++;
        }
        int minId=Integer.MAX_VALUE;
        for(int currId:graph.keySet()){
            if(minId>currId){
                minId=currId;
            }
        }
        graph.get(minId).setDist(0); //set dist to 0
        findMostHealthyPath();
    }
}
//represent a node in the graph
class Node implements Comparable<Node> {
    private int id;
    private int dist;


    public Node(int id) {
        this.id = id;
        this.dist = Integer.MIN_VALUE;
    }

    public int getId() {
        return id;
    }

    public int getDist() {
        return dist;
    }

    public void setDist(int dist1) {
        this.dist = dist1;
    }

    @Override
    public int compareTo(Node o) {
        if (this.dist < o.dist)
            return -1;
        else if (this.dist > o.dist)
            return 1;
        return 0;
    }
}
class Edge{
    private Node from;
    private Node to;
    private int changeHealth;

    public Edge(Node from, Node to,int changeHealth){
      this.from=from;
      this.to=to;
      this.changeHealth=changeHealth;
    }

    public int getChangeHealth() {
      return changeHealth;
    }

    public Node getFrom(){
      return from;
    }

    public Node getTo(){
        return to;
    }
}

