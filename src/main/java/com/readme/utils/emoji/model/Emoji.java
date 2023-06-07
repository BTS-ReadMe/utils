package com.readme.utils.emoji.model;

import com.readme.utils.emoji.dto.EmojiDto;
import com.readme.utils.utils.BaseTimeEntity;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Emoji extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String uuid;
    private Long episodeId;
    private Integer emoji;
    private Integer episodeRow;

    public Emoji(EmojiDto emojiDto) {
        this.uuid = emojiDto.getUuid();
        this.episodeId = emojiDto.getEpisodeId();
        this.emoji = emojiDto.getEmoji();
        this.episodeRow = emojiDto.getEpisodeRow();
    }
}
