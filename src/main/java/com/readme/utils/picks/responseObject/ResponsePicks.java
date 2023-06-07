package com.readme.utils.picks.responseObject;

import com.readme.utils.picks.dto.ResponsePickDto;
import java.time.LocalDateTime;
import lombok.Getter;

@Getter
public class ResponsePicks {
    private Long id;
    private Long novelsId;
    private LocalDateTime createDate;

    public ResponsePicks(ResponsePickDto responsePickDto) {
        this.id = responsePickDto.getId();
        this.novelsId = responsePickDto.getNovelsId();
        this.createDate = responsePickDto.getCreateDate();
    }
}
