package ProjetoCineABC;
import java.util.Scanner;

public class CineABC {

    public static void main(String[] args) {
    
   
    	        Scanner sc = new Scanner(System.in);

    	        System.out.println("SEJA BEM-VINDO AO CINE ABC:");
    	        System.out.println("-----------------------------------------");

    	        // ----------------------------------------------------------------------------------------------------
    	        // PARTE 1: CADASTRO DE FILMES
    	        // ----------------------------------------------------------------------------------------------------

    	        System.out.print("Quantos filmes você deseja cadastrar? ");
    	        int QtdFilmes = sc.nextInt();
    	        sc.nextLine(); // LIMPAR O BUFFER DO SCANNER APÓS NEXTINT()

    	        String[] filmes = new String[QtdFilmes];
    	        int[] ingressosVendidosPorFilme = new int[QtdFilmes]; // Contador de ingressos vendidos por filme

    	        // ----------------------------------------------------------------------------------------------------
    	        // PARTE 2: CADASTRO DAS SALAS
    	        // ----------------------------------------------------------------------------------------------------

    	        System.out.print("Quantas salas de cinema você possui? ");
    	        int QtdSalas = sc.nextInt();
    	        sc.nextLine(); // LIMPAR O BUFFER DO SCANNER APÓS NEXTINT()

    	        int[] capacidadeSalas = new int[QtdSalas];
    	        for (int i = 0; i < QtdSalas; i++) {
    	            System.out.print("Digite a quantidade de poltronas para a Sala " + (i + 1) + ": ");
    	            capacidadeSalas[i] = sc.nextInt();
    	            sc.nextLine(); // LIMPAR O BUFFER DO SCANNER APÓS NEXTINT()
    	        }

    	        // CRIAR O VETOR DE FILMES
    	        for (int i = 0; i < QtdFilmes; i++) {
    	            System.out.print("Digite o título do filme " + (i + 1) + ": ");
    	            filmes[i] = sc.nextLine();
    	        }

    	        // ----------------------------------------------------------------------------------------------------
    	        // PARTE 3: ASSOCIAÇÃO DE FILMES ÀS SALAS
    	        // ----------------------------------------------------------------------------------------------------

    	        String[] salasFilmes = new String[QtdFilmes];
    	        for (int i = 0; i < QtdFilmes; i++) {
    	            int NumeroSala = i % QtdSalas; // GARANTIR QUE AS SALAS SE REPITAM
    	            salasFilmes[i] = "Sala " + (NumeroSala + 1);
    	        }

    	        // ----------------------------------------------------------------------------------------------------
    	        // PARTE 4: CADASTRO DAS SESSÕES DE FILMES
    	        // ----------------------------------------------------------------------------------------------------

    	        String[][] listaSessoesFilmes = new String[QtdFilmes][];
    	        boolean[][][] poltronasPorSessao = new boolean[QtdFilmes][][]; // Poltronas específicas para cada sessão
    	        int[][] ingressosVendidosPorSessao = new int[QtdFilmes][]; // Contador de ingressos vendidos por sessão

    	        for (int i = 0; i < QtdFilmes; i++) {
    	            System.out.println("\nCadastro das Sessões para o filme: " + filmes[i]);
    	            System.out.println("-----------------------------------------------");

    	            String[] sessoesFilme = new String[10]; // TAMANHO INICIAL DE 10 SESSÕES POR FILME
    	            int sessaoCount = 0;

    	            String adicionarSessao;
    	            do {
    	                System.out.print("Digite o dia da exibição (dd/mm/aaaa): ");
    	                String dia = sc.nextLine();

    	                System.out.print("Digite a hora da exibição (hh:mm): ");
    	                String hora = sc.nextLine();

    	                sessoesFilme[sessaoCount] = "Dia: " + dia + " | Hora: " + hora + " | " + salasFilmes[i];
    	                sessaoCount++;

    	                System.out.print("Deseja adicionar outra sessão para este filme? (sim/nao): ");
    	                adicionarSessao = sc.nextLine().toLowerCase();
    	            } while (adicionarSessao.equals("sim"));

    	            listaSessoesFilmes[i] = new String[sessaoCount];
    	            System.arraycopy(sessoesFilme, 0, listaSessoesFilmes[i], 0, sessaoCount);

    	            // Inicializar poltronas disponíveis para cada sessão do filme
    	            int salaAssociada = i % QtdSalas;
    	            poltronasPorSessao[i] = new boolean[sessaoCount][capacidadeSalas[salaAssociada]];
    	            for (int j = 0; j < sessaoCount; j++) {
    	                for (int k = 0; k < capacidadeSalas[salaAssociada]; k++) {
    	                    poltronasPorSessao[i][j][k] = true;
    	                }
    	            }

    	            // Inicializar contador de ingressos vendidos por sessão
    	            ingressosVendidosPorSessao[i] = new int[sessaoCount];
    	        }

    	        // ----------------------------------------------------------------------------------------------------
    	        // PARTE 5: VENDA DE INGRESSOS
    	        // ----------------------------------------------------------------------------------------------------

    	        System.out.println("\nVENDA DE INGRESSOS");
    	        System.out.println("-----------------------------------------");

    	        while (true) {
    	            System.out.println("\nFilmes disponíveis:");
    	            for (int i = 0; i < QtdFilmes; i++) {
    	                System.out.println((i + 1) + ". " + filmes[i]);
    	            }

    	            System.out.print("Selecione o número do filme desejado ou digite 0 para sair: ");
    	            int filmeEscolhido = sc.nextInt();
    	            sc.nextLine(); // Limpar o buffer

    	            if (filmeEscolhido == 0) {
    	                System.out.println("Encerrando o sistema de vendas...");
    	                break;
    	            }

    	            if (filmeEscolhido < 1 || filmeEscolhido > QtdFilmes) {
    	                System.out.println("Opção inválida! Tente novamente.");
    	                continue;
    	            }

    	            System.out.println("\nSessões disponíveis para o filme " + filmes[filmeEscolhido - 1] + ":");
    	            for (int i = 0; i < listaSessoesFilmes[filmeEscolhido - 1].length; i++) {
    	                System.out.println((i + 1) + ". " + listaSessoesFilmes[filmeEscolhido - 1][i]);
    	            }

    	            System.out.print("Selecione o número da sessão desejada: ");
    	            int sessaoEscolhida = sc.nextInt() - 1;
    	            sc.nextLine(); // Limpar o buffer

    	            if (sessaoEscolhida < 0 || sessaoEscolhida >= listaSessoesFilmes[filmeEscolhido - 1].length) {
    	                System.out.println("Sessão inválida! Tente novamente.");
    	                continue;
    	            }

    	            System.out.println("\nPoltronas disponíveis para a sessão:");
    	            for (int i = 0; i < poltronasPorSessao[filmeEscolhido - 1][sessaoEscolhida].length; i++) {
    	                System.out.print((poltronasPorSessao[filmeEscolhido - 1][sessaoEscolhida][i] ? "[Livre] " : "[Ocupada] "));
    	            }

    	            System.out.print("\nEscolha o número da poltrona (1 a " + poltronasPorSessao[filmeEscolhido - 1][sessaoEscolhida].length + "): ");
    	            int poltronaEscolhida = sc.nextInt() - 1;
    	            sc.nextLine(); // Limpar o buffer

    	            if (poltronaEscolhida < 0 || poltronaEscolhida >= poltronasPorSessao[filmeEscolhido - 1][sessaoEscolhida].length || !poltronasPorSessao[filmeEscolhido - 1][sessaoEscolhida][poltronaEscolhida]) {
    	                System.out.println("Poltrona inválida ou já ocupada! Tente novamente.");
    	                continue;
    	            }

    	            System.out.print("Você irá pagar meia entrada ou inteira? (meia/inteira): ");
    	            String tipoEntrada = sc.nextLine().toLowerCase();

    	            if (!tipoEntrada.equals("meia") && !tipoEntrada.equals("inteira")) {
    	                System.out.println("Opção inválida! Venda cancelada.");
    	                continue;
    	            }

    	            poltronasPorSessao[filmeEscolhido - 1][sessaoEscolhida][poltronaEscolhida] = false;
    	            ingressosVendidosPorFilme[filmeEscolhido - 1]++;  // Incrementar o contador de ingressos do filme
    	            ingressosVendidosPorSessao[filmeEscolhido - 1][sessaoEscolhida]++;  // Incrementar o contador de ingressos da sessão

    	            // ----------------------------------------------------------------------------------------------------
    	            // PARTE 6: IMPRIMIR INGRESSOS
    	            // ----------------------------------------------------------------------------------------------------
    	            
    	            System.out.println("\n=== INGRESSO ===");
    	            System.out.println("Filme: " + filmes[filmeEscolhido - 1]);
    	            System.out.println("Sessão: " + listaSessoesFilmes[filmeEscolhido - 1][sessaoEscolhida]);
    	            System.out.println("Poltrona: " + (poltronaEscolhida + 1));
    	            System.out.println("Entrada: " + (tipoEntrada.equals("meia") ? "Meia-entrada" : "Inteira"));
    	            System.out.println("================");
    	        }

    	        // ----------------------------------------------------------------------------------------------------
    	        // CÁLCULOS FINAIS
    	        // ----------------------------------------------------------------------------------------------------

    	        // 1. Filme com mais ingressos vendidos e sua porcentagem
    	        int maxIngressos = -1;
    	        int filmeMaisVendido = -1;
    	        int totalIngressos = 0;

    	        for (int i = 0; i < QtdFilmes; i++) {
    	            totalIngressos += ingressosVendidosPorFilme[i];
    	            if (ingressosVendidosPorFilme[i] > maxIngressos) {
    	                maxIngressos = ingressosVendidosPorFilme[i];
    	                filmeMaisVendido = i;
    	            }
    	        }

    	        System.out.println("\nFilme com mais ingressos vendidos: " + filmes[filmeMaisVendido]);
    	        System.out.println("Ingressos vendidos: " + maxIngressos);
    	        System.out.println("Porcentagem em relação ao total: " + (maxIngressos * 100.0 / totalIngressos) + "%");

    	        // 2. Sessão com maior e menor ocupação
    	        int maxOcupacao = -1, minOcupacao = Integer.MAX_VALUE;
    	        int sessaoMaiorOcupacao = -1, sessaoMenorOcupacao = -1;

    	        for (int i = 0; i < QtdFilmes; i++) {
    	            for (int j = 0; j < ingressosVendidosPorSessao[i].length; j++) {
    	                int sessaoOcupacao = ingressosVendidosPorSessao[i][j];
    	                int totalPoltronas = capacidadeSalas[i % QtdSalas];
    	                double porcentagemOcupada = (sessaoOcupacao * 100.0 / totalPoltronas);
    	                if (sessaoOcupacao > maxOcupacao) {
    	                    maxOcupacao = sessaoOcupacao;
    	                    sessaoMaiorOcupacao = i * 100 + j;
    	                }
    	                if (sessaoOcupacao < minOcupacao) {
    	                    minOcupacao = sessaoOcupacao;
    	                    sessaoMenorOcupacao = i * 100 + j;
    	                }
    	            }
    	        }

    	        System.out.println("\nSessão com maior ocupação: " + listaSessoesFilmes[sessaoMaiorOcupacao / 100][sessaoMaiorOcupacao % 100]);
    	        System.out.println("Ocupação: " + maxOcupacao + " poltronas (" + (maxOcupacao * 100.0 / capacidadeSalas[sessaoMaiorOcupacao / 100]) + "%)");

    	        System.out.println("\nSessão com menor ocupação: " + listaSessoesFilmes[sessaoMenorOcupacao / 100][sessaoMenorOcupacao % 100]);
    	        System.out.println("Ocupação: " + minOcupacao + " poltronas (" + (minOcupacao * 100.0 / capacidadeSalas[sessaoMenorOcupacao / 100]) + "%)");

    	        sc.close();
    	    }
    	}