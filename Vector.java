package paquete;
import java.util.Random;
import java.util.stream.IntStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Vector {
	
	public static int[] cargarAscedente(int n) {
		int[] vector = new int[n];
		for (int i = 0; i < vector.length; i++)
			vector[i] = i+1;
		
		return vector;
	}
	
	public static int[] cargarDescendente(int n) {
		int[] vector = new int[n];
		for (int i = 0; i < vector.length; i++, n--)
			vector[i] = n;
		
		return vector;
	}
	
	public static int[] cargarRandom(int n) {
		int[] vector = IntStream.rangeClosed(1, n).toArray();
		Random r = new Random();
		for (int i = vector.length; i > 0; i--) {
		    int posicion = r.nextInt(i);
		    int aux = vector[i-1];
		    vector[i - 1] = vector[posicion];
		    vector[posicion] = aux;
		}
		
		return vector;
	}
	
	public static void mostrar(int[] vector) {
		System.out.print("Vector:");
		for (int i = 0; i < vector.length; i++)
			System.out.print(vector[i] + ",");
		System.out.println();
	}

	public static void main(String[] args) throws IOException {

		int[] cant = {1,10,25,50,75,100};
		
		for (int i = 0; i < cant.length; i++) {
			int registros = cant[i]*1000;
			PrintWriter fascendente = 	new PrintWriter(	new FileWriter(registros + " ascendente.in"));
			PrintWriter fdescendente = 	new PrintWriter(	new FileWriter(registros + " descendente.in"));
			PrintWriter frandom = 		new PrintWriter(	new FileWriter(registros + " random.in"));
			
			int[] vascendente = 	cargarAscedente(registros);
			int[] vdescendente = 	cargarDescendente(registros);
			int[] vrandom = 		cargarRandom(registros);
			
			fascendente.println(vascendente.length);
			fdescendente.println(vdescendente.length);
			frandom.println(vrandom.length);
			
			for (int j = 0; j <registros; j++) {
				fascendente.println(vascendente[j]);
				fdescendente.println(vdescendente[j]);
				frandom.println(vrandom[j]);
			}
			
			fascendente.close();
			fdescendente.close();
			frandom.close();
		}
		System.out.println("Fin de creacion de archivos");
		
	}
}
