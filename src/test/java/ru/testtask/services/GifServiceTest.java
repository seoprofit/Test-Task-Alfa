package ru.testtask.services;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.testtask.models.GifModel;



@SpringBootTest
public class GifServiceTest {
    @Autowired
    GifService gifService;
    @Autowired
    Services services;

    @Test
    public void getRandomGif ()
    {
        GifModel gifRich =  gifService.getGif(services.getRandomGifRich());
        Assertions.assertNotNull(gifRich);
        GifModel gifBroke =  gifService.getGif(services.getRandomGifBroke());
        Assertions.assertNotNull(gifBroke);
    }

}
