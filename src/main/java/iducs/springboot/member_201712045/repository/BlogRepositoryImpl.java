package iducs.springboot.member_201712045.repository;

import iducs.springboot.member_201712045.domain.Blog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;


@Repository("blogRepositoryImpl")
public class BlogRepositoryImpl implements BlogRepository {
	private JdbcTemplate jdbcTemplate;
	@Autowired
	public BlogRepositoryImpl(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource); // Spring Bean dataSource 객체를 주입해야함.
	}
	@Override
	public int create(Blog blog) {
		String sql = "insert into blog201712045 values(seq_m200412000.nextval,?,?,?,?,?)";
		Object[] params = new Object[]{blog.getTitle(),blog.getContent(), blog.getFilepath(), blog.getBlogger(), blog.getRegDateTime()};
		return jdbcTemplate.update(sql, params);
	}

	@Override
	public Blog read(Blog blog) {
		String sql = "select * from blog201712045 where id=?";
		return jdbcTemplate.queryForObject(sql,new BeanPropertyRowMapper<>(Blog.class), blog.getId());
	}

	@Override
	public List<Blog> readList() {
		String sql = "select * from blog201712045";
		return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Blog.class));
	}

	@Override
	public int update(Blog blog) {
		String query ="update blog201712045 set title=?, content=?, filepath=?, blogger=?, regDate=? where id=?";
		return jdbcTemplate.update(query, blog.getTitle(),blog.getContent(),blog.getFilepath(),blog.getBlogger(),blog.getId());
	}

	@Override
	public int delete(Blog blog) {
		String sql = "delete from blog201712045 where id=?;";
		Long id = blog.getId();
		Object[] params = new Object[]{id};
		return jdbcTemplate.update(sql, params);
	}

	@Override
	public List<Blog> readByTitle(String title) {
		String sql = "select * from blog201712045 where title like ?";
		String wrappedTitle = "%" + title + "%";
		return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Blog.class),wrappedTitle);
	}


	@Override
	public List<Blog> getBlogsByPage(int index, int size) {
		return null;
	}

}
