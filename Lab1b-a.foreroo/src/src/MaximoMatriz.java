package src;

import java.util.concurrent.ThreadLocalRandom;

public class MaximoMatriz extends Thread{

	private final static int INT_MAX = 105345;
	
	private final static int DIM =3; //nxn
	
	private static int[][] matriz = new int[DIM][DIM];
	
	//mayor global
	private static int mayor = -1;
	
	//mayor local
	private int mayorFila = -1;
	
	private int idThread;
	
	private int fila;
	
	public MaximoMatriz(int pIdThread, int pFila) {
		this.idThread = pIdThread;
		this.fila = pFila;
	}
	
	public static void crearMatriz() {
		for (int i = 0; i < DIM; i++) {
			for (int j = 0; j < DIM; j++) {
				matriz[i][j] = ThreadLocalRandom.current().nextInt(0,INT_MAX);
			}
		}
		
		System.out.println("Matriz");
		System.out.println("==============================");
		imprimirMatriz();
		
	}
	
	private static void imprimirMatriz() {
		for (int i = 0; i < matriz.length; i++) {
			for (int j = 0; j < matriz.length; j++) {
				System.out.println(matriz[i][j] + "\t");
			}
			System.out.println();
		}
	}
	
	
	@Override
	public void run() {
		for (int i = 0; i < DIM; i++) {
			if(this.mayorFila < matriz[this.fila][i]) {
				this.mayorFila = matriz[this.fila][i];
			}
		}
		
		if(this.mayorFila> mayor) {
			try {
				Thread.sleep(250);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			mayor = this.mayorFila;
			String warn = String.format("-----------nuevo maximo encontrado------------ \n"
					+ "ID Thread: %d - Maximo local actual : %d - Maximo global %d \n"
					+ "\n"
					, this.idThread,
					mayor,
					this.mayorFila);
			System.out.println(warn);
			
			String msg = String.format("ID Thread: %d - Maximo local actual : %d - Maximo global %d \\n"
					,this.idThread,
					this.mayorFila,
					mayor);
			System.out.println(msg);
					
		}
	}
	
	public static void main(String[] args) {
		MaximoMatriz.crearMatriz();
		
		
		System.out.println();
		
		
		MaximoMatriz[] bThreads = new MaximoMatriz[DIM];
		for (int i = 0; i < DIM; i++) {
			bThreads[i]= new MaximoMatriz(i, i);
			bThreads[i].start();
		}
	}
	
	
	
}
