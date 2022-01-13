package com.gec.controller;

import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONObject;
import com.gec.domain.Document;
import com.gec.domain.PageBean;
import com.gec.domain.User;
import com.gec.service.DocumentService;


@Controller
@RequestMapping("/Document")
public class DocumentController extends BaseController {

	@Autowired
	private DocumentService documentService;

	@RequestMapping("/viewUpload")
	public String viewUpload() {
		return "document/upload";
	}

	@RequestMapping("/viewList")
	public String viewList() {
		return "document/list";
	}

	// 映射地址: /Document/upload
	@RequestMapping("/upload")
	public String upload(@RequestParam("file") MultipartFile file,
						 @RequestParam("documentName")String documentName,
						 @RequestParam("note")String note,
						 HttpSession session) throws Exception{
		// 取出当前登录用户。
		User curUser = (User) session.getAttribute("user");
		// 设置失败跳转地址。
		String target = "/Document/viewUpload";
		// 传递的参数。
		String urlMsg = "";
		Document document = new Document();
		document.setUploader(curUser.getId());
		document.setDocumentname(documentName);
		document.setNote(note);
		document.setFilename(file.getOriginalFilename());
		document.setFilesize(file.getSize()+"");
		document.setFile(file);
		try {
			//调用 service 保存文件..
			documentService.saveDocument(document);
			// 设置成功跳转地址
			target = "/Document/viewList";
			// 打包消息 op, DocumentName, result, cause
			urlMsg = "?op=upload&documentName="+documentName+"&result=success&cause=none";
		} catch (Exception e) {
			e.printStackTrace();
			String msg = URLEncoder.encode(e.getMessage(), "UTF-8");
			urlMsg = "?op=upload&documentName="+documentName+"&result=failed&cause="+msg;
		}
		return "redirect:" + target + urlMsg;
	}
	
	// /Document/download
	@RequestMapping("/download")
	@ResponseBody
	public void downDocument(@RequestParam("id")String id,HttpServletResponse resp){
		Document document = null;
		try {
			System.out.println("{下载文件} id=" + id);
			document = documentService.getDocumentById(id);
			String fileName = document.getFilename();
			System.out.println(fileName);
			download(fileName, resp);
			documentService.growDownloadCnt(id);
			System.out.println("下载完成");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/*
	 * 映射地址: /Document/jsonList 提交参数: page, limit, ...
	 */
	@RequestMapping(value="/jsonList",
			produces="text/html;charset=UTF-8")
	@ResponseBody
	public String jsonList(@RequestParam("page")int page,
						   @RequestParam("limit")int limit,
						   HttpServletRequest req){
		//获取前端提交的关键字
		String documentName = req.getParameter("documentName");
		String nickName = req.getParameter("nickName");
		String fileName = req.getParameter("fileName");
		String respTxt = "";
		PageBean<Document> pBean = null;
		try {
			// {2}调用 service 方法查询数据(传入关键字)
			pBean = documentService.getDocumentList(documentName,nickName,fileName,page,limit);
			// {3}转成 json 格式数据.
			respTxt = pBean.toJSON();
		} catch (Exception e) {
			e.printStackTrace(); 
			// 包装错误信息..
			respTxt = setErrorMsg(e);
		}
		return respTxt;
	}

	// 删除一个文件。
	// 映射地址: /Document/delete
	@RequestMapping(value="/delete",
			produces="text/html;charset=UTF-8")
	@ResponseBody
	public String delete(@RequestParam("id")String id) {
		JSONObject jsonObject = new JSONObject();
		String respTxt = "";
		try {
			documentService.delDocument(id);
			jsonObject.put("result", "success");
			respTxt = jsonObject.toString();
		} catch (Exception e) {
			e.printStackTrace();
			respTxt = setErrorMsg(e);
		}
		return respTxt;
	}
}
