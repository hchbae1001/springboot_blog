package iducs.springboot.member_201712045.service;

import iducs.springboot.member_201712045.domain.Blogger;

import java.util.List;

public interface BloggerService {
    Blogger getMember(long id);
    Blogger getMemberByEmail(String email);
    List<Blogger> getMembers();
    List<Blogger> getMembersByPage(int index, int size);
    int postMember(Blogger blogger);
    int putMember(Blogger blogger);
    int deleteMember(Blogger blogger);
}
