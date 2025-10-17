package rpg.armas;

import java.util.Random;
import rpg.personagens.Personagem;

public class AdagaSombria implements Arma {
    private static final Random GERADOR = new Random();

    private static final int DANO_PADRAO = 10;
    private static final int CUSTO_ENERGIA = 10;
    private static final int DESTREZA_MINIMA = 12;
    private static final int MULTIPLICADOR_FURTIVO = 3;
    private static final int CHANCE_GOLPE_CRITICO = 15; // %

    @Override
    public String getNome() {
        return "Adaga Sombria de Dol Blathanna";
    }

    @Override
    public int getDanoBase() {
        return DANO_PADRAO;
    }

    @Override
    public int getCustoMana() {
        return CUSTO_ENERGIA;
    }

    @Override
    public String getRequisito() {
        return "Requer Destreza de no mínimo " + DESTREZA_MINIMA;
    }

    @Override
    public boolean temRequisito(Personagem personagem) {
        return personagem.getDestreza() >= DESTREZA_MINIMA;
    }

    @Override
    public void atacar(Personagem atacante, Personagem alvo) {
        if (atacante.getMana() < CUSTO_ENERGIA) {
            System.out.println("   ✖ " + atacante.getNome() + " tenta atacar, mas lhe falta energia para manejar a " + getNome() + ".");
            return;
        }

        atacante.setMana(atacante.getMana() - CUSTO_ENERGIA);

        int dano = DANO_PADRAO + (atacante.getDestreza() / 3);
        dano += GERADOR.nextInt(5) - 2; // variação leve de dano

        // Golpe furtivo — vantagem do atacante com mais destreza
        if (atacante.getDestreza() > alvo.getDestreza()) {
            dano *= MULTIPLICADOR_FURTIVO;
            System.out.println("   🩸 [Golpe Furtivo] " + atacante.getNome() + " surge das sombras e acerta um ponto vital!");
        }

        // Chance de golpe crítico
        if (GERADOR.nextInt(100) < CHANCE_GOLPE_CRITICO) {
            dano *= 2;
            System.out.println("   ⚡ [Crítico] " + atacante.getNome() + " encontra uma brecha na defesa de " + alvo.getNome() + "!");
        }

        System.out.println("   ⚔️  " + atacante.getNome() + " perfura o ar com a " + getNome() + ", infligindo " + dano + " de dano a " + alvo.getNome() + ".");
        alvo.receberDano(dano);
    }
}
