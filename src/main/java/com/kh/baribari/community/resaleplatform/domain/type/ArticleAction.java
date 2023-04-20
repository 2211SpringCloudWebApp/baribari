package com.kh.baribari.community.resaleplatform.domain.type;

import lombok.Getter;

public enum ArticleAction
{
    CREATE("글쓰기", false),
    UPDATE("수정하기", true);


    @Getter
    private final String action;
    @Getter
    private final Boolean update;

    ArticleAction(String action, Boolean update)
    {
        this.action = action;
        this.update = update;
    }
}
