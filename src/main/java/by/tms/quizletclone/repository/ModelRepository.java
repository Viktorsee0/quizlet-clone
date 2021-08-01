package by.tms.quizletclone.repository;

import by.tms.quizletclone.entity.LearnModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ModelRepository extends JpaRepository<LearnModel, Long> {

//    List<LearnModel> findAllByUserId(long id);

    List<LearnModel> findAllByUserIdAndFolderIsNull(long id);

    @Query("SELECT m FROM LearnModel m WHERE m.user.id =:userId AND m.folder.id =:folderId")
    List<LearnModel> findAllByUserIdAndFolderId(long userId, long folderId);
}
