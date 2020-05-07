import java.io.*;
import java.net.*;
import java.util.*;

public class clientechat {

	String ip;
	int puerto;

	public static void main(String[] args) throws Exception {
		new clientechat("localhost", 4444).run();
	}

	public clientechat(String ip, int puerto) {
		this.ip = ip;
		this.puerto = puerto;
	}

	public void run() throws Exception {
		Socket cliente = new Socket(ip, puerto); //Se conecta al sevidor
		System.out.println("Conexión establecida.");
		System.out.println("");

		new Thread(new recibido(cliente.getInputStream())).start(); //Crea un hilo para administrar los mensajes del servidor

		Scanner sc = new Scanner(System.in);
		System.out.print("Nombre de usuario: ");
		String usuario = sc.nextLine();
		System.out.println("");

		System.out.println("Chat comenzado: ");
		System.out.println("");
		PrintStream output = new PrintStream(cliente.getOutputStream());
		while (sc.hasNextLine()) {
			output.println(usuario + ": " + sc.nextLine());
		}
	}
}

class recibido implements Runnable {

	InputStream servidor;

	public recibido(InputStream servidor) {
		this.servidor = servidor;
	}

	@Override
	public void run() { //Recibe mensajes del servidor y los imprime
		Scanner sc = new Scanner(servidor);
		while (sc.hasNextLine()) {
			System.out.println(sc.nextLine());
		}
		sc.close();
	}
}
