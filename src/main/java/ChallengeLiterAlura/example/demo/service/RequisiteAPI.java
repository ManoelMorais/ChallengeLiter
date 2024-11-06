package ChallengeLiterAlura.example.demo.service;

import ChallengeLiterAlura.example.demo.dto.BookDTO;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class RequisiteAPI {

    public static String getDadosAPI(String url) throws IOException, InterruptedException, ExecutionException {
        HttpClient client = HttpClient.newBuilder()
                .followRedirects(HttpClient.Redirect.NORMAL) // Habilitar redirecionamento
                .build();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() != 200) {
            throw new IOException("Erro na requisição: " + response.statusCode());
        }

        return response.body();
    }

    public static List<BookDTO> buscarLivrosGutendex(String query) throws IOException, InterruptedException, ExecutionException {
        String encodedQuery = URLEncoder.encode(query, StandardCharsets.UTF_8);
        String url = "https://gutendex.com/books?search=" + encodedQuery;
        String response = getDadosAPI(url);

        ObjectMapper mapper = new ObjectMapper();
        JsonNode rootNode = mapper.readTree(response);
        JsonNode booksNode = rootNode.path("results");

        return mapper.readValue(booksNode.toString(), new TypeReference<List<BookDTO>>() {});
    }
}
