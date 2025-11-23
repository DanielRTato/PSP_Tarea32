import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class Cliente {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        final int puertoServidor = 6666;

        try (DatagramSocket datagramSocket = new DatagramSocket()) {
            InetAddress direccionServidor = InetAddress.getByName("localhost");

            while (true) {
                System.out.println("Escribe la lista de palabras: (o 'salir' para terminar): ");
                String mensaje = scanner.nextLine();

                if (mensaje.equalsIgnoreCase("salir")) {
                    break;
                }

                // Enviar mensaje al servidor
                byte[] buffer = mensaje.getBytes();
                DatagramPacket paquete = new DatagramPacket(buffer, buffer.length, direccionServidor, puertoServidor);
                datagramSocket.send(paquete);
                System.out.println("Enviado: " + mensaje);

                // Recibir respuesta del servidor
                byte[] bufferRespuesta = new byte[1024];
                DatagramPacket respuesta = new DatagramPacket(bufferRespuesta, bufferRespuesta.length);
                datagramSocket.receive(respuesta);
                String mensajeServidor = new String(respuesta.getData(), 0, respuesta.getLength());
                System.out.println("Mensaje del servidor: " + mensajeServidor);
            }
            datagramSocket.close();

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

}
