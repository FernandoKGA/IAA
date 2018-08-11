/*
------------------------------------------------------
Nome: Fernando Karchiloff Gouveia de Amorim
Numero USP: 10387644
Materia: ACH2002 - Introducao a Analise de Algoritmos
Turma: 04
E-mail: fernando.kga@usp.br
------------------------------------------------------
*/

/*
LINKS USADOS DEVIDO A DUVIDAS:

1-	https://stackoverflow.com/questions/19788989/error-unreported-exception-filenotfoundexception-must-be-caught-or-declared-to
2-	https://www.devmedia.com.br/lendo-dados-de-txt-com-java/23221
3-	http://www.guj.com.br/t/limpar-console-resolvido/56259
4-	https://www.tutorialspoint.com/java/io/java_io_exceptions.htm
5-	https://pt.stackoverflow.com/questions/17025/usando-as-palavras-chave-throws-e-throw
6-  https://stackoverflow.com/questions/1388602/do-i-need-to-close-both-filereader-and-bufferedreader
7-  https://stackoverflow.com/questions/11589302/why-is-throws-exception-necessary-when-calling-a-function
8-  https://stackoverflow.com/questions/25962939/how-do-i-ensure-that-scanner-hasnextint-asks-for-new-input
9-  https://www.devmedia.com.br/copiando-o-conteudo-de-um-array-em-java/26732
10- https://www.devmedia.com.br/explorando-a-classe-arraylist-no-java/24298
11- https://www.tuturself.com/posts/view?menuId=98&postId=55
12- https://stackoverflow.com/questions/4450628/arraylist-how-does-the-size-increase
13- https://stackoverflow.com/questions/197986/what-causes-javac-to-issue-the-uses-unchecked-or-unsafe-operations-warning
14- https://docs.oracle.com/javase/7/docs/api/java/util/ArrayList.html
15- http://www.guj.com.br/t/como-faco-pra-pausar-a-execucao/32986/4
*/

//--------------IMPORTS-------------------
import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;
//---------------CLASS--------------------
public class EP{

	//-----------------------VARIAVEIS GLOBAIS----------------------------

	public static Posicao[][] matrizCaminho;  //Matriz global para ser utilizada pelos outros metodos.
    public static int mochila;  //Variavel para mochila.
    public static int[] inicio;  //Coordenadas do comeco.
    public static int[] fim;   //Coordenadas do fim.
    public static ArrayList<int[]> lista = new ArrayList<int[]>();
    public static ArrayList<int[]> bag = new ArrayList<int[]>();
    
    //-------------------------PREENCHIMENTOS-----------------------------

	//PREENCHE MATRIZ PARA CAMINHO/LABIRINTO   (GAMBIARRA EM EXECECAO!!! CUIDADO!!!)
	public static String preencheMatriz(BufferedReader lerArquivo) throws IOException{
        String segLin = null;
		segLin = lerArquivo.readLine();
		if(segLin.length() == matrizCaminho[0].length){  //Verificador para impedir erros.
			for(int g=0;g<matrizCaminho.length;g++){
				if(segLin.length() != matrizCaminho[0].length){
						System.out.println("\nQuantidade de caracteres diferente do tamanho da matriz. / ERRO NA LEITURA.\n(Verifique seu file!!!).\n");
						System.out.println("\nVerifique formatacao do arquivo.\n");
						System.out.println("Encerrando programa...\n");
						System.exit(1);
				}
				else{
                    //Coloca os caracteres na matriz.
					for(int j=0;j<matrizCaminho[0].length;j++){
						Posicao pos = new Posicao(segLin.charAt(j));
                        matrizCaminho[g][j] = pos;
                    }	
					segLin = lerArquivo.readLine();
				}
			}
			return segLin;   //Retorna ultima string com numeros  (Poderia ser ignorada...)
		}
		else{
			System.out.println("\nQuantidade de caracteres diferente do tamanho da matriz. / ERRO NA LEITURA.\n(Verifique seu file!!!).\n");
			System.out.println("\nVerifique formatacao do arquivo.\n");
			System.out.println("Encerrando programa...\n");
			System.exit(1);
		}
		return segLin;   //Retorna ultima string com numero (Poderia ser ignorada...)
	}
    
    
    //FUNCAO QUE COLOCA OS ITENS NA MATRIZ
	public static void preencheItens(BufferedReader lerArquivo, String tercLin){
        try{
            Scanner kaf = new Scanner(tercLin);
            int qtd = kaf.nextInt();
            boolean first = kaf.hasNextInt();
            if((qtd >= 1) && (first == false)){   //Verificar se nao eh linha de item.
                for(int i=0;i<qtd;i++){
                    tercLin = lerArquivo.readLine();
                    if(tercLin.length() == 7){       //Especificacoes dos itens
                        Scanner sc = new Scanner(tercLin);
                        int lin = sc.nextInt();
                        int col = sc.nextInt();
                        int val = sc.nextInt();
                        int peso = sc.nextInt();
                        Item it = new Item(val, peso);
                        matrizCaminho[lin][col].setItem(it);   //Coloca o item na posicao da matriz.
                    }
                    else{
                        System.out.println("\nLinha invalida de item para leitura!!! Encerrando programa...\n(Verifique seu file .txt!!!)\n");
                        System.out.println("\nVerifique formatacao do arquivo.\n");
                        System.out.println("Encerrando programa...\n");
                        System.exit(1);
                    }
                }
            }
            else{
                System.out.println("\nTamanho da entrada excede limite!\nVerifique o seu file .txt!!!\n");
                System.out.println("\nVerifique formatacao do arquivo.\n");
                System.out.println("Encerrando programa...\n");
                System.exit(1);
            }
        }
        catch(IOException e){
            System.out.println(e.getMessage());
        }
	}
    

