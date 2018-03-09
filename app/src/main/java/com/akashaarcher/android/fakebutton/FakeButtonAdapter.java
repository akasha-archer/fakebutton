package com.akashaarcher.android.fakebutton;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.akashaarcher.android.fakebutton.model.Candidate;

import java.util.List;

/**
 * Created by akashaarcher on 3/7/18.
 */

public class FakeButtonAdapter extends RecyclerView.Adapter<FakeButtonViewHolder> {

    private List<Candidate> candidates;

    public FakeButtonAdapter(List<Candidate> candidates) {
        this.candidates = candidates;
    }


    @NonNull
    @Override
    public FakeButtonViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new FakeButtonViewHolder(parent);
    }

    @Override
    public void onBindViewHolder(@NonNull FakeButtonViewHolder holder, int position) {
        Candidate candidate = candidates.get(position);
        holder.bind(candidate);
    }

    @Override
    public int getItemCount() {
        return candidates.size();
    }
}
