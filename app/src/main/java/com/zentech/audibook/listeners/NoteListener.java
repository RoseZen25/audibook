package com.zentech.audibook.listeners;

import com.zentech.audibook.entities.Note;

public interface NoteListener {

    void onNoteClicked(Note note, int position);

}
