package com.dh.serieservice.queue;

import com.dh.serieservice.model.Serie;
import com.dh.serieservice.service.SerieService;
import lombok.Data;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Data
@Component
public class SerieSender {

    private final SerieService serieService;
    private final RabbitTemplate rabbitTemplate;
    private final Queue queue;

    @Autowired
    public SerieSender(SerieService serieService, RabbitTemplate rabbitTemplate, Queue queue) {
        this.serieService = serieService;
        this.rabbitTemplate = rabbitTemplate;
        this.queue = queue;
    }

    public void send(Serie serie) {
        Serie s = serieService.save(serie);
        this.rabbitTemplate.convertAndSend(this.queue.getName(), s);
    }
}
