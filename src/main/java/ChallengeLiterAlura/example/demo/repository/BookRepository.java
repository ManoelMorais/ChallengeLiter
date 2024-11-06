package ChallengeLiterAlura.example.demo.repository;

import ChallengeLiterAlura.example.demo.models.BookModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<BookModel, Long> {
}
