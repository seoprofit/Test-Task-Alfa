package ru.testtask.services;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.testtask.models.GifModel;
import ru.testtask.models.RateModel;

import java.net.URI;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Service
public class Services {

    public URI tag;
    public DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    public LocalDate today = LocalDate.now();
    public LocalDate yesterday = today.minusDays(1);
    @Autowired
    private ExchangeService exchangeService;
    @Autowired
    private GifService gifService;

    public URI getTodayUrl() {
        today.format(timeFormatter);
        return URI.create("https://openexchangerates.org/api/historical/" + today + ".json?app_id=c41257283ac54b2fa4ed44c981fd8372&base=USD");
    }

    public URI getYesterdayUrl() {
        yesterday.format(timeFormatter);
        return URI.create("https://openexchangerates.org/api/historical/" + yesterday + ".json?app_id=c41257283ac54b2fa4ed44c981fd8372&base=USD");
    }


    public URI getRandomGifRich() {
        return URI.create("https://api.giphy.com/v1/gifs/random?api_key=xpGix8jcnJ0N3msQ0S9oUhiH7W3HQ5WO&tag=rich");
    }

    public URI getRandomGifBroke() {
        return URI.create("https://api.giphy.com/v1/gifs/random?api_key=xpGix8jcnJ0N3msQ0S9oUhiH7W3HQ5WO&tag=broke");
    }

    public int compareRates(RateModel r1, RateModel r2) {
        return r1.getRates().get("RUB").compareTo(r2.getRates().get("RUB"));
    }


    public RateModel createRateModelToday() {
        RateModel rateModelToday = exchangeService.getRate(getTodayUrl());
        return rateModelToday;
    }

    public RateModel createRateModelYesterday() {
        RateModel rateModelYesterday = exchangeService.getRate(getYesterdayUrl());
        return rateModelYesterday;
    }

    public GifModel getRandomGif() {
        int i = createRateModelToday().getRates().get("RUB").compareTo(createRateModelYesterday().getRates().get("RUB"));
        {

            if (i == -1) tag = getRandomGifBroke();
            else if (i == 1) tag = getRandomGifRich();
            else throw new RuntimeException();
        }
        GifModel gif = gifService.getGif(tag);
        String url = "https://media.giphy.com/media/" + gif.getData().getId() + "/giphy.gif";
        gif.setUrl(url);
        return gif;
    }


}
