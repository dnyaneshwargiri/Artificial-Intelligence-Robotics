/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bfsheuristics;

import java.util.Comparator;


public class FxComparator implements Comparator<HeadNode>               // Comparator for priority queue based on fx value
{

    @Override
    public int compare(HeadNode o1, HeadNode o2) {
        if(o1.getFx()> o2.getFx())
            return 1;
        else if(o1.getFx() < o2.getFx())
            return -1;
        return 0;
    }
    
}
