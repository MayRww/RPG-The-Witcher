package rpg.efeitos;

import rpg.personagens.Personagem;

public class Queimadura implements EfeitoStatus {

    private final Personagem vitima;
    private int duracao = 3;               
    private static final int DANO_TURNO = 9;

    public Queimadura(Personagem vitima) {
        this.vitima = vitima;
    }

    
    public void aplicar() {
        if (duracao <= 0) {
            System.out.println("As chamas em " + vitima.getNome() + " finalmente se apagam.");
            return;
        }

        System.out.println(vitima.getNome() + " sofre " + DANO_TURNO + " de dano por queimadura (" + duracao + " turnos restantes).");
        vitima.receberDano(DANO_TURNO);
        duracao--;

        if (duracao == 0) {
            System.out.println(vitima.getNome() + " se recuperou das queimaduras.");
        }
    }

    public boolean estaAtivo() {
        return duracao > 0;
    }

    public String getNome() {
        return "Queimadura";
    }

    @Override
    public int getTurnosRestantes() {
        
        throw new UnsupportedOperationException("Unimplemented method 'getTurnosRestantes'");
    }

    @Override
    public void aplicarEfeito(Personagem alvo) {
       
        throw new UnsupportedOperationException("Unimplemented method 'aplicarEfeito'");
    }

    @Override
    public void decrementarTurno() {
      
        throw new UnsupportedOperationException("Unimplemented method 'decrementarTurno'");
    }

    @Override
    public boolean isAtivo() {
     
        throw new UnsupportedOperationException("Unimplemented method 'isAtivo'");
    }
}
