package net.slipp.dao;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.io.IOException;
import java.io.InputStream;

import javax.sql.DataSource;
import javax.xml.crypto.Data;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.DatabasePopulatorUtils;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;

import net.slipp.domain.users.User;

public class MyBatisTest {

	private static final Logger log = LoggerFactory.getLogger(MyBatisTest.class);
	
	private SqlSessionFactory sqlSessionFactory;
	
	@Before
	public void setup() throws IOException {
		String resource = "mybatis-config-test.xml";
		InputStream inputStream = Resources.getResourceAsStream(resource);
		this.sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		
		ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
		populator.addScript(new ClassPathResource("slipp.sql"));
		DatabasePopulatorUtils.execute(populator, getDataSource());
		
		log.debug("database successfully initialized!");
	}

	private DataSource getDataSource() {
		BasicDataSource basicDataSource = new BasicDataSource();
		basicDataSource.setDriverClassName("org.h2.Driver");
		basicDataSource.setUrl("jdbc:h2:~/slipp");
		basicDataSource.setUsername("sa");
		
		return basicDataSource;
	}

	@Test
	public void gettingStarted() throws IOException {
		try (SqlSession session = sqlSessionFactory.openSession()) {
			User user = session.selectOne("UserMapper.findById", "prettykara");
			log.debug("User : {}", user);
		}
	}
	
	@Test
	public void insert() throws Exception {
		User user = new User("prettykara2",	"2222", "prettykara2", "prettykara2@gmail.com");
		
		try (SqlSession session = sqlSessionFactory.openSession()) {
			session.insert("UserMapper.create", user);
			User dbUser = session.selectOne("UserMapper.findById", user.getUserId());
			assertThat(dbUser, is(user));
		}
	}
}
