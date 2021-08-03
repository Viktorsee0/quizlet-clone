package by.tms.quizletclone.service.restService;

import by.tms.quizletclone.dto.UserRegDTO;
import by.tms.quizletclone.entity.User;
import by.tms.quizletclone.mapper.UserMapper;
import by.tms.quizletclone.repository.UserRepository;
import by.tms.quizletclone.service.excpetion.UserDataException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;


    public void save(UserRegDTO request) {
        log.info("Saving new user first name:{}; email:{}", request.getFirstName(), request.getEmail());
        log.info("Save user in database");

        if (userRepository.existsByEmail(request.getEmail())) {
            log.error("User with this email:{} already exist", request.getEmail());
            throw new UserDataException("User with this email already exist");
        }

        User user = UserMapper.INSTANCE.toUser(request);
        user.setPassword(passwordEncoder.encode(request.getPassword()));
    }


}
