package by.tms.quizletclone.service.impl;

import by.tms.quizletclone.dto.UserRegDTO;
import by.tms.quizletclone.entity.Role;
import by.tms.quizletclone.entity.User;
import by.tms.quizletclone.repository.UserRepository;
import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collections;


@RunWith(SpringRunner.class)
@SpringBootTest
class UserServiceImplTest {

    @Autowired
    private UserServiceImpl userService;

    @MockBean
    private MailSender mailSender;

    @MockBean
    private UserRepository userRepository;

    @MockBean
    private PasswordEncoder passwordEncoder;

    @Test
    void loadUserByUsername() {
    }

    @Test
    void registration() {
        UserRegDTO dto = new UserRegDTO();
        dto.setEmail("qwe@qwe.qwe");
        dto.setPassword("123");
        dto.setConfirmPassword("123");

        User user = userService.registration(dto);

        Assert.assertNotNull(user);
        Assert.assertNotNull(user.getActivationCode());
        Assert.assertFalse(user.isActive());
        Assert.assertTrue(CoreMatchers.is(user.getRoles()).matches(Collections.singleton(Role.USER)));

        Mockito.verify(userRepository, Mockito.times(1)).save(user);
        Mockito.verify(mailSender, Mockito.times(1)).send(
                ArgumentMatchers.eq(user.getEmail()),
                ArgumentMatchers.anyString(),
                ArgumentMatchers.anyString()
        );
    }

    @Test
    void activateUser() {
        User user = new User();

        user.setActivationCode("bingo!");

        Mockito.doReturn(user)
                .when(userRepository)
                .findByActivationCode("activate");

        userService.activateUser("activate");

        Assert.assertTrue(user.isActive());
        Assert.assertNull(user.getActivationCode());
        Mockito.verify(userRepository, Mockito.times(1)).save(user);
    }

}