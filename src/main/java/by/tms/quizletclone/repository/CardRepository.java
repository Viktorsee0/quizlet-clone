package by.tms.quizletclone.repository;

import by.tms.quizletclone.entity.Card;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CardRepository extends JpaRepository<Card, Long> {
    List<Card> findAllByModelId(long id);

    Optional<Card> findByIdAndModelId(long id, long modelId);
}
