package com.readme.utils.emoji.service;

import com.readme.utils.emoji.dto.EmojiDto;
import com.readme.utils.emoji.responseObject.ResponseEmoji;
import java.util.List;
import java.util.Map;

public interface EmojiService {

    void addEmoji(EmojiDto emojiDto);

    List<ResponseEmoji> getEmojiByEpisodeId(String uuid, Long episodeId);
}
