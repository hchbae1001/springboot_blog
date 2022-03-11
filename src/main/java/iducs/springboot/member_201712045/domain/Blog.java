package iducs.springboot.member_201712045.domain;

import lombok.*;

import java.sql.Timestamp;

@EqualsAndHashCode
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Blog {
    private Long id; // 블로그 아이디 (식별자)
    private String title; // 블로그 제목
    private String content; // 블로그 내용
    private String filepath; // 블로그 첨부 이미지
    private String blogger; // 블로그 작성자
    private Timestamp regDateTime; // 블로그 작성일 : 날짜와 시간
}
