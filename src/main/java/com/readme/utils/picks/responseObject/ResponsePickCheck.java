package com.readme.utils.picks.responseObject;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@NoArgsConstructor
@ToString
public class ResponsePickCheck {
    private boolean checked;

    public ResponsePickCheck(boolean checked) {
        this.checked = checked;
    }
}
