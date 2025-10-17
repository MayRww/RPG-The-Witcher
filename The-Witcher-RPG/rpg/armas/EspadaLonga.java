package rpg.armas;

import rpg.personagens.Personagem;

public class EspadaLonga implements Arma {

    private static final int REQ_FORCA = 10;
    private static final int DANO_BASE = 25;

    public EspadaLonga() {
       
    }

    @Override
    public boolean temRequisito(Personagem p) {
        return p.getForca() >= REQ_FORCA;
    }

    @Override
    public void atacar(Personagem atacante, Personagem alvo) {
        int dano = DANO_BASE + (atacante.getForca() / 2);
        System.out.println("   ⚔️  " + atacante.getNome() + " desfere um golpe devastador com a Espada Longa!");
        alvo.receberDano(dano);
    }

    @Override
    public String getNome() {
        return "Espada Longa";
    }

    @Override
    public int getDanoBase() {
        return DANO_BASE;
    }

    @Override
    public int getCustoMana() {
        return 0; 
    }

    @Override
    public String getRequisito() {
        return "Força mínima de " + REQ_FORCA;
    }
}
