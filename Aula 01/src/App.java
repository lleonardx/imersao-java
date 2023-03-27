import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;
import java.util.Map;

public class App {
    /**
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        // System.out.println("Hello, World!");

        // fazer a conexão HTTP e buscar top 250 series
        String url = "https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/TopTVs.json";
        URI endereco = URI.create(url);
        var client = HttpClient.newHttpClient();
        var request = HttpRequest.newBuilder(endereco).GET().build();
        HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
        String body = response.body();

        // Mostrar body da API
        // System.out.println(body);

        // extrair os dados que interessam (titulo, poster, classificação)
        var parse = new JsonParser();
        List<Map<String, String>> listaDeFilmes = parse.parse(body);

        // Mostrar quantos itens estão listados.
        // System.out.println(listaDeFilmes.size());

        // Mostrar a primeira posição do array
        // System.out.println(listaDeFilmes.get(0));

        // exibir e manipular os dados - Aula 01
        for (Map<String, String> filme : listaDeFilmes) {
            System.out.println(filme.get("tittle"));
            System.out.println(filme.get("image"));
            System.out.println(filme.get("imDbRating"));
            System.out.println();

        }

        // Desafio 01 - Buscar as Series
        for (int i = 0; i < 3; i++) {
            Map<String, String> filme = listaDeFilmes.get(i);
            System.out.println(filme.get("title"));
            System.out.println(filme.get("image"));
            System.out.println(filme.get("imDbRating"));
            System.out.println();
        }

        // Desafio 02 - Decorar o terminal
        for (int i = 0; i < 3; i++) {
            Map<String, String> filme = listaDeFilmes.get(i);
            System.out.println("\u001b[1mTítulo: \u001b[m" + filme.get("title"));
            System.out.println("\u001b[1mURL da Imagem: \u001b[m" + filme.get("image"));
            System.out.println("" + filme.get("imDbRating"));
            // System.out.println("");
            double classificacao = Double.parseDouble(filme.get("imDbRating"));
            int numeroEstrelinhas = (int) classificacao;

            for (int n = 1; n <= numeroEstrelinhas; n++) {
                // System.out.print("");
            }
            System.out.println("\n");

        }
    }
}