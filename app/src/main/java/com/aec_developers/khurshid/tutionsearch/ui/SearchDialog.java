package com.aec_developers.khurshid.tutionsearch.ui;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import com.aec_developers.khurshid.tutionsearch.R;

/**
 * Created by Khurshid on 3/11/2018.
 */

public class SearchDialog extends DialogFragment {

    private EditText subject, location;
    private SearchInterface searchInterface;

    public interface SearchInterface {
        public void dataFromSearchDialog(String subject, String location);
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            searchInterface = (SearchInterface) getTargetFragment();
        } catch (ClassCastException e) {
            e.printStackTrace();
        }
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.search_dialog_ui, null);

        subject = view.findViewById(R.id.etSearchSubject);
        location = view.findViewById(R.id.etSearchLocation);

        builder.setView(view)
                .setTitle("Search (Beta)")
                .setMessage("Search feature is still in beta testing mode. For now results may not be accurate.")
                .setPositiveButton("Search", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String sub = subject.getText().toString().trim();
                        String loc = location.getText().toString().trim();
                        searchInterface.dataFromSearchDialog(sub, loc);

                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dismiss();
                    }
                });

        return builder.create();
    }
}

