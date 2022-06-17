package ru.testtask.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.testtask.models.GifModel;
import ru.testtask.models.RateModel;
import ru.testtask.services.ExchangeService;
import ru.testtask.services.GifService;
import ru.testtask.services.Services;

import java.net.URI;

@Controller
public class MainController {

    @Autowired
    private ExchangeService exchangeService;
    @Autowired
    private GifService gifService;
    @Autowired
    private Services services;

    @GetMapping("/")
    public String home(Model model) {
        GifModel gif = services.getRandomGif();
        model.addAttribute("url", gif.getUrl());
        model.addAttribute("pars_url", services.tag);
        model.addAttribute("rateToday", services.createRateModelToday().getRates().get("RUB"));
        model.addAttribute("rateYesterday", services.createRateModelYesterday().getRates().get("RUB"));
        return "index";
    }
}


