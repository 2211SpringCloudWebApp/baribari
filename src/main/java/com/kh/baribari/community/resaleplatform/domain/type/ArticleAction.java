package com.kh.baribari.community.resaleplatform.domain.type;

import lombok.Getter;

public enum ArticleAction
{
    CREATE("새글쓰기", false),
    UPDATE("수정하기", true),
    READ_ONLY("읽기전용", false);
    @Getter
    private final String action;
    @Getter
    private final boolean isUpdate;
    ArticleAction(String action, boolean isUpdate)
    {
        this.action = action;
        this.isUpdate = isUpdate;
    }
}
