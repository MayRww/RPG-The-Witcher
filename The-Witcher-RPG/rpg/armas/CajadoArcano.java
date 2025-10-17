package rpg.armas;

import java.util.Random;
import rpg.efeitos.Queimadura;           
import rpg.personagens.Personagem;

public class CajadoArcano implements Arma {

    private static final Random SORTE = new Random();

  
    private static final int PODER_BASE = 8;
    private static final int CUSTO_ENERGIA = 25;
    private static final int INTEL_MINIMA = 12;
    private static final int CHANCE_MAGIA_INTENSA = 18; 

    @Override
    public String getNome() {
        return "Cajado Arcano de Aretuza";
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
        return "Inteligência mínima: " + INTEL_MINIMA;
    }

    @Override
    public boolean temRequisito(Personagem p) {
        return p.getInteligencia() >= INTEL_MINIMA;
    }

    @Override
    public void atacar(Personagem atacante, Personagem alvo) {
        if (atacante.getMana() < CUSTO_ENERGIA) {
            System.out.println("   Ataque falhou: " + atacante.getNome()
                    + " não possui energia mágica suficiente para usar o " + getNome() + ".");
            return;
        }

        atacante.setMana(atacante.getMana() - CUSTO_ENERGIA);

        
        int base = PODER_BASE + (atacante.getInteligencia() / 2);
        int variacao = SORTE.nextInt(5) - 2; // -2 a +2
        int dano = base + variacao;

     
        boolean magiaIntensa = SORTE.nextInt(100) < CHANCE_MAGIA_INTENSA;
        if (magiaIntensa) {
            dano *= 2;
            System.out.println("   Magia intensa: o feitiço de " + atacante.getNome()
                    + " atinge intensidade incomum e duplica o dano.");
        }

        System.out.println("   " + atacante.getNome() + " canaliza chamas através do " + getNome()
                + " e causa " + dano + " de dano em " + alvo.getNome() + ".");
        alvo.receberDano(dano);

    
        System.out.println("   Efeito: " + alvo.getNome() + " é tomado pelo fogo e sofre queimadura contínua.");
        alvo.adicionarEfeito(new Queimadura(alvo));
    }
}
