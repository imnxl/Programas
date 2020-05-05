
import java.io.*;
import java.net.*;
import java.util.*;

public class cliente {
 public static void main (String args[]) throws Exception{

   String IP, texto, input;
   int PUERTO;
   Scanner sc = new Scanner(System.in);

   System.out.print("Introduce la IP del servidor: (localhost por defecto) ");
   IP = sc.nextLine();

   System.out.print("Introduce puerto: ");
   PUERTO = sc.nextInt();
   System.out.println("");

   try {
     Socket socket = new Socket(IP, PUERTO);

     DataInputStream in = new DataInputStream(socket.getInputStream());
     DataOutputStream out = new DataOutputStream(socket.getOutputStream());
     BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

     System.out.print("Introduce texto: ");
     try {
       while (true) {
         texto = br.readLine();
         if (texto.equals(".")){
           socket.close();
         }
         out.writeUTF(texto);
         input = in.readUTF();
         System.out.println("Servidor: " + input);
         System.out.print("Tú: ");
       }
     }catch (Exception e) {
       System.out.println("");
       System.out.println("Conexión finalizada.");
     }
   }catch (Exception e) {
     System.out.println("Parece que hubo un error.");
   }
 }
}
