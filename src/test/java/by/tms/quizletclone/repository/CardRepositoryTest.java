package by.tms.quizletclone.repository;

import by.tms.quizletclone.entity.Card;
import by.tms.quizletclone.entity.LearnModel;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class CardRepositoryTest {

    @Autowired
    private CardRepository cardRepository;

    @BeforeEach
    public void init(){
        LearnModel model = new LearnModel();
        model.setId(1);
        model.setName("123");

        Card card = new Card();
        card.setTerm("go");
        card.setDefinition("idti");
        card.setModel(model);

        cardRepository.save(card);
    }

    @Test
    void findAllByModelId() {
        List<Card> allByModelId = cardRepository.findAllByModelId(1);
        Assert.assertFalse(allByModelId.isEmpty());
    }

    @Test
    void findAllByModelIdNotFound() {
        List<Card> allByModelId = cardRepository.findAllByModelId(2);
        Assert.assertTrue(allByModelId.isEmpty());
    }
}