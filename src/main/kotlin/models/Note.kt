package models

data class Note(var noteTitle: String, var notePriority: Int, var noteCategory: String, var isNoteArchived :Boolean){
    override fun toString(): String {
        return "Note(noteTitle='$noteTitle', notePriority=$notePriority, noteCategory='$noteCategory', isNoteArchived=$isNoteArchived)"
    }
}