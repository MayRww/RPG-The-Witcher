package rpg.sistema;

import java.util.List;
import java.util.stream.Collectors;
import rpg.armas.Arma;
import rpg.efeitos.EfeitoStatus;
import rpg.personagens.Personagem;

public class Batalha {
    private Personagem jogador1;
    private Personagem jogador2;
    private int turno = 0;

    public Batalha(Personagem p1, Personagem p2) {
        this.jogador1 = p1;
        this.jogador2 = p2;
        System.out.println("--- INÍCIO DA BATALHA ---");
        System.out.println(p1.getNome() + " (" + p1.getClass().getSimpleName() + ") vs " + p2.getNome() + " (" + p2.getClass().getSimpleName() + ")");
    }

    public void iniciar() {
        while (jogador1.estaVivo() && jogador2.estaVivo()) {
            turno++;
            System.out.println("\n===== TURNO " + turno + " =====");


            processarTurno(jogador1, jogador2);


            if (jogador2.estaVivo()) {
                processarTurno(jogador2, jogador1);
            }
        }

        System.out.println("\n--- FIM DA BATALHA ---");
        if (jogador1.estaVivo()) {
            System.out.println("Vencedor: " + jogador1.getNome() + "!");
        } else if (jogador2.estaVivo()) {
            System.out.println("Vencedor: " + jogador2.getNome() + "!");
        } else {
            System.out.println("Empate! Ambos caíram.");
        }
    }

    private void processarTurno(Personagem atacante, Personagem alvo) {
        if (!atacante.estaVivo()) return;


        atacante.aplicarEfeitosDeTurno(); 

        List<EfeitoStatus> atordoado = atacante.getEfeitosAtivos().stream()
            .filter(e -> e.getNome().equals("Atordoado"))
            .collect(Collectors.toList());

        if (!atordoado.isEmpty()) {

            System.out.println(atacante.getNome() + " está atordoado e PULA o ataque.");
            return; 
        }

        if (atacante.estaVivo()) {
            atacante.atacar(alvo);
        }
    }

    public void trocarArma(Personagem p, Arma novaArma) {
        p.equiparArma(novaArma);
    }
}
