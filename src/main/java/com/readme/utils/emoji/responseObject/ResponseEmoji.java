package com.readme.utils.emoji.responseObject;

import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class ResponseEmoji {
    private long id;
    private List<ResponseEmojiDetail> Emoji;

    public ResponseEmoji(long id, List<ResponseEmojiDetail> emoji) {
        this.id = id;
        Emoji = emoji;
    }

    @Getter
    public static class ResponseEmojiDetail {
        private long id;
        private boolean checked;
        private long count;

        public ResponseEmojiDetail(long id, boolean checked, long count) {
            this.id = id;
            this.checked = checked;
            this.count = count;
        }
    }
}
