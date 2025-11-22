import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class Cliente {
    public static void main(String[] args) {


        int puertoServidor = 6666;
        String[] mensajes = {
                "mensaje1: sin tv:",
                "mensaje2: y sin cerveza",
                "mensaje3: Homer"
        };

        while (true) {

        }

        try {
            InetAddress direccionServidor = InetAddress.getByName("localhost");
            DatagramSocket datagramSocket = new DatagramSocket();

            for (String mensaje : mensajes) {
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
                System.out.println("Respuesta del servidor: " + mensajeServidor);
            }

            datagramSocket.close();

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}