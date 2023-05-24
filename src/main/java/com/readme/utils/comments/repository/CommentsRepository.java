package com.readme.utils.comments.repository;

import com.readme.utils.comments.model.Comments;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentsRepository extends JpaRepository<Comments, Long> {
    Optional<Comments> findByIdAndUuid(Long id, String uuid);
}
