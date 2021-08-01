package by.tms.quizletclone.repository;

import by.tms.quizletclone.entity.Card;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CardRepository extends JpaRepository<Card, Long> {
    List<Card> findAllByModelId(long id);
}
