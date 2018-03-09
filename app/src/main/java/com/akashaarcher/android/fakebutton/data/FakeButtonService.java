package com.akashaarcher.android.fakebutton.data;

import com.akashaarcher.android.fakebutton.model.Candidate;
import com.akashaarcher.android.fakebutton.model.Transfer;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Created by akashaarcher on 3/7/18.
 */

public interface FakeButtonService {

    @POST("user")
    Call<Candidate> addNewUser(@Body Candidate candidate);

    @POST("transfer")
    Call<Transfer> createNewTransfer(@Body Transfer transfer);

    @GET("user?candidate=cjm123")
    Call<List<Candidate>> listUsers();



}
