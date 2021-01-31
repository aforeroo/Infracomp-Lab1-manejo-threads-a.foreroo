package src;

import java.util.Scanner;

/**
 * Solucion al ejercicio del lab parte 1-a usando herencia de Thread * 
 * @author Andres Forero - 201614341
 */
class ProcesoImpresionThread extends Thread {

	String tipo;
	int rango;
	int espera;

	public ProcesoImpresionThread(String tipo, int rango, int espera) {
		this.tipo = tipo;
		this.rango = rango;
		this.espera = espera;
	}

	public void run() {

		int i;
		if (tipo.equals("pares"))
			i = 2;
		else
			i = 1;

		while (i <= rango) {
			System.out.println("Thread-" + tipo + ": " + i);
			i += 2;
			try {
				Thread.sleep(espera);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
	}

}

public class solThread {

	final public static String PARES = "pares";
	final public static String IMPARES = "impares";

	public static void main(String[] args) {

		int rango;
		int espera;

		Scanner in = new Scanner(System.in);
		System.out.print("se imprimiran los numeros desde 1 hasta: ");
		rango = in.nextInt();
		System.out.print("milisegundos de espera entre impresion de numeros del mismo tipo: ");
		espera = in.nextInt();
		in.close();
		
		ProcesoImpresionThread impares = new ProcesoImpresionThread(IMPARES, rango, espera);
		ProcesoImpresionThread pares = new ProcesoImpresionThread(PARES, rango, espera);
		
		impares.start();
		pares.start();

	}

}
