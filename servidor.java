import java.io.*;
import java.net.*;
import java.util.*;
import java.text.*;

public class servidor {
	public static void main (String args[]) throws Exception{

    String input;
    int PUERTO, contador;

    Scanner sc = new Scanner(System.in);

    System.out.print("Puerto a eschuchar: ");
    PUERTO = sc.nextInt();

		while (true) {
			try {
	      ServerSocket serversocket = new ServerSocket(PUERTO);
	      System.out.println("");
	      System.out.println("Servidor iniciado, esperando conexión con un cliente.");
	      System.out.println("");


	      Socket socket = serversocket.accept();
	      System.out.println("Conexión establecida con éxito.");
	      System.out.println("");

	      DataInputStream in = new DataInputStream(socket.getInputStream());
	      DataOutputStream out = new DataOutputStream(socket.getOutputStream());
	      DateFormat fechayhora = new SimpleDateFormat("HH:mm dd/MM/yyyy");
	      Date fecha = new Date();

	      while (true) {
	        input = in.readUTF();
					System.out.println("Cliente: " + input);
	        if (input.equals(".")) {
	          socket.close();
	        }
	        else if (input.equals("hola")) {
	          out.writeUTF("buenos días");
	          System.out.println("Servidor: buenos días");
	        }
	        else if (input.equals("ip servidor")) {
	          out.writeUTF(socket.getInetAddress().getHostAddress());
	          System.out.println("Servidor: " + socket.getInetAddress().getHostAddress());
	        }
	        else if (input.equals("ip cliente")) {
	          out.writeUTF(socket.getInetAddress().getHostAddress());
	          System.out.println("Servidor: " + socket.getInetAddress().getHostAddress());
	        }
	        else if (input.equals("hora")) {
	          out.writeUTF(fechayhora.format(fecha));
	          System.out.println("Servidor: " + fechayhora.format(fecha));
	        }else {
						contador = 0;
						for (int i = 0; i < input.length(); i++) {
							char letra = input.charAt(i);
							if (letra != ' ') {
								contador++;
							}
						}
						out.writeUTF("Total de caracteres: " + contador);
						System.out.println("Servidor: Total de caracteres: " + contador);
	        }
	      }
			}catch (Exception e){

			}
		}
  }
}
