public class Personagem {
	char caracter;
	String nome;
	int[] posicaoAtual;

	public Personagem(char caracter, String nome, int[] posicaoAtual) {
		if(posicaoAtual.length == 2) {
			this.caracter = caracter;
			this.nome = nome;
			this.posicaoAtual = new int[2];
			this.posicaoAtual[0] = posicaoAtual[0];
			this.posicaoAtual[1] = posicaoAtual[1];
		}
	}
}
