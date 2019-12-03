import java.util.Scanner; //to be able to read input from the keyboard...breaks the input into tokens using a delimiter
import java.util.*;
import java.util.PriorityQueue;
//(whitespace)

public class dikjstra {
    private static Map<Integer, Node> graph = new HashMap<>(); //to keep track of all nodes
    private static PriorityQueue<Node> queue = new PriorityQueue<>();
    static int numVertices;

    static void findShortestPath() {
        while (!queue.isEmpty()) {
            Node v = queue.poll();
            if(v.visited)
                continue; //skip it if it has been visited before
            if (v.getId() == numVertices) {
                //found the last one!!
                //return the path
                printPath(v);
                return;
            }
            v.visited=true;
            Map<Node,Integer> myNeighbors = v.getNeighbors();
            for(Node curr: myNeighbors.keySet()) {
                if (!curr.visited) {
                    int tempDis = v.getDist() + v.getNeighbors().get(curr);
                    if (tempDis < curr.getDist()) {
                        curr.setDist(tempDis);
                        curr.setPrev(v);
                    }
                    queue.add(curr);
                }
            }
        }
        System.out.println(-1);
    }

    private static void printPath(Node v) {
        LinkedList<Integer> pathS = new LinkedList<>();
        pathS.addFirst(v.getId());
        Node curr = v.getPrev();
        while(curr!=null){
            pathS.addFirst(curr.getId());
            curr=curr.getPrev();
        }
        StringBuilder sb = new StringBuilder();
        for(Integer s:pathS) {
            sb.append(s);
            sb.append(" ");
        }
        System.out.println(sb);
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        numVertices = input.nextInt();
        int numEdges = input.nextInt();
        input.nextLine();
        int countLines = 0;
        while (countLines < numEdges) {
            int a = input.nextInt();
            int b = input.nextInt();
            int weight = input.nextInt();
            if (!graph.containsKey(a)) {
                graph.put(a, new Node(a));
            }
            if (!graph.containsKey(b)) {
                graph.put(b, new Node(b));
            }
            Node nodeA = graph.get(a);
            Node nodeB = graph.get(b);
            nodeA.addNeighbor(nodeB, weight);
            nodeB.addNeighbor(nodeA, weight);
            input.nextLine();
            countLines++;
        }
        int minId=Integer.MAX_VALUE;
        for(int currId:graph.keySet()){
            if(minId>currId){
                minId=currId;
            }
        }
        queue.add(graph.get(minId)); //add the start node
        graph.get(minId).setDist(0);
        findShortestPath();
    }
}
//represent a node in the graph
class Node implements Comparable<Node>{
    private int id;
    private int dist;
    private Node prev; //shortest path of nodes to get here
    private Map<Node,Integer> neighbors; //map of neighbors with edges
    boolean visited;


    public Node(int id){
        this.id=id;
        this.dist=Integer.MAX_VALUE;
        this.neighbors = new HashMap<>();
    }
    public Map<Node,Integer> getNeighbors(){
        return neighbors;
    }
    public Node getPrev(){
        return prev;
    }
    public int getId(){
        return id;
    }
    public int getDist(){
        return dist;
    }
    public void setDist(int dist1){
        this.dist=dist1;
    }
    public void setPrev(Node prev){
        this.prev=prev;
    }
    public void addNeighbor(Node neighbor,int edgeCost){
        neighbors.put(neighbor,edgeCost);
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