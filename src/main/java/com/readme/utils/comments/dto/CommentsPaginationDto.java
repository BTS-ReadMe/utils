package com.readme.utils.comments.dto;

import java.util.List;
import lombok.Getter;

@Getter
public class CommentsPaginationDto {
    List<ResponseCommentsDto> responseCommentsDtoList;
    PaginationDto paginationDto;

    public CommentsPaginationDto(List<ResponseCommentsDto> responseCommentsDtoList,
        PaginationDto paginationDto) {
        this.responseCommentsDtoList = responseCommentsDtoList;
        this.paginationDto = paginationDto;
    }
}
