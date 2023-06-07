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
        private String emoji;

        public ResponseEmojiDetail(long id, boolean checked, long count) {
            String emojiType = "";
            if (id == 1L) {
                emojiType = "\uD83D\uDE00";
            } else if (id == 2L) {
                emojiType = "\uD83D\uDE02";
            } else if (id == 3L) {
                emojiType = "\uD83D\uDE28";
            }

            this.id = id;
            this.checked = checked;
            this.count = count;
            this.emoji = emojiType;

        }
    }
}
