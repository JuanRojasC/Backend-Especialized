package com.dh.movieservice.queue;

import com.dh.movieservice.model.Movie;
import com.dh.movieservice.service.MovieService;
import lombok.Data;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Data
@Component
public class MovieSender {

    private final MovieService movieService;
    private final RabbitTemplate rabbitTemplate;
    private final Queue queue;

    @Autowired
    public MovieSender(MovieService movieService, RabbitTemplate rabbitTemplate, Queue queue) {
        this.movieService = movieService;
        this.rabbitTemplate = rabbitTemplate;
        this.queue = queue;
    }

    public void send(Movie movie) {
        Movie m = movieService.save(movie);
        this.rabbitTemplate.convertAndSend(this.queue.getName(), m);
    }
}
