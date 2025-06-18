//ServidorTCP.java


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class ServidorTCP {

    public static void main(String[] args) {
        // Se define el puerto en el que el servidor escuchará.
        int puerto = 8050;

        try {
            // 1. Creación del Socket del Servidor (ServerSocket)
            ServerSocket socketServidor = new ServerSocket(puerto);
            System.out.println("Servidor TCP iniciado. Esperando clientes en el puerto " + puerto + "...");

            // 3. Bucle infinito para escuchar y aceptar conexiones de clientes.
            while (true) {
                // 4. Aceptación de Conexiones. El método accept() es bloqueante.
                Socket socketCliente = socketServidor.accept();
                System.out.println("¡Cliente conectado desde " + socketCliente.getInetAddress().getHostAddress() + "!");

                // Se crea un nuevo hilo para manejar la comunicación con este cliente.
                ClientHandler clientHandler = new ClientHandler(socketCliente);
                new Thread(clientHandler).start();
            }
        } catch (IOException e) {
            // 7. Manejo de Errores: Problema al iniciar el servidor (e.g., puerto en uso).
            System.err.println("Error al iniciar el servidor: " + e.getMessage());
        }
    }
}

/**
 * Clase para manejar la comunicación con un cliente en un hilo separado.
 */
class ClientHandler implements Runnable {
    private Socket socketCliente;

    public ClientHandler(Socket socket) {
        this.socketCliente = socket;
    }

    @Override
    public void run() {
        try (
            // Flujos de entrada y salida para comunicar con el cliente.
            PrintWriter salida = new PrintWriter(socketCliente.getOutputStream(), true);
            BufferedReader entrada = new BufferedReader(new InputStreamReader(socketCliente.getInputStream()));
        ) {
            String lineaRecibida;
            // 5. Recepción de Datos: Leer mensajes del cliente.
            while ((lineaRecibida = entrada.readLine()) != null) {
                System.out.println(lineaRecibida);

                // Si el cliente envía "adios", terminamos la comunicación.
                if (lineaRecibida.toLowerCase().contains("adios")) {
                    salida.println("¡Hasta luego! Conexión cerrada.");
                    break;
                }

                // Enviar una respuesta al cliente.
                salida.println("Servidor dice: Eco -> " + lineaRecibida);
            }

        } catch (IOException e) {
            // 7. Manejo de Errores: Conexión cerrada inesperadamente.
            System.err.println("Error en la comunicación con el cliente: " + e.getMessage());
        } finally {
            try {
                // 6. Cierre de Conexión.
                if (socketCliente != null && !socketCliente.isClosed()) {
                    socketCliente.close();
                    System.out.println("Conexión con el cliente " + socketCliente.getInetAddress().getHostAddress() + " cerrada.");
                }
            } catch (IOException e) {
                System.err.println("Error al cerrar el socket del cliente: " + e.getMessage());
            }
        }
    }
}