package by.tms.quizletclone.service;

import by.tms.quizletclone.dto.ModelChangeDTO;
import by.tms.quizletclone.entity.Folder;
import by.tms.quizletclone.entity.LearnModel;
import by.tms.quizletclone.entity.User;

import java.util.List;

public interface ModelService {

    void save(LearnModel learnModel, User user);

    LearnModel getAll(long id);

    List<LearnModel> getAllWhereFolderIsNull(User user);

    List<LearnModel> getAll(User user, Folder folder);

    List<LearnModel> getAll();

    void changeTitle(long mId, ModelChangeDTO dto);

}
