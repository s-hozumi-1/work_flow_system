package jp.co.beattech.workflow.java.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import jp.co.beattech.workflow.java.app.domain.LoginUser;
import jp.co.beattech.workflow.java.app.domain.UserInfo;
import jp.co.beattech.workflow.java.app.service.LoginService;
import jp.co.beattech.workflow.java.app.service.UserInfoService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class LoginController {
	
	private final LoginService service ;
	private final UserInfoService userInfoService;
	private final HttpSession session;
	
	@GetMapping("/")
	public String showLogin(Model model) {
		
//		既にログインが完了している場合
		if (session.getAttribute("loginId") != null) {
			return "redirect:/apply";
		}
		
		
		LoginUser loginUser = new LoginUser();
		model.addAttribute("loginUser", loginUser);
		return "login/login";
	}
	
	@PostMapping
	public String login(@Valid LoginUser loginUser, Errors errors) {
		
		if (errors.hasErrors()) {
			return "login/login";
		}
		
		
		String loginId = loginUser.getLoginId();
		String password = loginUser.getPassword();
		
		boolean returnLoginFlag = false;
		
		if (loginUser.getLoginId().isBlank()) {
			errors.rejectValue("loginId", "error.blank_id");
			returnLoginFlag = true;
		}
		
		
		if (loginUser.getPassword().isBlank()) {
			errors.rejectValue("password", "error.blank_password");
			returnLoginFlag = true;
		}
		
		if (returnLoginFlag) {
			return "login/login";
		}
		
		
		if (!service.isCorrectIdAndPassword(loginId, password)) {
			errors.rejectValue("loginId", "error.incorrect_id_password");
			return "login/login";
		}
		
		
		UserInfo originalUser = userInfoService.getByLoginId(loginId);
		session.setAttribute("loginId", loginId);
		session.setAttribute("name", originalUser.getName());
		session.setAttribute("employeeNumber", originalUser.getEmployeeNumber());
		session.setAttribute("UserCd", originalUser.getUserCd());
		session.setAttribute("Level", originalUser.getLevel());
				
		return "redirect:/apply";
	}
	
	
	@GetMapping("/logout")
	public String logout() {
		
		session.invalidate();
		return "login/logout";
	}
	
}
