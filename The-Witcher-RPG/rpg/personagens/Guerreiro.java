package rpg.personagens;

/**
 * Geralt de Rívia — o Bruxo Lobo Branco.
 * Mestre em combate corpo a corpo e resistência sobre-humana, 
 * resultado de mutações e anos de treinamento em Kaer Morhen.
 */
public class Guerreiro extends Personagem {

    public Guerreiro() {
        super("Geralt de Rívia", "Humano", 120, 50, 15, 8, 5);
    }

   
    public Guerreiro(String nome) {
        super(nome, "Humano", 120, 50, 15, 8, 5);
    }

    @Override
    protected int aplicarPassivaDefesa(int danoBruto) {
        int danoReduzido = (int) (danoBruto * 0.80);
        System.out.println("   [Passiva] Reflexos Bruxos reduzem o dano de " 
                           + danoBruto + " para " + danoReduzido + ".");
        return danoReduzido;
    }

    @Override
    protected void aplicarPassivaTurno() {
        System.out.println("   ⚔️ " + nome + " mantém a guarda e observa o inimigo.");
    }
}
