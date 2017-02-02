package juego;

import java.util.Random;

public class Wumpus {

	
	private int numFilas;
	private int numColumnas;
	private int numPozos;
	private int numFlechas;
	
	private Celda[][] tablero;
	private boolean gameOver;
	private boolean ganaste;
	
	private int cazadorFila, cazadorColumna;
		
	private String mirada = "Norte"; //Por defecto que mire hacia el Norte
	
	private boolean tengoOro = false;
	private boolean wumpusMuerto = false;
	
	
	public Wumpus(int numFilas, int numColumnas, int numPozos, int numFlechas) { //Donde se hallaran los peligros que el cazador no ve
			
		this.numFilas = numFilas;
		this.numColumnas = numColumnas;
		this.numPozos = numPozos;
		this.numFlechas = numFlechas;
		
		
		
		tablero = new Celda[numFilas][numColumnas];
		Random random = new Random();
			
		for (int i = 0; i < numFilas; i++) {
			for (int j = 0; j < numColumnas; j++) {
				tablero[i][j] = new Celda();
			}
		}
		
		
		//ColocarWumpus

		int wumpusFila = random.nextInt(numFilas);
		int wumpusColumna = random.nextInt(numColumnas);

		tablero[wumpusFila][wumpusColumna].setPeligro("W");
		Vecinos(wumpusFila, wumpusColumna, "H"); //Colocamos Hedor
		
		
		//Colocar los pozos
		
		int pozoFila;
		int pozoColumna;
		
		for(int i = 0; i<numPozos; i++){
			
			do {
				pozoFila = random.nextInt(numFilas);
				pozoColumna = random.nextInt(numColumnas);
			} while (!tablero[pozoFila][pozoColumna].getPeligro().equals(" ")); //no pueden coincidir
			
			tablero[pozoFila][pozoColumna].setPeligro("P");
			Vecinos(pozoFila, pozoColumna, "B");
			
		}
		
		//Colocar el oro
		
		int oroFila;
		int oroColumna;
		
		do{
			
			oroFila = random.nextInt(numFilas);
			oroColumna = random.nextInt(numColumnas);
		} 

		while(!tablero[oroFila][oroColumna].getPeligro().equals(" "));
		
		tablero[oroFila][oroColumna].setPeligro("G");

		//Colocar salida
		
		int salidaFila;
		int salidaColumna;
		
		do{
			
			salidaFila = random.nextInt(numFilas);
			salidaColumna = random.nextInt(numColumnas);
		} 

		while(!tablero[salidaFila][salidaColumna].getPeligro().equals(" "));
		
		tablero[salidaFila][salidaColumna].setPeligro("S");
		
		//Colocar el cazador
			
		do {
			cazadorFila = random.nextInt(numFilas);
			cazadorColumna = random.nextInt(numColumnas);
		} while (!tablero[cazadorFila][cazadorColumna].getPeligro().equals("S"));
			
		
		tablero[cazadorFila][cazadorColumna].setEstaCazador(true);
		tablero[cazadorFila][cazadorColumna].setVisible(true);
	}


