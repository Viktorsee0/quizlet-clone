package by.tms.quizletclone.service.impl;

import by.tms.quizletclone.dto.ModelChangeDTO;
import by.tms.quizletclone.entity.Folder;
import by.tms.quizletclone.entity.LearnModel;
import by.tms.quizletclone.entity.User;
import by.tms.quizletclone.mapper.ModelChangeMapper;
import by.tms.quizletclone.repository.ModelRepository;
import by.tms.quizletclone.service.ModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class ModelServiceImpl implements ModelService {

    private ModelRepository modelRepository;

    @Autowired
    public ModelServiceImpl(ModelRepository modelRepository) {
        this.modelRepository = modelRepository;
    }

    @Override
    public void save(LearnModel learnModel, User user) {
        learnModel.setId(0L);
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

    @Override
    public List<LearnModel> getAllByTitle(String title){

       return modelRepository.findAllByNameContaining(title);
    }

    @Override
    public List<LearnModel> getAllByUser(long id){
        return modelRepository.findAllByUserId(id);
    }



}
