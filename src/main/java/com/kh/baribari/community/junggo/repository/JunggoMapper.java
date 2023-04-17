package com.kh.baribari.community.junggo.repository;

import com.kh.baribari.community.junggo.domain.type.SearchType;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

//마이바티스 매핑 인터페이스를 호출해주는 매퍼 인터페이스
// TODO : @Mapper 사용시 이점 및 적절성 고민해보자
@Mapper
public interface JunggoMapper {

    List<JunggoRepository> searchJunggo(SearchType searchType, String searchKeyword);


}


