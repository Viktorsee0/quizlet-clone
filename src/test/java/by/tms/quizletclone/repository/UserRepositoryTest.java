package by.tms.quizletclone.repository;

import by.tms.quizletclone.entity.User;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @BeforeEach
    public void init(){
        User user =new User();
        user.setId(1);
        user.setEmail("123@123.123");
        user.setPassword("123");
        user.setActivationCode("asd");

        userRepository.save(user);
    }

    @Test
    void findByEmail() {
        Optional<User> byEmail = userRepository.findByEmail("123@123.123");
        Assert.assertTrue(byEmail.isPresent());
    }

    @Test
    void findByActivationCode() {
        User asd = userRepository.findByActivationCode("asd");
        Assert.assertEquals(asd.getUsername(), "123@123.123");
    }

    @Test
    void findByEmailNotFound() {
        Optional<User> byEmail = userRepository.findByEmail("abc@123.123");
        Assert.assertFalse(byEmail.isPresent());
    }
}