package com.akashaarcher.android.fakebutton.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.akashaarcher.android.fakebutton.R;
import com.akashaarcher.android.fakebutton.data.FakeButtonService;
import com.akashaarcher.android.fakebutton.model.Candidate;
import com.akashaarcher.android.fakebutton.model.Transfer;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Akasha on 3/8/18.
 */

public class TransferDialogFragment extends DialogFragment {

    @BindView(R.id.transfer_amount)
    EditText newTransferAmountEt;

    @BindView(R.id.transfer_user_id)
    EditText newTransferUserIdEt;

    @BindView(R.id.transfer_candidate)
    EditText newTransferCandidateEt;


    public TransferDialogFragment() {
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        View v = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_transfer_dialog, null);
        ButterKnife.bind(this, v);


        return new AlertDialog.Builder(getActivity())
                .setView(v)
                .setTitle("Create new transfer")
                .setPositiveButton(android.R.string.ok, null)
                .setNegativeButton(android.R.string.cancel, null)
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        String amount = newTransferAmountEt.getText().toString().trim();
                        String userId = newTransferUserIdEt.getText().toString().trim();
                        String transferCandidate = newTransferCandidateEt.getText().toString().trim();
                        transferDialogListener.onDialogPositiveClick(amount, userId, transferCandidate);
                    }
                })
                // negative button
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogInterface, int i) {
                        // do nothing
                        Toast.makeText(getActivity(), "Transfer cancelled", Toast.LENGTH_LONG).show();
                    }
                })
                .create();
    }

    public interface TransferDialogListener {
        public void onDialogPositiveClick(String amount, String id, String candidate);
    }

    TransferDialogListener transferDialogListener;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        // Verify that the host activity implements the callback interface
        try {
            // Instantiate the NoticeDialogListener so we can send events to the host
            transferDialogListener = (TransferDialogListener) activity;
        } catch (ClassCastException e) {
            // The activity doesn't implement the interface, throw exception
            throw new ClassCastException(activity.toString()
                    + " must implement TransferDialogListener");
        }
    }

}
