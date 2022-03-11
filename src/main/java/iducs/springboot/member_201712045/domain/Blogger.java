package iducs.springboot.member_201712045.domain;

import lombok.*;

// lombok annotation : 반복적으로 사용되는 코드의 작성을 줄이기 위한 프로젝트
@EqualsAndHashCode
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Blogger {
    /** DTO, VO : 데이터를 보내고 받는데 사용하는 객체, Data Access Layer에서 사용하는 객체
     * DTO(Data Transfer Object) - creat, update, delete 포함
     * VO(Valuable Object) - read 중심, select query 대상
     * 데이터베이스 기본 연산 - CRUD(Create, Read, Update, Delete) - 의 대상
     */
    private Long id;
    //  @NotBlank
    //  @Email
    private String email;
    private String pw;
    private String name;
    private String phone;
    private String address;
}
