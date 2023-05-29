package com.readme.utils.emoji.repository;

import com.readme.utils.emoji.model.Emoji;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmojiRepository extends JpaRepository<Emoji, Long> {

    boolean existsByUuidAndEpisodeIdAndEpisodeRow(String uuid, long episodeId, int episodeRow);

    Emoji findByUuidAndEpisodeIdAndEpisodeRow(String uuid, long episodeId, int episodeRow);
}
