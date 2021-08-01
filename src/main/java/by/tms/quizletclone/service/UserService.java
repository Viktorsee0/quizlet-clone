package by.tms.quizletclone.service;

import by.tms.quizletclone.dto.UserRegDTO;
import by.tms.quizletclone.entity.User;

public interface UserService {

    User registration(UserRegDTO userRegDTO);
    void activateUser(String code);
}
