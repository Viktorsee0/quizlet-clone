package by.tms.quizletclone.repository;

import by.tms.quizletclone.entity.LearnModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ModelRepository extends JpaRepository<LearnModel, Long> {
}
