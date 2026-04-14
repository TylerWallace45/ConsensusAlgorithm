import java.util.Scanner;

public class Worker extends Thread {
    private int num1;
    private int num2;
    private String operation;

	public Worker(int num1, int num2, String operation) {
        this.num1 = num1;
        this.num2 = num2;
        this.operation = operation;
        System.out.println("Hello from the worker!");
    }

    public void run(){
        System.out.println(num1 + " " + operation + " " + num2 + " = ? (Enter input)");
        Scanner scanner = new Scanner(System.in);
        int input = Integer.parseInt(scanner.nextLine());
        System.out.println(num1 + " " + operation + " " + num2 + " = " + input + " inputted!");
        scanner.close();
    }

    public static void main(String[] args) {
        Worker worker = new Worker(6, 7, "+");
        worker.start();
    }
}