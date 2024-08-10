package Apps;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    private static final String HOST = "netology.homework";
    private static final int PORT = 8080;

    public static void main(String[] args) {
        try (Socket clientSocket = new Socket(HOST, PORT);
             BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
             PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
             Scanner scanner = new Scanner(System.in)) {

            System.out.println("Connected to server");

            String serverMessage;
            while ((serverMessage = in.readLine())!= null) {
                System.out.println(serverMessage);
                if (serverMessage.contains("Write your name!") || serverMessage.contains("Are you a child?")) {
                    System.out.print("Answer: ");
                    String answer = scanner.nextLine();
                    out.println(answer);
                }
            }

        } catch (IOException e) {
            System.err.println("Error connecting to server: " + e.getMessage());
        }
    }
}