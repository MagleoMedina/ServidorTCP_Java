//ClienteTCP.java

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class ClienteTCP {

    public static void main(String[] args) {
        // Dirección IP del servidor (en este caso, localhost).
        String hostServidor = "127.0.0.1";
        // Puerto del servidor.
        int puertoServidor = 8050;

        try {
            // 1. Creación del Socket del Cliente y 2. Conexión al servidor.
            Socket socketCliente = new Socket(hostServidor, puertoServidor);
            System.out.println("Conectado al servidor en " + hostServidor + ":" + puertoServidor);

            // Flujos de entrada y salida para comunicar con el servidor.
            PrintWriter salida = new PrintWriter(socketCliente.getOutputStream(), true);
            BufferedReader entrada = new BufferedReader(new InputStreamReader(socketCliente.getInputStream()));
            BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));

            // Solicitar el nombre del usuario antes de conectarse.
            System.out.print("Por favor, ingresa tu nombre: ");
            String nombreUsuario = stdIn.readLine();
            System.out.println("Bienvenido, " + nombreUsuario + ". Conectando al servidor...");

            String userInput;
            System.out.println("Escribe un mensaje para enviar al servidor (o 'adios' para salir):");

            // 3. Bucle para Envío y Recepción de Datos.
            while ((userInput = stdIn.readLine()) != null) {
                // Enviar el mensaje al servidor junto con el nombre del usuario.
                salida.println(nombreUsuario + ": " + userInput);

                // Recibir y mostrar la respuesta del servidor.
                System.out.println("Respuesta del servidor: " + entrada.readLine());
                
                // Si el usuario escribió "adios", terminar el bucle.
                if ("adios".equalsIgnoreCase(userInput)) {
                    break;
                }
                
                System.out.println("\nEscribe otro mensaje:");
            }

            // 4. Cierre de Conexión.
            salida.close();
            entrada.close();
            stdIn.close();
            socketCliente.close();
            System.out.println("Conexión cerrada.");

        } catch (UnknownHostException e) {
            // 5. Manejo de Errores: El host del servidor no se puede encontrar.
            System.err.println("Host desconocido: " + hostServidor);
        } catch (IOException e) {
            // 5. Manejo de Errores: Problema de I/O (e.g., servidor no disponible).
            System.err.println("No se pudo obtener I/O para la conexión a " + hostServidor + ". ¿El servidor está en ejecución?");
        }
    }
}