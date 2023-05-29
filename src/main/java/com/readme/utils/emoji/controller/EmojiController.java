package com.readme.utils.emoji.controller;

import com.readme.utils.emoji.dto.EmojiDto;
import com.readme.utils.emoji.requestObject.RequestAddEmoji;
import com.readme.utils.emoji.service.EmojiService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
    public void addEmoji(@RequestHeader("uuid") String uuid, @RequestBody RequestAddEmoji requestAddEmoji) {
        emojiService.addEmoji(new EmojiDto(uuid, requestAddEmoji));
    }


}
