package by.tms.quizletclone.service.impl;

import by.tms.quizletclone.entity.Card;
import by.tms.quizletclone.entity.LearnModel;
import by.tms.quizletclone.repository.CardRepository;
import by.tms.quizletclone.repository.ModelRepository;
import by.tms.quizletclone.service.CardService;
import by.tms.quizletclone.service.excpetion.CardNotFoundException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.File;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CardServiceImpl implements CardService {

    @Value("${upload.path}")
    private static String webRootPath;


    private final CardRepository cardRepository;
    private final ModelRepository modelRepository;

    public CardServiceImpl(CardRepository cardRepository, ModelRepository modelRepository) {
        this.cardRepository = cardRepository;
        this.modelRepository = modelRepository;
    }

    @Override
    public void create(Card card, long modelId) {
        LearnModel learnModel = modelRepository.findById(modelId).get();
        card.setModel(learnModel);
        card.setId(0L);
        cardRepository.save(card);
    }

    @Override
    public List<Card> getAll(long modelId) {
        return cardRepository.findAllByModelId(modelId);
    }

    @Override
    public void delete(long id) {
        Card card = cardRepository.findById(id).get();
        String filename = card.getFilename();
        if (filename != null){
            File file = new File(webRootPath +"/"+ filename);
            file.delete();
        }
        cardRepository.delete(card);
    }

    public Card cardByIdAndModelId(long id, long modelId){
        Optional<Card> byIdAndModelId = cardRepository.findByIdAndModelId(id, modelId);
        if (byIdAndModelId.isEmpty()){
            throw new CardNotFoundException();
        }
        return byIdAndModelId.get();
    }


}
