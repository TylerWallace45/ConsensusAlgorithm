public class Worker extends Thread {
	public Worker() {
        System.out.println("Hello from the worker!");
    }

    public static void main(String[] args) {
        Worker worker = new Worker();
    }
}