package com.dh.catalogservice.queue;

import com.dh.catalogservice.service.CatalogService;
import com.dh.catalogservice.vo.Movie;
import com.dh.catalogservice.vo.Serie;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Data
@Component
@Slf4j
public class MoviesAndSeriesListener {

    private final CatalogService catalogService;

    @RabbitListener(queues = {"${queue.movie.name}"})
    public void receiveMovie(@Payload Movie movie) {
        log.info("New Movie added {}", movie.getId());
        catalogService.addMovie(movie);
    }

    @RabbitListener(queues = {"${queue.serie.name}"})
    public void receiveSerie(@Payload Serie serie) {
        log.info("New Serie added {}", serie.getId());
        catalogService.addSerie(serie);
    }

}
