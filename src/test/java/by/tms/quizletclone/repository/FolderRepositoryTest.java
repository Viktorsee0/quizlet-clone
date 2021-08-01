package by.tms.quizletclone.repository;

import by.tms.quizletclone.entity.Folder;
import by.tms.quizletclone.entity.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class FolderRepositoryTest {

    @Autowired
    private FolderRepository folderRepository;

    @BeforeEach
    public void init(){
        User user =new User();
        user.setId(1);
        user.setEmail("123@123.123");
        user.setPassword("123");

        Folder folder = new Folder();
        folder.setUser(user);
        folder.setTitle("123");

        folderRepository.save(folder);
    }

    @Test
    void findAllByUserId() {
        List<Folder> allByUserId = folderRepository.findAllByUserId(1);
        Assertions.assertEquals(allByUserId.get(0).getTitle(), "123");
    }

    @Test
    void findAllByUserIdNotFound() {
        List<Folder> allByUserId = folderRepository.findAllByUserId(5);
        Assertions.assertTrue(allByUserId.isEmpty());
    }
}