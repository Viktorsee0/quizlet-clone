package by.tms.quizletclone.service;

import by.tms.quizletclone.entity.Folder;
import by.tms.quizletclone.entity.LearnModel;
import by.tms.quizletclone.entity.User;
import by.tms.quizletclone.repository.FolderRepository;
import by.tms.quizletclone.repository.ModelRepository;
import by.tms.quizletclone.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class FolderService {

    private FolderRepository folderRepository;
    private ModelRepository modelRepository;
    private UserRepository userRepository;

    @Autowired
    public FolderService(FolderRepository folderRepository, ModelRepository modelRepository, UserRepository userRepository) {
        this.folderRepository = folderRepository;
        this.modelRepository = modelRepository;
        this.userRepository = userRepository;
    }

    public void save(Folder folder, User user){
        folder.setUser(user);
        folderRepository.save(folder);
    }

    public List<Folder> getAll(User user){
        return folderRepository.findAllByUserId(user.getId());
    }

    public Folder getById(long id){
        return folderRepository.findById(id).get();
    }

    public void addModule(long fId, long mId){
        Optional<Folder> byId = folderRepository.findById(fId);
        Optional<LearnModel> byId1 = modelRepository.findById(mId);

        Folder folder = byId.get();
        LearnModel learnModel = byId1.get();

        learnModel.setFolder(folder);

        modelRepository.save(learnModel);
    }

    public void delete(long mId){
        Optional<LearnModel> byId1 = modelRepository.findById(mId);

        LearnModel learnModel = byId1.get();

        learnModel.setFolder(null);

        modelRepository.save(learnModel);
    }
}
