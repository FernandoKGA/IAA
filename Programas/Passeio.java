import java.util.*;

class Elemento {

	private static int [] dirLin = {1, 2,  2,  1, -1, -2, -2, -1};
	private static int [] dirCol = {2, 1, -1, -2, -2, -1,  1,  2};

	private int lin, col, uDir;

	public Elemento(int l, int c, int u){

		lin = l;
		col = c;
		uDir = u;
	}

	public int getLin(){

		return lin;
	}

	public int getCol(){

		return col;
	}

	public int getUltimaDirecao(){

		return uDir;
	}

	public void setUltimaDirecao(int d){

		uDir = d;
	}

	public Elemento move(int direcao){

		int i = getLin() + dirLin[direcao];
		int j = getCol() + dirCol[direcao];

		return new Elemento(i, j, -1);
	}

	public boolean verifica(int [][] t){

		try{
			if(t[getLin()][getCol()] == 0) return true;
		}
		catch(Exception e){
		}

		return false;
	}

	private char translate(int x){

		return (char)(x + 'A');
	}

	public String toString(){
	
		return "(" + translate(getCol()) + ", " + (getLin() + 1) + ", " + getUltimaDirecao() + ")";
	}
}

class Pilha {

	private Elemento [] elementos;
	private int livre;

	private int num_adds = 0;
	private int num_remove = 0;

	public Pilha(int max){

		elementos = new Elemento[max];
		livre = 0;
	}

	public int tamanho(){

		return livre;
	}

	public boolean vazia(){

		return tamanho() == 0;
	}
	
	public void adiciona(Elemento e){

		elementos[livre] = e;
		livre++;

		num_adds++;
	}

	public Elemento remove(){

		Elemento e = elementos[livre - 1];
		livre--;
		num_remove++;

		return e;
	}

	public Elemento topo(){

		return elementos[livre - 1];
	}

	public void print(){

		print(false);
	}

	public void print(boolean force){

		if(Passeio.debug || force){

			System.out.println("Pilha (adicoes = " + num_adds + ", remocoes = " + num_remove + "):");
			System.out.println();

			for(int i = livre - 1; i >= 0; i--){

				System.out.println("  " + elementos[i]);
			}
			System.out.println();
		}
	}
}

public class Passeio {

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

			System.out.println("Tabuleiro:");
			System.out.println();

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

			System.out.println("------------------------------------------");
		}
	}

	public static void passeio(int [][] t, int i_lin, int i_col){

		int passo = 1;
		int nCasas = contaCasas(t);
		
		Pilha p = new Pilha(nCasas);		
		p.adiciona(new Elemento(i_lin, i_col, -1));
		
		t[p.topo().getLin()][p.topo().getCol()] = passo;
		passo++;

		p.print();
		imprime(t);

		while(!p.vazia()){

			if(p.tamanho() == nCasas){

				System.out.println("Achou passeio!");
				p.print(true);				
				imprime(t, true);
				return;
			}
			else {
			
				boolean moveu = false;

				for(int dir = p.topo().getUltimaDirecao() + 1; dir < 8; dir++){

					Elemento novo = p.topo().move(dir);
					
					if(novo.verifica(t)){
	
						p.topo().setUltimaDirecao(dir);
						p.adiciona(novo);
						t[p.topo().getLin()][p.topo().getCol()] = passo;
						passo++;

						p.print();
						imprime(t);

						moveu = true;
						break;
					}
				}

				if(!moveu){
			
					t[p.topo().getLin()][p.topo().getCol()] = 0;
					passo--;
					p.remove();

					p.print();
					imprime(t);
				}
			}
		}

		System.out.println("Passeio não encontrado!");
		p.print();
		

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
		Passeio.debug = Boolean.parseBoolean(args[1]);
		Passeio.passo_a_passo = Boolean.parseBoolean(args[2]); 

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

		if(option == 0) passeio(t0, 3, 0);
		if(option == 1) passeio(t1, 0, 0);
		if(option == 2) passeio(t2, 1, 0);
		if(option == 3) passeio(t3, 0, 0);
	}
}
