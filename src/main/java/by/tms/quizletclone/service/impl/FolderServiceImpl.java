package by.tms.quizletclone.service.impl;

import by.tms.quizletclone.entity.Folder;
import by.tms.quizletclone.entity.LearnModel;
import by.tms.quizletclone.entity.User;
import by.tms.quizletclone.repository.FolderRepository;
import by.tms.quizletclone.repository.ModelRepository;
import by.tms.quizletclone.service.FolderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class FolderServiceImpl implements FolderService {

    private FolderRepository folderRepository;
    private ModelRepository modelRepository;

    @Autowired
    public FolderServiceImpl(FolderRepository folderRepository, ModelRepository modelRepository) {
        this.folderRepository = folderRepository;
        this.modelRepository = modelRepository;
    }

    public void save(Folder folder, User user) {
        folder.setUser(user);
        folderRepository.save(folder);
    }

    @Override
    public List<Folder> getAll(User user) {
        return folderRepository.findAllByUserId(user.getId());
    }

    @Override
    public Folder getById(long id) {
        return folderRepository.findById(id).get();
    }

    @Override
    public void addModule(long fId, long mId) {
        Optional<Folder> byId = folderRepository.findById(fId);
        Optional<LearnModel> byId1 = modelRepository.findById(mId);

        Folder folder = byId.get();
        LearnModel learnModel = byId1.get();

        learnModel.setFolder(folder);

        modelRepository.save(learnModel);
    }

    @Override
    public void delete(long mId) {
        Optional<LearnModel> byId1 = modelRepository.findById(mId);

        LearnModel learnModel = byId1.get();

        learnModel.setFolder(null);

        modelRepository.save(learnModel);
    }
}