    //COLOCA EM ARRANJOS O INICIO E O FIM QUE O AVENTUREIRO DEVE FAZER. 
    public static void preencheInicioFim(BufferedReader lerArquivo){
        try{
            String partida = lerArquivo.readLine();
            Scanner kafka = new Scanner(partida);
            int[] in = new int[2];
            in[0] = kafka.nextInt();
            in[1] = kafka.nextInt();
            if(((in[0] >= 0) && (in[0] <= matrizCaminho.length-1)) && ((in[1] >= 0) && (in[1] <= matrizCaminho[0].length-1))){  //Impedir valores negativos e invalidos.
            	if(matrizCaminho[in[0]][in[1]].getCaracter() == '.'){
            		inicio = in.clone();   //Atribui a variavel global de inicio
            		String chegada = lerArquivo.readLine();
            		kafka = new Scanner(chegada);
            		in[0] = kafka.nextInt();
            		in[1] = kafka.nextInt();
            		if(((in[0] >= 0) && (in[0] <= matrizCaminho.length-1)) && ((in[1] >= 0) && (in[1] <= matrizCaminho[0].length-1))){
            			if(matrizCaminho[in[0]][in[1]].getCaracter() == '.'){
            				fim = in.clone();  //Atribui a variavel global de fim.
            			}
            			else{
            				System.out.println("\nVoce esta terminando em uma posicao bloqueada!\nTente modificar sua chegada no file .txt!!!");
            				System.out.println("\nEncerrando programa...\n");
            				System.exit(1);
            			}
            		}
            		else{
            			System.out.println("\nParametros com valores negativos/invalidos!!!\n(Verifique seu file .txt!!!)");
            			System.out.println("\nEncerrando programa...\n");
            			System.exit(1);
            		}
            	}
            	else{
            		System.out.println("\nVoce esta iniciando em uma posicao bloqueada!\nTente modificar seu inicio no file .txt!!!");
            		System.out.println("\nEncerrando programa...\n");
            		System.exit(1);
            	}
            }
            else{
            	System.out.println("\nParametros com valores negativos/invalidos!!!\n(Verifique seu file .txt!!!)");
            	System.out.println("\nEncerrando programa...\n");
            	System.exit(1);
            }
        }
        catch(IOException e){
            System.out.println(e.getMessage());
        }
    }


