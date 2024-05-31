import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) {
//        if (args.length < 1) {
//            System.out.println("Usage: java FTPServer <port>");
//            System.exit(-1);
//        }
//
//        int port = Integer.parseInt(args[0]);
        int port = 4007;
        ServerSocket serverSocket = null;

        try {
            serverSocket = new ServerSocket(port);
            System.out.println("Server started on port " + port);
        } catch (IOException e) {
            System.err.println("Error starting server: " + e.getMessage());
            System.exit(-1);
        }

        while (true) {
            try {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Client connected: " + clientSocket.getInetAddress().getHostName());

                FTPServerThread serverThread = new FTPServerThread(clientSocket);
                serverThread.start();
            } catch (IOException e) {
                System.err.println("Error accepting client connection: " + e.getMessage());
            }
        }
    }
}

class FTPServerThread extends Thread {
    private Socket socket = null;
    private BufferedReader in = null;
    private PrintWriter out = null;

    public FTPServerThread(Socket socket) {
        super("FTPServerThread");
        this.socket = socket;
    }

    public void run() {
        try {
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(), true);

            String command;
            while ((command = in.readLine()) != null) {
                String[] tokens = command.split(" ");
                if (tokens[0].equals("get")) {
                    String filename = tokens[1];
                    File file = new File(filename);
                    if (file.exists() && !file.isDirectory()) {
                        out.println("file exists");
                        FileInputStream fileIn = new FileInputStream(file);
                        byte[] buffer = new byte[1024];
                        int bytesRead;
                        while ((bytesRead = fileIn.read(buffer)) != -1) {
                            socket.getOutputStream().write(buffer, 0, bytesRead);
                        }
                        fileIn.close();
                        System.out.println("File sent: " + filename);
                    } else {
                        out.println("file does not exist");
                    }
                } else if (tokens[0].equals("upload")) {
                    String filename = tokens[1];
                    FileOutputStream fileOut = new FileOutputStream(new File(filename));
                    byte[] buffer = new byte[1024];
                    int bytesRead;
                    while ((bytesRead = socket.getInputStream().read(buffer)) != -1) {
                        fileOut.write(buffer, 0, bytesRead);
                    }
                    fileOut.close();
                    System.out.println("File received: " + filename);
                } else {
                    out.println("Invalid command");
                }
            }
        } catch (IOException e) {
            System.err.println("Error handling client connection: " + e.getMessage());
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                System.err.println("Error closing socket: " + e.getMessage());
            }
        }
    }

}
