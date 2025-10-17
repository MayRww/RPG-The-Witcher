# RPG-The-Witcher
The Witcher RPG ⚔️
Sistema de Batalha com Padrão Strategy
🎮 Descrição do Projeto

The Witcher RPG é um mini-jogo de console escrito em Java, inspirado no universo de The Witcher.
O sistema demonstra o uso do Padrão de Projeto Strategy, aplicado ao combate e às armas, permitindo criar personagens e armas com comportamentos dinâmicos e facilmente extensíveis.

Os personagens podem:

Equipar diferentes armas (espadas, arcos, cajados, katanas…);

Atacar com base em seus atributos (força, destreza, inteligência);

Receber efeitos e status (sangramento, queimadura, vulnerabilidade);

Usar sinais mágicos, como Igni e Quen;

Consumir poções e regenerar energia durante o combate.

⚙️ Padrão de Projeto Utilizado

Este projeto utiliza o Strategy Pattern, onde:

Cada arma implementa a interface Arma e define seu próprio comportamento de ataque;

Cada personagem usa uma arma diferente, mas o método de ataque (atacar()) é genérico;

Novas armas podem ser adicionadas sem alterar o código existente — basta criar uma nova classe que implemente Arma.

🛠️ Como Compilar e Executar (Windows PowerShell)

1️⃣ Acesse a pasta do projeto:

cd "C:\Users\Maywo\OneDrive\Área de Trabalho\Personagem RPG"


2️⃣ Compile todas as classes (com suporte a UTF-8):

javac -encoding UTF-8 -d . $(Get-ChildItem -Recurse -Filter *.java | ForEach-Object { $_.FullName })


3️⃣ Execute o jogo:

java -cp . rpg.sistema.Main

🧝‍♀️ Personagens Jogáveis
Personagem	Descrição	Atributos Principais
Geralt de Rívia	Bruxo da Escola do Lobo. Mestre em combate corpo a corpo.	Força alta, resistência
Yennefer de Vengerberg	Feiticeira poderosa, domina os sinais mágicos.	Inteligência e mana
Milva	Caçadora élfica precisa e ágil.	Destreza e esquiva
Cahir aep Ceallach	Cavaleiro de Nilfgaard, disciplinado e frio.	Equilíbrio e regeneração
