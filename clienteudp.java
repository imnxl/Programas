import java.io.*;
import java.net.*;
import java.util.*;
import java.nio.*;

class clienteudp{
  public static void main (String args[]) throws Exception{

    Scanner sc = new Scanner(System.in);

    int contador = 0;
    int error = 0;

    DatagramSocket socket = new DatagramSocket(); //Creamos el socket UDP

    try {
      while (true) {
        System.out.print("IP y puerto: ");
        String texto = sc.nextLine();

        if (texto.equals("")){
          break;
        }

        String[] partes = texto.split(" ");
        String ip = partes[0];
        InetAddress IP = InetAddress.getByName(ip);
        String puerto= partes[1];
        int puertoservidor= Integer.parseInt(puerto);

        contador++; //Contador de lineas introducidas
        byte[] buffer = ByteBuffer.allocate(4).putInt(contador).array();

        DatagramPacket enviarpaquete = new DatagramPacket(buffer,buffer.length,IP,puertoservidor); //Nuevo datagrama con el array de bytes, su longitud, ip y puerto
        socket.send(enviarpaquete); //Lo envía

        DatagramPacket recibirpaquete = new DatagramPacket(buffer,buffer.length); //Nuevo datagrama vacío para llenar con lo que reciba
        socket.receive(recibirpaquete); //Lo recibe
        byte[] recibido = recibirpaquete.getData();

        int respuesta = ByteBuffer.wrap(recibido).getInt(); //Saca la respueta del servidor
        System.out.println("");
        System.out.println("Servidor: " + respuesta);
        System.out.println("");
      }
   }catch (Exception e) {
      System.out.println("");
      System.out.println("Parece que hubo un error.");
      error++;
   }
   if (error != 1){
     System.out.println("");
     System.out.println("Conexión finalizada.");
   }
 }
}
