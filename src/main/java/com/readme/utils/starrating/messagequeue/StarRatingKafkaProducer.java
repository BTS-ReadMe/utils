package com.readme.utils.starrating.messagequeue;

import com.readme.utils.starrating.dto.StarRatingKafkaDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class StarRatingKafkaProducer {
    private final KafkaTemplate<String, StarRatingKafkaDto> starRatingKafkaTemplate;

    public void updateStarRating(String topic, StarRatingKafkaDto starRatingKafkaDto) {


        starRatingKafkaTemplate.send(topic, starRatingKafkaDto);
        log.info("topic : {}, message : {}", topic, starRatingKafkaDto);
    }
}
