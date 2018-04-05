package net.slipp.dao.users;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import net.slipp.domain.users.User;

@Repository("userDao")
public class MyBatisUserDao implements UserDao {
//	@Autowired
	@Resource(name="sqlSession")
	private SqlSession sqlSession;
	
//	private DataSource dataSource;
	
/*	@PostConstruct
	public void initialize() {
		ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
		populator.addScript(new ClassPathResource("slipp.sql"));
		DatabasePopulatorUtils.execute(populator, dataSource);
		
		log.debug("database successfully initialized!");
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}*/

	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}

	@Override
	public User findById(String userId) {
		return sqlSession.selectOne("UserMapper.findById", userId);
	}

	@Override
	public void create(User user) {
		sqlSession.insert("UserMapper.create", user);
	}

	@Override
	public void update(User user) {
		sqlSession.update("UserMapper.update", user);
	}
}
