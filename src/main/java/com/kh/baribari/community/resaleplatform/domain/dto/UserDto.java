package com.kh.baribari.community.resaleplatform.domain.dto;

import com.kh.baribari.user.domain.User;
import lombok.*;


@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    private int userNo;
    private String userId;
    private String userNickName;

    public static UserDto of(int userNo, String userId, String userNickName) {
        return new UserDto(userNo, userId, userNickName);
    }

    public static UserDto from(User user) {
        if (user == null) {
            return null;
        }
        UserDto userDto = new UserDto();
        userDto.setUserNo(user.getUserNo());
        userDto.setUserId(user.getUserId());
        userDto.setUserNickName(user.getUserNickName());

        return userDto;
    }
}