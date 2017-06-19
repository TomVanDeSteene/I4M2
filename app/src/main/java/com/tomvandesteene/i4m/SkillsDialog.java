package com.tomvandesteene.i4m;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;

import java.util.ArrayList;

/**
 * Created by Tom Van de Steene on 16/06/2017.
 */

public class SkillsDialog extends DialogFragment {

    interface SkillsPickerDialogFragmentListener{
        void skillsPicked(String skills);
    }
    private SkillsPickerDialogFragmentListener mSkillsListener;

    final String[] skills = {"Java EE", "C", "C++", "C#", ".NET", "Android Studio", "Swift", "Objective C", "Go(lang)", "PHP", "Xamarin", "Cisco",
            "Python", "Network", "Servers", "React", "JavaScript", "Angular", "Agile", "Scrum", "NodeJS", "JQuery", "SQLite", "NOSQL"};

    ArrayList<String> skillsList = new ArrayList<String>();

    private DialogInterface.OnMultiChoiceClickListener skillsSelectListener = new DialogInterface.OnMultiChoiceClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which, boolean isChecked) {
            if (isChecked){
                skillsList.add(skills[which]);
            }else if (skillsList.contains(skills[which])){
                skillsList.remove(skills[which]);
            }
        }
    };

    private DialogInterface.OnClickListener okListener = new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            String skills= "";
            for (String ms : skillsList){
                skills = skills +  ms + ", ";
                mSkillsListener.skillsPicked(skills);
            }
        }
    };

    //wanneer fragment in een activity komt, verwijzing maken naar eigen listener
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {
            mSkillsListener = (SkillsDialog.SkillsPickerDialogFragmentListener) context;
        }
        catch (ClassCastException cce)
        {
            throw new ClassCastException("Activity is missing the correct interface");
        }
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Choose Skills");
        builder.setIcon(R.drawable.logo);
        builder.setMultiChoiceItems(skills, null, skillsSelectListener);
        builder.setPositiveButton("OK", okListener);
        return builder.create();
    }
}
