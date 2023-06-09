package com.readme.utils.config;

import com.readme.utils.starrating.dto.StarRatingKafkaDto;
import java.util.HashMap;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

@EnableKafka
@Configuration
@RequiredArgsConstructor
public class KafkaProducerConfig {

    private final Environment environment;

    @Bean
    public Map<String, Object> producerConfigs() {
        return CommonJsonSerializer.getStringObjectMap(environment.getProperty("kafka-config"));
    }

    @Bean
    public ProducerFactory<String, StarRatingKafkaDto> StarRatingKafkaProducerFactory() {
        return new DefaultKafkaProducerFactory<>(producerConfigs());
    }

    @Bean
    public KafkaTemplate<String, StarRatingKafkaDto> StarRatingKafkaKafkaTemplate() {
        return new KafkaTemplate<>(StarRatingKafkaProducerFactory());
    }

    @Bean
    public ProducerFactory<String, Object> getEmojiStatusProducerFactory() {
        Map<String, Object> properties = new HashMap<>();
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, environment.getProperty("kafka-config"));
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);

        return new DefaultKafkaProducerFactory<>(properties);
    }

    @Bean
    public KafkaTemplate<String, Object> kafkaTemplate() {
        return new KafkaTemplate<>(getEmojiStatusProducerFactory());
    }
}
