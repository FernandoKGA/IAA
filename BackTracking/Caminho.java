import java.util.Scanner;

public class Caminho {

	static char mapa[][] = {
		{'.', 'X', '.', 'X', '.', '.', 'X', 'X', 'X', '.', '.', '.', 'X', '.', 'X', 'X', 'X', '.', 'X'},
		{'.', '.', '.', 'X', '.', '.', '.', 'X', '.', '.', 'X', '.', 'X', '.', 'X', '.', 'X', '.', '.'},
		{'.', 'X', '.', 'X', '.', '.', '.', '.', '.', '.', '.', 'X', '.', '.', 'X', '.', 'X', 'X', '.'},
		{'.', 'X', '.', '.', '.', 'X', '.', 'X', 'X', '.', '.', '.', '.', '.', '.', '.', 'X', '.', 'X'},
		{'.', 'X', '.', 'X', '.', '.', '.', 'X', 'X', '.', 'X', 'X', '.', '.', '.', 'X', 'X', 'X', '.'},
		{'B', 'X', 'X', 'X', '.', 'X', '.', 'X', 'X', '.', 'X', 'X', '.', 'X', '.', '.', '.', '.', 'X'},
		{'.', '.', '.', '.', '.', '.', '.', '.', 'X', '.', 'X', 'X', '.', 'X', 'X', '.', 'X', 'X', 'X'},
		{'.', '.', '.', 'X', 'X', 'X', 'X', '.', 'X', '.', 'X', 'X', '.', '.', '.', '.', 'X', '.', '.'},
		{'.', '.', '.', 'X', 'X', 'X', '.', '.', 'X', 'X', 'X', 'X', 'X', '.', '.', '.', 'X', '.', '.'},
		{'.', '.', '.', 'X', 'X', '.', '.', '.', '.', '.', '.', 'X', 'X', '.', 'X', '.', 'X', '.', 'X'},
		{'.', 'X', '.', 'X', 'X', '.', '.', 'X', '.', '.', '.', 'X', 'X', '.', 'X', '.', '.', '.', '.'},
		{'X', 'X', 'X', 'X', '.', 'X', '.', 'X', 'X', 'X', '.', 'X', 'X', '.', 'X', '.', '.', '.', 'X'},
		{'.', '.', 'X', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', 'X', 'X', '.', '.', '-'}
	};

	public static void main(String args[]) {
		int posInicial[] = {0, 0};
		Personagem jailson = new Personagem('+', "Jailson", posInicial);
		Cenario cen1 = new Cenario(mapa, 'X', '.', jailson);

		Scanner entrada = new Scanner(System.in);
		char escolha = '0';

		cen1.inserePersonagem();

		do {
			cen1.imprimeMapaAtual();
			System.out.println("\n");

			System.out.print("Comandos:\n\t\"w\": cima\n\t\"s\": baixo\n\t\"a\": esqueda\n\t\"d\": direita\n\t\"0\": sair\n\n-> ");
			escolha = entrada.nextLine().charAt(0);

			// Limpa a tela no Ubuntu
			System.out.println("\033[2J");

			switch (escolha) {
				case 'w':
					if(!cen1.movimento(jailson, 'c')) System.out.println("[!] - Movimento inválido!");
					break;

				case 's':
					if(!cen1.movimento(jailson, 'b')) System.out.println("[!] - Movimento inválido!");
					break;

				case 'a':
					if(!cen1.movimento(jailson, 'e')) System.out.println("[!] - Movimento inválido!");
					break;

				case 'd':
					if(!cen1.movimento(jailson, 'd')) System.out.println("[!] - Movimento inválido!");
					break;

				default:
					escolha = '0';

			}
		} while(escolha != '0');

		cen1.imprimeMapaAtual();
		System.out.println("\n");

	}
}
