package jp.co.beattech.workflow.java.app.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import jp.co.beattech.workflow.java.app.domain.Apply;
import jp.co.beattech.workflow.java.app.domain.MstCode;
import jp.co.beattech.workflow.java.app.repository.MstCodeRepository;
import jp.co.beattech.workflow.java.app.service.ApplicationInfoService;
import jp.co.beattech.workflow.java.app.service.ApplyService;
import jp.co.beattech.workflow.java.app.service.AttachmentService;
import jp.co.beattech.workflow.java.app.service.MstApprovalService;
import jp.co.beattech.workflow.java.app.service.MstCodeService;
import jp.co.beattech.workflow.java.app.service.UserInfoService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("")
@RequiredArgsConstructor
public class ApplyController {

	private final int NUM_PER_PAGE = 10;
	private final ApplyService applyService;
	private final MstCodeService mstCodeService;
	private final AttachmentService attachmentService;
	private final MstApprovalService mstApprovalService;
	private final UserInfoService userInfoService;
	private final MstCodeRepository mstCodeRepository;
	private final ApplicationInfoService applicationInfoService;
	private final HttpSession session;
	@Value("${upload.path}")
	private String uploadDir;

	@GetMapping("/apply")
	public String show(@RequestParam(name = "page", defaultValue = "1") Integer page, Model model) {
		if (page == null || page < 1) {
			page = 1;
		}
		model.addAttribute("applyInfo", applicationInfoService.getApplyListByPage(page, NUM_PER_PAGE));
		model.addAttribute("page", page);
		model.addAttribute("totalPages", applicationInfoService.getTotalApplyPages(NUM_PER_PAGE));
		model.addAttribute("title", "申請一覧");
		session.setAttribute("applyPage", page);
		return "apply/apply";
	}

	@GetMapping("/unapproved")
	public String unapproved(@RequestParam(name = "page", defaultValue = "1") Integer page, Model model) {
		if (page == null || page < 1) {
			page = 1;
		}
		String applicationStatusCd = "STS001";
		model.addAttribute("unapprovedInfo",
				applicationInfoService.getUnapprovedListByPage(page, NUM_PER_PAGE, applicationStatusCd));
		model.addAttribute("page", page);
		model.addAttribute("totalPages",
				applicationInfoService.getTotalUnapprovedPages(NUM_PER_PAGE, applicationStatusCd));
		model.addAttribute("title", "未承認一覧");
		session.setAttribute("unapprovedPage", page);
		return "apply/unapproved";
	}

	@GetMapping("/unapproved/detail/{managementNumber}")
	public String detail(@PathVariable Integer managementNumber, Model model) {
		model.addAttribute("unapprovedDetail", applicationInfoService.getByManagementNumber(managementNumber));
		model.addAttribute("title", "未承認詳細");
		return "apply/unapprovedDetail";
	}

