package com.tomvandesteene.i4m;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;

/**
 * Created by Tom Van de Steene on 16/06/2017.
 */

public class Language3Dialog extends DialogFragment {

    //nodig om resultaat dialog in activity te krijgen
    //interface -> op welke acties reageren? -> eigen listener
    interface Language3PickerDialogFragmentListener{
        void language3Picked(String language1);
    }
    //verwijzing naar klasse die de listener gebruikt
    private Language3Dialog.Language3PickerDialogFragmentListener mLanguage3Listener;

    final String[] languages3 = {"Dutch", "French", "English", "German", "Spanish", "Russian", "Swedish", "Norwegian", "Finnish" , "Arabian", "Other", "None"};

    private DialogInterface.OnClickListener language3SelectListener = new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            mLanguage3Listener.language3Picked(languages3[which]);
        }
    };
    //wanneer fragment in een activity komt, verwijzing maken naar eigen listener
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {
            mLanguage3Listener = (Language3Dialog.Language3PickerDialogFragmentListener) context;
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
        AlertDialog.Builder languageDialog3 = new AlertDialog.Builder(getActivity());
        //dialoog instellen
        //1 -> title
        languageDialog3.setTitle("Choose First Language");
        //2 -> content
        languageDialog3.setItems(languages3, language3SelectListener);
        //3 -> knoppen
        languageDialog3.setNegativeButton("Clear All", null);
        //4 -> icoontje
        languageDialog3.setIcon(R.drawable.logo);
        //maakt effectief nieuwe instantie van dialog
        return languageDialog3.create();
    }
}
