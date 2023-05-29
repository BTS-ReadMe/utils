package com.readme.utils.emoji.repository;

import com.readme.utils.emoji.dto.EmojiGroupDto;
import com.readme.utils.emoji.model.Emoji;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface EmojiRepository extends JpaRepository<Emoji, Long> {

    boolean existsByUuidAndEpisodeIdAndEpisodeRow(String uuid, long episodeId, int episodeRow);

    Emoji findByUuidAndEpisodeIdAndEpisodeRow(String uuid, long episodeId, int episodeRow);

//    @Query(value = "SELECT episode_id, episode_row, emoji, COUNT(emoji) FROM emoji "
//        + "WHERE episode_id = ? GROUP BY emoji, episode_row ORDER BY episode_row",
//        nativeQuery = true)
    @Query("SELECT new com.readme.utils.emoji.dto.EmojiGroupDto(e.episodeId, e.episodeRow, e.emoji, COUNT(e.emoji)) "
        + "FROM Emoji e "
        + "WHERE e.episodeId = :episodeId "
        + "GROUP BY e.emoji, e.episodeRow "
        + "ORDER BY e.episodeRow")
    List<EmojiGroupDto> getEmojiGroups(@Param("episodeId") Long episodeId);

}
