package com.readme.utils.picks.service;

import com.readme.utils.picks.dto.PickDto;
import com.readme.utils.picks.dto.ResponsePickDto;
import com.readme.utils.picks.model.Pick;
import com.readme.utils.picks.repository.PickRepository;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    @Override
    public List<ResponsePickDto> getPicks(String uuid) {
        List<Pick> picks = pickRepository.findTop12ByUuidOrderByIdDesc(uuid);
        List<ResponsePickDto> responsePickDtoList = new ArrayList<>();

        picks.forEach(pick -> {
            responsePickDtoList.add(new ResponsePickDto(pick));
        });

        return responsePickDtoList;
    }
}
