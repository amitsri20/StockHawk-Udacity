package com.sam_chordas.android.stockhawk.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.github.mikephil.charting.data.Entry;
import com.sam_chordas.android.stockhawk.R;
import com.sam_chordas.android.stockhawk.QuoteService;
import com.sam_chordas.android.stockhawk.model.DateHigh;
import com.sam_chordas.android.stockhawk.model.DateHighMain;
import com.sam_chordas.android.stockhawk.model.Quote;
import com.sam_chordas.android.stockhawk.model.QuoteInfo;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DetailActivity extends AppCompatActivity implements Callback<QuoteInfo> {
//public class DetailActivity extends AppCompatActivity {

    TextView textView;
    String result;
    ProgressBar pb;

    DateHighMain dhm = new DateHighMain();


    public ArrayList<Entry> valueSet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
//        pb.setVisibility(View.VISIBLE);
//        LineChartFragment fragment = new LineChartFragment();
//        getSupportFragmentManager().beginTransaction()
//                .replace(R.id.container_fg, fragment)
//                .commit();

        String symbol = getIntent().getExtras().getString("symbol");
        pb = (ProgressBar)findViewById(R.id.progressBar);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://query.yahooapis.com/v1/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        // prepare call in Retrofit 2.0
        QuoteService stackOverflowAPI = retrofit.create(QuoteService.class);
//        String q = "select%20*%20from%20yahoo.finance.historicaldata%20where%20symbol%20%3D%20%22YHOO%22%20and%20startDate%20%3D%20%222016-03-21%22%20and%20endDate%20%3D%20%222016-03-25%22&format=json&diagnostics=true&env=store%3A%2F%2Fdatatables.org%2Falltableswithkeys&callback=";
//        Call<QuoteInfo> call = restClient.getService().getObjectWithNestedArraysAndObject();
        String q = "select * from yahoo.finance.historicaldata where symbol = \""+symbol+"\" and startDate = \"2015-10-23\" and endDate = \"2016-03-23\"";
        String diagnostics = "true";
        String env = "store://datatables.org/alltableswithkeys";
        String format = "json";
        Call<QuoteInfo> call = stackOverflowAPI.getObjectWithNestedArraysAndObject(q,diagnostics,env,format);

        //asynchronous call
        call.enqueue(this);

//        });
    }

    @Override
    public void onResponse(Call<QuoteInfo> call, Response<QuoteInfo> response) {
        pb.setVisibility(View.GONE);
//        mainlayout.setVisibility(View.VISIBLE);
        QuoteInfo quoteInfo = response.body();
        //Storing the response if any

        valueSet = new ArrayList<>();
        ArrayList<Quote> quoteArray = quoteInfo.query.results.quote;
        int i=0;

        for (i=0;i<quoteArray.size();i++
             ) {
            DateHigh dh = new DateHigh();
            dh.setQuoteDate(quoteArray.get(i).quote_date);
            dh.setQuoteHighValue(quoteArray.get(i).high);
            dhm.getDatehigh().add(dh);
        }



        Fragment fragment = LineChartFragment.newInstance(dhm);

//        if (result != null) {
//            Bundle args = new Bundle();
//            args.putParcelable(LineChartFragment.xAxisValues, valueSet);
//            fragment.setArguments(args);
//        }
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container_fg, fragment)
                .commit();
    }

    @Override
    public void onFailure(Call<QuoteInfo> call, Throwable t) {
        Toast.makeText(DetailActivity.this, t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
    }

}
