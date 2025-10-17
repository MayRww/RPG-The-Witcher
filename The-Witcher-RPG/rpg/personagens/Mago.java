package rpg.personagens;

/**
 * Yennefer de Vengerberg — feiticeira poderosa e astuta.
 * Conhecida por seu controle refinado sobre o caos e sua habilidade 
 * de manipular a energia mágica com elegância e destruição.
 */
public class Mago extends Personagem {

    private static final int REGEN_MANA_POR_TURNO = 10;

    public Mago() {
        super("Yennefer de Vengerberg", "Humano", 70, 150, 5, 7, 18);
    }

    public Mago(String nome) {
        super(nome, "Humano", 70, 150, 5, 7, 18);
    }

    @Override
    protected int aplicarPassivaDefesa(int danoBruto) {
    
        return danoBruto;
    }

    @Override
    protected void aplicarPassivaTurno() {
       
        if (mana < manaMaxima) {
            mana = Math.min(manaMaxima, mana + REGEN_MANA_POR_TURNO);
            System.out.println("   🔮 [Passiva] " + nome + " canaliza o caos e regenera "
                               + REGEN_MANA_POR_TURNO + " de mana. (" + mana + "/" + manaMaxima + ")");
        }
    }
}
