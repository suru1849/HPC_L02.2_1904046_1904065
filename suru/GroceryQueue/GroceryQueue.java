package suru.GroceryQueue;

import java.util.LinkedList;
import java.util.Queue;

public class GroceryQueue {
    // Atributes
    public int maxQueueSize;
    public Queue<Integer>[] queueArray;
    
    // Constructor
    @SuppressWarnings("unchecked")
    public GroceryQueue(int numberOfQueues,int maxQueueSize){
        this.maxQueueSize = maxQueueSize;

        queueArray = new LinkedList[numberOfQueues];
        for(int i = 0; i < queueArray.length; i++){
            queueArray[i] = new LinkedList<>();
        }
        
    }

}
