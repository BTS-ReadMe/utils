package com.readme.utils.emoji.service;

import com.readme.utils.emoji.dto.EmojiDto;
import com.readme.utils.emoji.dto.EmojiGroupDto;
import com.readme.utils.emoji.model.Emoji;
import com.readme.utils.emoji.repository.EmojiRepository;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmojiServiceImpl implements EmojiService {

    private final EmojiRepository emojiRepository;

    @Transactional
    @Override
    public void addEmoji(EmojiDto emojiDto) {

        if (emojiRepository.existsByUuidAndEpisodeIdAndEpisodeRow(emojiDto.getUuid(),
            emojiDto.getEpisodeId(), emojiDto.getEpisodeRow())) {

            Emoji emoji = emojiRepository.findByUuidAndEpisodeIdAndEpisodeRow(emojiDto.getUuid(),
                emojiDto.getEpisodeId(), emojiDto.getEpisodeRow());
            emoji.setEmoji(emojiDto.getEmoji());
            emojiRepository.save(emoji);
        } else {
            Emoji emoji = new Emoji(emojiDto);
            emojiRepository.save(emoji);
        }
    }

    @Override
    public Map<Long, Map<String, Long>> getEmojiByEpisodeId(String uuid, Long episodeId) {
        List<EmojiGroupDto> list = emojiRepository.getEmojiGroups(episodeId);

        Map<Long, Map<String, Long>> resultMap = new HashMap<>();

        list.forEach(emojiGroupDto -> {
            Map<String, Long> map = new HashMap<>();
            map.put("emoji1", 0L);
            map.put("emoji2", 0L);
            map.put("emoji3", 0L);

            resultMap.put((long)emojiGroupDto.getEpisodeRow(), map);
        });

        list.forEach(emojiGroupDto -> {
            String key = "emoji"+emojiGroupDto.getEmoji();
            Long value = emojiGroupDto.getCount();
            resultMap.get((long)emojiGroupDto.getEpisodeRow()).merge(key, value, Long::sum);
        });

        log.info("결과!!! : " + resultMap);

        return resultMap;
    }
}
