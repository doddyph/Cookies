package me.nitelab.cookies.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.nitelab.cookies.R;
import me.nitelab.cookies.model.Country;

/**
 * Created by dph on 06/08/17.
 */

public class CountryAdapter extends RecyclerView.Adapter<CountryAdapter.CountyViewHolder> {

    private List<Country> countries;

    public CountryAdapter(List<Country> countries) {
        this.countries = countries;
    }

    public void update(List<Country> countries) {
        this.countries = countries;
        notifyDataSetChanged();
    }

    @Override
    public CountyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.country_row_list, parent, false);
        return new CountyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(CountyViewHolder holder, int position) {
        Country country = countries.get(position);
        holder.tvName.setText(country.getName());
        holder.tvCode.setText(country.getAlphaCode2()+"/"+country.getAlphaCode3());
    }

    @Override
    public int getItemCount() {
        return countries.size();
    }

    class CountyViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.country_name)
        TextView tvName;
        @BindView(R.id.country_code)
        TextView tvCode;

        public CountyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
