    package com.kh.baribari.community.resaleplatform.domain.type;
    import lombok.Getter;
    public enum CommunityCategory {
        CATEGORY_2(2, "중고구매"),
        CATEGORY_3(3,"중고판매");
        @Getter
        private final int value;
        @Getter
        private final String description;
        CommunityCategory(int value, String description) {
            this.value = value;
            this.description = description;
        }
        public static CommunityCategory fromValue(int value) {
            for (CommunityCategory category : values()) {
                if (category.getValue() == value) {
                    return category;
                }
            }
            return null;
        }
    }
