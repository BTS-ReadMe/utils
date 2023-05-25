package com.readme.utils.comments.responsepObject;

import com.readme.utils.comments.dto.PaginationDto;
import com.readme.utils.comments.dto.ResponseCommentsDto;
import java.util.List;
import java.util.stream.Stream;
import lombok.Getter;

@Getter
public class ResponseCommentsPagination {
    Stream<ResponseComments> contents;
    Pagination pagination;

    public ResponseCommentsPagination(Stream<ResponseComments> contents, Pagination pagination) {
        this.contents = contents;
        this.pagination = pagination;
    }

    @Getter
    public static class Pagination {
        private int page;
        private int size;
        private long totalElements;
        private int totalPage;

        public Pagination(PaginationDto paginationDto) {
            this.page = paginationDto.getPage();
            this.size = paginationDto.getSize();
            this.totalElements = paginationDto.getTotalElements();
            this.totalPage = paginationDto.getTotalPage();
        }
    }
}
