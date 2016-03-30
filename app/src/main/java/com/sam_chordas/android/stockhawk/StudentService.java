package com.sam_chordas.android.stockhawk;

import com.sam_chordas.android.stockhawk.model.QuoteInfo;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Tan on 8/10/2015.
 */
public interface StudentService {

    //So these are the list available in our WEB API and the methods look straight forward


    @GET("public/yql?q=select%20*%20from%20yahoo.finance.historicaldata%20where%20symbol%20%3D%20%22YHOO%22%20and%20startDate%20%3D%20%222015-10-01%22%20and%20endDate%20%3D%20%222016-03-01%22&format=json&diagnostics=true&env=store%3A%2F%2Fdatatables.org%2Falltableswithkeys&callback=")
    Call<QuoteInfo> getObjectWithNestedArraysAndObject();
}
