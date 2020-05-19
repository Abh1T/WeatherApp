package com.example.weatherapp;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatDialogFragment;

import static java.lang.Integer.parseInt;

public class dialogg extends AppCompatDialogFragment {
    private EditText editz;
    private EditText edit;
    private dialoglisren lis;
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        android.app.AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflate = getActivity().getLayoutInflater();
        View view = inflate.inflate(R.layout.dialoglayout, null);
        builder.setView(view);
        builder.setTitle("Change Zip Code");
        builder.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String zip = editz.getText().toString();
                int im = parseInt(zip);
                String countrycode = edit.getText().toString();
                lis.applyTexts(im, countrycode);
            }
        });
        editz = view.findViewById(R.id.editzip);
        edit = view.findViewById(R.id.editText);
        return builder.create();
    }

    @Override
    public void onAttach(Context context){
        super.onAttach(context);
        try {
            lis = (dialoglisren) context;
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public interface dialoglisren{
        void applyTexts(int im, String another);
    }
}
