import java.io.*;
import java.util.*;

public class Practica5{
	public static void main (String args[]) throws Exception {
		
		String linea,ip,palabras[];
		ArrayList<String> ipselect = new ArrayList<String>();
		ArrayList<String> ipsystem = new ArrayList<String>();
		ArrayList<String> iptotales = new ArrayList<String>();
		ArrayList<String> repetidos = new ArrayList<String>();
		ArrayList<Integer> orden = new ArrayList<Integer>();
				
		int cont = 0;
		int k = 0;
		
		FileInputStream fi;
		InputStreamReader is;
		BufferedReader br;
		
		fi = new FileInputStream(args[0]);
		is = new InputStreamReader (fi);
		br = new BufferedReader (is);	
		
		linea = br.readLine();
		
		while (linea != null) {
			palabras = linea.split(" ");
			if (!palabras[0].equals("127.0.0.1")) {
				iptotales.add(palabras[0]);
				if (palabras[6].contains("SELECT") || palabras[6].contains("select")){
					if (!ipselect.contains(palabras[0])) {
						ipselect.add(palabras[0]);
					}
				}
				if (palabras[6].contains("system")){
					if (!ipsystem.contains(palabras[0])) {
						ipsystem.add(palabras[0]);
					}
				}
			}
			linea = br.readLine();
		}
		
		System.out.println("Total entradas por IP:");
		for(int x=0; x < iptotales.size(); x++) {
			for (int i=0; i < iptotales.size() && !repetidos.contains(iptotales.get(x)); i++) {
				if (iptotales.get(x).equals(iptotales.get(i))) {
					cont++;
				}
			}
			if (cont > 0) {
				repetidos.add(iptotales.get(x));
				orden.add(cont);
				Collections.sort(orden);
				Collections.reverse(orden);
			}
			cont = 0;
		}
		
		for (int j=0; j < repetidos.size(); j++) {
			System.out.println(repetidos.get(j) + ": " + orden.get(j));
		}
		
		System.out.println("Intentos de ataque SQL");
		for(int j=0; j < ipselect.size(); j++) {
			System.out.println(ipselect.get(j));
		}
		
		System.out.println("Intentos de backdoors");
		for(int l=0; l < ipsystem.size(); l++) {
			System.out.println(ipsystem.get(l));
		}
	}
}