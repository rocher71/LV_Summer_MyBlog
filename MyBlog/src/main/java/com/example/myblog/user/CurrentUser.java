package com.example.myblog.user;

import org.springframework.security.core.annotation.AuthenticationPrincipal;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME) //runtime 에서 여기 있는 기능을 추가 
@Target(ElementType.PARAMETER) //parameter일 때 annotation을 사용하겠다
@AuthenticationPrincipal(expression = "#this == 'annonymousUser' ? null : user")
//익명의 유저가 맞으면 null, 아니면 유저를 반환. 우리가 구현한 유저어카운트를 반환하는거임
public @interface CurrentUser {
}
