package com.readme.utils.emoji.service;

import com.readme.utils.emoji.dto.EmojiDto;
import java.util.Map;

public interface EmojiService {

    void addEmoji(EmojiDto emojiDto);

    Map<Long, Map<String, Long>> getEmojiByEpisodeId(String uuid, Long episodeId);
}
