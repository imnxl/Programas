import java.io.*;

public class genera {
	public static void main (String args[]) throws Exception {
		
		File archivo = new File("/home/alumno/Escritorio/archivo.pgm");
       
		if (!archivo.exists()) {
            archivo.createNewFile();
        }
		
		int numero = Integer.parseInt(args[1]);
		
        FileWriter fw = new FileWriter(archivo);
        BufferedWriter bw = new BufferedWriter(fw);
		
		bw.write("P2\n");
		bw.write(args[1] + " " + args[1]);
		bw.write("\n");
		bw.write(args[2]);
		bw.write("\n");
		
		for(int j=0; j < numero; j++) {
			bw.write(args[2] + " ");
			for (int i=1; i < numero; i++) {
				if (i <= j) {
					bw.write(args[2] + " ");  
				}else {
					bw.write(0 + " ");
				}	
			}
			bw.write("\n");
		}
		bw.close();
	}
}
