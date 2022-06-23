package lk.ijse.dep8.note.service.util;

import lk.ijse.dep8.note.dto.UserDTO;
import lk.ijse.dep8.note.entity.User;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class EntityDTOTransformer {

    private final ModelMapper mapper;

    public EntityDTOTransformer(ModelMapper mapper) {
        this.mapper = mapper;
    }

    public User getUserEntity(UserDTO userDTO) {
        return mapper.map(userDTO, User.class);
    }

    public UserDTO getUserDTO(User userEntity){
        return mapper.map(userEntity, UserDTO.class);
    }
}
