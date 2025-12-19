# ⛏️ Minervein Mod

**Minervein** é um mod simples e eficiente para **Minecraft Fabric** que facilita a coleta de recursos através da mecânica de *Vein Mining* (mineração em cadeia).

O objetivo deste mod é agilizar o jogo vanilla mantendo o equilíbrio: você ainda precisa das ferramentas corretas para minerar, e o corte de árvores exige um item especial.

## ✨ Funcionalidades

### 💎 Mineração de Minérios (Ores)
Ao quebrar um bloco de minério, todos os minérios idênticos conectados a ele serão quebrados e dropados automaticamente.
* **Compatibilidade:** Funciona com minérios padrão e de outros mods (tag `c:ores`).
* **Requisitos:** O jogador precisa ter a ferramenta correta capaz de coletar o item (ex: não é possível quebrar Diamante com picareta de madeira).
* **Restrição:** Não funciona no modo Criativo.

### 🪓 Corte de Árvores (Tree Felling)
Derrube árvores inteiras quebrando apenas um bloco do tronco!
* **Mecânica Única:** Para evitar que você destrua sua casa de madeira sem querer, esta função **só é ativada se você usar um Machado de Ouro**.
* **Motivo:** Isso dá uma utilidade real e valiosa para o ouro no jogo além de evitar acidentes em casas e construções com madeira.

## 🛠️ Instalação

1. Certifique-se de ter o **Fabric Loader** instalado.
2. Baixe a **Fabric API** e coloque na pasta `/mods`.
3. Baixe o arquivo `.jar` do **Minervein** (na aba Releases) e coloque na pasta `/mods`.
4. Inicie o jogo!

## ⚙️ Como funciona (Técnico)

O mod utiliza o sistema de eventos do Fabric (`PlayerBlockBreakEvents`).
- Para **minérios**, ele verifica a tag `c:ores` e utiliza um algoritmo de busca (Breadth-First Search) para encontrar blocos vizinhos iguais.
- Para **madeira**, ele verifica a tag `minecraft:logs` e valida se o item na mão principal é `Items.GOLDEN_AXE`.

## 📋 Requisitos

* Minecraft (Versão correspondente ao mod)
* Fabric Loader
* Fabric API

## 📝 Licença

Este projeto é de código aberto. Sinta-se à vontade para estudar o código ou contribuir!