package perm.amporosenok.debtorsappmvvm.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import perm.amporosenok.debtorsappmvvm.utils.Constants.Keys.NOTES_TABLE


@Entity(tableName = NOTES_TABLE)
data class Note(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @ColumnInfo
    val currentDate: String,
    @ColumnInfo
    val name: String,
    @ColumnInfo
    val money: String,
)


//@ColumnInfo
//val currentDate:String,

