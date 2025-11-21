import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class Servidor {
    public static void main(String[] args) {
        int puerto = 6666;
        String[] respuestas = {
                "Mensaje 1: Pierde",
                "Mensaje 2: La",
                "Mensaje 3: Cabeza"
        };

        try {
            DatagramSocket datagramSocket = new DatagramSocket(puerto);
            System.out.println("Servidor iniciado...");

            for (int i = 0; i < 3; i++) {
                // Recibir mensaje del cliente
                byte[] buffer = new byte[1024];
                DatagramPacket peticion = new DatagramPacket(buffer, buffer.length);
                datagramSocket.receive(peticion);
                String mensajeCliente = new String(peticion.getData(), 0, peticion.getLength());
                System.out.println("Recibido del cliente: " + mensajeCliente);

                // Enviar respuesta al cliente
                byte[] bufferRespuesta = respuestas[i].getBytes();
                InetAddress direccionCliente = peticion.getAddress();
                int puertoCliente = peticion.getPort();
                DatagramPacket respuesta = new DatagramPacket(bufferRespuesta, bufferRespuesta.length, direccionCliente, puertoCliente);
                datagramSocket.send(respuesta);
                System.out.println("Enviado: " + respuestas[i]);
            }

            datagramSocket.close();
            System.out.println("Servidor terminado");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}