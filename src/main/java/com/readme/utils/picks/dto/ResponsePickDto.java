package com.readme.utils.picks.dto;

import com.readme.utils.picks.model.Pick;
import java.time.LocalDateTime;
import lombok.Getter;

@Getter
public class ResponsePickDto {
    private Long id;
    private Long novelsId;
    private LocalDateTime createDate;

    public ResponsePickDto(Pick pick) {
        this.id = pick.getId();
        this.novelsId = pick.getNovelsId();
        this.createDate = pick.getCreateDate();
    }
}
