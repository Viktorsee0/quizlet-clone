package by.tms.quizletclone.service;

import by.tms.quizletclone.entity.LearnModel;
import by.tms.quizletclone.repository.ModelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class ModelService {

    private ModelRepository modelRepository;

    @Autowired
    public ModelService(ModelRepository modelRepository) {
        this.modelRepository = modelRepository;
    }

    public void save(LearnModel learnModel){
        modelRepository.save(learnModel);
    }


}
