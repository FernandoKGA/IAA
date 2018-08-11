public class Cenario {

	private char[][] mapa;
	private char[][] mapaAtual;

	private char objetivo = '-';
	private char bloqueio;
	private char trilha;
	private Personagem personagem;

	public Cenario(char[][] mapa, char bloqueio, char trilha, Personagem personagem) {
		this.mapa = mapa;
		this.bloqueio = bloqueio;
		this.trilha = trilha;
		this.personagem = personagem;
		inicializarMapa();
	}

	public boolean inicializarMapa() {
		if(this.mapa.length == 0) return false;
		this.mapaAtual = new char[this.mapa.length][this.mapa[0].length];

		for(int i = 0; i < this.mapa.length; i++) {
			for(int j = 0; j < this.mapa[i].length; j++) {
				this.mapaAtual[i][j] = this.mapa[i][j];
			}
		}
		return true;
	}

	public boolean inserePersonagem() {
		if(this.mapa.length == 0) return false;
		if(this.personagem.posicaoAtual[0] < this.mapa.length && this.personagem.posicaoAtual[1] < this.mapa[0].length) {
			if(this.mapa[personagem.posicaoAtual[0]][personagem.posicaoAtual[1]] != this.bloqueio) {
				this.mapaAtual[personagem.posicaoAtual[0]][personagem.posicaoAtual[1]] = personagem.caracter;
				return true;
			}
		}
		return false;
	}

	private boolean move(Personagem personagem, int x, int y) {
		if(this.mapa.length == 0) return false;
		if(personagem.posicaoAtual[0] + x < this.mapa.length && personagem.posicaoAtual[1] + y < this.mapa[0].length) {
			if(personagem.posicaoAtual[0] + x >= 0 && personagem.posicaoAtual[1] + y >= 0) {
				if(this.mapa[personagem.posicaoAtual[0] + x][personagem.posicaoAtual[1] + y] != this.bloqueio) {
					// Atualiza o mapa e as coordenadas da personagem
					this.mapaAtual[personagem.posicaoAtual[0]][personagem.posicaoAtual[1]] = this.mapa[personagem.posicaoAtual[0]][personagem.posicaoAtual[1]];
					personagem.posicaoAtual[0] += x;
					personagem.posicaoAtual[1] += y;
					this.mapaAtual[personagem.posicaoAtual[0]][personagem.posicaoAtual[1]] = personagem.caracter;
					return true;
				}
			}
		}
		return false;
	}

	public boolean movimento(Personagem personagem, char direcao) {
		switch(direcao) {
			case 'c':
				return move(personagem, -1, 0);

			case 'b':
				return move(personagem, 1, 0);

			case 'd':
				return move(personagem, 0, 1);

			case 'e':
				return move(personagem, 0, -1);	

			default:
				return false;
		}
	}

	public boolean chegou() {
		return (this.mapa[this.personagem.posicaoAtual[0]][this.personagem.posicaoAtual[1]] == this.objetivo);
	}

	public void imprimeMapa() {
		if(this.mapa.length < 0) return;
		System.out.print("\033[32m");
		for(int i = 0; i < this.mapa.length; i++) {
			for(int j = 0; j < this.mapa[0].length; j++) {
				if(i == this.personagem.posicaoAtual[0] && j == this.personagem.posicaoAtual[1]) {
					System.out.print("\033[31m");
					System.out.print(mapa[i][j]);
					System.out.print("\033[32m");
				}else System.out.print(mapa[i][j]);
			}
			System.out.println();
		}
	}

	public void imprimeMapaAtual() {
		if(this.mapa.length < 0) return;
		for(int i = 0; i < this.mapa.length; i++) {
			System.out.print("\033[32m");
			for(int j = 0; j < this.mapa[0].length; j++) {
				if(i == this.personagem.posicaoAtual[0] && j == this.personagem.posicaoAtual[1]) {
					System.out.print("\033[31m");
					System.out.print(mapaAtual[i][j]);
					System.out.print("\033[32m");
				}else System.out.print(mapaAtual[i][j]);
			}
			System.out.println();
		}
	}
}
