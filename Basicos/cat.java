import java.io.*;

public class cat{
	public static void main (String args[]) throws Exception {
		
		String linea;
		
		FileInputStream fi;
		InputStreamReader is;
		BufferedReader br;
		
		fi = new FileInputStream(args[0]);
		is = new InputStreamReader (fi);
		br = new BufferedReader (is);
		
		linea = br.readLine();
		
		while (linea != null) {
			System.out.println(linea);
			linea = br.readLine();
		}
	}
}