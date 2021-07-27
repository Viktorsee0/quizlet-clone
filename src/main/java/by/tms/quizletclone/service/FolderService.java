package by.tms.quizletclone.service;

import by.tms.quizletclone.entity.Folder;
import by.tms.quizletclone.entity.User;

import java.util.List;

public interface FolderService {
    void save(Folder folder, User user);

    List<Folder> getAll(User user);

    Folder getById(long id);

    void addModule(long fId, long mId);

    void delete(long mId);
}
