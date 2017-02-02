package test;

import static org.junit.Assert.*;


import juego.Celda;
import juego.Wumpus;

import org.junit.Test;

public class WumpusTest {

	
	// Prueba el método Vecinos, colocando el Wumpus en la zona central de un tablero de 5 x 5, y comprobando
	// que hay peligros a su alrededor
	
		@Test
		public void testgetVecinosPeligrosWumpusCentral() {
			int numFilas = 5; 
			int numColumnas = 5;
			int numPozos = 1;
			int numFlechas = 3;
			
			Wumpus nuevoJuego = new Wumpus(numFilas, numColumnas, numPozos, numFlechas);
			for (int i = 0; i < numFilas; i++) {
				for (int j = 0; j < numColumnas; j++) {
					nuevoJuego.getTablero()[i][j] = new Celda();
				}
			}
			nuevoJuego.Vecinos(2, 2, "H");  //coloco el wumpus aqui
			assertTrue( ( nuevoJuego.getTablero()[1][2].getPeligro() == "H" || nuevoJuego.getTablero()[1][2].getPeligro() == "*") );
			assertTrue( ( nuevoJuego.getTablero()[2][3].getPeligro() == "H" || nuevoJuego.getTablero()[1][2].getPeligro() == "*") );
			assertTrue( ( nuevoJuego.getTablero()[3][2].getPeligro() == "H" || nuevoJuego.getTablero()[1][2].getPeligro() == "*") );
			assertTrue( ( nuevoJuego.getTablero()[2][1].getPeligro() == "H" || nuevoJuego.getTablero()[1][2].getPeligro() == "*") );

		}
		
		
		// Prueba el método Vecinos, colocando 2 pozos en la zona central de un tablero de 5 x 5, y comprobando
		// que hay peligros a su alrededor
		
			@Test
			public void testgetVecinosPeligrosDosPozosCentral() {
				int numFilas = 5; 
				int numColumnas = 5;
				int numPozos = 2;
				int numFlechas = 3;
				
				Wumpus nuevoJuego = new Wumpus(numFilas, numColumnas, numPozos, numFlechas);
				for (int i = 0; i < numFilas; i++) {
					for (int j = 0; j < numColumnas; j++) {
						nuevoJuego.getTablero()[i][j] = new Celda();
					}
				}
				
				for(int i = 0; i<numPozos; i++){
					
					nuevoJuego.Vecinos(2, 2, "B");
					assertTrue( ( nuevoJuego.getTablero()[1][2].getPeligro() == "B" || nuevoJuego.getTablero()[1][2].getPeligro() == "*") );
					assertTrue( ( nuevoJuego.getTablero()[2][3].getPeligro() == "B" || nuevoJuego.getTablero()[1][2].getPeligro() == "*") );
					assertTrue( ( nuevoJuego.getTablero()[3][2].getPeligro() == "B" || nuevoJuego.getTablero()[1][2].getPeligro() == "*") );
					assertTrue( ( nuevoJuego.getTablero()[2][1].getPeligro() == "B" || nuevoJuego.getTablero()[1][2].getPeligro() == "*") );
					
					nuevoJuego.Vecinos(3, 3, "B");
					assertTrue( ( nuevoJuego.getTablero()[2][3].getPeligro() == "B" || nuevoJuego.getTablero()[1][2].getPeligro() == "*") );
					assertTrue( ( nuevoJuego.getTablero()[3][4].getPeligro() == "B" || nuevoJuego.getTablero()[1][2].getPeligro() == "*") );
					assertTrue( ( nuevoJuego.getTablero()[4][3].getPeligro() == "B" || nuevoJuego.getTablero()[1][2].getPeligro() == "*") );
					assertTrue( ( nuevoJuego.getTablero()[3][2].getPeligro() == "B" || nuevoJuego.getTablero()[1][2].getPeligro() == "*") );
				}				
				
			}
			
			
			//Prueba que el Oro está en el tablero
			
			@Test
			public void testgetOroTablero() {
				
				boolean estaG = false;
				int numFilas = 3; 
				int numColumnas = 3;
				int numPozos = 1;
				int numFlechas = 3;
				
				Wumpus nuevoJuego = new Wumpus(numFilas, numColumnas, numPozos, numFlechas);
				for (int i = 0; i < numFilas; i++) {
					for (int j = 0; j < numColumnas; j++) {
						
						if(nuevoJuego.getTablero()[i][j].getPeligro().equals("G")) {estaG = true; break;} 
					}
				}							
				assertTrue(estaG == true);
											
			}
			
	
}