	//FUNCAO QUE LE O ARQUIVO DE TEXTO E PREENCHE OS CAMPOS NECESSARIOS.
	public static void lerArq(BufferedReader lerArquivo){  //Verificar saida.

		try{
			String primLin = lerArquivo.readLine();   //Le soh a primeira linha.    
            Scanner sc = new Scanner(primLin);
            int[] m = new int[2];
            m[0] = sc.nextInt();
            m[1] = sc.nextInt();
            if((m[0] >= 1) && (m[1] >= 1)){ //Valores precisam ser maiores ou iguais que 1.
                Posicao[][] matriz = new Posicao[m[0]][m[1]]; //Cria a matriz com base na primeira linha.
                matrizCaminho = matriz;
                String num = preencheMatriz(lerArquivo);   //Preenche a matriz com caracteres.
                sc = new Scanner(num);
                if(sc.hasNextInt()){
                    preencheItens(lerArquivo,num);    //Insere itens na matriz.
                }
                String mo = lerArquivo.readLine(); //String para pegar tamanho da mochila.
                mochila = Integer.parseInt(mo);  //Atribui tamanho da mochila na var. global.
                preencheInicioFim(lerArquivo);  //Coloca o inicio e fim do caminho.
            }
            else{
                System.out.println("\nEntradas para matriz incorretas, por favor verificar seu .txt!!!");
                System.out.println("\nVerifique formatacao do arquivo.\n");
                System.out.println("Encerrando programa...\n");
                System.exit(1);
            }
		}
		catch (IOException e){
			System.out.println(e.getMessage()); //Pega mensagem da excecao.
		}
	}


	//-----------------------------CAMINHOS----------------------------

	/*
	Caminho mais curto - 1;

	Caminho que maximize o valor dos itens coletados, sem restricao quanto a carga
	maxima que o explorador pode levar - 2;

	Caminho que maximize o valor dos itens coletados, considerando que existe um
	limite de peso maximo que o explorador pode carregar em sua mochila - 3;
	*/

	//-----------------------------------------------------------------

	//===================== CAMINHO MAIS CURTO - 1 ====================

	//VERIFICADOR DE PASSOS UM 
	public static boolean verificaPassoUm(int linR,int colR){
		if(((linR >= 0) && (linR <= matrizCaminho.length-1)) && ((colR >= 0) && (colR <= matrizCaminho[0].length-1))){   //Impede que acesse lugar inexistente.
			//if((matrizCaminho[linR][colR].getAndou() == false)){    //Se nao andou, verifica caracter.
				if(matrizCaminho[linR][colR].getCaracter() == 'X'){ 
					//matrizCaminho[linR][colR].setAndou(true);
					return false;
				}
				else{
					if (matrizCaminho[linR][colR].getCaracter() == '.') return true;
				}
			//}
		}
		return false;
	}
	//LABIRINTO DO CAMINHO SELECIONADO.
	public static void labirintoUm(Aventureiro fer, int linR, int colR){
		//int num = 0;
		if(((linR >= 0) && (linR <= matrizCaminho.length-1)) && ((colR >= 0) && (colR <= matrizCaminho[0].length-1))){  //Condicoes do mapa.
			if((matrizCaminho[linR][colR].getAndou() == false)){    //Se nao andou pela posicao ao qual chegou.
				
				//Cria coordenadas.
				int[] coord = new int[2];
				coord[0] = linR;
				coord[1] = colR;

				//Adiciona coordenadas na lista.
				lista.add(coord);
				fer.setLinha(linR);
				fer.setColuna(colR);

				//Verifica se tem item.
				if(matrizCaminho[linR][colR].temItem == true){
					//Cria coordenada do item.
					int[] itensBag = new int[2];
					itensBag[0] = linR;
					itensBag[1] = colR;

					//Guarda na mochila as coordenadas.
					bag.add(itensBag);
					fer.setQtdItem(fer.getQtdItem()+1);  //Adiciona em 1 a quantidade de itens.
					fer.setValorTotal(fer.getValorTotal()+matrizCaminho[linR][colR].getValor());  //Adiciona valor do item ao valor total.
					fer.setPesoTotal(fer.getPesoTotal()+matrizCaminho[linR][colR].getPeso());  //Adiciona o peso do item ao peso total do aventureiro.
				}
				
				//Define que andou pela posicao.
				matrizCaminho[linR][colR].setAndou(true);

				if((fer.getLinha() == fim[0]) && (fer.getColuna() == fim[1])) { //Se esta na casa desejada.
					if(fer.melhorCaminho == null || lista.size() < fer.melhorCaminho.length) {  //Se a lista de caminho esta vazia ou o caminho achado é menor que o anterior.
						fer.melhorCaminho = new int[lista.size()][2];   //Cria um novo melhorCaminho com o tamanho do atual.
						fer.bagagem = new int[bag.size()][2]; //Cria um novo array de bagagem.
						for(int i = 0; i < lista.size(); i++) {     //For para injetar no melhorCaminho.
							int temp[] = lista.get(i);
							fer.melhorCaminho[i][0] = temp[0];       //Coordenadas do caminho.
							fer.melhorCaminho[i][1] = temp[1];
							//System.out.println(fer.melhorCaminho[i][0] + ", " + fer.melhorCaminho[i][1]);
						}
						for(int i = 0; i < bag.size();i++){   //For para injetar na bagagem.
							int aux[] = bag.get(i);
							fer.bagagem[i][0] = aux[0];  //Coordenadas item.
							fer.bagagem[i][1] = aux[1];
						}
					}
				
					return;  //Forca a volta.
				}
				else {
					//Tentar continuar avançando ate chegar.
					//Se nao eh igual, executa.
					if(verificaPassoUm(linR-1,colR)){   //Sobe
						/*int draven = */labirintoUm(fer,linR-1,colR);
						//if(draven >=1) return draven+1;
					}	
					if(verificaPassoUm(linR,colR+1)){  //Direita
						/*int karma = */labirintoUm(fer,linR,colR+1);
						//if(karma >=1) return karma+1;
					}	
					if(verificaPassoUm(linR,colR-1)){   //Esquerda
						/*int kayle = */labirintoUm(fer,linR,colR-1);
						//if(kayle >=1) return kayle+1;
					}
					if(verificaPassoUm(linR+1,colR)){   //Desce
						/*int nasus = */labirintoUm(fer,linR+1,colR);
						//if(nasus >=1) return nasus+1;
					}
					
					//Se nenhum der certo, tira a posição do array de print.
					lista.remove(lista.size()-1);

					//Verifica se a posicao tem item.
					if(matrizCaminho[linR][colR].temItem == true){
						bag.remove(bag.size()-1);  //Retira o item da lista.
						fer.setQtdItem(fer.getQtdItem()-1);   //Remove 1 em quantidade.
						fer.setValorTotal(fer.getValorTotal()-matrizCaminho[linR][colR].getValor());  //Retira valor do item ao valor total.
						fer.setPesoTotal(fer.getPesoTotal()-matrizCaminho[linR][colR].getPeso());  //Retira o peso do item do peso total do aventureiro.
					}

					//Desmarca que andou para poder fazer outro caminho se necessario.
					matrizCaminho[linR][colR].setAndou(false);

					//return num;
				}
			}
		}
		//return num;
	}

