package ChallengeLiterAlura.example.demo.principal;

import ChallengeLiterAlura.example.demo.dto.AuthorDTO;
import ChallengeLiterAlura.example.demo.dto.BookDTO;
import ChallengeLiterAlura.example.demo.service.RequisiteAPI;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ExecutionException;

public class principal {
    private Scanner sc = new Scanner(System.in);
    private final String BASE_URL = "http://localhost:8080/api/livros"; // URL do seu controller

    public void exibirMenuPrincipal() {

        var opcao = -1;
        while (opcao != 9) {
            System.out.println("Menu Principal");
            System.out.println("1 - Cadastrar Livro");
            System.out.println("2 - Listar Livros");
            System.out.println("3 - Atualizar Livro");
            System.out.println("4 - Excluir Livro");
            System.out.println("5 - Buscar Livro");
            System.out.println("9 - Sair");
            System.out.println("Digite a opção desejada: ");

            opcao = sc.nextInt();
            sc.nextLine();
            switch (opcao) {
                case 1:
                    System.out.println("Cadastrar Livro");
                    break;
                case 2:
                    System.out.println("Listar Livros");
                    break;
                case 3:
                    System.out.println("Atualizar Livro");
                    break;
                case 4:
                    System.out.println("Excluir Livro");
                    break;
                case 5:
                    System.out.print("Digite o termo de busca: ");
                    String query = sc.nextLine();
                    try {
                        List<BookDTO> resultado = RequisiteAPI.buscarLivrosGutendex(query);
                        if (resultado == null || resultado.isEmpty()) {
                            System.out.println("Nenhum livro encontrado.");
                        } else {
                            for (BookDTO book : resultado) {
                                System.out.println("Title: " + book.title());
                                for (AuthorDTO author : book.authors()) {
                                    System.out.println("Author: " + author.name());
                                }
                                System.out.println("Languages: " + book.languages());
                                System.out.println("Download Count: " + book.download_count());
                                System.out.println();
                            }
                        }
                    } catch (IOException | InterruptedException | ExecutionException e) {
                        System.out.println("Erro ao buscar livros: " + e.getMessage());
                    }
                    break;
                case 9:
                    System.out.println("Saindo do sistema");
                    break;
                default:
                    System.out.println("Opção inválida");
                    break;
            }
        }

        sc.close();
    }
}
