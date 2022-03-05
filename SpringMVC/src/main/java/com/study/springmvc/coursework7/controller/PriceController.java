package com.study.springmvc.coursework7.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import yahoofinance.Stock;
import yahoofinance.YahooFinance;
import yahoofinance.histquotes.HistoricalQuote;
import yahoofinance.histquotes.Interval;
import yahoofinance.quotes.stock.StockQuote;

import java.io.IOException;
import java.util.Calendar;
import java.util.List;

@RestController
@RequestMapping("/coursework7/price")
public class PriceController {

    // symbol is may be ^TWII, 2330.TW
    @GetMapping("/histquotes/{symbol:.+}")
    public List<HistoricalQuote> queryHistoricalQuotes(@PathVariable String symbol) {
        Calendar from = Calendar.getInstance();
        Calendar to = Calendar.getInstance();
        from.add(Calendar.MONTH, -1);
        List<HistoricalQuote> historicalQuotes = null;
        try {
            Stock stock = YahooFinance.get(symbol);
            historicalQuotes = stock.getHistory(from, to, Interval.DAILY);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return historicalQuotes;
    }

    @GetMapping("/latest/{symbol:.+}")
    public StockQuote queryLatest(@PathVariable String symbol) {
        StockQuote quote = new StockQuote(symbol);
        try {
            Stock stock = YahooFinance.get(symbol);
            quote = stock.getQuote();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return quote;
    }
}
