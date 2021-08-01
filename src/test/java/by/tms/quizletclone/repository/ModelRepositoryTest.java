package by.tms.quizletclone.repository;

import by.tms.quizletclone.entity.Folder;
import by.tms.quizletclone.entity.LearnModel;
import by.tms.quizletclone.entity.User;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;


@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class ModelRepositoryTest {
    @Autowired
    private ModelRepository modelRepository;

    @BeforeEach
    public void init() {
        User user = new User();
        user.setId(1);
        user.setEmail("123@123.123");
        user.setPassword("123");

        Folder folder = new Folder();
        folder.setId(3);
        folder.setUser(user);
        folder.setTitle("123");

        LearnModel model1 = new LearnModel();
        model1.setId(1);
        model1.setName("abc");
        model1.setUser(user);
        model1.setFolder(folder);

        LearnModel model2 = new LearnModel();
        model2.setId(2);
        model2.setName("qwe");
        model2.setUser(user);

        modelRepository.save(model1);
        modelRepository.save(model2);
    }

    @Test
    void findAllByUserIdAndFolderIsNull() {

        List<LearnModel> allByUserIdAndFolderIsNull = modelRepository.findAllByUserIdAndFolderIsNull(1);
        Assert.assertEquals(allByUserIdAndFolderIsNull.get(0).getName(), "qwe");

    }

}