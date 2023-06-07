package com.readme.utils.picks.dto;

import java.util.List;
import lombok.Getter;

@Getter
public class PickPaginationDto {
    private List<ResponsePickDto> contents;
    private PaginationDto paginationDto;

    public PickPaginationDto(List<ResponsePickDto> contents, PaginationDto paginationDto) {
        this.contents = contents;
        this.paginationDto = paginationDto;
    }
}
