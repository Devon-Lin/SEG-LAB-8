public class Chopstick {
    private int ID;
    private boolean free;

    Chopstick(int ID) {
        this.ID = ID;

        free = true;
    }

    synchronized void take() {
        //When this method is invoked, the philosopher is going to need to wait untill this chopstick is free
        while(!free){
            try {
                wait(); //Philosopher needs to wait
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        free = false; //Chopstick in use
        System.out.println("Chopstick with ID: " + getID() + ", is currently in use!");
        notify(); //One of the philosophers will be notified
    }

    synchronized void release() {

        while (free) {
            try {
                wait(); //No point in releasing if the chopstick is already free
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            free = true;
            System.out.println("Chopstick with ID: " + getID() + ", has been released");
            notify();
        }
    }
    public int getID () {
        return (ID);
    }
}
