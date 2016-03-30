package com.sam_chordas.android.stockhawk.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.github.mikephil.charting.data.Entry;
import com.sam_chordas.android.stockhawk.R;
import com.sam_chordas.android.stockhawk.StudentService;
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
        StudentService stackOverflowAPI = retrofit.create(StudentService.class);

//        Call<QuoteInfo> call = restClient.getService().getObjectWithNestedArraysAndObject();
        Call<QuoteInfo> call = stackOverflowAPI.getObjectWithNestedArraysAndObject();

        //asynchronous call
        call.enqueue(this);

//        });
    }

    @Override
    public void onResponse(Call<QuoteInfo> call, Response<QuoteInfo> response) {
        pb.setVisibility(View.GONE);
//        mainlayout.setVisibility(View.VISIBLE);
        QuoteInfo quoteInfo = response.body();
        for (Quote s:quoteInfo.query.results.quote
        ) {
            result+=s.high+"\n";
        }
//        textView.setText(result);
//        Log.d("result:::",quoteInfo.query.results.quote.get(1).high);
        //Storing the response if any

        valueSet = new ArrayList<>();
        int i=0;
        DateHigh dh = new DateHigh();
        for (Quote highVal:quoteInfo.query.results.quote
             ) {
            dh.quote_date = highVal.quote_date;
            dh.quote_high_value = highVal.high;
//            valueSet.add(new Entry(Float.parseFloat(highVal.high),i++));

        }
//        Entry v1e1 = new Entry(110.000f, 0);
//        valueSet1.add(v1e1);
        DateHighMain dhm = new DateHighMain();
//        if(dhm.datehigh.size()>0) {
//            dhm.datehigh.clear();
//        }
        dhm.datehigh.add(dh);


        LineChartFragment fragment = new LineChartFragment();

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
