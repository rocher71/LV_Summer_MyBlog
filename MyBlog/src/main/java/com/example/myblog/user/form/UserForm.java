package com.example.myblog.user.form;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class    UserForm {
    private Long id;
    @NotBlank
    private String name;
    @NotBlank
    private String type;
}
