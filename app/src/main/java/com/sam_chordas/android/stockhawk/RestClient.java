package com.sam_chordas.android.stockhawk;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Tan on 8/10/2015.
 */
public class RestClient {
    //You need to change the IP if you testing environment is not local machine
    //or you may have different URL than we have here
//    private static final String URL = "http://instinctcoder.com/wp-content/uploads/2015/08/";
    private static final String URL = "https://query.yahooapis.com/v1/";
//    private retrofit.RestAdapter restAdapter;
    private StudentService studentService;

    public RestClient()
    {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();


//        studentService = restAdapter.create(StudentService.class);
        studentService = retrofit.create(StudentService.class);
    }

    public StudentService getService()
    {
        return studentService;
    }
}
