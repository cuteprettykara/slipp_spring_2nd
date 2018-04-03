package net.slipp.web.users;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import net.slipp.dao.users.UserDao;
import net.slipp.domain.users.Authenticate;
import net.slipp.domain.users.User;

@Controller
@RequestMapping("/users")
public class UserController {
	
	private static final Logger log = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	private UserDao userDao;

	@RequestMapping("/form")
	public String form(Model model) {
		model.addAttribute("user", new User());
		return "users/form";
	}
	
	@RequestMapping(value="", method=RequestMethod.POST)
	public String create(@Valid User user, BindingResult bindingResult) {
		log.debug("User : {}", user);
		
		if (bindingResult.hasErrors()) {
			log.debug("Binding Result has error!");
			/*List<ObjectError> errors = bindingResult.getAllErrors();
			for (ObjectError error : errors) {
				log.debug("error : {}, {}, {}", error.getObjectName(), error.getCode(), error.getDefaultMessage());
			}*/
			
			List<FieldError> errors = bindingResult.getFieldErrors();
			for (FieldError error : errors) {
				log.debug("error : {}, {}, {}", error.getField(), error.getCode(), error.getDefaultMessage());
			}
			
			return "users/form";
		}
		
		userDao.create(user);
		log.debug("Database : {}", userDao.findById(user.getUserId()));
		return "redirect:/";
	}
	
	@RequestMapping("/login/form")
	public String loginForm(Model model) {
		model.addAttribute("authenticate", new Authenticate());
		return "users/login";
	}
	
	@RequestMapping("/login")
	public String login(@Valid Authenticate authenticate, BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
			return "users/login";
		}
		
		User user = userDao.findById(authenticate.getUserId());
		if (user == null) {
			// TODO 에러 처리 - 존재하지 않는 사용자입니다.
			model.addAttribute("errorMessage", "존재하지 않는 사용자입니다.");
			return "users/login";
		}
		
		if (!user.getPassword().equals(authenticate.getPassword())) {
			// TODO 에러 처리 - 비밀번호가 틀립니다.
		}
		
		// TODO 세션에 사용자 정보 저장
		
		return "users/login";
	}
}
