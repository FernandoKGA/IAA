/*
------------------------------------------------------
Nome: Fernando Karchiloff Gouveia de Amorim
Numero USP: 10387644
Materia: ACH2002 - Introducao a Analise de Algoritmos
Turma: 04
E-mail: fernando.kga@usp.br
------------------------------------------------------
*/

public class Aventureiro{
	
	//Variaveis para ajudar no labirinto
	public int melhorCaminho[][];
	public int bagagem[][];
	public int valorBagagem;

	int mochila;
	int qtdItens;
	int valorTotal;
	int pesoTotal;
	int linha;
	int coluna;

	//Construtor
	Aventureiro(int mochila, int qtdItens, int valorTotal, int pesoTotal, int linha, int coluna){
		this.mochila = mochila;
		this.qtdItens = qtdItens;
		this.valorTotal = valorTotal;
		this.pesoTotal = pesoTotal;
		this.linha = linha;
		this.coluna = coluna;
	}

	//Setters
	void setLinha(int linha){
		this.linha = linha;
	}
	void setColuna(int coluna){
		this.coluna = coluna;
	}
	void setQtdItem(int qtdItens){
		this.qtdItens = qtdItens;
	}
	void setValorTotal(int valorTotal){
		this.valorTotal = valorTotal;
	}
	void setPesoTotal(int pesoTotal){
		this.pesoTotal = pesoTotal;
	}

	//Getters
	int getLinha(){
		return this.linha;
	}
	int getColuna(){
		return this.coluna;
	}
	int getQtdItem(){
		return this.qtdItens;
	}
	int getValorTotal(){
		return this.valorTotal;
	}
	int getPesoTotal(){
		return this.pesoTotal;
	}
	
}
