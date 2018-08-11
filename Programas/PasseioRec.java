import java.util.*;

public class PasseioRec {


	public static int contador = 0;
	public static boolean debug = false;
	public static boolean passo_a_passo = false;

	public static int contaCasas(int [][] t){

		int contador = 0;

		for(int i = 0; i < t.length; i++){

			for(int j = 0; j < t[i].length; j++){

				if(t[i][j] >= 0) contador++;
			}
		}

		return contador;
	}

	public static void imprime(int [][] t){

		imprime(t, false);
	}

	public static void imprime(int [][] t, boolean force){

		if(debug || force){

			for(int i = 0; i < t.length; i++){

				for(int j = 0; j < t[i].length; j++){

					if(t[i][j] < 0) System.out.print(" X ");
					else if(t[i][j] == 0) System.out.print(" . ");
					else System.out.printf("%2d ", t[i][j]);
				}

				System.out.println();
			}

			System.out.println();
			
			if(passo_a_passo){
				
				new Scanner(System.in).nextLine();
			}
		}
	}

	public static boolean verifica(int [][] t, int lin, int col){

		try{
			if(t[lin][col] == 0) return true;
		}
		catch(Exception e){
		}

		return false;
	}

	public static boolean passeio(int [][] t, int lin, int col, int passo, int nCasas){
	
		if(verifica(t, lin, col)){

			contador++;

			passo++;
			t[lin][col] = passo;
			imprime(t);

			if(passo == nCasas){

				System.out.println("Achou passeio!");
				imprime(t, true);
				return true;				
			}

			if(passeio(t, lin + 1, col + 2, passo, nCasas)) return true;
			if(passeio(t, lin + 2, col + 1, passo, nCasas)) return true;
			if(passeio(t, lin + 2, col - 1, passo, nCasas)) return true;
			if(passeio(t, lin + 1, col - 2, passo, nCasas)) return true;
			if(passeio(t, lin - 1, col - 2, passo, nCasas)) return true;
			if(passeio(t, lin - 2, col - 1, passo, nCasas)) return true;
			if(passeio(t, lin - 2, col + 1, passo, nCasas)) return true;
			if(passeio(t, lin - 1, col + 2, passo, nCasas)) return true;

			t[lin][col] = 0;
			imprime(t);
		}

		return false;
	}

	public static int [][] getMat(int n){

		int [][] m = new int[n][n];

		for(int i = 0; i < n; i++){

			for(int j = 0; j < n; j++){

				m[i][j] = 0;
			}
		}

		return m;
	}

	public static void main(String [] args){

		int option = Integer.parseInt(args[0]);
		PasseioRec.debug = Boolean.parseBoolean(args[1]);
		PasseioRec.passo_a_passo = Boolean.parseBoolean(args[2]);

		int [][] t0 = { {-1, -1,  0},
				{ 0,  0, -1},
				{-1, -1,  0},
				{ 0, -1,  0}	};

		int [][] t1 = {	{ 0, 0, 0 },
				{ 0, 0, 0 },
				{ 0, 0, 0 }	};

		int [][] t2 = { { 0, 0, 0,  0 },
				{ 0, 0, 0, -1 },
				{ 0, 0, 0, -1 }	};

		int size = (args.length == 4 ? Integer.parseInt(args[3]) : 4);

		int [][] t3 = getMat(size);

		PasseioRec.contador = 0;

		if(option == 0) System.out.println(passeio(t0, 3, 0, 0, contaCasas(t0)));
		if(option == 1) System.out.println(passeio(t1, 0, 0, 0, contaCasas(t1)));
		if(option == 2) System.out.println(passeio(t2, 1, 0, 0, contaCasas(t2)));
		if(option == 3) System.out.println(passeio(t3, 0, 0, 0, contaCasas(t3)));

		System.out.println("Contador de chamadas: " + PasseioRec.contador);
	}
}
