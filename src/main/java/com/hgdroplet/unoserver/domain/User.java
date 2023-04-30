package com.hgdroplet.unoserver.domain;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class User {
    private Long id;
    private String nickname;
    private String password;
}