	//FUNCAO DE ENTRADA DO TIPO DE CAMINHO.
	public static void caminhoUm(){
		Aventureiro fer = new Aventureiro(mochila,0,0,0,inicio[0],inicio[1]);
		/*int tamCaminho = */labirintoUm(fer,fer.getLinha(),fer.getColuna());
		//if(tamCaminho > 0){
		if(fer.melhorCaminho != null && fer.melhorCaminho.length > 0) {   //Se existe melhor caminho e eh maior que 0.
			System.out.println(fer.melhorCaminho.length);  //Tamanho total do caminho.
			for(int i=0;i<fer.melhorCaminho.length;i++){
				System.out.println(fer.melhorCaminho[i][0]+" "+fer.melhorCaminho[i][1]);   //Printa o caminho.
			}
			System.out.println(fer.getQtdItem()+" "+fer.getValorTotal()+" "+fer.getPesoTotal());  //Status do aventureiro.
			
			//Printa o tamanho de itens.
			if(fer.getQtdItem() > 0){   //Se tem item.
				for(int i=0;i<fer.bagagem.length;i++){
					System.out.println(fer.bagagem[i][0]+" "+fer.bagagem[i][1]);   //Printa a coordenada do item.
				}
			}
		}
		else{
			System.out.println("\nNao tem caminho possivel.");
			System.out.println("Encerrando programa...\n");
			System.exit(1);
		}
	}
	//=================================================================


	//============= CAMINHO COM MAIOR VALOR DE ITENS - 2 ==============

	//VERIFICADOR DE PASSOS DOIS
	public static boolean verificaPassoDois(int linR,int colR){
		if(((linR >= 0) && (linR <= matrizCaminho.length-1)) && ((colR >= 0) && (colR <= matrizCaminho[0].length-1))){   //Impede que acesse lugar inexistente.
			//if((matrizCaminho[linR][colR].getAndou() == false)){    //Se nao andou, verifica caracter.
				if(matrizCaminho[linR][colR].getCaracter() == 'X'){ 
					//matrizCaminho[linR][colR].setAndou(true);
					return false;
				}
				else{
					if (matrizCaminho[linR][colR].getCaracter() == '.') return true;
				}
			//}
		}
		return false;
	}

