package suru.GroceryQueue;

import java.util.LinkedList;
import java.util.Queue;

public class GroceryQueue {
    private int maxQueueSize;
    private Queue<Integer>[] queueArray;
    
    // Constructor
    @SuppressWarnings("unchecked")
    public GroceryQueue(int numberOfQueues,int maxQueueSize){
        this.maxQueueSize = maxQueueSize;

        queueArray = new LinkedList[numberOfQueues];
        for(int i = 0; i < queueArray.length; i++){
            queueArray[i] = new LinkedList<>();
        }
        
    }


    public void show(){
        System.out.println(queueArray.length);
        for(int i = 0; i < queueArray.length; i++){
            System.out.println(queueArray[i].size());
        }
    }
}
