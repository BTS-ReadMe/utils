package com.readme.utils.comments.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class PaginationDto {
    private int page;
    private int size;
    private int totalPage;
    private long totalElements;

}
