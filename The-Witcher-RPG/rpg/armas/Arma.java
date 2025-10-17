package rpg.armas;

import rpg.personagens.Personagem;


public interface Arma {
    String getNome();
    int getDanoBase();
    int getCustoMana();
    String getRequisito();
    boolean temRequisito(Personagem p);


    void atacar(Personagem atacante, Personagem alvo);
}
