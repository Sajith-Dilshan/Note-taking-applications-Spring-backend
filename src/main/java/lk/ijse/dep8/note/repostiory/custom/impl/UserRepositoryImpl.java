package lk.ijse.dep8.note.repostiory.custom.impl;

import lk.ijse.dep8.note.entity.User;
import lk.ijse.dep8.note.repostiory.CrudRepositoryImpl;
import lk.ijse.dep8.note.repostiory.custom.UserRepository;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepositoryImpl extends CrudRepositoryImpl<User, String> implements UserRepository {
    @Override
    public boolean existsUserByEmail(String email) {
        return !entityManager.
                createQuery("SELECT u FROM lk.ijse.dep8.note.entity.User u WHERE u.email = :email")
                .setParameter("email", email).getResultList().isEmpty();
    }
}
