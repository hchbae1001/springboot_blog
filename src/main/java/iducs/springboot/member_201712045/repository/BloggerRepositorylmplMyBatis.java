package iducs.springboot.member_201712045.repository;

import iducs.springboot.member_201712045.domain.Blogger;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;
import java.util.List;

@Primary
@Repository
public class BloggerRepositorylmplMyBatis implements BloggerRepository {

    private SqlSession sqlSession;
    @Autowired
    public BloggerRepositorylmplMyBatis(SqlSession sqlSession){
        this.sqlSession=sqlSession;
    }
    private static String namespace = "iducs.springboot.member_201712045.mapper.BloggerMapper";


    @Override
    public int create(Blogger blogger) {
        return sqlSession.insert(namespace + ".create", blogger);
    }

    @Override
    public Blogger readById(Blogger blogger) {
        return sqlSession.selectOne(namespace + ".readById",blogger.getId());
    }

    @Override
    public Blogger readByEmail(Blogger blogger) {
        return sqlSession.selectOne(namespace + ".readByEmail", blogger.getEmail());
    }

    @Override
    public List<Blogger> readMembers() {
        System.out.println("MyBatis : " + sqlSession);
        return sqlSession.selectList(namespace + ".readList");
    }

    @Override
    public int update(Blogger blogger) {
        return sqlSession.update(namespace + ".update", blogger);
    }

    @Override
    public int delete(Blogger blogger) {
        return sqlSession.delete(namespace + ".delete", blogger.getId());
    }
}
