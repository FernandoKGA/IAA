/*
------------------------------------------------------
Nome: Fernando Karchiloff Gouveia de Amorim
Numero USP: 10387644
Materia: ACH2002 - Introducao a Analise de Algoritmos
Turma: 04
E-mail: fernando.kga@usp.br
------------------------------------------------------
*/

public class Posicao{
	
	boolean andou = false;
	boolean temItem = false;
	char caracter;
	Item obj;

	//Construtor
	Posicao(char caracter){
		this.caracter = caracter;
	}
	
	//Setters
	void setItem(Item obj){   //Insere um item na posicao.
		this.obj = obj;
		this.temItem = true;
	}
	void setCarac(char caracter){
		this.caracter = caracter;
	}
	void setAndou(boolean andou){
		this.andou = andou;
	}

	//Getters
	boolean getAndou(){
		return this.andou;
	}
	boolean getItem(){
		return this.temItem;
	}
	char getCaracter(){
		return this.caracter;
	}
	int getPeso(){
		return this.obj.peso;
	}
	int getValor(){
		return this.obj.val;
	}
}