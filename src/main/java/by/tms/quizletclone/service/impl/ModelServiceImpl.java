package by.tms.quizletclone.service.impl;

import by.tms.quizletclone.dto.ModelChangeDTO;
import by.tms.quizletclone.entity.Card;
import by.tms.quizletclone.entity.Folder;
import by.tms.quizletclone.entity.LearnModel;
import by.tms.quizletclone.entity.User;
import by.tms.quizletclone.mapper.ModelChangeMapper;
import by.tms.quizletclone.repository.CardRepository;
import by.tms.quizletclone.repository.ModelRepository;
import by.tms.quizletclone.service.ModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ModelServiceImpl implements ModelService {

    private ModelRepository modelRepository;
    private CardRepository cardRepository;

    @Autowired
    public ModelServiceImpl(ModelRepository modelRepository, CardRepository cardRepository) {
        this.modelRepository = modelRepository;
        this.cardRepository = cardRepository;
    }

    @Override
    public void save(LearnModel learnModel, User user) {
        learnModel.setUser(user);
        modelRepository.save(learnModel);
    }

    @Override
    public LearnModel getAll(long id) {
        return modelRepository.findById(id).get();
    }

    @Override
    public List<LearnModel> getAllWhereFolderIsNull(User user) {
        return modelRepository.findAllByUserIdAndFolderIsNull(user.getId());
    }

    @Override
    public List<LearnModel> getAll(User user, Folder folder) {
        return modelRepository.findAllByUserIdAndFolderId(user.getId(), folder.getId());
    }

    @Override
    public List<LearnModel> getAll() {
        return modelRepository.findAll();
    }

    @Override
    public void changeTitle(long mId, ModelChangeDTO dto) {

        LearnModel learnModel = ModelChangeMapper.INSTANCE.toModel(dto);
        learnModel.setId(mId);
        modelRepository.save(learnModel);

    }


}
