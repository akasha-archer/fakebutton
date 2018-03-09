package com.akashaarcher.android.fakebutton.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Akasha on 3/8/18.
 */

public class Transfer {

    private long id;
    private String amount;
    @SerializedName("user_id")
    @Expose
    private String userId;
    private String candidate;

    public Transfer(String amount, String userId, String candidate) {
        this.amount = amount;
        this.userId = userId;
        this.candidate = candidate;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getCandidate() {
        return candidate;
    }

    public void setCandidate(String candidate) {
        this.candidate = candidate;
    }

}
