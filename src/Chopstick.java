public class Chopstick {
    private int ID;
    private boolean free;
    private int waitPeriod = 5000;

    Chopstick(int ID) {
        this.ID = ID;

        free = true;
    }

    synchronized boolean take() {
        //When this method is invoked, the philosopher is going to need to wait until this chopstick is free
        if(!free){
            try {
                wait(waitPeriod); //Philosopher needs to wait
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        if(free){
            free = false; //Chopstick in use
            System.out.println("Chopstick with ID: " + getID() + ", is currently in use!");
            notify(); //One of the philosophers will be notified
            return true;
        } else{
            return false;
        }
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
