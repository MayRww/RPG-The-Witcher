package rpg.armas;

import java.util.Random;
import rpg.efeitos.Atordoamento;
import rpg.personagens.Personagem;

public class MachadoGuerra implements Arma {

    private static final Random SORTE = new Random();

    private static final int DANO_BASE = 18;
    private static final int CUSTO_MANA = 5;
    private static final int REQ_FORCA = 15;
    private static final int CHANCE_ATORDOAR = 25; 
    private static final int CHANCE_CRITICO = 20;

    @Override
    public String getNome() {
        return "Machado de Mahakam";
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
        return "Forca >= " + REQ_FORCA;
    }

    @Override
    public boolean temRequisito(Personagem p) {
        return p.getForca() >= REQ_FORCA;
    }

    @Override
    public void atacar(Personagem atacante, Personagem alvo) {
        if (atacante.getMana() < CUSTO_MANA) {
            System.out.println("   Ataque falhou: " + atacante.getNome() + " esta exausto para empunhar o " + getNome() + ".");
            return;
        }

        atacante.setMana(atacante.getMana() - CUSTO_MANA);

        int base = DANO_BASE + (atacante.getForca() / 2);
        int variacao = SORTE.nextInt(5) - 2;
        int dano = base + variacao;

        boolean critico = SORTE.nextInt(100) < CHANCE_CRITICO;
        if (critico) {
            dano *= 2;
            System.out.println("   Golpe devastador! A forca de " + atacante.getNome() + " dobra o impacto.");
        }

        System.out.println("   " + atacante.getNome() + " balanca o " + getNome() + " com brutalidade, causando " + dano + " de dano em " + alvo.getNome() + ".");
        alvo.receberDano(dano);

        if (SORTE.nextInt(100) < CHANCE_ATORDOAR) {
            System.out.println("   O impacto e tao forte que " + alvo.getNome() + " fica momentaneamente desorientado!");
            alvo.adicionarEfeito(new Atordoamento(alvo));
        }
    }
}
