package com.readme.utils.emoji.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class EmojiStatusDto {

    private long episodeId;
    private int episodeRow;
    private int emoji;
}
