package com.akashaarcher.android.fakebutton.data;

/**
 * Created by akashaarcher on 3/7/18.
 */

public class FakeButtonApiUtils {

    private FakeButtonApiUtils() {

    }

    public static final String BASE_URL = "http://fake-button.herokuapp.com/";

    public static FakeButtonService getFakeButtonService() {

        return FakeButtonClient.getClient(BASE_URL).create(FakeButtonService.class);
    }
}
