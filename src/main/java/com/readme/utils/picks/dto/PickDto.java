package com.readme.utils.picks.dto;

import com.readme.utils.picks.requestObject.RequestPick;
import lombok.Getter;

@Getter
public class PickDto {

    private String uuid;
    private long novelsId;

    public PickDto(String uuid, RequestPick requestPick) {
        this.uuid = uuid;
        this.novelsId = requestPick.getNovelsId();
    }
}
