package perm.amporosenok.debtorsappmvvm.utils

import perm.amporosenok.debtorsappmvvm.database.DatabaseRepository

const val TYPE_DATABASE = "type_database"
const val TYPE_ROOM = "type_room"
const val TYPE_FIREBASE = "type_firebase"


lateinit var REPOSITORY:DatabaseRepository

object Constants{
    object Keys{
            const val NOTE_DATABASE = "notes_database"
            const val NOTES_TABLE = "notes_table"
            const val ADD_NEW_NOTE = "Add new note"
            const val NOTE_NAME = "Name"
            const val NOTE_MONEY = "Money"
            const val ADD_DEBTOR = "Add Debtor"
            const val NAME = "Name"
            const val MONEY = "Money"
            const val WHAT_WILL_WE_USE = "What will we use"
            const val ROOM_DATABASE = "Room Database"
            const val FIREBASE_DATABASE = "Firebase Database"
            const val ID = "Id"
            const val NONE = "None"
            const val UPDATE = "UPDATE"
            const val DELETE = "DELETE"
            const val NAV_BACK = "NAV_BACK"
            const val EDIT_NOTE = "Edit note"
            const val EMPTY = ""
        const val UPDATE_NOTE = "UPDATE NOTE"

    }

    object Screens{

        const val START_SCREEN = "start_screen"
        const val MAIN_SCREEN = "main_screen"
        const val ADD_SCREEN = "add_screen"
        const val NOTE_SCREEN = "note_screen"

    }
}