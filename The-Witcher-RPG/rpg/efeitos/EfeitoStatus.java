package rpg.efeitos;

import rpg.personagens.Personagem;

public interface EfeitoStatus {
    String getNome();
    int getTurnosRestantes();
    void aplicarEfeito(Personagem alvo); 
    void decrementarTurno();             
    boolean isAtivo();                   
}