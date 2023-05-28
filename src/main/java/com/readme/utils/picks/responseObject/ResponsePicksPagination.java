package com.readme.utils.picks.responseObject;

import com.readme.utils.picks.dto.PaginationDto;
import lombok.Getter;
import lombok.ToString;

@Getter
public class ResponsePicksPagination {
    private Object contents;
    private Pagination pagination;

    public ResponsePicksPagination(Object contents, Pagination pagination) {
        this.contents = contents;
        this.pagination = pagination;
    }

    @Getter
    public static class Pagination {
        private int page;
        private int size;
        private int totalPage;
        private long totalElements;

        public Pagination(PaginationDto paginationDto) {
            this.page = paginationDto.getPage();
            this.size = paginationDto.getSize();
            this.totalPage = paginationDto.getTotalPage();
            this.totalElements = paginationDto.getTotalElements();
        }
    }
}
