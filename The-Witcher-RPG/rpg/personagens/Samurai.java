package rpg.personagens;

/**
 * Cahir Mawr Dyffryn aep Ceallach — cavaleiro de Nilfgaard.
 * Treinado para manter a disciplina e a mente fria em meio ao caos do campo de batalha.
 */
public class Samurai extends Personagem {

    private static final int REGEN_MANA_POR_TURNO = 10;

    public Samurai() {
        super("Cahir aep Ceallach", "Humano", 110, 80, 12, 15, 5);
    }

    public Samurai(String nome) {
        super(nome, "Humano", 110, 80, 12, 15, 5);
    }

    @Override
    protected int aplicarPassivaDefesa(int danoBruto) {
        return danoBruto;
    }

    @Override
    protected void aplicarPassivaTurno() {
        if (mana < manaMaxima) {
            mana = Math.min(manaMaxima, mana + REGEN_MANA_POR_TURNO);
            System.out.println("   🧘 [Passiva] " + nome + " recupera " 
                               + REGEN_MANA_POR_TURNO + " de energia. (" + mana + "/" + manaMaxima + ")");
        }
    }
}
