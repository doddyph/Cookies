package me.nitelab.cookies.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by dph on 06/08/17.
 */

public class CountryResponse {
    @SerializedName("RestResponse")
    private RestResponse restResponse;

    public RestResponse getRestResponse() {
        return restResponse;
    }

    public class RestResponse {
        private List<String> messages;
        private List<Country> result;

        public List<String> getMessages() {
            return messages;
        }

        public List<Country> getResult() {
            return result;
        }
    }
}
