package com.readme.utils.emoji.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class EmojiGroupDto {
    private long episodeId;
    private int episodeRow;
    private int emoji;
    private long count;

}
