package ru.university.mapper;

import org.mapstruct.Mapper;
import ru.university.dto.SimpleTeacherDTO;
import ru.university.entity.Teacher;

import java.util.List;

@Mapper
public interface DTOMapper {
    SimpleTeacherDTO toSimpleTeacherDTO(Teacher teacher);
    List<SimpleTeacherDTO> toSimpleTeacherDTOs(List<Teacher> teachers);
}
