package com.sam_chordas.android.stockhawk.ui;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.sam_chordas.android.stockhawk.R;
import com.sam_chordas.android.stockhawk.RestClient;
import com.sam_chordas.android.stockhawk.StudentService;
import com.sam_chordas.android.stockhawk.model.Quote;
import com.sam_chordas.android.stockhawk.model.QuoteInfo;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DetailActivity extends Activity implements Callback<QuoteInfo> {

    RestClient restClient;
    TextView textView;
    String result;
    ProgressBar pb;
    RelativeLayout mainlayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        String symbol = getIntent().getExtras().getString("symbol");
        mainlayout = (RelativeLayout)findViewById(R.id.mainLayout);
        textView = (TextView)findViewById(R.id.textView);
        pb = (ProgressBar)findViewById(R.id.progressBar);
        textView.setText(symbol);
        mainlayout.setVisibility(View.INVISIBLE);
//
//        setProgressBarIndeterminateVisibility(true);
//        setProgressBarVisibility(true);
//        restClient = new RestClient();
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
//        setProgressBarIndeterminateVisibility(false);
//        textView.setText(response.body().query.lang);
        pb.setVisibility(View.GONE);
        mainlayout.setVisibility(View.VISIBLE);
        QuoteInfo quoteInfo = response.body();
        for (Quote s:quoteInfo.query.results.quote
        ) {
            result+=s.high+"\n";
        }
        textView.setText(result);
//        Log.d("result:::",quoteInfo.query.results.quote.get(1).high);
    }

    @Override
    public void onFailure(Call<QuoteInfo> call, Throwable t) {
        Toast.makeText(DetailActivity.this, t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
    }

}
