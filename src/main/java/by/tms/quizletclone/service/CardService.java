package by.tms.quizletclone.service;

import by.tms.quizletclone.entity.Card;

import java.util.List;

public interface CardService {
     void create(Card card, long modelId);
     List<Card> getAll(long modelId);
     void delete(long id);
}
