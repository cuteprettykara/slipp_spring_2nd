package net.slipp.dao.users;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

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
public class MyBatisUserDaoTest {
	
	private static final Logger log = LoggerFactory.getLogger(MyBatisUserDaoTest.class);

	@Autowired
	MyBatisUserDao userDao;

	@Test
	public void findById() {
		User user = userDao.findById("prettykara");
		
		log.debug("User : {}", user);
		assertNotNull(user);
	}

	@Test
	public void create() {
		User user = new User("prettykara4",	"4444", "prettykara4", "prettykara4@gmail.com");
		userDao.create(user);
		
		User actual = userDao.findById("prettykara4");
		assertThat(actual, is(user));
	}
	
	@Test
	public void update() {
		User user = new User("prettykara",	"2222", "prettykara2", "prettykara2@gmail.com");
		userDao.update(user);
		
		User actual = userDao.findById("prettykara");
		assertThat(actual, is(user));
	}
}
