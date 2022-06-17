package ru.testtask.services;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import ru.testtask.models.RateModel;
import java.net.URI;

@FeignClient(name = "Exchange", url = "${server.exchange.url}")
public interface ExchangeService {
    @GetMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    RateModel getRate(URI uri);


}
