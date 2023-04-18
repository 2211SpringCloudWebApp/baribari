package com.kh.baribari.community.junggo.repository.mybatis;

import com.kh.baribari.community.junggo.domain.type.SearchType;
import com.kh.baribari.community.junggo.repository.JunggoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class JunggoRepositoryImpl implements JunggoRepository {

    private final JunggoMapper junggoMapper;


    public List<JunggoRepositoryImpl> searchJunggo(SearchType searchType, String searchKeyword) {
        return List.of();
    }
}
