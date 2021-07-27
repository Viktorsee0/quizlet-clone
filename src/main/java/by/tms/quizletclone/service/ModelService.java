package by.tms.quizletclone.service;

import by.tms.quizletclone.dto.ModelChangeDTO;
import by.tms.quizletclone.entity.Card;
import by.tms.quizletclone.entity.Folder;
import by.tms.quizletclone.entity.LearnModel;
import by.tms.quizletclone.entity.User;
import by.tms.quizletclone.mapper.ModelChangeMapper;
import by.tms.quizletclone.repository.CardRepository;
import by.tms.quizletclone.repository.ModelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ModelService {

    private ModelRepository modelRepository;
    private CardRepository cardRepository;

    @Autowired
    public ModelService(ModelRepository modelRepository,CardRepository cardRepository) {
        this.modelRepository = modelRepository;
        this.cardRepository = cardRepository;
    }

    public void save(LearnModel learnModel, User user) {
        learnModel.setUser(user);
        modelRepository.save(learnModel);
    }

//    public void addCard(long id, Card card) {
//        card.setId(0L);
//
//        Optional<LearnModel> byId = modelRepository.findById(id);
//
//        if (byId.isPresent()) {
//            LearnModel learnModel = byId.get();
//            learnModel.addCard(card);
//            modelRepository.save(learnModel);
//        }

//    }

    public LearnModel getAll(long id) {
        return modelRepository.findById(id).get();
    }

    public List<LearnModel> getAllWhereFolderIsNull(User user) {
        return modelRepository.findAllByUserIdAndFolderIsNull(user.getId());
    }

    public List<LearnModel> getAll(User user, Folder folder) {
        return modelRepository.findAllByUserIdAndFolderId(user.getId(), folder.getId());
    }

    public List<LearnModel> getAll() {
        return modelRepository.findAll();
    }

//    public List<LearnModel> getAll(User user, Folder folder){
//        return modelRepository.findAllByUserIdAndAndFolderId(user.getId(), folder.getId());
//    }

    public void changeTitle(long mId, ModelChangeDTO dto) {

        LearnModel learnModel = ModelChangeMapper.INSTANCE.toModel(dto);
        learnModel.setId(mId);
        modelRepository.save(learnModel);

    }


    public void removeCard(long mId, long cId) {

        Optional<Card> byId = cardRepository.findById(cId);
        Card card = byId.get();

    }



}
