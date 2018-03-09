package com.akashaarcher.android.fakebutton.data;

import com.akashaarcher.android.fakebutton.model.Candidate;
import com.akashaarcher.android.fakebutton.model.Transfer;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Created by akashaarcher on 3/7/18.
 */

public class FakeButtonClient {

    private final String BASE_URL = "http://fake-button.herokuapp.com/";

    private static FakeButtonClient instance;
    private final FakeButtonService fakeButtonService;

    public static FakeButtonClient getInstance() {
        if (instance == null) {
            instance = new FakeButtonClient();
        }
        return instance;
    }

    private FakeButtonClient() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        fakeButtonService = retrofit.create(FakeButtonService.class);
    }

   public Call<Candidate> addUser(@Body Candidate candidate) {
        return fakeButtonService.addNewUser(candidate);
   }

    public Call<List<Candidate>> showAllUsers() {
        return fakeButtonService.listUsers();
    }

    public Call<Transfer> makeNewTransfer(@Body Transfer transfer) {
        return fakeButtonService.createNewTransfer(transfer);
    }


}
