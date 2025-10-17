package rpg.armas;

import java.util.Random;
import rpg.personagens.Personagem;

public class ArcoElfico implements Arma {
    private static final Random GERADOR = new Random();

    private static final int PODER_BASE = 12;
    private static final int CUSTO_ENERGIA = 15;
    private static final int DESTREZA_MINIMA = 8;
    private static final double BONUS_AREA = 1.4;
    private static final int CHANCE_GOLPE_PRECISO = 18; 

    @Override
    public String getNome() {
        return "Arco Lunar dos Aen Seidhe";
    }

    @Override
    public int getDanoBase() {
        return PODER_BASE;
    }

    @Override
    public int getCustoMana() {
        return CUSTO_ENERGIA;
    }

    @Override
    public String getRequisito() {
        return "Destreza mínima: " + DESTREZA_MINIMA;
    }

    @Override
    public boolean temRequisito(Personagem arqueiro) {
        return arqueiro.getDestreza() >= DESTREZA_MINIMA;
    }

    @Override
    public void atacar(Personagem arqueiro, Personagem alvo) {
        if (arqueiro.getMana() < CUSTO_ENERGIA) {
            System.out.println("   " + arqueiro.getNome() + " não tem vigor suficiente para tensionar o " + getNome() + ".");
            return;
        }

        arqueiro.setMana(arqueiro.getMana() - CUSTO_ENERGIA);

        int danoBase = (int) (PODER_BASE * BONUS_AREA) + (arqueiro.getDestreza() / 2);
        int variacao = GERADOR.nextInt(6) - 2;
        int dano = danoBase + variacao;

        boolean golpePreciso = GERADOR.nextInt(100) < CHANCE_GOLPE_PRECISO;
        if (golpePreciso) {
            dano *= 2;
            System.out.println("  [Golpe Preciso] A flecha corta o vento e acerta um ponto vital!");
        }

        System.out.println("   🏹 " + arqueiro.getNome() + " dispara uma saraivada com o " + getNome() + "!");
        System.out.println("      As flechas ecoam por Kaer Morhen, atingindo múltiplos inimigos. Dano total: " + dano + ".");

        alvo.receberDano(dano);
    }
}
