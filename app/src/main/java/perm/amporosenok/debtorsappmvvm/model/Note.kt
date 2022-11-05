package perm.amporosenok.debtorsappmvvm.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "notes_table")
data class Note(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @ColumnInfo
    val currentDate: String,
    @ColumnInfo
    val name: String,
    @ColumnInfo
    val money: String
)

