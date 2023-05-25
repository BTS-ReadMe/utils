package com.readme.utils.comments.responsepObject;

import com.readme.utils.comments.dto.ResponseCommentsDto;
import lombok.Getter;

@Getter
public class ResponseComments {
    private long id;
    private String writer;
    private String content;
    private long episodesId;
    private String uuid;
    private long novelsId;
    private boolean myComment;
    private String createDate;
    private boolean recent;

    public ResponseComments(ResponseCommentsDto responseCommentsDto) {
        this.id = responseCommentsDto.getId();
        this.writer = responseCommentsDto.getWriter();
        this.content = responseCommentsDto.getContent();
        this.episodesId = responseCommentsDto.getEpisodesId();
        this.uuid = responseCommentsDto.getUuid();
        this.novelsId = responseCommentsDto.getNovelsId();
        this.myComment = responseCommentsDto.isMyComment();
        this.createDate = responseCommentsDto.getCreateDate();
        this.recent = responseCommentsDto.isRecent();
    }
}
