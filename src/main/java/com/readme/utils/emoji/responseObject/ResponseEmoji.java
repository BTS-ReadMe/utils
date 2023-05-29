package com.readme.utils.emoji.responseObject;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ResponseEmoji {
    private long episodeId;
    private long episodeRow;
    private int emoji;
    private long count;
}
