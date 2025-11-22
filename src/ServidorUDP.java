import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class ServidorUDP {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        final int puerto = 6666;

        try {
            DatagramSocket datagramSocket = new DatagramSocket(puerto);
            System.out.println("Servidor iniciado...");

            while (true) {
                // Parte de recibir mensaje del cliente
                byte[] bufferEntrada = new byte[1024];
                DatagramPacket peticion = new DatagramPacket(bufferEntrada, bufferEntrada.length);
                datagramSocket.receive(peticion);
                String mensajeCliente = new String(peticion.getData(), 0, peticion.getLength());
                System.out.println("Recibido del cliente: " + mensajeCliente);

                // Parte de enviar respuestas al cliente
                System.out.print("Escribe aquí tu mensaje para el cliente: ");
                String respuesta = scanner.nextLine();
                byte[] bufferSalida = respuesta.getBytes();
                InetAddress direccionCliente = peticion.getAddress();
                int puertoCliente = peticion.getPort();
                DatagramPacket respuestaPaquete = new DatagramPacket(bufferSalida, bufferSalida.length, direccionCliente, puertoCliente);
                datagramSocket.send(respuestaPaquete);
            }

        } catch (Exception e) {
            System.out.println("Error del servidor: " + e.getMessage());
        }
        System.out.println("Servidor cerrado");
    }
}
