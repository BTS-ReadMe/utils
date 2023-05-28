package com.readme.utils.picks.repository;

import com.readme.utils.picks.model.Pick;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PickRepository extends JpaRepository<Pick, Long> {

    boolean existsByNovelsIdAndUuid(long novelsId, String uuid);

    void deleteByNovelsIdAndUuid(long novelsId, String uuid);

    Page<Pick> findTop12ByUuidOrderByIdDesc(String uuid, Pageable pageable);
}
