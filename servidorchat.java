import java.io.*;
import java.net.*;
import java.util.*;

public class servidorchat {

	int puerto;
  List<PrintStream> clientes;

	public static void main(String[] args) throws Exception {
		new servidorchat(4444).run();
	}

	public servidorchat(int puerto) {
		this.puerto = puerto;
		this.clientes = new ArrayList<PrintStream>();
	}

	public void run() throws Exception {
		ServerSocket servidor = new ServerSocket(puerto); //Inicia el servidor
		System.out.println("Servidor iniciado.");
    System.out.println("");

		while (true) {
			Socket cliente = servidor.accept(); //Acepta al cliente
			System.out.println("Conexión establecida con: " + cliente.getInetAddress().getHostAddress());
      System.out.println("");
			this.clientes.add(new PrintStream(cliente.getOutputStream())); //Lo añade a la lista

			new Thread(new administrar(this, cliente.getInputStream())).start(); //Nuevo hilo para administrar clientes
		}
	}

	void enviar(String mensaje) {
		for (PrintStream cliente : this.clientes) {
			cliente.println(mensaje);
		}
	}
}

class administrar implements Runnable {

	servidorchat servidor;
	InputStream cliente;

	public administrar(servidorchat servidor, InputStream cliente) {
		this.servidor = servidor;
		this.cliente = cliente;
	}

	@Override
	public void run() {
		Scanner sc = new Scanner(this.cliente); //Envia un mensaje a todos los clientes
		while (sc.hasNextLine()) {
			String mensaje = sc.nextLine();
			servidor.enviar(mensaje);
		}
		sc.close();
	}
}
