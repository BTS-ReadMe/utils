package com.readme.utils.emoji.dto;

import com.readme.utils.emoji.requestObject.RequestAddEmoji;
import lombok.Getter;

@Getter
public class EmojiDto {
    private String uuid;
    private long episodeId;
    private int emoji;
    private int episodeRow;

    public EmojiDto(String uuid, RequestAddEmoji requestAddEmoji) {
        this.uuid = uuid;
        this.episodeId = requestAddEmoji.getEpisodeId();
        this.emoji = requestAddEmoji.getEmoji();
        this.episodeRow = requestAddEmoji.getEpisodeRow();
    }
}