	//LABIRINTO DO CAMINHO SELECIONADO.
	public static void labirintoDois(Aventureiro fer, int linR, int colR){
		//int num = 0;
		if(((linR >= 0) && (linR <= matrizCaminho.length-1)) && ((colR >= 0) && (colR <= matrizCaminho[0].length-1))){  //Condicoes do mapa.
			if((matrizCaminho[linR][colR].getAndou() == false)){    //Se nao andou pela posicao ao qual chegou.
				
				//Cria coordenadas.
				int[] coord = new int[2];
				coord[0] = linR;
				coord[1] = colR;

				//Adiciona coordenadas na lista.
				lista.add(coord);
				fer.setLinha(linR);
				fer.setColuna(colR);

				//Verifica se tem item.
				if(matrizCaminho[linR][colR].temItem == true){

					//Cria coordenada do item e valor dele.
					int[] itensBag = new int[3];
					itensBag[0] = linR;
					itensBag[1] = colR;
					itensBag[2] = matrizCaminho[linR][colR].getValor();

					//Guarda na mochila as coordenadas.
					bag.add(itensBag);
					fer.setQtdItem(fer.getQtdItem()+1);  //Adiciona em 1 a quantidade de itens.
					fer.setValorTotal(fer.getValorTotal()+matrizCaminho[linR][colR].getValor());  //Adiciona valor do item ao valor total.
					fer.setPesoTotal(fer.getPesoTotal()+matrizCaminho[linR][colR].getPeso());  //Adiciona o peso do item ao peso total do aventureiro.
				}
				
				//Define que andou pela posicao.
				matrizCaminho[linR][colR].setAndou(true);

				if((fer.getLinha() == fim[0]) && (fer.getColuna() == fim[1])) { //Se esta na casa desejada.
					if(fer.bagagem == null || fer.getValorTotal() > fer.valorBagagem) {  //Se a lista de caminho esta vazia ou o caminho achado é menor que o anterior.
						
						fer.melhorCaminho = new int[lista.size()][2];   //Cria um novo melhorCaminho com o tamanho do atual.
						fer.bagagem = new int[bag.size()][2]; //Cria um novo array de bagagem.
						fer.valorBagagem = 0;  //Deve zerar sempre que encontrar caminho com quantidade de itens de valor maior.

						//For para injetar no melhorCaminho.
						for(int i = 0; i < lista.size(); i++) {     
							int temp[] = lista.get(i);
							fer.melhorCaminho[i][0] = temp[0];       //Coordenadas do caminho.
							fer.melhorCaminho[i][1] = temp[1];
							//System.out.println(fer.melhorCaminho[i][0] + ", " + fer.melhorCaminho[i][1]);
						}

						//For para injetar na bagagem.
						for(int i = 0; i < bag.size();i++){   
							int aux[] = bag.get(i);
							fer.bagagem[i][0] = aux[0];  //Coordenadas item.
							fer.bagagem[i][1] = aux[1];
							fer.valorBagagem += aux[2]; //Valor item.
						}
					}
					return;  //Forca a volta.
				}
				else{
					//Tentar continuar avançando ate chegar.
					//Se nao eh igual, executa.
					//if(verificaPasso(linR-1,colR)){   //Sobe
					if(verificaPassoDois(linR-1,colR)){   //Sobe
						/*int draven = */labirintoDois(fer,linR-1,colR);
						//if(draven >=1) return draven+1;
					}	
					//if(verificaPasso(linR,colR+1)){  //Direita
					if(verificaPassoDois(linR,colR+1)){  //Direita
						/*int karma = */labirintoDois(fer,linR,colR+1);
						//if(karma >=1) return karma+1;
					}	
					//if(verificaPasso(linR,colR-1)){   //Esquerda
					if(verificaPassoDois(linR,colR-1)){   //Esquerda
						/*int kayle = */labirintoDois(fer,linR,colR-1);
						//if(kayle >=1) return kayle+1;
					}
					//if(verificaPasso(linR+1,colR)){   //Desce
					if(verificaPassoDois(linR+1,colR)){   //Desce
						/*int nasus = */labirintoDois(fer,linR+1,colR);
						//if(nasus >=1) return nasus+1;
					}
					
					//Se nenhum der certo, tira a posição do array de print.
					lista.remove(lista.size()-1);

					//Verifica se a posicao tem item.
					if(matrizCaminho[linR][colR].temItem == true){
						bag.remove(bag.size()-1);  //Retira o item da lista.
						fer.setQtdItem(fer.getQtdItem()-1);   //Remove 1 em quantidade.
						fer.setValorTotal(fer.getValorTotal()-matrizCaminho[linR][colR].getValor());  //Retira valor do item ao valor total.
						fer.setPesoTotal(fer.getPesoTotal()-matrizCaminho[linR][colR].getPeso());  //Retira o peso do item do peso total do aventureiro.
						fer.valorBagagem = fer.valorBagagem - matrizCaminho[linR][colR].getValor();
					}

					//Desmarca que andou para poder fazer outro caminho se necessario.
					matrizCaminho[linR][colR].setAndou(false);

					//return num;
				}
			}
		}
		//return num;
	}

