package org.JavaPro.model;


import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class Animal {

    private String nickName;
    private int age;
    private String breed;

}
