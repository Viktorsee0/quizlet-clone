package by.tms.quizletclone.service;

import by.tms.quizletclone.entity.Card;
import by.tms.quizletclone.entity.LearnModel;
import by.tms.quizletclone.repository.CardRepository;
import by.tms.quizletclone.repository.ModelRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class CardService {

    private final CardRepository cardRepository;
    private final ModelRepository modelRepository;

    public CardService(CardRepository cardRepository, ModelRepository modelRepository) {
        this.cardRepository = cardRepository;
        this.modelRepository = modelRepository;
    }

    public void create(Card card, long modelId) {
        LearnModel learnModel = modelRepository.findById(modelId).get();
        card.setModel(learnModel);
        card.setId(0L);
        cardRepository.save(card);
    }

    public List<Card> getAll(long modelId) {
        return cardRepository.findAllByModelId(modelId);
    }

    public void delete(long id){
        Card card = cardRepository.findById(id).get();
        cardRepository.delete(card);
    }


}
