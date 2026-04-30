package jp.co.beattech.workflow.java.app.controller;

import java.util.List;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jp.co.beattech.workflow.java.app.domain.User;
import jp.co.beattech.workflow.java.app.domain.UserInfo;
import jp.co.beattech.workflow.java.app.service.MstCodeService;
import jp.co.beattech.workflow.java.app.service.UserInfoService;
import jp.co.beattech.workflow.java.app.service.UserService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

	private final int NUM_PER_PAGE = 10;
	private final UserService userService;
	private final UserInfoService userInfoService;
	private final MstCodeService mstCodeService;
	private final HttpSession session;

	@GetMapping
	public String show(@RequestParam(name = "page", defaultValue = "1") Integer page, Model model) {
		if (page == null || page < 1) {
			page = 1;
		}
		model.addAttribute("userInfo", userInfoService.getUserListByPage(page, NUM_PER_PAGE));
		model.addAttribute("page", page);
		model.addAttribute("totalPages", userInfoService.getTotalPages(NUM_PER_PAGE));
		model.addAttribute("title", "ユーザ一覧");
		session.setAttribute("userPage", page);
		return "user/user";
	}

	@GetMapping("/detail/{employeeNumber}")
	public String detail(@PathVariable Integer employeeNumber, Model model) {
		model.addAttribute("user", userInfoService.getByUserInfoEmployeeNumber(employeeNumber));
		model.addAttribute("title", "ユーザ詳細");
		return "user/userDetail";
	}

	@GetMapping("/register")
	public String register(Model model) {
		model.addAttribute("user", new User());
		model.addAttribute("userTypeList", mstCodeService.getCodesByType("user"));
		model.addAttribute("departmentList", mstCodeService.getCodesByType("department"));
		model.addAttribute("branchList", mstCodeService.getCodesByType("branch"));
		model.addAttribute("title", "ユーザ登録");
		return "user/userRegister";
	}

//
	@PostMapping("/register")
	public String registerDone(@Valid User user, Errors errors, Model model) {
		boolean flag = false;
		if (errors.hasErrors()) {
			flag = true;
		}
		List<UserInfo> userList = userInfoService.getAllUsers();
		for (UserInfo list : userList) {
			if (list.getLoginId().equals(user.getLoginId())) {
				errors.rejectValue("loginId", "error.loginId");
				flag = true;
				break;
			}
			if (list.getEmployeeNumber().equals(user.getEmployeeNumber())) {
				errors.rejectValue("employeeNumber", "error.employeeNumber");
				flag = true;
				break;
			}
		}
		if (flag) {
			model.addAttribute("userTypeList", mstCodeService.getCodesByType("user"));
			model.addAttribute("departmentList", mstCodeService.getCodesByType("department"));
			model.addAttribute("branchList", mstCodeService.getCodesByType("branch"));
			model.addAttribute("title", "ユーザ登録");
			return "user/userRegister";
		}
		String hashed = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());
		user.setPassword(hashed);
		userService.addUser(user);
		model.addAttribute("title", "登録完了画面");
		return "user/userRegisterDone";

	}

	@GetMapping("/edit/{employeeNumber}")
	public String edit(@PathVariable Integer employeeNumber, Model model) {
		model.addAttribute("user", userService.getByEmployeeNumber(employeeNumber));
		model.addAttribute("userTypeList", mstCodeService.getCodesByType("user"));
		model.addAttribute("departmentList", mstCodeService.getCodesByType("department"));
		model.addAttribute("branchList", mstCodeService.getCodesByType("branch"));
		model.addAttribute("title", "ユーザ編集画面");
		return "user/userEdit";
	}

	@PostMapping("/edit/{employeeNumber}")
	public String editDone(@PathVariable Integer employeeNumber, @Valid User user, Errors errors, Model model) {
		boolean flag = false;
		if (errors.hasErrors()) {
//			System.out.println(2);
			List<ObjectError> objList = errors.getAllErrors();
			if (user.getPassword().isBlank()) {
				if (objList.size() != 1) {
					flag = true;
				}
			}
			if (!user.getPassword().isBlank()) {
				flag = true;
			}
		}
		List<UserInfo> userList = userInfoService.getAllUsers();
		if (user.getLoginId().isBlank()) {
			user.setLoginId(userService.getByEmployeeNumber(employeeNumber).getLoginId());
		} else {
			for (UserInfo list : userList) {
				if (list.getLoginId().equals(user.getLoginId()) && !list.getEmployeeNumber().equals(employeeNumber)) {
					errors.rejectValue("loginId", "error.loginId");
					flag = true;
					break;
				}
			}
		}
		if (flag) {
			model.addAttribute("userTypeList", mstCodeService.getCodesByType("user"));
			model.addAttribute("departmentList", mstCodeService.getCodesByType("department"));
			model.addAttribute("branchList", mstCodeService.getCodesByType("branch"));
			model.addAttribute("title", "ユーザ編集画面");
			return "user/userEdit";
		}
		if (userService.getByEmployeeNumber(employeeNumber).getName().equals(session.getAttribute("name"))
				&& !user.getName().equals(session.getAttribute("name"))) {
			session.setAttribute("name", user.getName());
		}

		model.addAttribute("title", "ユーザ編集完了画面");
		String hashed = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());
		user.setPassword(hashed);
		userService.editUser(user);
		return "user/userEditDone";

	}

	@PostMapping("/delete/{employeeNumber}")
	public String deleteDone(@PathVariable Integer employeeNumber, Model model) {
		userService.deleteUser(employeeNumber, 1);
		Integer page = (Integer) session.getAttribute("userPage");
		int totalPage = userInfoService.getTotalPages(NUM_PER_PAGE);
		if (page > totalPage) {
			session.setAttribute("userPage", page - 1);
			page = page - 1;
		}

		if (employeeNumber == session.getAttribute("userEmployeeNumber")) {
			session.invalidate();
		}

		model.addAttribute("title", "ユーザ削除完了画面");
		return "user/userDeleteDone";
	}

}
