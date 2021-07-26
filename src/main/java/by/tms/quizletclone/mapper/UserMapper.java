package by.tms.quizletclone.mapper;

import by.tms.quizletclone.dto.UserRegDTO;
import by.tms.quizletclone.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    User toUser(UserRegDTO dto);

}
