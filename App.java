
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        LinkedList<Palavra> list = new LinkedList<>();
        String aux[];
        WordTree tree = new WordTree();

        Path path1 = Paths.get("dicionario.csv");

        try (BufferedReader reader = Files.newBufferedReader(path1, Charset.forName("UTF-8"))) {// Charset.defaultCharset())
            String line = reader.readLine();
            while (line != null) {
                aux = line.split(";");
                if (list.size() == 0) {
                    aux[0] = aux[0].substring(1);
                    System.out.println(aux[0]);
                }
                Palavra p = new Palavra(aux[0], aux[1]);
                list.add(p);
                line = reader.readLine();
            }
        } catch (IOException e) {
            System.err.format("Erro na leitura do arquivo: ", e);
        }
        System.out.println("Lista de palavras e seus significados" + list);

        // Adicionar palavras na árvore
        for (Palavra p : list) {
            tree.addWord(p.getPalavra(), p.getSignificado());
        }

        // Buscar palavras
        while (true) {
            System.out.println("Digite o começo de uma palavra que deseja buscar, ou 0 para sair:");
            String findPrefix = in.nextLine();
            if (findPrefix.equals("0")) {
                in.close();
                System.exit(0);
            }

            LinkedList<String> wordsFound = tree.searchAll(findPrefix);

            if (wordsFound.size() == 0) {
                System.out.println("Nenhuma palavra encontrada.");
            } else {
                System.out.println("Palavras encontradas: " + wordsFound);
            }
            System.out.println("Digite uma das palavras encontradas para ver seu significado:");
            String findWord = in.nextLine();

            if (wordsFound.contains(findWord)) {
                String significado = tree.getSignificadoNode(findWord);
                System.out.println("Significado: " + significado);
            } else {
                System.out.println("Palavra não faz parte da lista.");
            }
        }
    }
}
