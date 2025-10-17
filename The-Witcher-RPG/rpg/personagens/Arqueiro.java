package rpg.personagens;

import java.util.Random;

/**
 * Arqueiro inspirado em Milva, uma das aliadas de Geralt de Rívia.
 * Ágil e precisa, sua especialidade é evitar ataques com reflexos apurados.
 */
public class Arqueiro extends Personagem {

    private static final Random SORTE = new Random();
    private static final int CHANCE_ESQUIVA = 25;

    public Arqueiro() {
        super("Milva, a Caçadora dos Bosques", "Humano", 90, 80, 8, 15, 7);
    }

  
    public Arqueiro(String nome) {
        super(nome, "Humano", 90, 80, 8, 15, 7);
    }

    @Override
    protected int aplicarPassivaDefesa(int danoBruto) {
        if (SORTE.nextInt(100) < CHANCE_ESQUIVA) {
            System.out.println("   🏹 [Passiva] " + nome + " se esquiva com precisão — o ataque erra!");
            return 0;
        }
        return danoBruto;
    }

    @Override
    protected void aplicarPassivaTurno() {
        System.out.println("   👁️ " + nome + " observa o campo de batalha.");
    }
}
