package com.kh.baribari.common.annotation;
import org.springframework.security.core.annotation.AuthenticationPrincipal;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.PARAMETER})
@AuthenticationPrincipal(expression = "#this == 'anonymousUser' ? null : user")
public @interface LoginUser{
}