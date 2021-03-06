package controllers

import models.Note
import persistence.Serializer
import utils.Validation.isValidListIndex

 class NoteAPI(serializerType: Serializer) {

    private var serializer: Serializer = serializerType
    private var notes = ArrayList<Note>()



    //  crud methods

    fun add(note: Note): Boolean {
        return notes.add(note)
    }

    fun deleteNote(indexToDelete: Int): Note? {
        return if (isValidListIndex(indexToDelete, notes)) {
            notes.removeAt(indexToDelete)
        } else null
    }

    fun updateNote(indexToUpdate: Int, note: Note?): Boolean {
        //find the note object by the index number
        val foundNote = findNote(indexToUpdate)

        //if the note exists, use the note details passed as parameters to update the found note in the ArrayList.
        if ((foundNote != null) && (note != null)) {
            foundNote.noteTitle = note.noteTitle
            foundNote.notePriority = note.notePriority
            foundNote.noteCategory = note.noteCategory
            return true
        }

        //if the note was not found, return false, indicating that the update was not successful
        return false
    }

    fun archiveNote(indexToArchive: Int): Boolean {
        if (isValidIndex(indexToArchive)) {
            val noteToArchive = notes[indexToArchive]
            if (!noteToArchive.isNoteArchived) {
                noteToArchive.isNoteArchived = true
                return true
            }
        }
        return false
    }


    //  listing methods

    fun listAllNotes(): String =
        if (notes.isEmpty()) "No notes stored"
        else formatListString(notes)

    fun listActiveNotes(): String =
        if (numberOfActiveNotes() == 0) "No active notes stored"
        else

           formatListString(notes.filter { note -> !note.isNoteArchived })



    fun listArchivedNotes(): String =
        if (numberOfArchivedNotes() == 0) "No archived notes stored"
        else

            formatListString(notes.filter { note -> note.isNoteArchived})


    fun listNotesBySelectedPriority(priority: Int): String {
        return if (notes.isEmpty()) {
            "No notes stored"
        } else {
            var listOfNotes = ""
            for (i in notes.indices) {
                if (notes[i].notePriority == priority) {
                    listOfNotes +=
                        """$i: ${notes[i]}
                        """.trimIndent()
                }
            }
            if (listOfNotes.equals("")) {
                "No notes with priority: $priority"
            } else {
                "${numberOfNotesByPriority(priority)} notes with priority $priority: $listOfNotes"
            }
        }
    }


    //  counting methods

    fun numberOfNotes(): Int {
        return notes.size
    }

    fun numberOfArchivedNotes(): Int = notes.count { note: Note -> note.isNoteArchived }

    fun numberOfActiveNotes(): Int = notes.count { note: Note -> !note.isNoteArchived }

    fun numberOfNotesByPriority(priority: Int): Int =
         notes.count({it.notePriority==priority})



    //  searching methods

    fun findNote(index: Int): Note? {
        return if (isValidListIndex(index, notes)) {
            notes[index]
        } else null
    }

    fun isValidIndex(index: Int) :Boolean{
        return isValidListIndex(index, notes);
    }

    fun searchByTitle (searchString : String):String {

       return formatListString(notes.filter { note -> note.noteTitle.contains(searchString, ignoreCase = true) })


    }


    //  helper methods



    fun formatListString(notesToFormat : List<Note>) : String =
        notesToFormat
            .joinToString (separator = "\n") { note ->
                notes.indexOf(note).toString() + ": " + note.toString() }


    //  persistence methods

    @Throws(Exception::class)
    fun load() {
        notes = serializer.read() as ArrayList<Note>
    }

    @Throws(Exception::class)
    fun store() {
        serializer.write(notes)
    }

    fun firstNote() : Note {
        return notes.first()
    }

}

