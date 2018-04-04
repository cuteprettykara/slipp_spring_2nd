package net.slipp.dao.users;

import org.apache.ibatis.session.SqlSession;

import net.slipp.domain.users.User;

public class MyBatisUserDao implements UserDao {
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
