package iducs.springboot.member_201712045.repository;

import iducs.springboot.member_201712045.domain.Blog;

import java.util.List;

public interface BlogRepository {
	int create(Blog blog);
	Blog read(Blog blog);
	List<Blog> readList();
	int update(Blog blog);
	int delete(Blog blog);
	List<Blog> readByTitle(String title);
	List<Blog> getBlogsByPage(int index, int size);
}
