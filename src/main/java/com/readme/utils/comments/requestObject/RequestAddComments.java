package com.readme.utils.comments.requestObject;

import lombok.Getter;

@Getter
public class RequestAddComments {

     private String writer;
     private String content;
     private long episodesId;
     private long novelsId;
     private String episodeTitle;
}
