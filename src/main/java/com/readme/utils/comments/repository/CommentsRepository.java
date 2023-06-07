package com.readme.utils.comments.repository;

import com.readme.utils.comments.model.Comments;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentsRepository extends JpaRepository<Comments, Long> {

    Optional<Comments> findByIdAndUuid(Long id, String uuid);

    Page<Comments> findAllByEpisodesIdOrderByCreateDateDesc(Long episodesId, Pageable pageable);

    Page<Comments> findAllByNovelsIdOrderByCreateDateDesc(Long novelsId, Pageable pageable);
}
