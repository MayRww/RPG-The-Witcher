package rpg.efeitos;

import rpg.personagens.Personagem;

public class Vulnerabilidade implements EfeitoStatus {
    private int turnosRestantes = 1; 
    private final Personagem alvo; 
    
    public Vulnerabilidade(Personagem alvo) {
        this.alvo = alvo;
    }

    @Override
    public String getNome() {
        return "Vulnerabilidade";
    }

    @Override
    public int getTurnosRestantes() {
        return turnosRestantes;
    }

    @Override
    public void aplicarEfeito(Personagem alvo) {
        if (isAtivo()) {
            System.out.println("   [EFEITO] " + alvo.getNome() + " está Vulnerável.");
        }
    }

    @Override
    public void decrementarTurno() {
        if (turnosRestantes > 0) {
            turnosRestantes--;
            if (turnosRestantes == 0) {
                System.out.println("   [EFEITO] Vulnerabilidade em " + alvo.getNome() + " passou.");
            }
        }
    }

    @Override
    public boolean isAtivo() {
        return turnosRestantes > 0;
    }
}