import java.util.Scanner;

public class InteracaoAspirador {
    public static void main(String[] args) {
        AspiradorInteligente aspirador = new AspiradorInteligente();
        interagirComAspirador(aspirador);
    }

    public static void interagirComAspirador(AspiradorInteligente aspirador) {
        Scanner scanner = new Scanner(System.in);
    
        while (true) {
            System.out.print("Digite um comando (mover [direcao], limpar, sair): ");
            String comando = scanner.nextLine().toLowerCase();
    
            if (comando.startsWith("mover")) {
                String[] partes = comando.split(" ");
                if (partes.length == 2) {
                    aspirador.mover(partes[1].substring(0, 1).toUpperCase() + partes[1].substring(1));
                } else {
                    System.out.println("Comando de movimento inválido. Tente novamente.");
                }
            } else if (comando.equals("limpar")) {
                aspirador.limparQuarto();
                System.out.print("Quarto limpo. ");
    
                // Chama método para continuar interação
                if (!continuarInteracao(scanner)) {
                    break;
                }
            } else if (comando.equals("sair")) {
                break;
            } else {
                System.out.println("Comando inválido. Tente novamente.");
            }
        }
    
        scanner.close();
    }
    
    public static boolean continuarInteracao(Scanner scanner) {
        while (true) {
            System.out.print("Deseja realizar mais alguma ação? (sim/não): ");
            String continuar = scanner.nextLine().toLowerCase();
            if (continuar.equals("sim")) {
                return true;  // Continuar interação
            } else if (continuar.equals("não")) {
                return false;  // Sair do segundo loop
            } else {
                System.out.println("Resposta inválida. Tente novamente.");
            }
        }
    }
    

}