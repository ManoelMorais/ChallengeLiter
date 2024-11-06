package ChallengeLiterAlura.example.demo.controllers;

import ChallengeLiterAlura.example.demo.dto.BookDTO;
import ChallengeLiterAlura.example.demo.service.RequisiteAPI;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/api/livros")
public class BookController {

    @GetMapping("/buscar")
    public List<BookDTO> buscarLivros(@RequestParam String query) throws IOException, InterruptedException, ExecutionException {
        return RequisiteAPI.buscarLivrosGutendex(query);
    }
}
