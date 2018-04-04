package net.slipp.dao.users;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import net.slipp.domain.users.User;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:/applicationContext.xml")
public class JdbcUserDaoTest {
	
	private static final Logger log = LoggerFactory.getLogger(JdbcUserDaoTest.class);

	@Autowired
	private UserDao userDao;
	
	@Test
	public void findById() {
		User user = userDao.findById("prettykara");
		
		log.debug("User : {}", user);
//		assertNotNull(user);
	}

	@Test
	public void create() {
		User user = new User("prettykara4",	"4444", "prettykara4", "prettykara4@gmail.com");
		userDao.create(user);
		
		User actual = userDao.findById("prettykara4");
		assertThat(actual, is(user));
	}
}