	@GetMapping("/files/{file:.+}")
	@ResponseBody
	public ResponseEntity<?> serveFile(@PathVariable String file) {
	    try {
	        Path filePath = Paths.get(uploadDir).resolve(file).normalize();
	        System.out.println("ファイルのパス: " + filePath.toAbsolutePath());

	        // ファイルの存在と読み込み可能チェック
	        if (!Files.exists(filePath) || !Files.isReadable(filePath)) {
	        	return ResponseEntity.status(404).body("ファイルが見つかりません");
	        }

	        Resource resource = new UrlResource(filePath.toUri());

	        if (resource.exists() && resource.isReadable()) {
	            // ファイルのContent-Typeを取得。なければoctet-streamにフォールバック
	            String contentType = Files.probeContentType(filePath);
	            if (contentType == null) {
	                contentType = "application/octet-stream";
	            }

	            return ResponseEntity.ok()
	                    .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + resource.getFilename() + "\"")
	                    .header(HttpHeaders.CONTENT_TYPE, contentType)  // ここでContent-Typeをセット
	                    .body(resource);
	        } else {
	        	return ResponseEntity.status(404).body("ファイルが読み込めません");
	        }
	    } catch (Exception e) {
	    	return ResponseEntity.status(500).body("ファイルの取得に失敗しました");
	    }
	}

	@PostMapping("/unapproved/approve/{managementNumber}")
	public String approveDone(@PathVariable Integer managementNumber, Model model) {
		String applicationStatusCd = "STS001";
		applyService.update(managementNumber, "STS002");
		Integer page = (Integer) session.getAttribute("Page");
		int totalPage = applicationInfoService.getTotalUnapprovedPages(NUM_PER_PAGE, applicationStatusCd);
		if (page > totalPage) {
			session.setAttribute("Page", page - 1);
			page = page - 1;
		}

		if (managementNumber == session.getAttribute("userEmployeeNumber")) {
			session.invalidate();
		}

		model.addAttribute("title", "承認完了画面");
		model.addAttribute("result", "approve");
		return "apply/approvedResult";
	}
	
	@PostMapping("/unapproved/returned/{managementNumber}")
	public String returnedDone(@PathVariable Integer managementNumber, Model model) {
		String applicationStatusCd = "STS001";
		applyService.update(managementNumber, "STS003");
		Integer page = (Integer) session.getAttribute("Page");
		int totalPage = applicationInfoService.getTotalUnapprovedPages(NUM_PER_PAGE, applicationStatusCd);
		if (page > totalPage) {
			session.setAttribute("Page", page - 1);
			page = page - 1;
		}

		if (managementNumber == session.getAttribute("userEmployeeNumber")) {
			session.invalidate();
		}

		model.addAttribute("title", "差し戻し完了画面");
		model.addAttribute("result", "returned");
		return "apply/approvedResult";
	}

	@GetMapping("apply/register")
	public String register(Model model) {
		model.addAttribute("apply", new Apply());
		model.addAttribute("detailList", mstCodeService.getCodesByType("application_detail"));
		model.addAttribute("expenseList", mstCodeService.getCodesByType("expense"));
		model.addAttribute("title", "申請登録");
		return "apply/applyRegister";
	}
	
	@PostMapping("apply/confirm")
	public String confirm(@Valid Apply apply, Errors errors, Model model, @RequestParam("file") MultipartFile file) {
	    if (errors.hasErrors()) {
	        model.addAttribute("detailList", mstCodeService.getCodesByType("application_detail"));
	        model.addAttribute("expenseList", mstCodeService.getCodesByType("expense"));
	        model.addAttribute("title", "申請登録");
	        return "apply/applyRegister";  // 入力画面に戻る
	    }
	    MstCode detailCode = apply.getDetail();
	    MstCode expenseCode = apply.getExpense();    
	    String detailName = detailCode != null ? mstCodeService.findNameByCode(detailCode.getCode()) : "";
	    String expenseName = expenseCode != null ? mstCodeService.findNameByCode(expenseCode.getCode()) : "";



	    // ファイルはここでセッションや一時保存に入れる方法が必要
	    // 今回はファイルの扱いは割愛します

	    model.addAttribute("apply", apply);
	    model.addAttribute("detailName", detailName);
	    model.addAttribute("expenseName", expenseName);
	    try {
	        if (file != null && !file.isEmpty()) {
	            session.setAttribute("uploadedFile", file.getBytes());
	            session.setAttribute("uploadedFileName", file.getOriginalFilename());
	            session.setAttribute("uploadedFileContentType", file.getContentType());
	        } else {
	            session.removeAttribute("uploadedFile");
	            session.removeAttribute("uploadedFileName");
	            session.removeAttribute("uploadedFileContentType");
	        }
	    } catch (IOException e) {
	        model.addAttribute("fileError", "ファイル処理に失敗しました");
	        model.addAttribute("title", "申請登録");
	        return "apply/applyRegister";
	    }
	    model.addAttribute("fileName", session.getAttribute("uploadedFileName"));
	    model.addAttribute("title", "申請内容確認");
	    return "apply/applyConfirm";  // 確認画面テンプレート
	}
	
	@PostMapping("apply/register")
	public String registerDone(@Valid Apply apply, Errors errors, Model model, HttpSession session) {
	    Integer employeeNumber = (Integer) session.getAttribute("employeeNumber");
	    apply.setEmployeeNumber(employeeNumber);
	    MstCode applicationStatus = mstCodeRepository.findByCode("STS001");
	    apply.setApplicationStatus(applicationStatus);

	    if (errors.hasErrors()) {
	        model.addAttribute("detailList", mstCodeService.getCodesByType("application_detail"));
	        model.addAttribute("expenseList", mstCodeService.getCodesByType("expense"));
	        model.addAttribute("title", "申請登録");
	        return "apply/applyRegister";
	    }

	    try {
	        // 申請情報をDB保存し、管理番号を取得
	        Apply savedApply = applyService.addApply(apply);

	        // セッションからファイル情報を取得
	        byte[] fileBytes = (byte[]) session.getAttribute("uploadedFile");
	        String originalFilename = (String) session.getAttribute("uploadedFileName");
	        String contentType = (String) session.getAttribute("uploadedFileContentType");

	        if (fileBytes != null && originalFilename != null) {
	            Path uploadPath = Paths.get(uploadDir);
	            if (!Files.exists(uploadPath)) {
	                Files.createDirectories(uploadPath);
	            }

	            // UUIDを付けず、そのままのファイル名で保存
	            Path filePath = uploadPath.resolve(originalFilename);

	            // ファイル書き込み
	            Files.write(filePath, fileBytes);

	            // 添付ファイル情報を作成してDBに保存
	            jp.co.beattech.workflow.java.app.domain.Attachment attachment = new jp.co.beattech.workflow.java.app.domain.Attachment();
	            attachment.setManagementNumber(savedApply.getManagementNumber());
	            attachment.setName(originalFilename);
	            attachment.setFilePath(filePath.toString());
	            attachment.setFileType(contentType != null ? contentType : "unknown");
	            attachment.setUploadDate(java.time.LocalDateTime.now());

	            attachmentService.addAttachment(attachment);
	        }

	        // セッションのファイル情報は不要なので削除
	        session.removeAttribute("uploadedFile");
	        session.removeAttribute("uploadedFileName");
	        session.removeAttribute("uploadedFileContentType");

	    } catch (Exception e) {
	        e.printStackTrace();
	        model.addAttribute("fileError", "ファイルの保存に失敗しました");
	        model.addAttribute("detailList", mstCodeService.getCodesByType("application_detail"));
	        model.addAttribute("expenseList", mstCodeService.getCodesByType("expense"));
	        model.addAttribute("title", "申請登録");
	        return "apply/applyRegister";
	    }

	    model.addAttribute("title", "登録完了画面");
	    return "apply/applyRegisterDone";
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
