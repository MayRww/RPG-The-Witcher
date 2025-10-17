package rpg.efeitos;

import rpg.personagens.Personagem;

public class SangramentoEffect implements EfeitoStatus {
    private int turnosRestantes = 3;
    private static final int DANO_POR_TURNO = 5;
    

    private final Personagem alvo; 
    
    public SangramentoEffect(Personagem alvo) {
        this.alvo = alvo;
    }

    @Override
    public String getNome() {
        return "Sangramento";
    }

    @Override
    public int getTurnosRestantes() {
        return turnosRestantes;
    }

    @Override
    public void aplicarEfeito(Personagem alvo) {
        if (isAtivo()) {
            System.out.println("   [EFEITO] " + alvo.getNome() + " sangra, perdendo " + DANO_POR_TURNO + " de vida.");
            alvo.receberDano(DANO_POR_TURNO);
        }
    }

    @Override
    public void decrementarTurno() {
        if (turnosRestantes > 0) {
            turnosRestantes--;
            if (turnosRestantes == 0) {
                System.out.println("   [EFEITO] Sangramento em " + alvo.getNome() + " cessou."); 
            }
        }
    }

    @Override
    public boolean isAtivo() {
        return turnosRestantes > 0;
    }
}