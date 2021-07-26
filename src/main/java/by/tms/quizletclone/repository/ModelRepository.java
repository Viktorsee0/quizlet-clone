package by.tms.quizletclone.repository;

import by.tms.quizletclone.entity.LearnModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ModelRepository extends JpaRepository<LearnModel, Long> {

    List<LearnModel> findAllByUserId(long id);

//    @Query("SELECT m FROM LearnModel m WHERE m.user.id =:id AND m.folder is NULL")
    List<LearnModel> findAllByUserIdAndFolderIsNull(long id);

//    @Query("SELECT m FROM LearnModel m WHERE m.user.id =:id AND m.folder is NULL")
    List<LearnModel> findAllByUserIdAndFolderId(long userId, long folderId);
}
