package org.bong.domain;

import lombok.Data;



@Data
/*  @Data 어노테이션은 getter/setter, equals(), toString()등의 메서드를 자동 생성해준다.
* */
public class SampleDTO {
    private String name;
    private int age;


}
