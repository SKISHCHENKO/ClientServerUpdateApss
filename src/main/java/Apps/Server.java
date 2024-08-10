package Apps;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    public static final int PORT = 8080;

    public static void main(String[] args) {
        try(ServerSocket serverSocket = new ServerSocket(PORT)){
            System.out.println("Server started on port " + PORT);
            while(true){
                handleClientConnection(serverSocket);
            }

        }catch (IOException e){
            throw new RuntimeException(e);
        }
    }
    private static void handleClientConnection(ServerSocket serverSocket) {
        try (Socket clientSocket = serverSocket.accept();
             PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))) {

            System.out.println("New connection accepted from " + clientSocket.getInetAddress());
            handleClientRequest(out, in, clientSocket);
        } catch (IOException e) {
            System.err.println("Error handling client connection: " + e.getMessage());
        }
    }
    private static void handleClientRequest(PrintWriter out, BufferedReader in, Socket clientSocket) throws IOException {
        out.println("Write your name!");
        String clientName = in.readLine();
        out.println(String.format("Hi %s, your port is %d", clientName, clientSocket.getPort()));
        out.println("Are you a child? (yes/no)");
        String answer = in.readLine();
        if (answer.equalsIgnoreCase("yes")) {
            out.println("Welcome to the kids area, " + clientName + "! Let's play!");
        } else {
            out.println("Welcome to the adult zone, " + clientName + "! Have a good rest, or a good working day!");
        }
    }
}