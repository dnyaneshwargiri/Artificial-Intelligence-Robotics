/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bfsheuristics;

import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.JOptionPane;


public class Graph {                                                  // Class for graph
    
    ArrayList<HeadNode> headNodesList;
    int n;
    
    public Graph(int size)                                            // Initialize size and head node list
    {
        this.n = size;
        headNodesList = new ArrayList<>();
    }
    
    public void initGraph()                                           // Initialize graph nodes and edges
    {
        Scanner sc = new Scanner(System.in);
        for(int i=0;i<n;i++)                                          // Accept node names and their heuristic values
        {
            
            HeadNode hn = new HeadNode();
            hn.setName(JOptionPane.showInputDialog("Enter the name of node " +(i+1)+" : "));
            hn.setHx(Integer.parseInt(JOptionPane.showInputDialog("Enter the heuristic value of node " +(i+1)+" : ")));
            headNodesList.add(hn);
          
        }
        for(int i=0;i<n;i++)
        {
            HeadNode tempHeadNode = headNodesList.get(i);
            
            while(true)                                              // Accept adjacent nodes and their distances
            {
                String name = tempHeadNode.getName();
                String ans = JOptionPane.showInputDialog("\nDo you want to add any adjacent node to node "+ name + "? (y/n) : ");
                if(ans.equals("n")  || ans.equals("N"))
                    break;
               // sc.skip("\n");
                String tempName=JOptionPane.showInputDialog("Enter the name of adjacent node of "+ name + " : ");
                //sc.skip("\n");
                                    
                tempHeadNode.setNodeInfo(tempName);
                headNodesList.set(i, tempHeadNode);
            
            }
        }
    }
    
    public void displayGraph()                                       // Display graph adjacency list
    {
        for(int i=0;i<n;i++)
        {
            HeadNode tempHeadNode = headNodesList.get(i);
            System.out.print("\n"+ tempHeadNode.getName() + " (hx = "+tempHeadNode.getHx()+") : ");
            tempHeadNode.displayNodeList();
        }
        System.out.println("");
    }
    
    public int getIndex(String name)                                 // Get index for given name
    {
        for(int i=0;i<n;i++)
        {
            HeadNode tempHeadNode = headNodesList.get(i);
            if(tempHeadNode.getName().equals(name))
                return i;
        }
        return -1;
    }
    
    public ArrayList getNeighbours(String node)                      // Get neighbour nodes list
    {
        int headIndex=getIndex(node);
        return headNodesList.get(headIndex).getNodeList();
    }
    
    
    
    public HeadNode getHeadNode (String name){                       // Get Head node by name
        return headNodesList.get(getIndex(name));
    }
    
    /*
    public void setFx(Node neighbour, HeadNode curr)                 // Set fx for neighbour via current node
    {
        int tempGx = curr.getGx() + neighbour.getDistance();         // Get distance from source to neighbour via current node
        HeadNode adj = getHeadNode(neighbour.getName());             // Get adjacent head node
        if(tempGx >= adj.getGx())                                    // Check if calculated distance is less than previous distance
            return;
        
        adj.setGx(tempGx);                                           // Set gx as calculated distance
        headNodesList.set(getIndex(adj.getName()), adj);             // Update headnode list
    }*/
}
