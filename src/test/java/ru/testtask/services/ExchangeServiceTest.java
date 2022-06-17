package ru.testtask.services;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.testtask.models.RateModel;


@SpringBootTest
public class ExchangeServiceTest {

    @Autowired
    ExchangeService exchangeService;
    @Autowired
    Services services;
    @Test
    public void getRate ()
    {
        RateModel rateModelToday = exchangeService.getRate(services.getTodayUrl());
        Assertions.assertNotNull(rateModelToday);
        RateModel rateModelYesterday = exchangeService.getRate(services.getYesterdayUrl());
        Assertions.assertNotNull(rateModelYesterday);
    }


}
