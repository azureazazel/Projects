/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */
package com.mycompany.alicebobchat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author SHAMU
 */
public class AliceBobChat {

    public static void main(String[] args) throws IOException {
        BufferedReader consoleInput = new BufferedReader(new InputStreamReader(System.in));

        System.out.print("Enter A(Alice) or B(Bob): ");
        String choice = consoleInput.readLine();

        if (choice.equalsIgnoreCase("A")) {
            runServer();
        } else if (choice.equalsIgnoreCase("B")) {
            runClient();
        } else {
            System.out.println("Invalid choice.");
        }
    }

    private static void runServer() {
        try ( ServerSocket serverSocket = new ServerSocket(8000)) {
            System.out.println("Alice's port is " + serverSocket.getLocalPort());

            Socket socket = serverSocket.accept();
            System.out.println("Connected to Bob at " + socket.getInetAddress().getHostAddress() + ":" + socket.getPort());

            BufferedReader socketInput = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter socketOutput = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader consoleInput = new BufferedReader(new InputStreamReader(System.in));

            // Start a separate thread to handle sending messages
            Thread writingThread = new Thread(() -> {
                try {
                    while (true) {
                        if (consoleInput.ready()) {
                            String message = consoleInput.readLine();
                            socketOutput.println(message);
                        }

                        Thread.sleep(100);
                    }
                } catch (IOException | InterruptedException e) {
                    System.err.println("Error sending message: " + e.getMessage());
                    System.exit(1);
                }
            });
            writingThread.start();

            while (true) {
                if (socketInput.ready()) {
                    String message = socketInput.readLine();
                    System.out.println("Bob: " + message);
                }

                if (consoleInput.ready()) {
                    String message = consoleInput.readLine();
                    socketOutput.println(message);
                }

                Thread.sleep(100);
            }
        } catch (IOException | InterruptedException e) {
            System.err.println("Error: " + e.getMessage());
            System.exit(1);
        }
    }

    private static void runClient() {
        try ( Socket socket = new Socket("127.0.0.1", 8000, InetAddress.getByName("localhost"), 5000);  BufferedReader socketInput = new BufferedReader(new InputStreamReader(socket.getInputStream()));  PrintWriter socketOutput = new PrintWriter(socket.getOutputStream(), true);  BufferedReader consoleInput = new BufferedReader(new InputStreamReader(System.in))) {

            System.out.println("Bob's Port is " + socket.getLocalPort());
            System.out.println("Connected to Alice at " + socket.getInetAddress().getHostAddress() + ":" + socket.getPort());

            while (true) {
                if (socketInput.ready()) {
                    String message = socketInput.readLine();
                    System.out.println("Alice: " + message);
                }

                // Start a separate thread to handle sending messages
                Thread writingThread = new Thread(() -> {
                    try {
                        while (true) {
                            if (consoleInput.ready()) {
                                String message = consoleInput.readLine();
                                socketOutput.println(message);
                            }

                            Thread.sleep(100);
                        }
                    } catch (IOException | InterruptedException e) {
                        System.err.println("Error sending message: " + e.getMessage());
                        System.exit(1);
                    }
                });
                writingThread.start();

                Thread.sleep(100);
            }
        } catch (IOException | InterruptedException e) {
            System.err.println("Error: " + e.getMessage());
            System.exit(1);
        }
    }
}
