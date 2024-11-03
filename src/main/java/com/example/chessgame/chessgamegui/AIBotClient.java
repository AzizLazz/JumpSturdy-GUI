package com.example.chessgame.chessgamegui;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

public class AIBotClient {

    private static final String SERVER_ADDRESS = "localhost";
    private static final int SERVER_PORT = 5095;

    public static String getBestMoveFromServer_2(String fen, int port, boolean turn) {
        String bestMove = "";
        String message = fen + (turn ? " r" : " b"); // Append " r" if turn is true, otherwise " b"

        try (Socket socket = new Socket(SERVER_ADDRESS, port);
             PrintWriter out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {

            // Send the FEN with turn indicator to the server
            System.out.println("Sending FEN with turn to server: " + message);
            out.println(message);

            // Receive the best move from the server
            bestMove = in.readLine();
            System.out.println("Received best move from server: " + bestMove);

        } catch (Exception e) {
            System.out.println("Error connecting to AI server: " + e.getMessage());
            e.printStackTrace();
        }

        return bestMove;
    }


}