package lk.ijse.dep8.note.repostiory.custom.impl;

import lk.ijse.dep8.note.entity.User;
import lk.ijse.dep8.note.repostiory.CrudRepositoryImpl;
import lk.ijse.dep8.note.repostiory.custom.UserRepository;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepositoryImpl extends CrudRepositoryImpl<User, String> implements UserRepository {
}
