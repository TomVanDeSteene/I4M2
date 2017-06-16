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

public class Language4Dialog extends DialogFragment {
    //nodig om resultaat dialog in activity te krijgen
    //interface -> op welke acties reageren? -> eigen listener
    interface Language4PickerDialogFragmentListener{
        void language4Picked(String language4);
    }
    //verwijzing naar klasse die de listener gebruikt
    private Language4Dialog.Language4PickerDialogFragmentListener mLanguage4Listener;

    final String[] languages4 = {"Dutch", "French", "English", "German", "Spanish", "Russian", "Swedish", "Norwegian", "Finnish" , "Arabian", "Other", "None"};

    private DialogInterface.OnClickListener language4SelectListener = new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            mLanguage4Listener.language4Picked(languages4[which]);
        }
    };
    //wanneer fragment in een activity komt, verwijzing maken naar eigen listener
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {
            mLanguage4Listener = (Language4Dialog.Language4PickerDialogFragmentListener) context;
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
        AlertDialog.Builder languageDialog4 = new AlertDialog.Builder(getActivity());
        //dialoog instellen
        //1 -> title
        languageDialog4.setTitle("Choose Fourth Language");
        //2 -> content
        languageDialog4.setItems(languages4, language4SelectListener);
        //3 -> knoppen
        languageDialog4.setNegativeButton("Clear All", null);
        //4 -> icoontje
        languageDialog4.setIcon(R.drawable.logo);
        //maakt effectief nieuwe instantie van dialog
        return languageDialog4.create();
    }
}
