/*
------------------------------------------------------
Nome: Fernando Karchiloff Gouveia de Amorim
Numero USP: 10387644
Materia: ACH2002 - Introducao a Analise de Algoritmos
Turma: 04
E-mail: fernando.kga@usp.br
------------------------------------------------------
*/

public class Item{
    int val;
    int peso;
    
    //Construtor
    Item(int val, int peso){
        this.val = val;
        this.peso = peso;
    }
    //Getters
    int getVal(){
        return this.val;
    }
    int getPeso(){
        return this.peso;
    }
}