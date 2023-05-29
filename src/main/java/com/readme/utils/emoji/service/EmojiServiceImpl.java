package com.readme.utils.emoji.service;

import com.readme.utils.emoji.dto.EmojiDto;
import com.readme.utils.emoji.model.Emoji;
import com.readme.utils.emoji.repository.EmojiRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
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
}
