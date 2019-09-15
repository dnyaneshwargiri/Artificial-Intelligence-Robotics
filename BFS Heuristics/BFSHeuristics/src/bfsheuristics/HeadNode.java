/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bfsheuristics;

import java.util.ArrayList;
import java.util.Iterator;

public class HeadNode                                           // Adjacency list head node
{
    private String name;                                        // node name
    private int hx;                                             // heuristic value hx
    private int fx;                                             // fx = hx value
    private ArrayList<Node> adjnodes = new ArrayList<>();       // Adjacent nodes list
    
    public HeadNode()                                           // Initialize hx and fx to infinity 
    {
        hx=999;
        fx = hx;
    }

    

    public int getHx() {                                  
        return hx;
    }

    public void setHx(int hx) {                                 // Set hx and update fx accordingly
        this.hx = hx;
        setFx(this.hx);
    }

    public int getFx() {
        return fx;
    }

    public void setFx(int fx) {
        this.fx = fx;
    }
    
    
    
    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setNodeInfo(String name)                        // Set adjacent node name and distance
    {
        Node n = new Node(name);
        adjnodes.add(n);                                        // Add node to list
    }
 
    public ArrayList getNodeList()
    {
        return adjnodes;
    }
    
    public void displayNodeList()                              // Display adjacent nodes list (name,distance)
    {
                
        Iterator i = adjnodes.iterator();
        if(i.hasNext())
        {
            Node temp= (Node)i.next();
            System.out.print("("+temp.getName()+")");
        
        }
        while(i.hasNext())
        {
            Node temp= (Node)i.next();
            System.out.print(", ("+temp.getName()+")");
        }
    }
    
}
