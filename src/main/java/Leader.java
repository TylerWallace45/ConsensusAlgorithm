
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Leader extends Thread {

    private Socket conn;
    private int id;

    public Leader(Socket socket, int id) {
        System.out.println("Hello from the Leader!");
    }

    public void run() {
        System.out.println("Enter an operation: (EG. +, -, *)");
        boolean invalidOperation = true;

        while (invalidOperation) {
            Scanner scanner = new Scanner(System.in);
            String operation = scanner.nextLine();
            if (operation.equals("+")) {
                System.out.println("Chose addition!");
                invalidOperation = false;
            } else if (operation.equals("-")) {
                System.out.println("Chose subtraction!");
                invalidOperation = false;
            } else if (operation.equals("*")) {
                System.out.println("Chose multiplication!");
                invalidOperation = false;
            } else {
                System.out.println("Unknown operation, try again");
            }
        }
    }

    public static void main(String[] args) {
        Socket sock = null;
        int id = 0;
        try {
            if (args.length != 1) {
                System.out.println("Usage: gradle runLeader --args=<port num>");
                System.exit(0);
            }
            int portNo = Integer.parseInt(args[0]);
            if (portNo <= 1024) {
                portNo = 8888;
            }
            ServerSocket serv = new ServerSocket(portNo);
            while (true) {
                System.out.println("Leader server waiting for connects on port " + portNo);
                sock = serv.accept();
                System.out.println("Leader server connected to client-" + id);
                // create thread
                Leader myLeaderThread = new Leader(sock, id++);
                // run thread and don't care about managing it
                myLeaderThread.start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (sock != null) {
                try {
                    sock.close();
                } catch (IOException e) {
                    System.out.println("IOException! Socket not closed...");
                    return;
                }
            }
        }
    }
}
