/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bfsheuristics;

import java.util.ArrayList;
import java.util.PriorityQueue;
import javax.swing.JOptionPane;

public class BFSHeuristics {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        int n;
//        Scanner sc = new Scanner(System.in);
//        n = sc.nextInt();
//    
         n=Integer.parseInt(JOptionPane.showInputDialog("Enter No of nodes"));              // Enter no. of rows
        
         PriorityQueue<HeadNode> open = new PriorityQueue<>(new FxComparator());             // Initilize priority queue openlist
        ArrayList<HeadNode> closed = new ArrayList<>(n);                                     // Initialize closed list
        ArrayList<String> parent = new ArrayList<>(n);                                      // Store parent node
        for(int i=0;i<n;i++)
        {
            parent.add("NIL");                                                              // Set parent of all nodes NIL
        }
        
        Graph graph = new Graph(n);                                                         // Create graph instance
        graph.initGraph();                                                                  // Initialize graph
        graph.displayGraph();                                                               // Display graph as adjacency list
        
        String start, goal;                                                                 // Accept start and goal nodes
        start = JOptionPane.showInputDialog("Enter the name of start node : ");
        
        goal = JOptionPane.showInputDialog("Enter the name of goal node : ");
        
        open.add(graph.getHeadNode(start));                                                 // Add start node to open list
        parent.set(graph.getIndex(start), "NIL");                                           // Set parent of start NIL


        displayQueue(open);
        displayClosed(closed);
        
        while(!open.isEmpty())                                                              // Process until open list is not empty
        {
            HeadNode temp = open.poll();                                                    // Remove node with minimum fx from open list
            closed.add(temp);                                                               // Add it to closed list
            displayQueue(open);
            displayClosed(closed);
            if(temp.getName().equals(goal))                                                 // Check if goal node is found
            {
  
                System.out.println("\nGoal node '"+temp.getName() + "'  found");
                break;
            }
            else
            {
                ArrayList<Node> neighbours = temp.getNodeList();                            // Get the neighbours of the retrieved node
                for(Node n1:neighbours)                                                     // For all adjacent nodes
                {
                    if(inClosed(n1.getName(), closed))                                      // If node in closed list, process next node
                        continue;
                    if(!inOpen(n1.getName(), open))                                         // Check if not in open list
                    {
                      
                        open.add(graph.getHeadNode(n1.getName()));                          // Add it toopen list
                        parent.set(graph.getIndex(n1.getName()), temp.getName());           // Set parent of neighbour node
                    }
                }
                displayQueue(open);
            }
         
            
        }
        
        tracePath(parent, graph, goal);
    }
    
    
    private static void displayQueue(PriorityQueue<HeadNode> open)                          // Fuction to display queue open list
    {
        System.out.print("\nOpen List : ");
        if(open.isEmpty())
        {
            System.out.println("Empty");
            return;
        }
        for(HeadNode n: open)
        {
            System.out.print(n.getName()+"\t");
        }
        System.out.println("");
    }
    
    private static void displayClosed(ArrayList<HeadNode> closed)                          // Fuction to display closed list
    {
        System.out.print("\nClosed List : ");
        if(closed.isEmpty())
        {
            System.out.println("Empty");
            return;
        }
        for(HeadNode n: closed)
        {
            System.out.print(n.getName()+"\t");
        }
        System.out.println("");
    }
    
    private static boolean inClosed(String name, ArrayList<HeadNode> closed)               // Check if node in closed list
    {
        for(HeadNode n: closed)
        {
            if(n.getName().equals(name))
                return true;
        }
        return false;
    }
    
    private static boolean inOpen(String name, PriorityQueue<HeadNode> open)              // Check if node in closed list
    {
        for(HeadNode n: open)
        {
            if(n.getName().equals(name))
                return true;
        }
        return false;
    }
    
    private static void tracePath(ArrayList<String> parent, Graph graph, String goal)       // Function to trace the path
    {
        System.out.println("\n\nPath : ");
        String path = goal;
        String temp = goal;
        while(!parent.get(graph.getIndex(temp)).equals("NIL"))                              // Continue path till parent is not NIL
        {
            temp = parent.get(graph.getIndex(temp));
            path = temp + ", " + path;
        }
        
        System.out.println(path);
    }
}


/*

OUTPUT:

run:

A (hx = 10) : (B), (C)
B (hx = 3) : (D)
C (hx = 7) : (D)
D (hx = 0) : 

Open List : A	

Closed List : Empty

Open List : Empty

Closed List : A	

Open List : B	C	

Open List : C	

Closed List : A	B	

Open List : D	C	

Open List : C	

Closed List : A	B	D	

Goal node 'D'  found


Path : 
A, B, D
BUILD SUCCESSFUL (total time: 1 minute 50 seconds)

*/