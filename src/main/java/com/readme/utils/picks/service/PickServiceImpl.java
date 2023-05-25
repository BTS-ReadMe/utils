package com.readme.utils.picks.service;

import com.readme.utils.picks.dto.PickDto;
import com.readme.utils.picks.model.Pick;
import com.readme.utils.picks.repository.PickRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PickServiceImpl implements PickService {

    private final PickRepository pickRepository;

    @Transactional
    @Override
    public void pickNovels(PickDto pickDto) {

        if (pickRepository.existsByNovelsIdAndUuid(pickDto.getNovelsId(), pickDto.getUuid())) {
            pickRepository.deleteByNovelsIdAndUuid(pickDto.getNovelsId(), pickDto.getUuid());
        } else {
            Pick pick = Pick.builder()
                .uuid(pickDto.getUuid())
                .novelsId(pickDto.getNovelsId())
                .build();

            pickRepository.save(pick);
        }
    }
}
