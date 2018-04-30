package paquete;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;
import java.util.Scanner;
import java.util.stream.IntStream;

public class Ordenarmiento {

	public static void burbujeo(int[] vec) {
		boolean intercambio;
		do {
			int k = 1;
			intercambio = false;
			for (int i = 0; i < vec.length - k; i++)
				if (vec[i] > vec[i + 1]) {
					int aux = vec[i];
					vec[i] = vec[i + 1];
					vec[i + 1] = aux;
					intercambio = true;
				}
			k++;
		} while (intercambio);

	}

	public static void insercion(int[] vec) {
		for (int i = 1; i < vec.length; i++) {
			int j, extraido = vec[i];
			for (j = i - 1; j > -1 && extraido < vec[j]; j--)
				vec[j + 1] = vec[j];
			vec[j + 1] = extraido;
		}
	}

	public static void seleccion(int[] vec) {
		for (int i = 0; i < vec.length - 1; i++) {
			int pmin = i, aux;
			for (int j = i + 1; j < vec.length; j++)
				if (vec[j] < vec[pmin])
					pmin = j;
			aux = vec[i];
			vec[i] = vec[pmin];
			vec[pmin] = aux;
		}
	}

	public static int[] cargarAscedente(int n) {
		int[] vector = new int[n];
		for (int i = 0; i < vector.length; i++)
			vector[i] = i + 1;

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
			int aux = vector[i - 1];
			vector[i - 1] = vector[posicion];
			vector[posicion] = aux;
		}

		return vector;
	}

	public static void mostrar(int[] vector) {
		System.out.print("Vector: ");
		for (int i = 0; i < vector.length; i++)
			System.out.print(vector[i] + ",");
		System.out.println();
	}

	public static int[] cargarDeArchivo(String arch) throws FileNotFoundException {
		Scanner sc = new Scanner(new File(arch));
		int[] vector = new int[sc.nextInt()];

		for (int i = 0; i < vector.length; i++)
			vector[i] = sc.nextInt();
		sc.close();
		return vector;
	}

	public static void main(String[] args) throws IOException {
		int[] cant = { 1, 10, 25, 50, 75, 100 };
		String[] nomb = { " ascendente.in", " descendente.in", " random.in" };
		PrintWriter salida = new PrintWriter(new FileWriter("Tiempos.out"));

		for (int i = 0; i < cant.length; i++) {
			int registros = cant[i] * 1000;
			for (int j = 0; j < nomb.length; j++) {
				int[] vector = cargarDeArchivo(registros + nomb[j]);
				
				long tini = System.currentTimeMillis();
				burbujeo(vector);
				long tfin = System.currentTimeMillis();
				
				System.out.println(registros + nomb[j] + ": " + (tfin - tini) + " milisegundos");
				salida.println(registros + nomb[j] + ": " + (tfin - tini) + " milisegundos");
			}

		}

		salida.close();
		System.out.println("Fin");

	}

}
