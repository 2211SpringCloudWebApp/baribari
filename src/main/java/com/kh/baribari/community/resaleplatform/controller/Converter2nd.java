package com.kh.baribari.community.resaleplatform.controller;

import com.github.pagehelper.PageInfo;
import com.kh.baribari.community.resaleplatform.domain.dto.ArticleDto;
import com.kh.baribari.community.resaleplatform.domain.dto.ReadingListResponse;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class Converter2nd
{

    public static PageInfo<ReadingListResponse> toReadingListResponse(PageInfo<ArticleDto> pageInfo) {
        var readingListResponses = pageInfo.getList().stream()
                                           .map(ReadingListResponse::from)
                                           .collect(Collectors.toList());

        PageInfo<ReadingListResponse> result = new PageInfo<>();
        result.setList(readingListResponses);
        result.setTotal(pageInfo.getTotal());
        result.setPageNum(pageInfo.getPageNum());
        result.setPageSize(pageInfo.getPageSize());
        result.setSize(pageInfo.getSize());
        result.setStartRow(pageInfo.getStartRow());
        result.setEndRow(pageInfo.getEndRow());
        result.setPages(pageInfo.getPages());
        result.setPrePage(pageInfo.getPrePage());
        result.setNextPage(pageInfo.getNextPage());
        result.setIsFirstPage(pageInfo.isIsFirstPage());
        result.setIsLastPage(pageInfo.isIsLastPage());
        result.setHasPreviousPage(pageInfo.isHasPreviousPage());
        result.setHasNextPage(pageInfo.isHasNextPage());
        result.setNavigatePages(pageInfo.getNavigatePages());
        result.setNavigatepageNums(pageInfo.getNavigatepageNums());
        result.setNavigateFirstPage(pageInfo.getNavigateFirstPage());
        result.setNavigateLastPage(pageInfo.getNavigateLastPage());

        return result;
    }
}
