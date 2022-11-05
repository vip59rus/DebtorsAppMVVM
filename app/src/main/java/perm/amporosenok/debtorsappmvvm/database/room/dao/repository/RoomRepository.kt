package perm.amporosenok.debtorsappmvvm.database.room.dao.repository

import androidx.lifecycle.LiveData
import perm.amporosenok.debtorsappmvvm.database.DatabaseRepository
import perm.amporosenok.debtorsappmvvm.database.room.dao.NoteRoomDao
import perm.amporosenok.debtorsappmvvm.model.Note

class RoomRepository(private val noteRoomDao: NoteRoomDao):DatabaseRepository {
    override val readAll: LiveData<List<Note>>
        get() = noteRoomDao.getAllNotes()

    override suspend fun create(note: Note, onSuccess: () -> Unit) {
        noteRoomDao.addNote(note = note)
    }

    override suspend fun update(note: Note, onSuccess: () -> Unit) {
        noteRoomDao.updateNote(note = note)
    }

    override suspend fun delete(note: Note, onSuccess: () -> Unit) {
        noteRoomDao.deleteNote(note = note)
    }

}