package com.readme.utils.comments.dto;

import com.readme.utils.comments.model.Comments;
import lombok.Getter;

@Getter
public class ResponseCommentsDto {
    private long id;
    private String writer;
    private String content;
    private long episodesId;
    private String uuid;
    private long novelsId;
    private boolean myComment;
    private String createDate;
    private boolean recent;
    private String episodeTitle;

    public ResponseCommentsDto(Comments comments, boolean myComment, boolean recent) {
        this.id = comments.getId();
        this.writer = comments.getWriter();
        this.content = comments.getContent();
        this.episodesId = comments.getEpisodesId();
        this.uuid = comments.getUuid();
        this.novelsId = comments.getNovelsId();
        this.myComment = myComment;
        this.recent = recent;
        this.episodeTitle = comments.getEpisodeTitle();
    }

    public void setFormattedDate(String createDate) {
        this.createDate = createDate;
    }

}
