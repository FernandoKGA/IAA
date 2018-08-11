public class BackTrackingCaminho {

	static char mapa[][] = {
		{'.', 'X', '.', 'X', '.', 'X', 'X', 'X', 'X', '.', '.', '.', 'X', 'X', 'X', 'X', 'X', '.', 'X'},
		{'.', '.', '.', 'X', '.', 'X', '.', 'X', 'X', '.', 'X', '.', 'X', 'X', 'X', '.', 'X', '.', '.'},
		{'.', 'X', '.', 'X', '.', '.', '.', '.', '.', '.', 'X', 'X', '.', 'X', 'X', '.', 'X', 'X', '.'},
		{'.', 'X', '.', '.', '.', 'X', '.', 'X', 'X', '.', '.', '.', '.', 'X', '.', '.', 'X', '.', 'X'},
		{'.', 'X', '.', 'X', '.', '.', '.', 'X', 'X', '.', 'X', 'X', '.', 'X', '.', 'X', 'X', 'X', '.'},
		{'X', 'X', 'X', 'X', 'X', 'X', '.', 'X', 'X', '.', 'X', 'X', 'X', 'X', '.', '.', '.', '.', 'X'},
		{'.', '.', '.', 'X', '.', '.', '.', '.', 'X', '.', 'X', 'X', '.', 'X', 'X', '.', 'X', 'X', 'X'},
		{'.', 'X', 'X', 'X', 'X', 'X', 'X', '.', 'X', '.', 'X', 'X', '.', 'X', 'X', '.', 'X', 'X', 'X'},
		{'.', 'X', 'X', 'X', 'X', 'X', 'X', '.', 'X', 'X', 'X', 'X', 'X', '.', '.', '.', 'X', '.', '.'},
		{'.', '.', '.', 'X', 'X', 'X', '.', '.', '.', '.', '.', 'X', 'X', '.', 'X', '.', 'X', '.', 'X'},
		{'.', 'X', '.', 'X', 'X', 'X', '.', 'X', 'X', 'X', '.', 'X', 'X', '.', 'X', '.', 'X', '.', '.'},
		{'X', 'X', '.', '.', '.', 'X', '.', 'X', 'X', 'X', '.', 'X', 'X', '.', 'X', '.', '.', '.', 'X'},
		{'X', 'X', 'X', 'X', '.', '.', '.', '.', '.', 'X', '.', '.', '.', '.', 'X', 'X', 'X', '.', '-'}
	};

	public static char inverso(char passo) {
		switch(passo) {
			case 'c':
				return 'b';

			case 'b':
				return 'c';

			case 'd':
				return 'e';

			case 'e':
				return 'd';

			default:
				return ' ';
		}
	}

	public static boolean anda(Cenario cen, Personagem p, char [] movimentos, char ultimoPasso) {
		try {
			Thread.sleep(200);
		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println("\033[2J");
		cen.imprimeMapaAtual();
		System.out.println("\n\n");

		for(int i = 0; i < movimentos.length; i++) {
			if(movimentos[i] == ultimoPasso) continue;
			if(!cen.movimento(p, movimentos[i])) continue;
			if(cen.chegou()) return true;
			if(anda(cen, p, movimentos, inverso(movimentos[i]))) return true;
		}

		// Desfaz o movimento
		//System.out.println("vai voltar");
		cen.movimento(p, ultimoPasso);

		try {
			Thread.sleep(200);
		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println("\033[2J");
		cen.imprimeMapaAtual();
		System.out.println("\n\n");
		return false;
	}

	public static void main(String args[]) {
		int posInicial[] = {0, 0};
		Personagem jailson = new Personagem('+', "Jailson", posInicial);
		Cenario cen1 = new Cenario(mapa, 'X', '.', jailson);

		//Scanner entrada = new Scanner(System.in);
		char escolha = '0';

		cen1.inserePersonagem();

		char[] movimentos = {'c', 'b', 'e', 'd'};
		anda(cen1, jailson, movimentos, 'c');

		cen1.imprimeMapaAtual();

		/*do {
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
		System.out.println("\n");*/

	}
}
