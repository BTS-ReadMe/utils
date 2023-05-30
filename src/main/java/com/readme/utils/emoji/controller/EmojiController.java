package com.readme.utils.emoji.controller;

import com.readme.utils.commonResponseObject.CommonResponseData;
import com.readme.utils.emoji.dto.EmojiDto;
import com.readme.utils.emoji.requestObject.RequestAddEmoji;
import com.readme.utils.emoji.responseObject.ResponseEmoji;
import com.readme.utils.emoji.service.EmojiService;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/emoji")
@RequiredArgsConstructor
@Slf4j
public class EmojiController {

    private final EmojiService emojiService;

    @PostMapping
    public void addEmoji(@RequestHeader("uuid") String uuid,
        @RequestBody RequestAddEmoji requestAddEmoji) {
        emojiService.addEmoji(new EmojiDto(uuid, requestAddEmoji));
    }

    @GetMapping("/{episodeId}")
    public ResponseEntity<CommonResponseData<List<ResponseEmoji>>> getEmojiByEpisodeId(
        @RequestHeader("uuid") String uuid, @PathVariable Long episodeId) {

        List<ResponseEmoji> emojiList = emojiService.getEmojiByEpisodeId(uuid, episodeId);

        return ResponseEntity.ok(new CommonResponseData<>(emojiList));
    }


}
