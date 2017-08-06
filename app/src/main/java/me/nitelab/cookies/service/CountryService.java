package me.nitelab.cookies.service;

import me.nitelab.cookies.model.CountryResponse;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

/**
 * Created by dph on 06/08/17.
 */

public class CountryService {

    private static final String BASE_URL = "http://services.groupkt.com/";

    public interface CountryAPI {
        @GET("country/get/all")
        Call<CountryResponse> getCountries();
    }

    public CountryAPI getAPI() {
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(loggingInterceptor);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient.build())
                .build();

        return retrofit.create(CountryAPI.class);
    }
}
