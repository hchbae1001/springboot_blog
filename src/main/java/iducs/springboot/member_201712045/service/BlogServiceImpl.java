package iducs.springboot.member_201712045.service;

import iducs.springboot.member_201712045.domain.Blog;
import iducs.springboot.member_201712045.repository.BlogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BlogServiceImpl implements iducs.springboot.member_201712045.service.BlogService {
	private BlogRepository blogRepository;
	@Autowired
	public BlogServiceImpl(BlogRepository blogRepository) {
		this.blogRepository = blogRepository;
	}

	@Override
	public int postBlog(Blog blog) {
		return blogRepository.create(blog);
	}

	@Override
	public Blog getBlog(Long id) {
		Blog blog = new Blog();
		blog.setId(id);
		return blogRepository.read(blog);
	}

	@Override
	public List<Blog> getBlogs() {
		return blogRepository.readList();
	}

	@Override
	public List<Blog> getBlogsByTitle(String title) {
		return blogRepository.readByTitle(title);
	}

	@Override
	public List<Blog> getBlogsByBlogger(String blogger) {
		return null;
	}

	@Override
	public List<Blog> getBlogsByPage(int index, int size) {
		return null;
	}

	@Override
	public int updateBlog(Blog blog) {
		return blogRepository.update(blog);
	}

	@Override
	public int deleteBlog(Long id) {
		Blog blog = new Blog();
		blog.setId(id);
		return blogRepository.delete(blog);
	}

	@Override
	public int getTotalRowCountByTitle(String title) {
		return 0;
	}

	@Override
	public int getTotalRowCount() {
		return 0;
	}
}