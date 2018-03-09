package com.akashaarcher.android.fakebutton.model;

/**
 * Created by akashaarcher on 3/7/18.
 */

public class Candidate {

    private long id;
    private String name;
    private String email;
    private String candidate;

    public Candidate(String name, String email, String candidate) {
        this.name = name;
        this.email = email;
        this.candidate = candidate;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCandidate() {
        return candidate;
    }

    public void setCandidate(String candidate) {
        this.candidate = candidate;
    }
}
