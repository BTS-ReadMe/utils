package com.readme.utils.starrating.repository;

import com.readme.utils.starrating.model.StarRating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface StarRepository extends JpaRepository<StarRating, Long> {
    boolean existsByUuidAndEpisodeId(String uuid, long episodeId);

    StarRating findByUuidAndEpisodeId(String uuid, long episodeId);

    @Query(value = "select avg(star_rating) from star_rating where episode_id = ?", nativeQuery = true)
    Double findAvgEpisodeIdByEpisodeId(Long episodeId);
}
