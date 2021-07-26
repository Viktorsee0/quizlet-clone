package by.tms.quizletclone.service;

import by.tms.quizletclone.dto.UserRegDTO;
import by.tms.quizletclone.entity.Role;
import by.tms.quizletclone.entity.User;
import by.tms.quizletclone.mapper.UserMapper;
import by.tms.quizletclone.repository.UserRepository;
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

@Service
@Transactional
public class UserServiceImpl implements UserDetailsService, UserService {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    public UserServiceImpl(@Lazy PasswordEncoder passwordEncoder, UserRepository userRepository) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
    }


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        if (userRepository.findByEmail(email).isEmpty()) {
            throw new UserIsDeletedException("User does not exist");
        }
        return userRepository.findByEmail(email).get();
    }

    @Override
    public void registration(UserRegDTO userRegDTO) {

        if (!userRegDTO.getPassword().equals(userRegDTO.getConfirmPassword())) {
            throw new PasswordException("Password mismatch");
        }

        Optional<User> byEmail = userRepository.findByEmail(userRegDTO.getEmail());

        if (byEmail.isEmpty()) {
            User user = UserMapper.INSTANCE.toUser(userRegDTO);

            user.setPassword(passwordEncoder.encode(userRegDTO.getPassword()));
            HashSet<Role> roles = new HashSet<>();
            roles.add(Role.USER);
            user.setRoles(roles);

            userRepository.save(user);
        } else {
            throw new SuchUserIsPresentAlreadyException("User with this email already exists");
        }
        ;

    }


//
//
//    @Override
//    public User getUserById(long id) {
//        return userRepository.findById(id);
//    }
//
//    @Override
//    public void setUserDeleted(User user){
//        user.setDeleted(true);
//        userRepository.save(user);
//    }
//
//    @Override
//    public void setUserNotDeleted(String username){
//        userRepository.findByUsername(username).setDeleted(false);
//    }

}