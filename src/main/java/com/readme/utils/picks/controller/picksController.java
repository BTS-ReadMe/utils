package com.readme.utils.picks.controller;

import com.readme.utils.picks.dto.PickDto;
import com.readme.utils.picks.repository.PickRepository;
import com.readme.utils.picks.requestObject.RequestPick;
import com.readme.utils.picks.service.PickService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/pick")
@RequiredArgsConstructor
public class picksController {

    private final PickService pickService;

    @PostMapping
    public void pickNovels(@RequestHeader("uuid") String uuid, @RequestBody RequestPick requestPick) {

        pickService.pickNovels(new PickDto(uuid, requestPick));

    }

}
