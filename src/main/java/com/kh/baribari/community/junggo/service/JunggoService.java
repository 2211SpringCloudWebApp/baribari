package com.kh.baribari.community.junggo.service;

import com.kh.baribari.community.junggo.domain.type.SearchType;
import com.kh.baribari.community.junggo.repository.mybatis.JunggoRepositoryImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class JunggoService {

    private final JunggoRepositoryImpl junggoRepositoryImpl;
//
    public List<JunggoRepositoryImpl> searchJunggo(SearchType searchType, String searchKeyword)
    {
        return List.of();
    }

}
