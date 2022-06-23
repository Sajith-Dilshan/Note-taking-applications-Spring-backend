package lk.ijse.dep8.note.repostiory.custom.impl;

import lk.ijse.dep8.note.config.WebAppConfig;
import lk.ijse.dep8.note.config.WebRootConfig;
import lk.ijse.dep8.note.entity.User;
import lk.ijse.dep8.note.repostiory.custom.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@Transactional
@SpringJUnitConfig({WebRootConfig.class, WebAppConfig.class})
class UserRepositoryImplTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    void save() {
        // given
        User user = new User(UUID.randomUUID().toString(), "dualnga@ijse.lk", "abc", "Dulanga");

        // when
        User savedUser = userRepository.save(user);

        // then
        assertEquals(user, savedUser);
    }

    @Test
    void deleteById() {
        // given
        User user = new User(UUID.randomUUID().toString(), "dualnga@ijse.lk", "abc", "Dulanga");
        userRepository.save(user);

        // when
        userRepository.deleteById(user.getId());

        // then
        assertFalse(userRepository.existsById(user.getId()));
    }


    @Test
    void findAll() {
        // given
        User user1 = new User(UUID.randomUUID().toString(), "dualnga@ijse.lk", "abc", "Dulanga");
        User user2 = new User(UUID.randomUUID().toString(), "lahiru@ijse.lk", "abc", "Lahiru");
        userRepository.save(user1);
        userRepository.save(user2);

        // when
        List<User> users = userRepository.findAll();

        // then
        assertTrue(users.size() >= 2);
    }

    @Test
    void count() {
        // given
        User user1 = new User(UUID.randomUUID().toString(), "dualnga@ijse.lk", "abc", "Dulanga");
        User user2 = new User(UUID.randomUUID().toString(), "lahiru@ijse.lk", "abc", "Lahiru");
        userRepository.save(user1);
        userRepository.save(user2);

        // when
        long count = userRepository.count();

        // then
        assertTrue(count >= 2);
    }


    @Test
    void existsUserByEmail() {
        // given
        User user1 = new User(UUID.randomUUID().toString(), "dualnga@ijse.lk", "abc", "Dulanga");
        User user2 = new User(UUID.randomUUID().toString(), "lahiru@ijse.lk", "abc", "Lahiru");
        userRepository.save(user1);
        userRepository.save(user2);

        // when
        boolean dulaExists = userRepository.existsUserByEmail("dualnga@ijse.lk");
        boolean lahiruExists = userRepository.existsUserByEmail("lahiru@ijse.lk");
        boolean abcExists = userRepository.existsUserByEmail("abc@ijse.lk");

        // then
        assertTrue(dulaExists);
        assertTrue(lahiruExists);
        assertFalse(abcExists);
    }
}