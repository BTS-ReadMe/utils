package com.readme.utils.picks.service;

import com.readme.utils.picks.dto.PickDto;
import com.readme.utils.picks.dto.PickPaginationDto;
import com.readme.utils.picks.dto.ResponsePickDto;
import java.util.List;
import org.springframework.data.domain.Pageable;

public interface PickService {

    void pickNovels(PickDto pickDto);

    PickPaginationDto getPicks(String uuid, Pageable pageable);
}
