package by.tms.quizletclone.service;

import by.tms.quizletclone.dto.UserRegDTO;

public interface UserService {

    void registration(UserRegDTO userRegDTO);
    void activateUser(String code);
}
