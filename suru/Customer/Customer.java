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
        boolean ans = rel.tryLock();

        System.out.println("from serving : " + ans);
        if(gq.queueArray[queueNumber].size() == 0){
            System.out.println(queueNumber + " is empty.");
            try {
                Thread.sleep(1*1000);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else{
            System.out.println(queueNumber + " is serving.");
           
            gq.queueArray[queueNumber].poll();
             
        
            try {
               // serve for 300 to 60 seconds
               long x = (long)Math.random()*(300-60+1) + 60;
               Thread.sleep(x*1000);
            } catch (Exception e) {
               e.printStackTrace();
            }     
        }
        
        rel.unlock();
    }

    // add customer to the queue
    public void addCustomer(){
        boolean ans = rel.tryLock();

        if(ans){
            try {
                int index = getLeastIndex();
                gq.queueArray[index].add(1);
                System.out.println("added customer to : " + index);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                // unlock
                rel.unlock();
                try {
                  // wait for 60 to 20 seconds
                //   long x = (long)Math.random()*(40-20+1) + 20;
                  Thread.sleep(2*1000);  
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
