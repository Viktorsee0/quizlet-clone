package by.tms.quizletclone.mapper;

import by.tms.quizletclone.dto.ModelChangeDTO;
import by.tms.quizletclone.entity.LearnModel;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ModelChangeMapper {
    ModelChangeMapper INSTANCE = Mappers.getMapper(ModelChangeMapper.class);

    LearnModel toModel(ModelChangeDTO dto);
}
