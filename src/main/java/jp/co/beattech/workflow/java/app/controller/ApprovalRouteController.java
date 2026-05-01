package jp.co.beattech.workflow.java.app.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.co.beattech.workflow.java.app.domain.ApprovalRoute;
import jp.co.beattech.workflow.java.app.domain.MstApproval;
import jp.co.beattech.workflow.java.app.domain.UserInfo;
import jp.co.beattech.workflow.java.app.service.MstApprovalService;
import jp.co.beattech.workflow.java.app.service.MstCodeService;
import jp.co.beattech.workflow.java.app.service.UserInfoService;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("")
@RequiredArgsConstructor
public class ApprovalRouteController {

	private final MstCodeService mstCodeService;
	private final MstApprovalService mstApprovalService;
	private final UserInfoService userInfoService;

	
	@GetMapping("approvalRoute")
	public String show(Model model) {
		
		List<MstApproval> approverNo1List = mstApprovalService.getEmployeeNumberByLevel(1);
		for (MstApproval approver : approverNo1List) {
		    UserInfo user = userInfoService.getByUserInfoEmployeeNumber(approver.getEmployeeNumber());
		    if (user != null) {
		        approver.setName(user.getName());
		    } else {
		        approver.setName("(不明)");
		    }
		}
		List<MstApproval> approverNo2List = mstApprovalService.getEmployeeNumberByLevel(2);
		for (MstApproval approver : approverNo2List) {
		    UserInfo user = userInfoService.getByUserInfoEmployeeNumber(approver.getEmployeeNumber());
		    if (user != null) {
		        approver.setName(user.getName());
		    } else {
		        approver.setName("(不明)");
		    }
		}
		List<MstApproval> approverNo3List = mstApprovalService.getEmployeeNumberByLevel(3);
		for (MstApproval approver : approverNo3List) {
		    UserInfo user = userInfoService.getByUserInfoEmployeeNumber(approver.getEmployeeNumber());
		    if (user != null) {
		        approver.setName(user.getName());
		    } else {
		        approver.setName("(不明)");
		    }
		}
	    model.addAttribute("approvalRoute", new ApprovalRoute());
		model.addAttribute("detailList", mstCodeService.getCodesByType("application_detail"));
		model.addAttribute("pattenList", mstCodeService.getCodesByType("approval_patten"));
		model.addAttribute("approverNo1List", approverNo1List);
		model.addAttribute("approverNo2List", approverNo2List);
		model.addAttribute("approverNo3List", approverNo3List);
		model.addAttribute("title", "承認ルート設定");
		return "apply/applyRoute";
	}
	

//	@PostMapping("apply/register")
//	public String registerDone(@Valid Apply apply, Errors errors, @RequestParam("file") MultipartFile file, Model model) {
//		Integer employeeNumber = (Integer) session.getAttribute("employeeNumber");
//		apply.setEmployeeNumber(employeeNumber);
//		MstCode applicationStatus = mstCodeRepository.findByCode("STS001");
//		apply.setApplicationStatus(applicationStatus);
//		if (errors.hasErrors()) {
//			model.addAttribute("detailList", mstCodeService.getCodesByType("application_detail"));
//			model.addAttribute("expenseList", mstCodeService.getCodesByType("expense"));
//			model.addAttribute("title", "申請登録");
//			return "apply/applyRegister";
//		}
//		
//        // ファイルがあれば保存する
//        if (!file.isEmpty()) {
//            try {
//                String fileName = file.getOriginalFilename();
//                String fileType = file.getContentType();
//                String filePath = Paths.get(uploadDir, fileName).toString();
//
//                // ファイル保存
//                Path savePath = Paths.get(filePath);
//                file.transferTo(savePath);
//
//             // Apply保存（戻り値を受け取る）
//                Apply savedApply = applyService.addApply(apply);
//                System.out.println("Saved apply managementNumber: " + savedApply.getManagementNumber());
//
//                // 添付ファイルレコード作成
//                Attachment attachment = new Attachment();
//                attachment.setManagementNumber(savedApply.getManagementNumber());
//                attachment.setFilePath(filePath);
//                attachment.setName(fileName);
//                attachment.setFileType(fileType != null ? fileType : "不明");
//                attachment.setUploadDate(LocalDateTime.now());
//
//                // DBに保存
//                attachmentService.addAttachment(attachment);
//
//            } catch (IOException e) {
//                model.addAttribute("fileError", "ファイル保存に失敗しました");
//                model.addAttribute("detailList", mstCodeService.getCodesByType("application_detail"));
//                model.addAttribute("expenseList", mstCodeService.getCodesByType("expense"));
//                model.addAttribute("title", "申請登録");
//                return "apply/applyRegister";
//            }
//        } else {
//            // Applyのみ保存
//            applyService.addApply(apply);
//        }
//		model.addAttribute("title", "登録完了画面");
//		return "apply/applyRegisterDone";
//
//	}

}
