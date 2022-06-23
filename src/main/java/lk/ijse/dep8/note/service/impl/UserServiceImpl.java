package lk.ijse.dep8.note.service.impl;

import lk.ijse.dep8.note.dto.UserDTO;
import lk.ijse.dep8.note.entity.User;
import lk.ijse.dep8.note.repostiory.custom.UserRepository;
import lk.ijse.dep8.note.service.UserService;
import lk.ijse.dep8.note.service.exception.DuplicateEmailException;
import lk.ijse.dep8.note.service.exception.NotFoundException;
import lk.ijse.dep8.note.service.util.EntityDTOTransformer;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final EntityDTOTransformer transformer;

    public UserServiceImpl(UserRepository userRepository, EntityDTOTransformer transformer) {
        this.userRepository = userRepository;
        this.transformer = transformer;
    }

    @Override
    public UserDTO registerUser(UserDTO user) throws DuplicateEmailException {
        if (userRepository.existsUserByEmail(user.getEmail())) throw new DuplicateEmailException("Email already exists");
        return transformer.getUserDTO(userRepository.save(transformer.getUserEntity(user)));
    }

    @Override
    public void updateUser(UserDTO user) throws NotFoundException {
        Optional<User> optUser = userRepository.findById(user.getEmail());
        if (!optUser.isPresent()) throw new NotFoundException("Invalid user id");
        optUser.get().setFullName(user.getFullName());
        optUser.get().setPassword(user.getPassword());
    }

    @Override
    public UserDTO getUserInfo(String userId) throws NotFoundException {
        return userRepository.findById(userId).map(transformer::getUserDTO)
                .orElseThrow(() -> new NotFoundException("Invalid user id"));
    }

    @Override
    public void deleteUser(String userId) throws NotFoundException {
        if (!userRepository.existsById(userId)) throw new NotFoundException("Invalid user id");
        userRepository.deleteById(userId);
    }
}
