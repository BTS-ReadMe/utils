package com.readme.utils.messagequeue;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.readme.utils.emoji.dto.EmojiStatusDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class EmojiStatusProducer {

    private final KafkaTemplate<String, Object> kafkaTemplate;

    public void sendEmojiStatus(long episodeId, int episodeRow, int emoji) {

        ObjectMapper mapper = new ObjectMapper();

        EmojiStatusDto emojiStatusDto =
            EmojiStatusDto.builder()
                .episodeId(episodeId)
                .episodeRow(episodeRow)
                .emoji(emoji)
                .build();

        try {
            String data = mapper.writeValueAsString(emojiStatusDto);
            kafkaTemplate.send("emojiStatus", data);
        } catch (Exception e){
            log.info(e.toString());
        }
    }
}
