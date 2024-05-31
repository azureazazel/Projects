
import java.io.*;
import java.net.*;

public class FTPClient {

    private Socket socket = null;
    private BufferedReader in = null;
    private PrintWriter out = null;

    public FTPClient(String host, int port) {
        try {
            socket = new Socket(host, port);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(), true);
        } catch (UnknownHostException e) {
            System.err.println("Error connecting to host: " + e.getMessage());
            System.exit(-1);
        } catch (IOException e) {
            System.err.println("Error establishing connection: " + e.getMessage());
            System.exit(-1);
        }
    }
    public void get(String filename) {
        out.println("get " + filename);
        try {
            String response = in.readLine();

            if (response.equals("file exists")) {

                FileOutputStream fileOut = new FileOutputStream(new File("new"+filename));
                byte[] buffer = new byte[1024];
                int bytesRead;
                while ((bytesRead = socket.getInputStream().read(buffer)) != -1) {
                    fileOut.write(buffer, 0, bytesRead);
//                    socket.getInputStream().read(buffer, 0, bytesRead);
                }
                fileOut.close();
                System.out.println("File received: new" + filename);
            } else {
                System.out.println("File does not exist on server");
            }
        } catch (IOException e) {
            System.err.println("Error retrieving file: " + e.getMessage());
        }
    }

    public void upload(String filename) {
        try {
            File file = new File(filename);
            System.out.println(filename);
            if (file.exists() && !file.isDirectory()) {
                out.println("upload " + filename);
                FileInputStream fileIn = new FileInputStream(file);
                byte[] buffer = new byte[1024];
                int bytesRead;
                while ((bytesRead = fileIn.read(buffer)) != -1) {
                    socket.getOutputStream().write(buffer, 0, bytesRead);
                }
                fileIn.close();
                System.out.println("File sent: " + filename);
            } else {
                System.out.println("File does not exist or is a directory");
            }
        } catch (IOException e) {
            System.err.println("Error uploading file: " + e.getMessage());
        }
    }

    public void close() {
        try {
            socket.close();
        } catch (IOException e) {
            System.err.println("Error closing connection: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
//        if (args.length < 1) {
//            System.out.println("Usage: java FTPClient <port>");
//            System.exit(-1);
//        }
//
//        int port = Integer.parseInt(args[0]);
        int port = 4007;
        FTPClient client1 = new FTPClient("localhost", port);
        FTPClient client2 = new FTPClient("localhost", port);

//        client1.upload("uploadTestFile.pptx");
        client1.upload("downTestFile2.pptx");
        client1.close();
        client2.close();
    }
}
