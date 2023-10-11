@Entity(tableName = "notes")
data class Note(
    @PrimaryKey val title: String?,
    val description: String?
)
