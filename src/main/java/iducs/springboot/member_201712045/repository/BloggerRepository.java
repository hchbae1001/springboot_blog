package iducs.springboot.member_201712045.repository;

import iducs.springboot.member_201712045.domain.Blogger;

import java.util.List;

public interface BloggerRepository {
    int create(Blogger blogger); // 레코드 생성
    Blogger readById(Blogger blogger); //하나 레코드 가져오기, 유일키 사용
    Blogger readByEmail(Blogger blogger); //하나 레코드 가져오기
    List<Blogger> readMembers();  //다수의 레코드 가져오기
    int update(Blogger blogger); // 레코드 수정
    int delete(Blogger blogger); // 레코드 삭제
}