		public void Vecinos(int fila, int columna, String peligro) {
			
			//Perimetro del tablero
			if(fila == 0 && columna == 0) {
				if(tablero[fila+1][columna].getPeligro()=="H")tablero[fila+1][columna].setPeligro("*");
				else tablero[fila+1][columna].setPeligro(peligro);
				
				if(tablero[fila][columna+1].getPeligro()=="H")tablero[fila][columna+1].setPeligro("*");
				else tablero[fila][columna+1].setPeligro(peligro);
				}
			
			if(fila == 0 && columna > 0 && columna < numColumnas-1) {
				if(tablero[fila+1][columna].getPeligro()=="H")tablero[fila+1][columna].setPeligro("*");
				else tablero[fila+1][columna].setPeligro(peligro);
				
				if(tablero[fila][columna+1].getPeligro()=="H")tablero[fila][columna+1].setPeligro("*");
				else tablero[fila][columna+1].setPeligro(peligro);
				
				if(tablero[fila][columna-1].getPeligro()=="H")tablero[fila][columna-1].setPeligro("*");
				else tablero[fila][columna-1].setPeligro(peligro);
				}
			
			if(fila == 0 && columna == numColumnas-1) {				
				if(tablero[fila+1][columna].getPeligro()=="H")tablero[fila+1][columna].setPeligro("*");
				else tablero[fila+1][columna].setPeligro(peligro);
				
				if(tablero[fila][columna-1].getPeligro()=="H")tablero[fila][columna-1].setPeligro("*");
				else tablero[fila][columna-1].setPeligro(peligro);
				}
			
			if(columna == 0 && fila > 0 && fila < numFilas-1){
				if(tablero[fila-1][columna].getPeligro()=="H")tablero[fila-1][columna].setPeligro("*");
				else tablero[fila-1][columna].setPeligro(peligro);
				
				if(tablero[fila][columna+1].getPeligro()=="H")tablero[fila][columna+1].setPeligro("*");
				else tablero[fila][columna+1].setPeligro(peligro);
				
				if(tablero[fila+1][columna].getPeligro()=="H")tablero[fila+1][columna].setPeligro("*");
				else tablero[fila+1][columna].setPeligro(peligro);
			}
			
			if(columna == numColumnas-1 && fila > 0 && fila < numFilas-1){
				if(tablero[fila-1][columna].getPeligro()=="H")tablero[fila-1][columna].setPeligro("*");
				else tablero[fila-1][columna].setPeligro(peligro);
				
				if(tablero[fila][columna-1].getPeligro()=="H")tablero[fila][columna-1].setPeligro("*");
				else tablero[fila][columna-1].setPeligro(peligro);
				
				if(tablero[fila+1][columna].getPeligro()=="H")tablero[fila+1][columna].setPeligro("*");
				else tablero[fila+1][columna].setPeligro(peligro);
			}
			
			if(fila == numFilas-1 && columna == 0) {
				if(tablero[fila-1][columna].getPeligro()=="H")tablero[fila-1][columna].setPeligro("*");
				else tablero[fila-1][columna].setPeligro(peligro);
				
				if(tablero[fila][columna+1].getPeligro()=="H")tablero[fila][columna+1].setPeligro("*");
				else tablero[fila][columna+1].setPeligro(peligro);
				}
			
			if(fila == numFilas-1 && columna > 0 && columna < numColumnas-1) {
				if(tablero[fila][columna-1].getPeligro()=="H")tablero[fila][columna-1].setPeligro("*");
				else tablero[fila][columna-1].setPeligro(peligro);
				
				if(tablero[fila-1][columna].getPeligro()=="H")tablero[fila-1][columna].setPeligro("*");
				else tablero[fila-1][columna].setPeligro(peligro);
				
				if(tablero[fila][columna+1].getPeligro()=="H")tablero[fila][columna+1].setPeligro("*");
				else tablero[fila][columna+1].setPeligro(peligro);
				}
			
			if(fila == numFilas-1 && columna == numColumnas-1) {
				if(tablero[fila][columna-1].getPeligro()=="H")tablero[fila][columna-1].setPeligro("*");
				else tablero[fila][columna-1].setPeligro(peligro);
				
				if(tablero[fila-1][columna].getPeligro()=="H")tablero[fila-1][columna].setPeligro("*");
				else tablero[fila-1][columna].setPeligro(peligro);
				}
			
			if(fila > 0 && fila < numFilas-1 && columna > 0 && columna < numColumnas-1) {
				if(tablero[fila-1][columna].getPeligro()=="H")tablero[fila-1][columna].setPeligro("*");
				else tablero[fila-1][columna].setPeligro(peligro);
				
				if(tablero[fila+1][columna].getPeligro()=="H")tablero[fila+1][columna].setPeligro("*");
				else tablero[fila+1][columna].setPeligro(peligro);
				
				if(tablero[fila][columna-1].getPeligro()=="H")tablero[fila][columna-1].setPeligro("*");
				else tablero[fila][columna-1].setPeligro(peligro);
				if(tablero[fila][columna+1].getPeligro()=="H")tablero[fila][columna+1].setPeligro("*");
				else tablero[fila][columna+1].setPeligro(peligro);
			}			

		}
	
		
		//Lanzar una flecha
		public void lanzarFlecha() {

			if (mirada.equals("Norte")) {
				lanzarFlechaNorte();
			} else if (mirada.equals("Sur")) {
				lanzarFlechaSur();
			} else if (mirada.equals("Este")) {
				lanzarFlechaEste();
			} else if (mirada.equals("Oeste")) {
				lanzarFlechaOeste();
			} 
			
		}
	
		
		public void lanzarFlechaNorte() {
				 
			if(!wumpusMuerto){
			for (int i = cazadorFila - 1; i >= 0; i--) {
				if (numFlechas!=0 && tablero[i][cazadorColumna].getPeligro().equals("W")) {
					System.out.println("Alguien ha chillado...\nHas matado al Wumpus!!!\n");
					numFlechas--;
					wumpusMuerto = true;
					return;				
				}				
			}
					
				if(numFlechas==0) {
					System.out.println("\nTe has quedado sin flechas\n");
					
				}
				else {
					System.out.println("\nLa flecha pegó en el muro Norte\n");
					numFlechas--;
				}
				
			}//Hemos matado el Wumpus pero pueden quedar flechas
			else {
				if(numFlechas==0) {
				System.out.println("\nTe has quedado sin flechas\n");
				
			}
			else {
				System.out.println("\nLa flecha pegó en el muro Norte\n");
				numFlechas--;
				}
			}
		}
		
