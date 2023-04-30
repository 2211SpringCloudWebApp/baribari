package com.kh.baribari.community.resaleplatform.domain.type;

import lombok.Getter;

public enum SearchType {
    SUBJECT("제목"),
    CONTENT("내용"),
    BOTH("제목+내용"),
    HASHTAG("해시태그");

    @Getter
    private final String typeName;

    SearchType(String typeName) {
        this.typeName = typeName;
    }

    public String getValue() {
        return typeName;
    }

}

