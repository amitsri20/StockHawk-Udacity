package com.sam_chordas.android.stockhawk.model;

/**
 * Created by amit on 3/30/2016.
 */
public class DateHigh {

    private String quoteDate;
    private String quoteHighValue;

    /**
     *
     * @return
     * The quoteDate
     */
    public String getQuoteDate() {
        return quoteDate;
    }

    /**
     *
     * @param quoteDate
     * The quote_date
     */
    public void setQuoteDate(String quoteDate) {
        this.quoteDate = quoteDate;
    }

    /**
     *
     * @return
     * The quoteHighValue
     */
    public String getQuoteHighValue() {
        return quoteHighValue;
    }

    /**
     *
     * @param quoteHighValue
     * The quote_high_value
     */
    public void setQuoteHighValue(String quoteHighValue) {
        this.quoteHighValue = quoteHighValue;
    }
}
