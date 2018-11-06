package Main;

public class TestThread implements Runnable {

    private int number;

    public TestThread(int number){
        this.number = number;
    }

    public void run(){
        while(true){
            System.out.println(number);
        }
    }
}
