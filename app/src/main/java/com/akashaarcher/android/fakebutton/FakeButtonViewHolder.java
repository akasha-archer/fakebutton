package com.akashaarcher.android.fakebutton;

import android.content.Context;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.akashaarcher.android.fakebutton.dialog.TransferDialogFragment;
import com.akashaarcher.android.fakebutton.model.Candidate;

/**
 * Created by akashaarcher on 3/7/18.
 */

public class FakeButtonViewHolder extends RecyclerView.ViewHolder {

    private View view;
    private TextView nameTv;
    private TextView emailTv;
    private TextView candidateTv;


    public FakeButtonViewHolder(ViewGroup parent) {
        super(inflateView(parent));
        view = itemView;
        nameTv = (TextView) view.findViewById(R.id.name);
        emailTv = (TextView) view.findViewById(R.id.email);
        candidateTv = (TextView) view.findViewById(R.id.candidate_name);
    }

    private static View inflateView(ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        return inflater.inflate(R.layout.user_view_holder, parent, false);
    }

    public void bind(Candidate candidate) {
        nameTv.setText(candidate.getName());
        emailTv.setText(candidate.getEmail());
        candidateTv.setText(candidate.getCandidate());
    }
}
