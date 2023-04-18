package com.kh.baribari.community.junggo.controller;

import com.kh.baribari.community.junggo.domain.type.SearchType;
import com.kh.baribari.community.junggo.service.JunggoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("junggo")
public class JunggoController {

    private final JunggoService junggoService;

    @GetMapping
    public String showJunggoList(@RequestParam(required = false) SearchType searchType,
                                @RequestParam(required = false) String searchValue,
                                ModelMap modelMap)
    {
        modelMap.addAttribute("JunggoList", junggoService.searchJunggo(searchType, searchValue));

        return "community/junggo/junggo";
    }


}
