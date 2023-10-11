@Database(version = 1, entities = [Note::class])
abstract class NoteDatabase : RoomDatabase {
    abstract fun getNoteDao(): NoteDao
}