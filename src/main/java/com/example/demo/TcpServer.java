package com.example.demo;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

@Component
public class TcpServer {

    @PostConstruct
    public void startServer() {
        new Thread(() -> {
            try (ServerSocket serverSocket = new ServerSocket(7777)) {
                System.out.println("Servidor TCP iniciado na porta 7777...");

                while (true) {
                    Socket clientSocket = serverSocket.accept();
                    handleClientConnection(clientSocket);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }

    private void handleClientConnection(Socket clientSocket) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
             OutputStream output = clientSocket.getOutputStream()) {

            String inputLine;
            while ((inputLine = reader.readLine()) != null) {
                System.out.println("Recebido: " + inputLine);
                // Processar os dados recebidos do rastreador
                // Enviar uma resposta, se necessário
                output.write(("Dados recebidos: " + inputLine + "\n").getBytes());
                output.flush();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
