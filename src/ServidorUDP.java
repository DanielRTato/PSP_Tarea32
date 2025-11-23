import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class ServidorUDP {
    public static void main(String[] args) {

        final int puerto = 6666;

        try (DatagramSocket datagramSocket = new DatagramSocket(puerto)) {
            System.out.println("Servidor iniciado...");
            byte[] bufferEntrada = new byte[1024];

            while (true) {
                // Parte de recibir mensaje del cliente
                DatagramPacket peticion = new DatagramPacket(bufferEntrada, bufferEntrada.length);
                datagramSocket.receive(peticion);
                String mensajeCliente = new String(peticion.getData(), 0, peticion.getLength());
                System.out.println("Recibido del cliente: " + mensajeCliente);

               String palabraLarga = palabraLarga(mensajeCliente);

                // Parte de enviar respuestas al cliente
                String respuesta = "Palabra más larga: " + palabraLarga + " (longitud: " + palabraLarga.length() + ")";

                responder(datagramSocket, peticion.getAddress(), peticion.getPort(), respuesta);
            }

        } catch (Exception e) {
            System.out.println("Error del servidor: " + e.getMessage());
        }
        System.out.println("Servidor cerrado");
    }

    private static void responder(DatagramSocket socket, InetAddress ip, int puerto, String respuesta) throws IOException {
        byte[] bufferSalida = respuesta.getBytes();
        DatagramPacket respuestaPaquete = new DatagramPacket(bufferSalida, bufferSalida.length, ip, puerto);
        socket.send(respuestaPaquete);
    }

    private static String palabraLarga(String mensajeCliente) {
        String[] listaPalabras = mensajeCliente.split(" ");
        String palabraLarga = "";

        for (String palabra : listaPalabras) {
            if (palabraLarga.length() < palabra.length())
                palabraLarga = palabra;
        }
        return palabraLarga;
    }

}


