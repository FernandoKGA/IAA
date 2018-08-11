import java.util.Scanner;
public class aula1209edn{
	
	public static void main(String[] args){
		Scanner ouch = new Scanner(System.in);
		System.out.println("------Analise de Algoritmos------");
		System.out.println("  Prever o consumo de RECURSOS");
		System.out.println("  para a execucao do algoritmo.\n");
		System.out.println("-Tempo\n-Memoria\n-Numero de acessos a disco(''AED'')\n-Banda de comunicacao\n");
		String s = 
	}
}


/*
public static int max(int [] a){
	int max = a[0];              // 1
	int i=1;                          // 1
	while(i<a.length){         //n                  É igual ao tamanho do vetor pois ele precisa fazer a última verificação.
		if(a[i]>max){             //n-1
			max = a[i];            // 0<=x<=n-1        Pode nunca entrar, pode entrar algumas vezes, pode entrar todas as vezes.
		}
		i++;                          //n-1
	}
	return max;                   // 1
}
*/
/*
Tempo desse código, depende de quantas coisas ele faz. (abstrato)
Cada linha é um passo.
T(n) daquela função é:   /////   T(n) = (somatório (começando em i)) custo(i) * repetições(i);  \\\\
C1,2,3,4,5,6,7 (são de custo "idealmente" fixos); (c é simplifcação de constante), podem ser de tempos diferentes.
Verificar se uma linha modifica o tempo de execução. 
Instância de problema.

T(n) = c1+c2+c3*n+c4*(n-1)+c5*x+c6*(n-1)+c7;
T(n) = (c3+c4+c6)*n + (c1+c2-c4-c6+c7)+c5*x;
Verificar o x, onde o algoritmo executa os extremos do algoritmo.

0 <= x <= (n-1)

Melhor caso(x = 0)
Tm(n) = (c3+c4+c6)*n+(c1+c2-c4-c6+c7)                     Tm(n) = a*n + b

Pior caso(x=n-1)
Tp(n) = (c3+c4+c6)*n+(c1+c2-c4-c5-c6+c7)                  Tp(n) = a' * n + b'

Caso médio(x=x')
x' = (0+1+2+3+...+(n-1))/n-1                     P.A. todos os valores poderiam acontecer com a mesma chance. E calcula a média entre esses valores e divide entre o numero de possibilidades.
x' = (n(n-1))/2 * 1/n = (n-1)/2
Tmed(n) = (c3+c4+c5/2+c6)*n + (c1+c2-c4-c5/2-c6+c7)             Tmed(n)= a" * n + b"

Geral  = T(n) = a*n+b

Pior caso é o mais útil na maioria das vezes.
Melhor caso é para argumentar se um algoritmo é ruim, o quão ruim ele é.
Caso médio é o mais interessante na prática por ser uma média(porém é difícil de calcular muitas vezes).

Se Tm, Tp, Tmed tem comportamentos muito parecidos (funções lineares em n).
*/

/*1) Verificar o custo de cada linha (Ci)
   2) Repetições de cada linha.
	
	Simplicações já adotadas:
	1)Custos abstratos(Ci);
	2)Agrupamento dos custos (a = (c3+c4+c6) ; b=(c1+c2-c4-c5-c6+c7); )
	3)Desprezar os termos de menor ordem.      T(n) ~ a*n    (menos b contribui)
	Como ela se relaciona com o tempo, como ela cresce?
	4)Desprezar o coeficiente do termo de maior ordem.     T(n) = ¢(n)           ¢ = "teta (letra grega), 'cara' da função(em relação ao crescimento)";
	
	¢ é uma notação assintótica que caracteriza a "cara de uma função quanto ao crescimento." 
	
	T(n) = ¢(n) -> Determinou a complexidade assintótica do algoritmo.
	
	Ta(n) = 1000*n;        Ao aumentar do n, um ponto de ruptura ocorre, mesmo que o tamanho seja grande.
	Tb(n) = n²/1000;
	
*/


/*
	public static boolean verifica(int[] a, int x){
		int i=0;                                         C1  // 1
		while(i<a.length){						C2  // 1 <= x <= (n+1)
			if(a[i] == x){							C3  // 1 <= y <= n
				return true;							Cret  // 1 or 0
			}
			i++;											C5  // 0 <= z <= n
		}
		return false;								Cret  // 1 or 0
	}
	
	
	T(n) = C1+C2*x+C3*y+C5*z+Cret
	
	Melhor caso (x = 1; y = 1, z = 0)
	Tm(n) = C1+C2+C3+Cret
	Tm(n) = K                //Função é constante.
	Tm(n) = ¢(1)
			  = ¢(73)
	No melhor caso, ele independe do tamanho do problema.
	
	Pior caso (x = n+1, y = n, z = n)
	Tp(n) = (C2+C3+C5)*n + (C1+C2+Cret)
	Tp(n) = K1 * n + K2
	Tp(n) = ¢(n)
	
	Comportamento diferente no melhor caso em relação ao pior caso, usar o caso médio.
	
	x' ~ n/2 , y' ~ n/2 , z' ~ n/2
	Tmed(n) ~ ((C2+C3+C5)*n)/2 + (C1+Cret)
	Tmed(n) ~ K1' * n + K2' ~ K1' * n
	Tmed(n) = ¢(n)
	
	Pior caso e caso médio são similares geralmente.
	
	Tm(n) = ¢(1)
	Tp(n) = ¢(n)
	Tmed(n) = ¢(n)
	
	Errado afirmar tanto T(n) = ¢(n) quanto T(n) = ¢(n)
	
	T(n) = Ô(1)    //Limite inferior de crescimento.
	T(n) = O(n)    //Limite superior de crescimento.
*/