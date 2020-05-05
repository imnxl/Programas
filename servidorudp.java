import java.io.*;
import java.net.*;
import java.util.*;
import java.nio.*;

class servidorudp{
  public static void main (String args[]) throws Exception{

    byte[] buffer = new byte[1024];

    Scanner sc = new Scanner(System.in);

    System.out.print("Puerto a eschuchar: ");
    int PUERTO = sc.nextInt();

    DatagramSocket socket = new DatagramSocket(PUERTO); //Crea un socket UDP escuchando en un puerto

    System.out.println("");
    System.out.println("Servidor iniciado.");
    System.out.println("");

    while (true) {
      DatagramPacket recibirpaquete = new DatagramPacket(buffer,buffer.length); //Nuevo datagrama vacío esperando recibir algo
      socket.receive(recibirpaquete); //Recibe algo
      byte[] recibido = recibirpaquete.getData();

      int entero = ByteBuffer.wrap(recibido).getInt(); //Saca el entero que envió el cliente

      InetAddress ip = recibirpaquete.getAddress(); //Saca la dirección IP de origen del datagrama
      int puertocliente = recibirpaquete.getPort(); //Saca el puerto origen del datagrama

      DatagramPacket enviarpaquete = new DatagramPacket(recibido,recibido.length,ip,puertocliente); //Nuevo datagrama con el array de bytes, su longitud, ip y puerto
      socket.send(enviarpaquete); //Lo envía
      System.out.println("Total de conexiones: " + entero);
    }
  }
}
