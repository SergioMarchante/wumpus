package juego;

import java.util.Scanner;


public class HuntTheWumpus {
	
	public int numFilas, numColumnas, numPozos, numFlechas;
	
	
	public static void main(String[] args) {
		Scanner teclado = new Scanner(System.in);
		Scanner tecladoInicio = new Scanner(System.in);
		String eleccion = "";
		
		int numFilas;
		int numColumnas;
		int numPozos;
		int numFlechas;

		
		//Parametrizar filas, columnas, pozos y flechas
		System.out.println("Hunt the Wumpus");
		System.out.println("¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯");
		System.out.println("Sigue tu instinto. Evita los pozos. Encuentra el oro. Escapa. Ten cuidado con el Wumpus!!!\n");
		
		
		do{
		System.out.println("Introduce el número de filas (mínimo 4): ");
        numFilas = tecladoInicio.nextInt();
    
		}while(numFilas < 4);
		
		do{
			System.out.println("Introduce el número de columnas (mínimo 4): ");
	        numColumnas = tecladoInicio.nextInt();
	    
			}while(numColumnas < 4);
		
		
			System.out.println("Introduce el número de pozos: (Recomendado según el tamaño del tablero): "+(((numFilas+numColumnas)/2)-1));
	        numPozos = tecladoInicio.nextInt();
	    
	  
			System.out.println("Introduce el número de flechas: ");
		    numFlechas = tecladoInicio.nextInt();			
		
		
		do {
									
			Wumpus nuevoJuego = new Wumpus(numFilas, numColumnas, numPozos, numFlechas);
			eleccion = "";
			nuevoJuego.setGameOver(false);
			nuevoJuego.setGanaste(false);
			
			nuevoJuego.mostrarMenu();

			while (!nuevoJuego.getGameOver()) {
							
				System.out.println(nuevoJuego.toString()); //tablero en string
				 
				System.out.println("Leyenda: W -> Wumpus | P -> Pozo | G -> Oro | H -> Hedor del Wumpus");
				System.out.println("         B -> Brisa del pozo | * -> Hedor del Wumpus y brisa del pozo");
				System.out.println("         V,>,<,^ -> Cazador | X -> Celda sin pisar | S -> Salida");
				System.out.println("¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯");
				
				eleccion = teclado.nextLine().toLowerCase();

				if (eleccion.equals("w")) {
					nuevoJuego.moverCazador(eleccion);
					
				} else if(eleccion.equals("d")){ //girar hacia derecha
					nuevoJuego.moverCazador(eleccion);
					
				} else if(eleccion.equals("a")){ //girar hacia izquierda
					nuevoJuego.moverCazador(eleccion);
								
				} else if (eleccion.equals("f")) { //lanzar flecha
					nuevoJuego.lanzarFlecha();
					
				} else if(eleccion.equals("q")) {
					nuevoJuego.setGameOver(true);
				
				} else if (eleccion.equals("n")) {
					break;
					
				} else {
					System.out.println("Tecla incorrecta\n");
				}
			} //fin del juego
		
			
			
//			Cuando sea gameover, puede ser que se haya ganado o perdido	
			if ((!eleccion.equals("n")) && (!eleccion.equals("q"))) {
				
				nuevoJuego.mostrarMapa();
				
				if (nuevoJuego.getGanaste()) {
					System.out.println("\nEscapaste con el ORO!!!!\nHas ganado!!!\n");
					System.out.println(nuevoJuego.mostrarMapa());
				} 
					else {
					System.out.println("Has perdido\n");
					System.out.println(nuevoJuego.mostrarMapa());
				}
			
				System.out.println("Pulsa N para jugar de nuevo o Q para salir del juego");
				eleccion = teclado.nextLine().toLowerCase();
			}

		} while (!eleccion.equals("q"));
		System.out.println("\nHasta la proxima!!!");
		teclado.close();
		
	} //fin de main
		
}//fin de clase