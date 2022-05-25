package fr.epf.application;

import android.content.DialogInterface;

import org.jetbrains.annotations.NotNull;

import fr.epf.application.Adapters.ToDoAdapter;
import fr.epf.application.Utils.DatabaseHandler;

public interface DialogCloseListener {
    public void handleDialogClose(DialogInterface dialog);

}
