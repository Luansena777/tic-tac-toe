import java.util.InputMismatchException;
import java.util.Scanner;

public class TicTacToe {
    // Matriz que representa o tabuleiro do jogo
    public static char[][] tabuleiro = {{' ', ' ', ' '}, {' ', ' ', ' '}, {' ', ' ', ' '}};

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        char jogadorAtual = 'X'; // Começa com o jogador X
        boolean jogoAtivo = true;
        int linha = -1;
        int coluna = -1;

        while (jogoAtivo) {
            exibirTabuleiro(); // Mostra o estado atual do tabuleiro
            try {
                System.out.print("Jogador " + jogadorAtual + ", escolha uma linha (1-3): ");
                linha = scanner.nextInt();
                System.out.print("Jogador " + jogadorAtual + ", escolha uma coluna (1-3):");
                coluna = scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Entrada inválida! Por favor, digite apenas números.");
                scanner.next(); // Limpa o buffer do scanner para evitar um loop infinito
                continue; // Pula para a próxima iteração do loop
            }

            // Verifica se a posição está dentro dos limites válidos
            if (linha <= 0 || coluna <= 0 || linha > 3 || coluna > 3) {
                System.out.println("Posição invalida! Tente novamente.");
                continue;
            }
            // Verifica se a posição está vazia
            if (tabuleiro[linha - 1][coluna - 1] == ' ') {
                tabuleiro[linha - 1][coluna - 1] = jogadorAtual;
                // Verifica se o jogador venceu
                if (checarVitoria(jogadorAtual)) {
                    exibirTabuleiro();
                    System.out.println("Parabens, jogador " + jogadorAtual + " venceu!");
                    jogoAtivo = false;
                }
                // Verifica empate
                else if (checarEmpate()) {
                    exibirTabuleiro();
                    System.out.println("EMPATE!");
                    jogoAtivo = false;
                } else {
                    jogadorAtual = (jogadorAtual == 'X') ? 'O' : 'X'; // Troca de jogador
                }
            } else {
                System.out.println("Posição ja ocupada! Tente novamente.");
                continue;
            }
        }
        scanner.close();
    }

    // Exibe o tabuleiro atual no console
    public static void exibirTabuleiro() {
        System.out.println("-------------");
        for (int i = 0; i < 3; i++) {
            System.out.print("| ");
            for (int j = 0; j < 3; j++) {
                System.out.print(tabuleiro[i][j] + " | ");
            }
            System.out.println();
            System.out.println("-------------");
        }
    }
    // Requisitos para vitoria
    public static boolean checarVitoria(char jogador) {
        //Verificar linhas
        for (int i = 0; i < 3; i++) {
            if (tabuleiro[i][0] == jogador && tabuleiro[i][1] == jogador && tabuleiro[i][2] == jogador) {
                return true;
            }
        }

        //Verificar colunas
        for (int i = 0; i < 3; i++) {
            if (tabuleiro[0][i] == jogador && tabuleiro[1][i] == jogador && tabuleiro[2][i] == jogador) {
                return true;
            }
        }

        //Verificar diagonais
        if (tabuleiro[0][0] == jogador && tabuleiro[1][1] == jogador && tabuleiro[2][2] == jogador ||
                tabuleiro[0][2] == jogador && tabuleiro[1][1] == jogador && tabuleiro[2][0] == jogador) {
            return true;
        }
        return false;
    }

    // Verifica se todas as posições estão preenchidas e ninguém venceu
    public static boolean checarEmpate() {
        for (char[] linha : tabuleiro) {
            for (char coluna : linha) {
                if (coluna == ' ') {
                    return false;
                }
            }
        }
        return true;
    }
}