package com.kh.baribari.community.resaleplatform.domain.type;

import lombok.Getter;

public enum SearchType
{
    SUBJECT("제목", "communitySubject"),
    CONTENT("내용", "communityContent"),
    BOTH("제목+내용", "communitySubjectAndContent"),
    HASHTAG("해시태그", "hashtags");

    @Getter
    private final String typeName;
    @Getter
    private final String column;

    SearchType(String typeName, String column)
    {
        this.typeName = typeName;
        this.column = column;
    }

    public String getValue()
    {
        return typeName;
    }
}


