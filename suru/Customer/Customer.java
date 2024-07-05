package suru.Customer;

import suru.GroceryQueue.GroceryQueue;

public class Customer {
    GroceryQueue gq;

    public Customer(GroceryQueue gq){
        this.gq = gq;
    }

    public void serveCustomer(int queueNumber){
        System.out.println("Thread : "+ queueNumber + ", Queue : " + gq.queueArray[queueNumber].size());
    }
}
