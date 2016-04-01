package com.sam_chordas.android.stockhawk.ui;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.sam_chordas.android.stockhawk.R;
import com.sam_chordas.android.stockhawk.model.DateHigh;
import com.sam_chordas.android.stockhawk.model.DateHighMain;

import java.util.ArrayList;



public class LineChartFragment extends Fragment {

    private Typeface tf;
    static final ArrayList<Entry> xAxisValues= null;
//    DateHighMain dhm = new DateHighMain();
private static final String DESCRIBABLE_KEY = "describable_key";
    private DateHighMain mDescribable;


    public LineChartFragment() {
        // Required empty public constructor
    }

    public static LineChartFragment newInstance(DateHighMain describable) {
        LineChartFragment fragment = new LineChartFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable(DESCRIBABLE_KEY, describable);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_line_chart, container, false);

        mDescribable = (DateHighMain) getArguments().getSerializable(
                DESCRIBABLE_KEY);
        LineChart mChart = (LineChart) rootView.findViewById(R.id.chart);
        LineData data = new LineData(getXAxisValues(),getDataSet());

//        data.setValueTypeface(tf);
//        DateHighMain dhm = new DateHighMain();

//        for (DateHigh dh:mDescribable.datehigh
//                ) {
////            Log.d("Quote value::",dh.quote_date+dh.quote_high_value);
//        }
        setUpChart(mChart,data);


        return rootView;
    }

    private void setUpChart(LineChart mChart, LineData data) {
        mChart.setData(data);
        mChart.setDescription("Quote history");
        mChart.setNoDataTextDescription("No data available.");
        mChart.setDrawGridBackground(false);
//        chart.getRenderer().getGridPaint().setGridColor(Color.WHITE & 0x70FFFFFF);

        // enable touch gestures
        mChart.setTouchEnabled(true);
        data.setDrawValues(true);
        // enable scaling and dragging
        mChart.setDragEnabled(false);
        mChart.setScaleEnabled(false);
        mChart.setPinchZoom(false);

        mChart.setBackgroundColor(Color.BLACK);
//        tf = Typeface.createFromAsset(getContext().getAssets(), "Roboto-Medium.ttf");
        Legend l = mChart.getLegend();

        // modify the legend ...
        // l.setPosition(LegendPosition.LEFT_OF_CHART);
        l.setForm(Legend.LegendForm.LINE);
//        l.setTypeface(tf);
        l.setTextSize(11f);
        l.setTextColor(Color.WHITE);
        l.setPosition(Legend.LegendPosition.BELOW_CHART_LEFT);
//        l.setYOffset(11f);

        XAxis xAxis = mChart.getXAxis();
//        xAxis.setTypeface(tf);
        xAxis.setTextSize(12f);
        xAxis.setTextColor(Color.WHITE);
        xAxis.setDrawGridLines(false);
        xAxis.setDrawAxisLine(false);
        xAxis.setSpaceBetweenLabels(1);
        xAxis.setEnabled(false);

        YAxis leftAxis = mChart.getAxisLeft();
//        leftAxis.setTypeface(tf);
        leftAxis.setTextColor(Color.WHITE);
        leftAxis.setAxisMaxValue(200f);
        leftAxis.setDrawGridLines(true);

//        YAxis rightAxis = mChart.getAxisRight();
////        rightAxis.setTypeface(tf);
//        rightAxis.setTextColor(Color.WHITE);
//        rightAxis.setAxisMaxValue(900);
//        rightAxis.setStartAtZero(false);
//        rightAxis.setAxisMinValue(-200);
//        rightAxis.setDrawGridLines(false);
        mChart.animateXY(2000, 2000);
        mChart.invalidate();
    }

    private ArrayList<ILineDataSet> getDataSet() {
        ArrayList<ILineDataSet> dataSets = null;

        ArrayList<Entry> valueSet1 = new ArrayList<>();
//        Entry v1e1 = new Entry(110.000f, 0); // Jan
        int i=0;

        for (DateHigh dh:mDescribable.datehigh
                ) {
//            Log.d("Quote value::",dh.quote_date+dh.quote_high_value);
            valueSet1.add(new Entry(Float.parseFloat(dh.getQuoteHighValue()), i++));
        }



        LineDataSet lineDataSet = new LineDataSet(valueSet1, "Brand 1");
        lineDataSet.setColor(Color.rgb(0, 155, 0));

        dataSets = new ArrayList<>();
        dataSets.add(lineDataSet);
//        dataSets.add(barDataSet2);
        return dataSets;
    }

    private ArrayList<String> getXAxisValues() {
        ArrayList<String> xAxis = new ArrayList<>();

        for (DateHigh dh:mDescribable.datehigh
                ) {
            xAxis.add("A");
        }
        return xAxis;
    }





}
