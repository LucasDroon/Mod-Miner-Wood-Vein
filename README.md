# ⛏️ Minervein Mod

**Minervein** é um mod para **Minecraft Fabric** que aprimora a mineração e coleta de recursos.
O principal objetivo do mod é trazer "Qualidade de Vida" (QoL) sem quebrar o equilíbrio do jogo, além de **finalmente dar uma utilidade valiosa para as ferramentas de Ouro**.

## ✨ Funcionalidades

### 💎 Mineração de Minérios (Vein Mining)
Ao quebrar um minério, todos os blocos idênticos conectados são quebrados em cadeia.
* **Inteligente:** Efeitos de encantamento, como fortuna, influenciam na quantidade de minérios a serem dropados.
* **Compatibilidade:** Suporta minérios vanilla e de mods (tag `c:ores`).
* **Restrição:** Funciona apenas no modo Sobrevivência e só funciona se você tiver a ferramenta correta para aquele minério (ex: não quebra diamante com picareta de pedra).

### 🪓 Corte de Árvores (Tree Felling)
Derrube a árvore inteira quebrando apenas um bloco do tronco.
* **Requisito Especial:** Funciona **exclusivamente com o Machado de Ouro**.
* **Equilíbrio:** Evita que você destrua sua própria casa de madeira acidentalmente (já que ninguém costuma usar e reparar com um machado de ouro) e valoriza o item.

### 🔨 Tunelamento 3x3 (Area Mining)
Facilite sua construção de túneis e busca por minérios. Quebra uma área de 3x3 blocos de uma vez.
* **Requisito Especial:** Funciona **exclusivamente com a Picareta de Ouro**.
* **Dinâmico:** A área de quebra se adapta baseada na direção em que o jogador está olhando (norte, sul, leste e oeste).

## 🛠️ Instalação

1. Instale o **Fabric Loader**.
2. Baixe a **Fabric API** e coloque na pasta `/mods`.
3. Baixe o **Minervein** (.jar) e coloque na pasta `/mods`.

## ⚙️ Detalhes Técnicos

O mod é focado em performance para evitar lag no servidor durante a quebra de múltiplos blocos.

- **Minérios:** Utiliza *Breadth-First Search (BFS)* para localizar veios conectados.
- **3x3 e Árvores:** Utiliza um algoritmo direcional otimizado (`Smart Directional Lookup`) para calcular os vizinhos sem excesso de alocação de memória.
- **Tags:** Utiliza as tags padrão `c:ores` e `minecraft:logs` para máxima compatibilidade.

## 📝 Licença

Este projeto é de código aberto. Sinta-se à vontade para contribuir!