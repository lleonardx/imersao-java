import java.io.File;
import java.io.InputStream;
import java.net.URI;
import java.net.URL;
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
        String url = "https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/TopMovies.json";
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
        var diretorio = new File("figurinhas/");
        diretorio.mkdir();

        var geradora = new GeradoraDeFigurinhas();
        for (int i = 0; i < 5; i++) {
            var filme = listaDeFilmes.get(i);

            String urlImagem = filme.get("image");
            String titulo = filme.get("title");

            InputStream inputStream = new URL(urlImagem).openStream();
            String nomeArquivo = "figurinhas/" + titulo + " .png";

            geradora.cria(inputStream, nomeArquivo);

            System.out.println(titulo);
            // System.out.println(filme.get("image"));
            // System.out.println(filme.get("imDbRating"));
            System.out.println();

        }

    }
}