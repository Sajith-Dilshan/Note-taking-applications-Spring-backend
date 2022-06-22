package lk.ijse.dep8.note.repostiory.custom.impl;

import lk.ijse.dep8.note.entity.Note;
import lk.ijse.dep8.note.repostiory.CrudRepositoryImpl;
import lk.ijse.dep8.note.repostiory.custom.NoteRepository;
import org.springframework.stereotype.Repository;

@Repository
public class NoteRepositoryImpl extends CrudRepositoryImpl<Note, Integer> implements NoteRepository {
}
