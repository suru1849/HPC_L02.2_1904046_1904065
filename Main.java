import suru.Customer.Customer;
import suru.GroceryQueue.GroceryQueue;

public class Main {
    public static volatile boolean running = true;

    public static void main(String[] args) {
        GroceryQueue obj = new GroceryQueue(5, 10);
        Customer obj1 = new Customer(obj);

        Thread[] cashier = new Thread[5];
        for(int i = 0; i < cashier.length; i++){
            final int x = i;
            cashier[i] = new Thread(()->{
                while (running) {
                    obj1.serveCustomer(x);
                }
            });
        }

        for(int i = 0; i < cashier.length; i++){
            cashier[i].start();
        }

        try {
            Thread.sleep(900);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        running = false;

    }
}