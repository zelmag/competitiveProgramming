import java.util.Scanner; //to be able to read input from the keyboard...breaks the input into tokens using a delimiter
import java.util.*;
import java.util.PriorityQueue;
//(whitespace)
public class intersectionDijkstra {
    private static Map<Integer, Node> graph = new HashMap<>(); //to keep track of all nodes
    private static PriorityQueue<Node> queue = new PriorityQueue<>(); //to choose the next min intersection
    static int numIntersections;
    static int numRoads;

    static void findShortestPath() {
        while (!queue.isEmpty()) {
            Node v = queue.poll();
            if(v.visited)
                continue; //skip it if it has been visited before
            if (v.getId() == numIntersections) {
                //found the last one!!
                //print the time
                System.out.println(Math.round(v.getDist()));
                return;
            }
            v.visited=true;
            Map<Node,Double> myNeighbors = v.getNeighbors();
            for(Node curr: myNeighbors.keySet()) {
                if (!curr.visited) {
                    double tempDis = v.getDist() + v.getNeighbors().get(curr);
                    if (tempDis < curr.getDist()) {
                        curr.setDist(tempDis);
                    }
                    queue.add(curr);
                }
            }
        }
    }
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        numIntersections = input.nextInt();
        numRoads = input.nextInt();
        input.nextLine();
        int countLines = 0;
        while (countLines < numRoads) {
            int a = input.nextInt();
            int b = input.nextInt();
            int speedLimit = input.nextInt();
            int length = input.nextInt();
            double time = ((double) length)/speedLimit;

            if (!graph.containsKey(a)) {
                graph.put(a, new Node(a));
            }
            if (!graph.containsKey(b)) {
                graph.put(b, new Node(b));
            }
            Node nodeA = graph.get(a);
            Node nodeB = graph.get(b);
            nodeA.addNeighbor(nodeB, time); //one way roads
            if(input.hasNextLine()) {
                input.nextLine();
            }
            countLines++;
        }
        queue.add(graph.get(1)); //add the start node
        graph.get(1).setDist(0);
        findShortestPath();
    }
}
//represent a node in the graph
class Node implements Comparable<Node>{
    private int id;
    private double dist;
    private Map<Node,Double> neighbors; //map of neighbors with edges
    boolean visited;


    public Node(int id){
        this.id=id;
        this.dist=Integer.MAX_VALUE;
        this.neighbors = new HashMap<>();
    }
    public Map<Node,Double> getNeighbors(){
        return neighbors;
    }
    public int getId(){
        return id;
    }
    public double getDist(){
        return dist;
    }
    public void setDist(double dist1){
        this.dist=dist1;
    }
    public void addNeighbor(Node neighbor,double edgeCost){
        if(neighbors.containsKey(neighbor)){
            if(edgeCost<neighbors.get(neighbor)){
                //the lesser edge
                neighbors.put(neighbor,edgeCost);
            }
        }else {
            //its not there yet
            neighbors.put(neighbor, edgeCost);
        }
    }

    @Override
    public int compareTo(Node o) {
        if(this.dist<o.dist)
            return -1;
        else if(this.dist>o.dist)
            return 1;
        return 0;
    }
}

