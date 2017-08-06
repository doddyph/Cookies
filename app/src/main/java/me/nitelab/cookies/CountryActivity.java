package me.nitelab.cookies;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.nitelab.cookies.adapter.CountryAdapter;
import me.nitelab.cookies.model.Country;
import me.nitelab.cookies.presenter.CountryPresenter;

public class CountryActivity extends AppCompatActivity implements CountryPresenter.CountryView {

    @BindView(R.id.country_progress_bar)
    ProgressBar progressBar;
    @BindView(R.id.country_list)
    RecyclerView recyclerView;

    private CountryAdapter countryAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_country);
        ButterKnife.bind(this);

        RecyclerView.LayoutManager layoutManager =
                new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        countryAdapter = new CountryAdapter(new ArrayList<Country>());
        recyclerView.setAdapter(countryAdapter);

        new CountryPresenter(this).getCountries();
    }

    @Override
    public void onProgress(boolean onProgress) {
        if (onProgress) progressBar.setVisibility(View.VISIBLE);
        else progressBar.setVisibility(View.GONE);
    }

    @Override
    public void onSuccess(List<Country> countries) {
        if (countries != null) countryAdapter.update(countries);
    }

    @Override
    public void onFailure(String mesasage) {
        Toast.makeText(this, mesasage, Toast.LENGTH_LONG).show();
    }
}
