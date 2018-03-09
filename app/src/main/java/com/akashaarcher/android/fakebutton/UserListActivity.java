package com.akashaarcher.android.fakebutton;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.akashaarcher.android.fakebutton.data.FakeButtonClient;
import com.akashaarcher.android.fakebutton.data.FakeButtonService;
import com.akashaarcher.android.fakebutton.dialog.TransferDialogFragment;
import com.akashaarcher.android.fakebutton.model.Candidate;
import com.akashaarcher.android.fakebutton.model.Transfer;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class UserListActivity extends AppCompatActivity implements TransferDialogFragment.TransferDialogListener {

    private static String TAG = "UserListActivity";
    private FakeButtonAdapter adapter;

    private FakeButtonClient fakeButtonClient;

    private List<Candidate> candidateList = new ArrayList<>();

    public final String BASE_URL = "http://fake-button.herokuapp.com/";

    @BindView(R.id.user_name)
    EditText userNameEt;

    @BindView(R.id.user_email)
    EditText userEmailEt;

    @BindView(R.id.user_candidate_name)
    EditText userCandidateNameEt;

    @BindView(R.id.add_user_btn)
    Button addUserBtn;

    @BindView(R.id.user_list_rv)
    RecyclerView userListRv;

    @BindView(R.id.new_transfer_fab)
    FloatingActionButton newTransferFab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_list);

        ButterKnife.bind(this);

        userListRv.setLayoutManager(new LinearLayoutManager(this));
        displayUsers();

        newTransferFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showTransferDialog();
            }
        });

        addUserBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userName = userNameEt.getText().toString().trim();
                String email = userEmailEt.getText().toString().trim();
                String userCandidateName = userCandidateNameEt.getText().toString().trim();

                Candidate candidate = new Candidate(userName, email, userCandidateName);

                if (!TextUtils.isEmpty(userName) && !TextUtils.isEmpty(email)
                        && !TextUtils.isEmpty(userCandidateName)) {
                    createNewUser(candidate);
                }

                clearTextEntries(userNameEt);
                clearTextEntries(userEmailEt);
                clearTextEntries(userCandidateNameEt);
            }
        });
    }


    private void clearTextEntries(EditText et) {
        et.getText().clear();
    }

    public void displayUsers() {

        fakeButtonClient = FakeButtonClient.getInstance();
        Call<List<Candidate>> call = fakeButtonClient.showAllUsers();
        call.enqueue(new Callback<List<Candidate>>() {
            @Override
            public void onResponse(Call<List<Candidate>> call, Response<List<Candidate>> response) {
                List<Candidate> candidateList = response.body();
                adapter = new FakeButtonAdapter(candidateList);
                userListRv.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<List<Candidate>> call, Throwable t) {
                Log.d(TAG, "There was a failure" + t.getMessage());
            }
        });
    }

    public void createNewUser(final Candidate candidate) {

        fakeButtonClient = FakeButtonClient.getInstance();
        Call<Candidate> postCall = fakeButtonClient.addUser(candidate);
        postCall.enqueue(new Callback<Candidate>() {
            @Override
            public void onResponse(Call<Candidate> call, Response<Candidate> response) {
                if (response.isSuccessful()) {
                    Toast toast = Toast.makeText(getApplicationContext(), candidate.getName() + " added to the list!", Toast.LENGTH_LONG);
                    toast.setGravity(Gravity.CENTER, 0, 0);
                    toast.show();
                    displayUsers();
                }
            }

            @Override
            public void onFailure(Call<Candidate> call, Throwable t) {
                Log.d(TAG, "There was a failure" + t.getMessage());
            }
        });
    }


    public void showTransferDialog() {
        FragmentManager manager = getSupportFragmentManager();
        DialogFragment newFragment = new TransferDialogFragment();
        newFragment.show(manager, "Transfer Dialog");
    }


    @Override
    public void onDialogPositiveClick(String amount, String id, String candidate) {
        Log.i(TAG, "onDialogPositiveClick: " + amount + id + candidate);
        Transfer newTransfer = new Transfer(amount, id, candidate);
        postNewTransfer(newTransfer);
    }


    private void postNewTransfer(Transfer transfer) {

        fakeButtonClient = FakeButtonClient.getInstance();
        Call<Transfer> postCall = fakeButtonClient.makeNewTransfer(transfer);
        postCall.enqueue(new Callback<Transfer>() {
            @Override
            public void onResponse(Call<Transfer> call, Response<Transfer> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(getApplicationContext(), "Your transfer was completed!", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<Transfer> call, Throwable t) {
                Log.d(TAG, "There was a failure" + t.getMessage());
            }
        });

    }

}