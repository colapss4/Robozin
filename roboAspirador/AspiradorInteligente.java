import java.util.*;

public class AspiradorInteligente {
    private int energia;
    private int sujeiraNaBolsa;
    private int[] localizacao;
    private boolean objetivoAlcancado;
    private Map<String, int[]> direcoes;
    private String[][] ambiente;

    public AspiradorInteligente() {
        this.energia = 100;
        this.sujeiraNaBolsa = 0;
        this.localizacao = new int[]{0, 0};
        this.objetivoAlcancado = false;
        this.direcoes = new HashMap<>();
        this.direcoes.put("Norte", new int[]{-1, 0});
        this.direcoes.put("Sul", new int[]{1, 0});
        this.direcoes.put("Leste", new int[]{0, 1});
        this.direcoes.put("Oeste", new int[]{0, -1});
        this.ambiente = new String[][]{
                {"A", "B", "C", "D", "E", "F", "G", "H"},
                {"I", "J", "K", "L", "M", "N", "O", "P"},
                {"Q", "R", "S", "T", "U", "V", "W", "X"},
                {"Y", "Z", "AA", "AB", "AC", "AD", "AE", "AF"},
                {"AG", "AH", "AI", "AJ", "AK", "AL", "AM", "AN"},
                {"AO", "AP", "AQ", "AR", "AS", "AT", "AU", "AV"},
                {"AW", "AX", "AY", "AZ", "BA", "BB", "BC", "BD"},
                {"BE", "BF", "BG", "BH", "BI", "BJ", "BK", "BL"}
        };
    }

    public void aspirarSujeira() {
        int linha = this.localizacao[0];
        int coluna = this.localizacao[1];
        String localAtual = this.ambiente[linha][coluna];
        this.ambiente[linha][coluna] = " ";

        this.sujeiraNaBolsa += 1;
        this.energia -= 1;
        System.out.println("Aspirou sujeira em " + localAtual);
    }

    public void mover(String direcao) {
        int linha = this.localizacao[0];
        int coluna = this.localizacao[1];
        int[] deslocamento = this.direcoes.get(direcao);
        int novaLinha = linha + deslocamento[0];
        int novaColuna = coluna + deslocamento[1];

        if (this.validarLocalizacao(novaLinha, novaColuna)) {
            this.localizacao = new int[]{novaLinha, novaColuna};
            this.energia -= 1;
        }
    }

    public boolean validarLocalizacao(int linha, int coluna) {
        return 0 <= linha && linha < 8 && 0 <= coluna && coluna < 8;
    }

    public void voltarParaCasa() {
        while (!Arrays.equals(this.localizacao, new int[]{0, 0})) {
            this.mover(this.determinarDirecaoDeVolta());
        }
    }

    public String determinarDirecaoDeVolta() {
        int linha = this.localizacao[0];
        int coluna = this.localizacao[1];
        if (linha > 0) {
            return "Norte";
        } else if (coluna > 0) {
            return "Oeste";
        } else {
            return "Sul";
        }
    }

    public void verificarObjetivo() {
        if (this.sujeiraNaBolsa >= 10) {
            this.sujeiraNaBolsa = 0;
            this.objetivoAlcancado = true;
        }
    }

    public void limparQuarto() {
        while (!this.objetivoAlcancado) {
            if (this.energia <= 0) {
                System.out.println("Sem energia suficiente para continuar.");
                break;
            }

            this.aspirarSujeira();
            this.verificarObjetivo();

            if (this.objetivoAlcancado) {
                System.out.println("\nBolsa cheia.");
                this.voltarParaCasa();
                this.sujeiraNaBolsa = 0;
                System.out.println("\nBolsa esvaziada, continuando a limpeza.");
            }

            if (!this.objetivoAlcancado) {
                this.mover(this.determinarProximaAcao());
            }
        }

        System.out.println("\nQuarto limpo!");
    }

    public String determinarProximaAcao() {
        List<String> direcoesPossiveis = new ArrayList<>(this.direcoes.keySet());
        Random random = new Random();
        return direcoesPossiveis.get(random.nextInt(direcoesPossiveis.size()));
    }
}


