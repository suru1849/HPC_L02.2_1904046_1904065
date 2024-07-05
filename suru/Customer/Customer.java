package suru.Customer;

import java.util.concurrent.locks.ReentrantLock;

import suru.GroceryQueue.GroceryQueue;

public class Customer {
    GroceryQueue gq;
    ReentrantLock rel;
    
    // Customer
    public Customer(GroceryQueue gq,ReentrantLock rel){
        this.gq = gq;
        this.rel = rel;
    }

    // Serve the customer
    public void serveCustomer(int queueNumber){
        if(gq.queueArray[queueNumber].size() == 0){
            System.out.println(queueNumber + " queue is empty.");
            try {
                Thread.sleep(2*1000);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else{
            boolean ans = rel.tryLock();

            if(ans){
                System.out.println(queueNumber + " server is serving customer");
                try {
                    gq.queueArray[queueNumber].poll();
                    
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    // unlock
                    rel.unlock();
                    try {
                       // serve for 300 to 60 seconds
                       long x = (long)Math.random()*(300-60+1) + 60;
                       Thread.sleep(x*1000);
                    } catch (Exception e) {
                       e.printStackTrace();
                    }

                }
            } else {
                System.out.println(queueNumber + " is waiting.");
            }
        }
    }

    // add customer to the queue
    public void addCustomer(){
        boolean ans = rel.tryLock();

        if(ans){
            System.out.println("added customer");
            try {
                int index = getLeastIndex();
                if(index == gq.maxQueueSize){ 
                    // wait for 10 seconds
                    Thread.sleep(10*1000);                 
                }else{
                    gq.queueArray[index].add(1);
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                // unlock
                rel.unlock();
                try {
                  // wait for 60 to 20 seconds
                  long x = (long)Math.random()*(60-20+1) + 20;
                  Thread.sleep(x*1000);  
                } catch (Exception e) {
                   e.printStackTrace();
                }
            }

        } else{
            System.out.println("Waiting for add customer to the queue.");
        }
    }

    // Get the queue index with least number of customer
    public int getLeastIndex(){
        int x = 0;
        for(int i = 0; i< gq.queueArray.length; i++){
           if(gq.queueArray[i].size() <= gq.queueArray[x].size()){
              x = i;
           }
        }
        return x;

    }
}
