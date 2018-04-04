package net.slipp.domain.users;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:/applicationContext.xml")
public class UserTest {

	private static Validator validator;
	
	private static final Logger log = LoggerFactory.getLogger(UserTest.class);


	@BeforeClass
	public static void setUp() {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		validator = factory.getValidator();
	}
	
	@Test
	public void userIdWhenIsEmpty() throws Exception {
		User user = new User("", "1111", "남상범", "cuteprettykara@gmail.com");
		Set<ConstraintViolation<User>> constraintViolations = validator.validate(user);
		assertThat(constraintViolations.size(), is(1));
		
		for (ConstraintViolation<User> constraintViolation : constraintViolations) {
			log.debug("violation error message : {}, {}", constraintViolation.getPropertyPath(), constraintViolation.getMessage());
		}
	}

	@Test
	public void matchPassword() throws Exception {
		Authenticate authenticate = new Authenticate("userId", "1111");
		User user = new User("userId", "1111", "남상범", "cuteprettykara@gmail.com");
		
		assertTrue(user.matchPassword(authenticate));
	}
	
	@Test
	public void notMatchPassword() throws Exception {
		Authenticate authenticate = new Authenticate("userId", "2222");
		User user = new User("userId", "1111", "남상범", "cuteprettykara@gmail.com");
		
		assertFalse(user.matchPassword(authenticate));
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void updateWhenMissmatchUserId() throws Exception {
		User user = new User("userId", "1111", "남상범", "cuteprettykara@gmail.com");
		User updateUser = new User("kara", "1111", "kara", "kara@gmail.com");
		
		user.update(updateUser);
	}
	
	@Test
	public void update() throws Exception {
		User user = new User("userId", "1111", "남상범", "cuteprettykara@gmail.com");
		User updateUser = new User("userId", "1111", "kara", "kara@gmail.com");
		
		User updatedUser =user.update(updateUser);
		
		assertThat(updatedUser, is(updateUser));
	}
}
