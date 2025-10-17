package rpg.personagens;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import rpg.armas.Arma;
import rpg.efeitos.EfeitoStatus;
import rpg.efeitos.EfeitoBasico;

public abstract class Personagem {
    protected String nome;
    protected String especie; // Humano, Bruxo, Monstro, Feiticeira...
    protected int vida;
    protected int vidaMaxima;
    protected int mana;
    protected int manaMaxima;
    protected int forca;
    protected int destreza;
    protected int inteligencia;

    // Elementos do universo Witcher
    protected String escolaBruxo; // Ex: Lobo, Urso, Gato, Grifo
    protected int toxicidade; // Poções aumentam, meditação reduz
    protected String oleoAtual; // Ex: Óleo contra espectros, necrofagos etc.
    protected List<String> sinaisConhecidos = new ArrayList<>(); // Ex: Aard, Igni, Quen, Yrden, Axii

    protected Arma armaEquipada; 
    protected List<EfeitoStatus> efeitosAtivos = new ArrayList<>();

    public Personagem(String nome, String especie, int vida, int mana, int forca, int destreza, int inteligencia) {
        this.nome = nome;
        this.especie = especie;
        this.vida = vida;
        this.vidaMaxima = vida;
        this.mana = mana;
        this.manaMaxima = mana; 
        this.forca = forca;
        this.destreza = destreza;
        this.inteligencia = inteligencia;
        this.toxicidade = 0;
    }

    public Personagem(String string, int i, int j, int k, int l, int m) {
        // Construtor alternativo opcional
    }

    // ==============================
    // COMBATE
    // ==============================
    public void equiparArma(Arma novaArma) {
        if (novaArma.temRequisito(this)) {
            this.armaEquipada = novaArma;
            System.out.println(nome + " equipou: " + novaArma.getNome());
        } else {
            System.out.println(nome + " não pode equipar " + novaArma.getNome() + 
                               " (Requisito: " + novaArma.getRequisito() + ")");
        }
    }

    public void atacar(Personagem alvo) {
        if (armaEquipada == null) {
            System.out.println(nome + " tenta atacar, mas não possui arma equipada!");
            return;
        }
        
        System.out.println("\n⚔️  TURNO de " + nome + " (" + especie + ")");
        armaEquipada.atacar(this, alvo); 
    }

    // ==============================
    // DANO E EFEITOS
    // ==============================
    public void receberDano(int danoBruto) {
        int danoFinal = danoBruto;
        danoFinal = aplicarPassivaDefesa(danoFinal); 

        double multiplicador = 1.0;
        boolean vulneravel = false;

        for (EfeitoStatus efeito : new ArrayList<>(efeitosAtivos)) { 
            String nomeEfeito = efeito.getNome();
            if (nomeEfeito.equals("Vulnerabilidade")) {
                multiplicador += 0.25;
                vulneravel = true;
            } else if (nomeEfeito.equals("Sangramento")) {
                this.vida -= 5;
            } else if (nomeEfeito.equals("Queimadura")) {
                this.vida -= 8;
            }
        }

        danoFinal = (int) (danoFinal * multiplicador);
        
        if (vulneravel)
            System.out.println("   [Vulnerável] Dano aumentado para " + danoFinal + ".");

        this.vida -= danoFinal;
        if (this.vida < 0) this.vida = 0;
        
        System.out.println("   " + this.nome + " recebeu " + danoFinal + " de dano. Vida restante: " + vida);
        
        if (this.vida <= 0)
            System.out.println("   ☠️  " + this.nome + " foi derrotado!");
    }

    public void adicionarEfeito(EfeitoStatus efeito) {
        efeitosAtivos.add(efeito);
        System.out.println("   🔮 " + nome + " foi afetado por " + efeito.getNome() + " (" + efeito.getTurnosRestantes() + " turnos)");
    }

    public void aplicarEfeitosDeTurno() {
        efeitosAtivos.forEach(e -> e.aplicarEfeito(this));
        
        for (Iterator<EfeitoStatus> iterator = efeitosAtivos.iterator(); iterator.hasNext();) {
            EfeitoStatus efeito = iterator.next();
            efeito.decrementarTurno();
            if (!efeito.isAtivo()) {
                System.out.println("   ✨ " + nome + " se recuperou do efeito: " + efeito.getNome());
                iterator.remove();
            }
        }

        aplicarPassivaTurno();
    }

    // ==============================
    // SINAIS
    // ==============================
    public void usarSinal(String sinal, Personagem alvo) {
        if (!sinaisConhecidos.contains(sinal)) {
            System.out.println(nome + " não domina o sinal " + sinal + ".");
            return;
        }
        if (mana < 10) {
            System.out.println(nome + " está sem energia para usar " + sinal + ".");
            return;
        }

        System.out.println("   🌀 " + nome + " conjura o sinal " + sinal + "!");
        mana -= 10;

        if (sinal.equals("Igni")) {
            alvo.receberDano(inteligencia * 2);
        } else if (sinal.equals("Aard")) {
            System.out.println("   " + alvo.getNome() + " é empurrado para trás!");
        } else if (sinal.equals("Quen")) {
            adicionarEfeito(new EfeitoBasico("Escudo Quen", 2));
        } else if (sinal.equals("Yrden")) {
            System.out.println("   Um selo mágico desacelera o inimigo!");
        } else if (sinal.equals("Axii")) {
            System.out.println("   O inimigo fica confuso e hesita no ataque!");
        } else {
            System.out.println("   " + nome + " tenta usar um sinal desconhecido!");
        }
    }

    // ==============================
    // POÇÕES
    // ==============================
    public void beberPocao(String nomePocao) {
        if (toxicidade >= 100) {
            System.out.println(nome + " está intoxicado e não pode beber mais poções!");
            return;
        }

        System.out.println("   🧪 " + nome + " bebeu uma poção: " + nomePocao + ".");

        if (nomePocao.equals("Andorinha")) {
            this.vida = Math.min(vida + 40, vidaMaxima);
        } else if (nomePocao.equals("Gato")) {
            System.out.println("   Agora " + nome + " enxerga no escuro!");
        } else if (nomePocao.equals("Raio")) {
            this.forca += 5;
        } else if (nomePocao.equals("Trovão")) {
            this.destreza += 5;
        }

        toxicidade += 20;
        if (toxicidade > 100) toxicidade = 100;
    }

    // ==============================
    // MÉTODOS ABSTRATOS / UTILITÁRIOS
    // ==============================
    protected abstract int aplicarPassivaDefesa(int danoBruto); 
    protected abstract void aplicarPassivaTurno();              
    
    public boolean estaVivo() { return vida > 0; }

    // ==============================
    // GETTERS E SETTERS COMPLETOS
    // ==============================
    public String getNome() { return nome; }
    public String getEspecie() { return especie; }
    public int getVida() { return vida; }
    public int getMana() { return mana; }
    public int getManaMaxima() { return manaMaxima; }
    public int getToxicidade() { return toxicidade; }
    public List<EfeitoStatus> getEfeitosAtivos() { return efeitosAtivos; }

    // ✅ ADICIONADOS — usados pelas armas:
    public int getForca() { return forca; }
    public int getDestreza() { return destreza; }
    public int getInteligencia() { return inteligencia; }

    public void setMana(int novaMana) { this.mana = novaMana; }
}
