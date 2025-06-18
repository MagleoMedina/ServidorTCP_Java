# Telecomunicaciones TCP Project

## Descripción

Este proyecto implementa una comunicación cliente-servidor utilizando sockets TCP en Java. El cliente envía mensajes al servidor, y el servidor responde con un eco del mensaje recibido. Además, el cliente debe proporcionar su nombre antes de conectarse, y el servidor muestra el nombre del cliente junto con cada mensaje enviado.

## Estructura del Proyecto

- **ClienteTCP.java**: Implementa la lógica del cliente TCP. Solicita el nombre del usuario y envía mensajes al servidor.
- **ServidorTCP.java**: Implementa la lógica del servidor TCP. Escucha conexiones de clientes y responde a los mensajes recibidos.

## Requisitos

- Java Development Kit (JDK) instalado.
- Entorno de desarrollo para ejecutar programas en Java.
- Puerto 8050 disponible en la máquina donde se ejecutará el servidor.

## Cómo Ejecutar

### Servidor
1. Navega al directorio del proyecto.
2. Compila el archivo `ServidorTCP.java`:
   ```bash
   javac src/ServidorTCP.java
   ```
3. Ejecuta el servidor:
   ```bash
   java -cp bin ServidorTCP
   ```
4. El servidor estará escuchando en el puerto `8050`.

### Cliente
1. Navega al directorio del proyecto.
2. Compila el archivo `ClienteTCP.java`:
   ```bash
   javac src/ClienteTCP.java
   ```
3. Ejecuta el cliente:
   ```bash
   java -cp bin ClienteTCP
   ```
4. Ingresa tu nombre cuando se te solicite y comienza a enviar mensajes al servidor.

## Ejemplo de Uso

### Cliente
```plaintext
Por favor, ingresa tu nombre: Juan
Bienvenido, Juan. Conectando al servidor...
Escribe un mensaje para enviar al servidor (o 'adios' para salir):
Hola
Respuesta del servidor: Servidor dice: Eco -> Juan: Hola
```

### Servidor
```plaintext
Servidor TCP iniciado. Esperando clientes en el puerto 8050...
¡Cliente conectado desde 127.0.0.1!
Juan: Hola
```

## Notas

- El cliente puede enviar múltiples mensajes al servidor hasta que escriba `adios` para cerrar la conexión.
- El servidor maneja múltiples clientes simultáneamente utilizando hilos.

## Autor
Magleo Medina 

Este proyecto fue desarrollado como parte de un ejercicio de la materia de telecomunicaciones dictada por el Ing. Germain Salazar.
