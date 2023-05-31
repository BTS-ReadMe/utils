package com.readme.utils.emoji.controller;

import com.readme.utils.commonResponseObject.CommonResponseData;
import com.readme.utils.emoji.dto.EmojiDto;
import com.readme.utils.emoji.requestObject.RequestAddEmoji;
import com.readme.utils.emoji.responseObject.ResponseEmoji;
import com.readme.utils.emoji.service.EmojiService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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

    @Operation(summary = "이모지 등록/수정", description = "이모지 등록/수정 (로그인 필요)", tags = {"이모지"})
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "OK"),
        @ApiResponse(responseCode = "400", description = "BAD REQUEST"),
        @ApiResponse(responseCode = "404", description = "NOT FOUND"),
        @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR")
    })
    @PostMapping
    public void addEmoji(@RequestHeader("uuid") String uuid,
        @RequestBody RequestAddEmoji requestAddEmoji) {
        emojiService.addEmoji(new EmojiDto(uuid, requestAddEmoji));
    }

    @Operation(summary = "이모지 조회", description = "이모지 조회 (로그인 필수x, 로그인시 본인 체크한 거 표시)", tags = {"이모지"})
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "OK"),
        @ApiResponse(responseCode = "400", description = "BAD REQUEST"),
        @ApiResponse(responseCode = "404", description = "NOT FOUND"),
        @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR")
    })
    @GetMapping("/{episodeId}")
    public ResponseEntity<CommonResponseData<List<ResponseEmoji>>> getEmojiByEpisodeId(
        @RequestHeader(value = "uuid",required = false, defaultValue = "") String uuid, @PathVariable Long episodeId) {

        List<ResponseEmoji> emojiList = emojiService.getEmojiByEpisodeId(uuid, episodeId);

        return ResponseEntity.ok(new CommonResponseData<>(emojiList));
    }


}
