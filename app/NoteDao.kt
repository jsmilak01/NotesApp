@Dao
interface NoteDao {
    @Insert
    fun insertAll(vararg notes: Note)
}