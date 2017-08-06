package me.nitelab.cookies.presenter;

import java.util.List;

import me.nitelab.cookies.model.Country;
import me.nitelab.cookies.model.CountryResponse;
import me.nitelab.cookies.service.CountryService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by dph on 06/08/17.
 */

public class CountryPresenter {

    private CountryService countryService;
    private CountryView view;

    public interface CountryView {
        void onProgress(boolean onProgress);
        void onSuccess(List<Country> countries);
        void onFailure(String message);
    }

    public CountryPresenter(CountryView view) {
        countryService = new CountryService();
        this.view = view;
    }

    public void getCountries() {
        view.onProgress(true);
        countryService.getAPI().getCountries()
                .enqueue(new Callback<CountryResponse>() {
                    @Override
                    public void onResponse(Call<CountryResponse> call, Response<CountryResponse> response) {
                        view.onProgress(false);
                        CountryResponse.RestResponse restResponse = response.body().getRestResponse();
                        if (restResponse != null) {
                            List<Country> countries = restResponse.getResult();
                            if (countries != null) view.onSuccess(countries);
                            else view.onFailure("List of Country is null");
                        }
                        else view.onFailure("RestResponse is null");
                    }

                    @Override
                    public void onFailure(Call<CountryResponse> call, Throwable t) {
                        view.onProgress(false);
                        view.onFailure(t.getMessage());
                    }
                });
    }
}
