package rpg.sistema;

import rpg.armas.*;
import rpg.personagens.*;

public class Main {
    public static void main(String[] args) {
        
        System.out.println("\n--- The Witcher RPG ⚔️ — Sistema com Padrão Strategy ---\n");

       
        Guerreiro geralt = new Guerreiro("Geralt de Rívia");
        Mago yennefer = new Mago("Yennefer de Vengerberg");
        Arqueiro milva = new Arqueiro("Milva, a Caçadora dos Bosques");
        Samurai cahir = new Samurai("Cahir aep Ceallach"); 

        
        EspadaLonga espada = new EspadaLonga(); 
        MachadoGuerra machado = new MachadoGuerra();
        CajadoArcano cajado = new CajadoArcano();
        ArcoElfico arco = new ArcoElfico();
        AdagaSombria adaga = new AdagaSombria();
        Katana katana = new Katana(); 
        
        System.out.println("\n--- FASE DE EQUIPAMENTO ---");
        geralt.equiparArma(espada);
        yennefer.equiparArma(cajado);
        milva.equiparArma(arco);
        cahir.equiparArma(katana);

        System.out.println("\n--- INÍCIO DA BATALHA ---");
        Batalha luta = new Batalha(geralt, cahir);
        luta.iniciar();
    }
}
