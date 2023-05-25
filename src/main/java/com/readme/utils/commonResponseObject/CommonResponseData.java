package com.readme.utils.commonResponseObject;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
public class CommonResponseData<T> {
    T data;
}
