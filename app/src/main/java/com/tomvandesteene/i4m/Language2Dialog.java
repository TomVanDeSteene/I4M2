package com.tomvandesteene.i4m;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;

/**
 * Created by Tom Van de Steene on 15/06/2017.
 */

public class Language2Dialog extends DialogFragment {

    //nodig om resultaat dialog in activity te krijgen
    //interface -> op welke acties reageren? -> eigen listener
    interface Language2PickerDialogFragmentListener{
        void language2Picked(String language2);
    }
    //verwijzing naar klasse die de listener gebruikt
    private Language2Dialog.Language2PickerDialogFragmentListener mLanguage2Listener;

    final String[] languages2 = {"Dutch", "French", "English", "German", "Spanish", "Russian", "Swedish", "Norwegian", "Finnish" , "Arabian", "Other", "None"};

    private DialogInterface.OnClickListener language2SelectListener = new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            mLanguage2Listener.language2Picked(languages2[which]);
        }
    };
    //wanneer fragment in een activity komt, verwijzing maken naar eigen listener
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {
            mLanguage2Listener = (Language2Dialog.Language2PickerDialogFragmentListener) context;
        }
        catch (ClassCastException cce)
        {
            throw new ClassCastException("Activity is missing the correct interface");
        }
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        //Dialog bevat teveel opties om constructors te voorzien
        //builder klasse vervangt dit, op je builder stel je alles in en dit gebruik je dan om een dialog aan te maken
        AlertDialog.Builder languageDialog2 = new AlertDialog.Builder(getActivity());
        //dialoog instellen
        //1 -> title
        languageDialog2.setTitle("Choose First Language");
        //2 -> content
        languageDialog2.setItems(languages2, language2SelectListener);
        //3 -> knoppen
        languageDialog2.setNegativeButton("Clear All", null);
        //4 -> icoontje
        languageDialog2.setIcon(R.drawable.logo);
        //maakt effectief nieuwe instantie van dialog
        return languageDialog2.create();
    }
}
