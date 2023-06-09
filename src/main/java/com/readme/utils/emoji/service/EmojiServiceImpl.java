package com.readme.utils.emoji.service;

import com.readme.utils.emoji.dto.EmojiDto;
import com.readme.utils.emoji.dto.EmojiGroupDto;
import com.readme.utils.emoji.model.Emoji;
import com.readme.utils.emoji.repository.EmojiRepository;
import com.readme.utils.emoji.responseObject.ResponseEmoji;
import com.readme.utils.emoji.responseObject.ResponseEmoji.ResponseEmojiDetail;
import com.readme.utils.messagequeue.EmojiStatusProducer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmojiServiceImpl implements EmojiService {

    private final EmojiRepository emojiRepository;
    private final EmojiStatusProducer emojiStatusProducer;

    @Transactional(isolation = Isolation.SERIALIZABLE)
    @Override
    public void addEmoji(EmojiDto emojiDto) {

        if (emojiRepository.existsByUuidAndEpisodeIdAndEpisodeRow(emojiDto.getUuid(),
            emojiDto.getEpisodeId(), emojiDto.getEpisodeRow())) {

            Emoji emoji = emojiRepository.findByUuidAndEpisodeIdAndEpisodeRow(emojiDto.getUuid(),
                emojiDto.getEpisodeId(), emojiDto.getEpisodeRow());
            if (emoji.getEmoji() != emojiDto.getEmoji()) {
                emoji.setEmoji(emojiDto.getEmoji());
                emojiRepository.save(emoji);
                emojiStatusProducer.sendEmojiStatus(emojiDto.getEpisodeId(), emojiDto.getEpisodeRow(),
                    emojiDto.getEmoji());
            } else {
                emojiRepository.deleteById(emoji.getId());
            }

        } else {
            Emoji emoji = new Emoji(emojiDto);
            emojiRepository.save(emoji);
            emojiStatusProducer.sendEmojiStatus(emojiDto.getEpisodeId(), emojiDto.getEpisodeRow(),
                emojiDto.getEmoji());
        }
    }

    @Transactional(readOnly = true, isolation = Isolation.SERIALIZABLE)
    @Override
    public List<ResponseEmoji> getEmojiByEpisodeId(String uuid, Long episodeId) {
        List<EmojiGroupDto> list = emojiRepository.getEmojiGroups(episodeId);

        Map<Long, Map<String, Long>> resultMap = new LinkedHashMap<>();

        list.forEach(emojiGroupDto -> {
            Map<String, Long> map = new HashMap<>();
            map.put("1", 0L);
            map.put("2", 0L);
            map.put("3", 0L);
            resultMap.put((long) emojiGroupDto.getEpisodeRow(), map);

        });

        list.forEach(emojiGroupDto -> {
            String key = Integer.toString(emojiGroupDto.getEmoji());
            Long value = emojiGroupDto.getCount();
            resultMap.get((long) emojiGroupDto.getEpisodeRow()).merge(key, value, Long::sum);
        });

        log.info("결과!!! : " + resultMap);

        List<ResponseEmoji> responseEmojiList = new ArrayList<>();
        resultMap.forEach((row, data) -> {
            ResponseEmoji responseEmoji = new ResponseEmoji(row, new ArrayList<>());

            if (emojiRepository.existsByUuidAndEpisodeIdAndEpisodeRow(uuid, episodeId,
                row.intValue())) {
                Emoji userEmoji = emojiRepository.findByUuidAndEpisodeIdAndEpisodeRow(uuid,
                    episodeId, row.intValue());

                data.forEach((key, value) -> {
                    boolean checked = key.equals(userEmoji.getEmoji().toString());
                    ResponseEmojiDetail responseEmojiDetail = new ResponseEmojiDetail(
                        Long.parseLong(key), checked, value);
                    responseEmoji.getEmoji().add(responseEmojiDetail);
                });

            } else {
                data.forEach((key, value) -> {
                    ResponseEmojiDetail responseEmojiDetail = new ResponseEmojiDetail(
                        Long.parseLong(key), false, value);
                    responseEmoji.getEmoji().add(responseEmojiDetail);

                });
            }

            responseEmojiList.add(responseEmoji);
        });

        return responseEmojiList;
    }
}