	//FUNCAO DE ENTRADA DO TIPO DO CAMINHO.
	public static void caminhoDois(){
		Aventureiro fer = new Aventureiro(mochila,0,0,0,inicio[0],inicio[1]);
		/*int tamCaminho = */labirintoDois(fer,fer.getLinha(),fer.getColuna());
		/*if(tamCaminho > 0){
			System.out.println(tamCaminho);
			for(int i=0;i<lista.size();i++){
				int[] coord = lista.get(i);
				System.out.println(coord[0]+" "+coord[1]);
			}
			System.out.println(fer.getQtdItem()+" "+fer.getValorTotal()+" "+fer.getPesoTotal());
			//Printa arrayList
		}*/
		if(fer.melhorCaminho != null && fer.melhorCaminho.length > 0) {   //Se existe melhor caminho e eh maior que 0.
			System.out.println(fer.melhorCaminho.length);
			for(int i=0;i<fer.melhorCaminho.length;i++){
				System.out.println(fer.melhorCaminho[i][0]+" "+fer.melhorCaminho[i][1]);
			}
			System.out.println(fer.getQtdItem()+" "+fer.getValorTotal()+" "+fer.getPesoTotal());
			
			//Printa o tamanho de itens.
			if(fer.getQtdItem() > 0){   //Se tem item.
				for(int i=0;i<fer.bagagem.length;i++){
					System.out.println(fer.bagagem[i][0]+" "+fer.bagagem[i][1]);   //Printa a coordenada do item.
				}
			}
		}
		else{
			System.out.println("\nNao tem caminho possivel.");
			System.out.println("Encerrando programa...\n");
			System.exit(1);
		}
	}
	//=================================================================



	//------------------------------MAIN-------------------------------

	public static void main(String[] args) throws FileNotFoundException{
		
		try{
			if(args.length == 2){
				FileReader arquivo = new FileReader(args[0]);   //Pega o nome do TXT e inicia arquivo.
				BufferedReader lerArquivo = new BufferedReader(arquivo);   //Coloca em memoria.
				int caminho = Integer.parseInt(args[1]);    //Pega qual o tipo de caminho.
				//Printa as informacoes.
				if(caminho>= 1 && caminho <=3){

					lerArq(lerArquivo);   //Faz a leitura e insercoes pelo arquivo(todas as operacoes).
                    
                    //Executa labirinto.
                    System.out.println("\n--------------SAIDA---------------\n");
                    switch(caminho){
                    	//Completo
                    	case 1: caminhoUm();  //Caminho mais curto.
                    	break;
                    	//Incompleto
                    	case 2:	caminhoDois(); //Caminho com maior valor de itens.
                    	break;
                    	//Incompleto
                    	case 3:	System.out.println("Funcao 3 incompleta. Verificar com o criador. \n(Ele que sabe o que faz ne...)");
                    	break;
                    }
					
					try{
						arquivo.close();  //Fecha o arquivo.	
						System.exit(0); //Termina programa.
					}
					catch(IOException e){
						System.out.println(e.getMessage());
					}
				}
				else{
					System.out.println("Caminho invalido, escolha entre 1 a 3.\n");
					System.out.println("\nEncerrando programa...\n");
					System.exit(1);
				}
			}
			else{
				System.out.println("\nEntrada invalida. Mais de dois parametros.\n");
				System.out.println("\nEncerrando programa...\n");
				System.exit(1);
			}
		}
		catch (FileNotFoundException e){
			System.out.println("\nArquivo invalido! Tente novamente com um arquivo valido.\n");
			System.out.println("\nEncerrando programa...\n");
			System.exit(1);
		}
	}
}