		private void lanzarFlechaSur() {
			
			if(!wumpusMuerto){
			
			for (int i = cazadorFila + 1; i < numFilas; i++) {
				if (numFlechas!=0 && tablero[i][cazadorColumna].getPeligro().equals("W")) {
					System.out.println("\nAlguien ha chillado...\nHas matado al Wumpus!!!\n");
					numFlechas--;
					wumpusMuerto = true;
					return;	
				}
			}
			
			if(numFlechas==0) {
				System.out.println("\nTe has quedado sin flechas\n");
				
			}
			else {
				System.out.println("\nLa flecha pegó en el muro Sur\n");
				numFlechas--;
				}
			
			}//Hemos matado el Wumpus pero pueden quedar flechas
			else {
				if(numFlechas==0) {
				System.out.println("\nTe has quedado sin flechas\n");
				
			}
			else {
				System.out.println("\nLa flecha pegó en el muro Sur\n");
				numFlechas--;
				}
			}
			
		}//fin de metodo
		
		private void lanzarFlechaEste() {
			
			if(!wumpusMuerto){
			for (int j = cazadorColumna + 1; j < numColumnas; j++) {
				if (numFlechas!=0 && tablero[cazadorFila][j].getPeligro().equals("W")) {
					System.out.println("\nAlguien ha chillado...\nHas matado al Wumpus!!!");
					numFlechas--;
					wumpusMuerto = true;
					return;
				}
			}
			if(numFlechas==0) {
				System.out.println("\nTe has quedado sin flechas\n");
				
			}
			else {
				System.out.println("\nLa flecha pegó en el muro Este\n");
				numFlechas--;
				}
			
			}//Hemos matado el Wumpus pero pueden quedar flechas
			else {
				if(numFlechas==0) {
				System.out.println("\nTe has quedado sin flechas\n");
				
			}
			else {
				System.out.println("\nLa flecha pegó en el muro Este\n");
				numFlechas--;
				}
			}
		}

		public void lanzarFlechaOeste() {
			
			if(!wumpusMuerto){
			for (int j = cazadorColumna - 1; j >= 0; j--) {
				if (numFlechas!=0 && tablero[cazadorFila][j].getPeligro().equals("W")) {
					System.out.println("\nAlguien ha chillado...\nHas matado al Wumpus!!!");
					numFlechas--;
					wumpusMuerto = true;
					return;
				}
			}
			if(numFlechas==0) {
				System.out.println("\nTe has quedado sin flechas");
				
			}
			else {
				System.out.println("\nLa flecha pegó en el muro Oeste");
				numFlechas--;
				}
			
			}//Hemos matado el Wumpus pero pueden quedar flechas
			else {
				if(numFlechas==0) {
				System.out.println("\nTe has quedado sin flechas");
				
			}
			else {
				System.out.println("\nLa flecha pegó en el muro Oeste");
				numFlechas--;
				}
			}
		}

		
		
		
		
		public void mostrarMenu() {
					
		System.out.println("\nW -> Avanzar 				 F -> Lanzar una flecha");
		System.out.println("D -> Girar 90º a la derecha	         N -> Empezar un nuevo juego");
		System.out.println("A -> Girar 90º a la izquierda            Q -> Finalizar el juego");
		
		System.out.println("¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯");
		
	}
	
	
	// Hacer el tablero en String
		public String toString() {			
			
			String cadena = "";

			for (int i = 0; i < numFilas; i++) {				
				for (int j = 0; j < numColumnas; j++) {
					if (tablero[i][j].getEstaCazador()) {
						
						if(mirada == "Norte") cadena += "[^] ";
						if(mirada == "Sur") cadena += "[v] ";
						if(mirada == "Este") cadena += "[>] ";
						if(mirada == "Oeste") cadena += "[<] ";						
										
					} else if (tablero[i][j].getVisible()) {
						cadena += "[" + tablero[i][j].getPeligro() + "] ";
					} else {
						cadena += "[X] ";
					}
				}
				cadena += "\n";			
			}
			if(gameOver == false)cadena += "\nFlechas restantes: "+numFlechas+"\n";
			return cadena;
		}
	
