package com.kh.baribari.community.resaleplatform.domain.dto;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;
import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Getter @Setter @ToString
@NoArgsConstructor
@AllArgsConstructor
public class LoginUserDto implements UserDetails, OAuth2User
{
    private int userNo;
    private String userId;
    private String userPw;
    private Collection<? extends GrantedAuthority> authorities;
    private String userEmail;
    private String userNickName;
    private Map<String, Object> oAuth2Attributes;

    public static LoginUserDto of(int userNo,String userId, String userPw,
                                  String userEmail, String userNickName)
    {
        return LoginUserDto.of(userNo, userId, userPw, userEmail, userNickName, Map.of());
    }
    public static LoginUserDto of(int userNo,String userId, String userNickName)
    {
        return LoginUserDto.of(userNo, userId, userNickName);
    }

    public static LoginUserDto of(int userNo, String userId, String userPw,
                                  String userEmail, String userNickName,
                                  Map<String, Object> oAuth2Attributes)
    {
        Set<RoleType> roleTypes = Set.of(RoleType.USER);

        return new LoginUserDto(
                userNo,
                userId,
                userPw,
                roleTypes.stream()
                        .map(RoleType::getName)
                        .map(SimpleGrantedAuthority::new)
                        .collect(Collectors.toUnmodifiableSet()),
                userEmail,
                userNickName,
                oAuth2Attributes
        );
    }

    public static LoginUserDto from(UserDto dto)
    {
        return LoginUserDto.of(
                dto.getUserNo(),
                dto.getUserId(),
                dto.getUserNickName()
        );
    }

    public UserDto toDto()
    {
        return UserDto.of(
                userNo,
                userId,
                userNickName
        );
    }

    @Override
    public boolean isAccountNonExpired()
    {
        return true;
    }

    @Override
    public boolean isAccountNonLocked()
    {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired()
    {
        return true;
    }

    @Override
    public boolean isEnabled()
    {
        return true;
    }

    @Override
    public Map<String, Object> getAttributes()
    {
        return oAuth2Attributes;
    }

    @Override
    public String getName()
    {
        return userId;
    }

    public String getFullName()
    {
        return this.userNickName + " (" + this.userEmail + ")";
    }

    public String getRolesAsString()
    {
        return this.authorities.stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(", "));
    }

    @Override
    public String getUsername()
    {
        return userId;
    }

    @Override
    public String getPassword()
    {
        return userPw;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities()
    {
        return authorities;
    }

    public enum RoleType {
        USER("ROLE_USER");

        @Getter
        private final String name;

        RoleType(String name)
        {
            this.name = name;
        }
    }
}
