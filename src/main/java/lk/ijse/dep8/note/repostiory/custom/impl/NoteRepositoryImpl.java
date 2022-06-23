package lk.ijse.dep8.note.repostiory.custom.impl;

import lk.ijse.dep8.note.entity.Note;
import lk.ijse.dep8.note.entity.User;
import lk.ijse.dep8.note.repostiory.CrudRepositoryImpl;
import lk.ijse.dep8.note.repostiory.custom.NoteRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class NoteRepositoryImpl extends CrudRepositoryImpl<Note, Integer> implements NoteRepository {
    @Override

    public List<Note> findAllNotesByUser(User user) {
        return entityManager.
                createQuery("SELECT n FROM lk.ijse.dep8.note.entity.Note n WHERE n.user = :user", Note.class)
                .setParameter("user", user)
                .getResultList();
    }
}
