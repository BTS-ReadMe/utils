package com.readme.utils.comments.dto;

import com.readme.utils.comments.requestObject.RequestAddComments;
import com.readme.utils.comments.requestObject.RequestUpdateComments;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class CommentsDto {
    private long id;
    private String writer;
    private String content;
    private long episodesId;
    private String uuid;
    private long novelsId;

    public CommentsDto(RequestAddComments requestAddComments, String uuid) {
        this.writer = requestAddComments.getWriter();
        this.content = requestAddComments.getContent();
        this.episodesId = requestAddComments.getEpisodesId();
        this.novelsId = requestAddComments.getNovelsId();
        this.uuid = uuid;
    }

    public CommentsDto(RequestUpdateComments requestUpdateComments, Long id, String uuid) {
        this.id = id;
        this.content = requestUpdateComments.getContent();
        this.uuid = uuid;
    }

}