		public String mostrarMapa() {

			for (int i = 0; i < numFilas; i++) {
				for (int j = 0; j < numColumnas; j++) {
					tablero[i][j].setEstaCazador(false);
					tablero[i][j].setVisible(true);
				}
			}
			return toString();
		}

		
		public void moverCazador(String eleccion) {
			tablero[cazadorFila][cazadorColumna].setEstaCazador(false);

			if (eleccion.equals("w")) { //Avanzar

				System.out.println("\nAvanzas en dirección "+mirada+"...");
				
				switch(mirada){
				
				case "Norte": 
					if(cazadorFila > 0) {
						cazadorFila -= 1;
					}else System.out.println("... pero te chocaste con un muro y no puedes seguir\n");
				break;
					
				case "Sur":
					if(cazadorFila < numFilas-1) {
						cazadorFila += 1;
					}else System.out.println("... pero te chocaste con un muro y no puedes seguir\n");
				break;
				
				case "Este":
					if(cazadorColumna < numColumnas-1) {
						cazadorColumna += 1;
					}else System.out.println("... pero te chocaste con un muro y no puedes seguir\n");
				break;
				
				case "Oeste":
					if(cazadorColumna > 0) {
						cazadorColumna -=1 ;
					}else System.out.println("... pero te chocaste con un muro y no puedes seguir\n");
				break;
				}		
				
			} 
			
			else if(eleccion.equals("d")){
				
				if(mirada == "Norte") mirada = "Este";
				else if(mirada == "Este") mirada = "Sur";
				else if(mirada == "Sur") mirada = "Oeste";
				else if(mirada == "Oeste") mirada = "Norte";
				
				System.out.println("\nGiras en dirección "+mirada+"...");
			}
			
			else if (eleccion.equals("a")) {
				
				if(mirada == "Norte") mirada = "Oeste";
				else if(mirada == "Oeste") mirada = "Sur";
				else if(mirada == "Sur") mirada = "Este";
				else if(mirada == "Este") mirada = "Norte";
//				
				System.out.println("\nGiras en dirección "+mirada+"...");
				
			} 

			Celda nuevaCelda = tablero[cazadorFila][cazadorColumna];
			nuevaCelda.setEstaCazador(true);
			nuevaCelda.setVisible(true);

			
			//Hemos encontrado oro
			if (nuevaCelda.getPeligro().equals("G") && !tengoOro){
				System.out.println("\nAlgo brilla... Has encontrado el oro!!! Vuelve por donde entraste!!!\n");
				tengoOro = true;
			}
			
			else if (nuevaCelda.getPeligro().equals("W")) {
				
				
				if(wumpusMuerto==false){
				System.out.println("...Te topaste con Wumpus!!!\n");
				gameOver = true;
				}
				else System.out.println("...El Wumpus yace aquí\n");
			} 
			
			else if (nuevaCelda.getPeligro().equals("P")) {
				gameOver = true;
				System.out.println("...Caiste en un pozo!!!\n");
			} 
			
			else if (nuevaCelda.getPeligro().equals("H") && wumpusMuerto == false) {
				System.out.println("...Se percibe un hedor...\n");
			}
			
			else if (nuevaCelda.getPeligro().equals("B")) {
				System.out.println("...Se percibe una brisa...\n");
			} 
			
			else if (nuevaCelda.getPeligro().equals("*") && wumpusMuerto == false) {
				System.out.println("...Se percibe una brisa...\n...Se percibe un hedor...\n");
							
			}
			
			else if (nuevaCelda.getPeligro().equals("*") && wumpusMuerto == true) {
				System.out.println("...Se percibe una brisa...\n");
			}
			
			else if (nuevaCelda.getPeligro().equals("S") && tengoOro == true){
				ganaste = true;
				gameOver = true;			
			}
			
			else System.out.println("...Aqui no hay nada...\n");
			}


	public boolean getGameOver() {
		return gameOver;
	}
	
	public void setGameOver(boolean gameOver) {
		this.gameOver = gameOver;
	}
	
	public boolean getGanaste() {
		return ganaste;
	}
	
	public void setGanaste(boolean ganaste) {
		this.ganaste = ganaste;
	}
	
	
	
	public Celda[][] getTablero() {
		return tablero;
	}
	
}
