import java.io.*;
import java.util.*;

public class Practica4 {
		public static void main (String argv[]) throws Exception{
			
			String linea, palabras[], ip, fecha, tamaño;
			ArrayList<String> iplist = new ArrayList<String>();
			ArrayList<String> primerafecha = new ArrayList<String>();
		
			int cont;
			cont = 0;
			int entradas;
			entradas = 0;
			
			FileInputStream fi;
			InputStreamReader is;
			BufferedReader br;
			
			fi = new FileInputStream (argv[0]);
			is = new InputStreamReader (fi);
			br = new BufferedReader (is);
			
			linea = br.readLine();
			
			while (linea != null) {
				palabras = linea.split(" ");
				ip = palabras[0];
				fecha = palabras[3];
				
				if (!ip.equals("127.0.0.1")) {
					entradas++;
					if (cont < 100) {
							if(!iplist.contains(ip)){
								iplist.add(ip);
								primerafecha.add(fecha);
								cont++;
							}
					}
				}
				linea = br.readLine();
			}
			System.out.println("Direcciones IP:");
			for(int x=0; x < iplist.size(); x++) {
				System.out.println(iplist.get(x));
			}
			System.out.println("Datos generales:");
			tamaño = primerafecha.get(primerafecha.size() - 1);
			System.out.println ("Primera entrada: " + primerafecha.get(0) + " " + tamaño);
			for(int x=0; x < iplist.size(); x++) {
				System.out.println(iplist.get(x) + " " + primerafecha.get(x));
			}
			
			System.out.println("Total entradas globales:");
	        System.out.println(entradas);
		}
}