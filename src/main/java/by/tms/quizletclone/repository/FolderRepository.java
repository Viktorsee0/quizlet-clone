package by.tms.quizletclone.repository;

import by.tms.quizletclone.entity.Folder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FolderRepository extends JpaRepository<Folder, Long> {

    List<Folder> findAllByUserId(long id);
}
