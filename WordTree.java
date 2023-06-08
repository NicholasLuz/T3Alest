// 4645G-04 - Algoritmos e Estruturas de Dados I
// 2023-1

import java.util.LinkedList;
import java.util.Stack;

public class WordTree {

    // Classe interna
    private class CharNode {
        private char character;
        private String significado;
        private boolean isFinal;
        private CharNode father;
        private LinkedList<CharNode> children;

        public CharNode(char character) {
            this(character, false);
        }

        public CharNode(char character, boolean isFinal) {
            this.father = null;
            this.children = new LinkedList<>();
            this.significado = null;
            this.character = character;
            this.isFinal = isFinal;
        }

        /**
         * Adiciona um filho (caracter) no nodo. Não pode aceitar caracteres repetidos.
         * 
         * @param character - caracter a ser adicionado
         * @param isfinal   - se é final da palavra ou não
         */
        public CharNode addChild(char character, boolean isfinal) {
            CharNode child = new CharNode(character, isfinal);
            child.father = this;
            children.add(child);
            return child;
        }

        public int getNumberOfChildren() {
            return children.size();
        }

        public CharNode getChild(int index) {
            return children.get(index);
        }

        /**
         * Obtém a palavra correspondente a este nodo, subindo até a raiz da árvore
         * 
         * @return a palavra
         */
        private String getWord() {
            Stack<Character> charStack = new Stack<>();
            CharNode currentNode = this;

            while (currentNode != null) {
                charStack.push(currentNode.character);
                currentNode = currentNode.father;
            }

            String word = "";

            while (!charStack.isEmpty()) {
                word = word + charStack.pop();
            }

            return word;
        }

        /**
         * Encontra e retorna o nodo que tem determinado caracter.
         * 
         * @param character - caracter a ser encontrado.
         */
        public CharNode findChildChar(char character) {
            for (CharNode c : children) {
                if (c.character == character) {
                    return c;
                }
            }
            return null;
        }

    }

    // Atributos
    private CharNode root;
    private int totalNodes = 0;
    private int totalWords = 0;

    // Construtor
    public WordTree() {
      ...
    }

    // Metodos
    public int getTotalWords() {
        ...
    }

    public int getTotalNodes() {
        ...
    }

    /**
    *Adiciona palavra na estrutura em árvore
    *@param word
    */
    public void addWord(String word) {
        ...
    }

    /**
     * Vai descendo na árvore até onde conseguir encontrar a palavra
     * @param word
     * @return o nodo final encontrado
     */
    private CharNode findCharNodeForWord(String word) {
        ...
    }

    /**
    * Percorre a árvore e retorna uma lista com as palavras iniciadas pelo prefixo dado.
    * Tipicamente, um método recursivo.
    * @param prefix
    */
    public List<String> searchAll(String prefix) {
        ...
    }

}
