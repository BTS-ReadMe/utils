package com.readme.utils.starrating.requestObject;

import lombok.Getter;

@Getter
public class RequestAddStar {
    private double starRating;
    private long episodeId;
    private long novelId;
}
