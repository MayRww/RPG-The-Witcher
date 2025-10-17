package rpg.armas;

import java.util.Random;
import rpg.efeitos.Vulnerabilidade;  
import rpg.personagens.Personagem;

public class Katana implements Arma {

    private static final Random SORTE = new Random();

    private static final int DANO_BASE = 14;
    private static final int CUSTO_MANA = 10;
    private static final int REQ_FORCA = 10;
    private static final int REQ_DESTREZA = 10;

    private static final int CHANCE_VULNERABILIDADE = 35; // %
    private static final int CHANCE_CRITICO = 18;         // %

    @Override
    public String getNome() {
        return "Lamina de Zerrikania";
    }

    @Override
    public int getDanoBase() {
        return DANO_BASE;
    }

    @Override
    public int getCustoMana() {
        return CUSTO_MANA;
    }

    @Override
    public String getRequisito() {
        
        return "Forca >= " + REQ_FORCA + " e Destreza >= " + REQ_DESTREZA;
    }

    @Override
    public boolean temRequisito(Personagem p) {
        return p.getForca() >= REQ_FORCA && p.getDestreza() >= REQ_DESTREZA;
    }

    @Override
    public void atacar(Personagem atacante, Personagem alvo) {
        if (atacante.getMana() < CUSTO_MANA) {
            System.out.println("   Ataque falhou: " + atacante.getNome()
                    + " nao possui energia suficiente para usar " + getNome() + ".");
            return;
        }

        atacante.setMana(atacante.getMana() - CUSTO_MANA);

        int base = DANO_BASE + (atacante.getDestreza() / 3);
        int variacao = SORTE.nextInt(5) - 2; // -2 a +2
        int dano = base + variacao;

        boolean critico = SORTE.nextInt(100) < CHANCE_CRITICO;
        if (critico) {
            dano *= 2;
            System.out.println("   Golpe preciso: dano duplicado.");
        }

        System.out.println("   " + atacante.getNome() + " executa um corte rapido com a " + getNome()
                + ", causando " + dano + " de dano em " + alvo.getNome() + ".");
        alvo.receberDano(dano);

    
        if (SORTE.nextInt(100) < CHANCE_VULNERABILIDADE) {
            System.out.println("   Efeito: o corte exposto deixa " + alvo.getNome() + " vulneravel por alguns turnos.");
            alvo.adicionarEfeito(new Vulnerabilidade(alvo));
        }
    }
}
