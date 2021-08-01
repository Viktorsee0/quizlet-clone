package by.tms.quizletclone.service.impl;

import by.tms.quizletclone.dto.UserRegDTO;
import by.tms.quizletclone.entity.Role;
import by.tms.quizletclone.entity.User;
import by.tms.quizletclone.mapper.UserMapper;
import by.tms.quizletclone.repository.UserRepository;
import by.tms.quizletclone.service.UserService;
import by.tms.quizletclone.service.excpetion.PasswordException;
import by.tms.quizletclone.service.excpetion.SuchUserIsPresentAlreadyException;
import by.tms.quizletclone.service.excpetion.UserIsDeletedException;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
public class UserServiceImpl implements UserDetailsService, UserService {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final MailSender mailSender;

    public UserServiceImpl(@Lazy PasswordEncoder passwordEncoder,
                           UserRepository userRepository,
                           MailSender mailSender) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.mailSender = mailSender;
    }


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        if (userRepository.findByEmail(email).isEmpty()) {
            throw new UserIsDeletedException("User does not exist");
        }
        return userRepository.findByEmail(email).get();
    }

    @Override
    public User registration(UserRegDTO userRegDTO) {

        if (!userRegDTO.getPassword().equals(userRegDTO.getConfirmPassword())) {
            throw new PasswordException("Password mismatch");
        }

        Optional<User> byEmail = userRepository.findByEmail(userRegDTO.getEmail());

        User user;

        if (byEmail.isEmpty()) {
            user = UserMapper.INSTANCE.toUser(userRegDTO);

            user.setPassword(passwordEncoder.encode(userRegDTO.getPassword()));
            HashSet<Role> roles = new HashSet<>();
            roles.add(Role.USER);
            user.setRoles(roles);

            user.setActivationCode(UUID.randomUUID().toString());

            userRepository.save(user);

            String message = String.format(
                    "Hello!\n" +
                    "Welcome to ... \n" +
                            "Please, visit next link: http://localhost:8080/activate/%s",
                    user.getActivationCode()
            );
            mailSender.send(user.getEmail(), "Activation code", message);

        } else {
            throw new SuchUserIsPresentAlreadyException("User with this email already exists");
        }

        return user;
    }

    @Override
    public void activateUser(String code) {
       User user = userRepository.findByActivationCode(code);
       user.setActivationCode(null);
       user.setActive(true);
       userRepository.save(user);
    }


}