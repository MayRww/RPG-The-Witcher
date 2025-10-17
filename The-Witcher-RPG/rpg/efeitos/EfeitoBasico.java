package rpg.efeitos;

import rpg.personagens.Personagem;

public class EfeitoBasico implements EfeitoStatus {
    private String nome;
    private int turnosRestantes;
    private boolean ativo = true;

    public EfeitoBasico(String nome, int duracao) {
        this.nome = nome;
        this.turnosRestantes = duracao;
    }

    @Override
    public String getNome() {
        return nome;
    }

    @Override
    public int getTurnosRestantes() {
        return turnosRestantes;
    }

    @Override
    public void aplicarEfeito(Personagem alvo) {
        switch (nome) {
            case "Escudo Quen":
                System.out.println("   🛡️ " + alvo.getNome() + " está protegido pelo Quen!");
                break;
            case "Sangramento":
                System.out.println("   💉 " + alvo.getNome() + " sofre sangramento (-5 HP).");
                alvo.receberDano(5);
                break;
            case "Queimadura":
                System.out.println("   🔥 " + alvo.getNome() + " sofre queimaduras (-8 HP).");
                alvo.receberDano(8);
                break;
            default:
                System.out.println("   ✨ " + nome + " afeta " + alvo.getNome());
                break;
        }
    }

    @Override
    public void decrementarTurno() {
        turnosRestantes--;
        if (turnosRestantes <= 0) {
            ativo = false;
        }
    }

    @Override
    public boolean isAtivo() {
        return ativo;
    }
}
