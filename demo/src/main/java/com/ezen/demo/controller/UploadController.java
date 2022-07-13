package com.ezen.demo.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.ezen.demo.model.AttachVO;
import com.ezen.demo.model.UploadVO;
import com.ezen.demo.service.UploadService;

@Controller
@RequestMapping("/files")
public class UploadController 
{
	@Autowired
	ResourceLoader resourceLoader;
	
	@Autowired
	private UploadService svc;
	
	@PostMapping("/delete/{num}")
	@ResponseBody
	public Map<String, Object> delete(HttpServletRequest request, @PathVariable("num") int num) {
		Map<String,Object> map = new HashMap<>();
		boolean deleted= svc.deleteAttach(request, num);
		map.put("deleted", deleted);

		return map;
	}
	
	@GetMapping("/deleteUpload/{num}") //게시물 전체삭제 요청
	@ResponseBody
	public Map<String, Object> deleteUpload(HttpServletRequest request, @PathVariable("num") int num) {
		boolean deleted = false;
		Map<String,Object> map = new HashMap<>();
		
		try {
			deleted = svc.deleteUpload(request, num);
		} catch (Exception e) {
			e.printStackTrace();
		}
		map.put("deleted", deleted);
		return map;
	}
	
	@GetMapping("/detail/{num}")
	public String detail(@PathVariable("num") int num, Model model) {
		UploadVO vo = svc.getDetail(num);
		model.addAttribute("vo", vo);
		return "upload/detail";
	}
	
	@GetMapping("/upload")
	public String getForm() 
	{
		return "upload/upload_form";
	}
	
	
	@PostMapping("/upload")
	@ResponseBody
	public String upload(@RequestParam("files") MultipartFile[] mfiles,
						@RequestParam(name="pnum", defaultValue="0") int pnum,
							HttpServletRequest request,
							UploadVO vo) 
	{
//		vo.setNum(pnum);
		
		ServletContext context = request.getServletContext();
		String savePath = context.getRealPath("/WEB-INF/files");
		vo.setFpath(savePath);
		
		List<AttachVO> attList = new ArrayList<>();
		/* static/upload 디렉토리에 업로드하려면, 아래처럼 절대경로를 구하여 사용하면 된다
		 * Resource resource = resourceLoader.getResource("classpath:/static");
		 * String absolutePath = resource.getFile().getAbsolutePath();
		 */ 
		try {
			for(int i=0;i<mfiles.length;i++) {
				String fname_orign = mfiles[i].getOriginalFilename();
				String[] token = fname_orign.split("\\.");
				String fname_changed = token[0]+System.nanoTime()+"."+token[1];
				
				AttachVO att = new AttachVO();
				att.setPnum(pnum);
				att.setFname(fname_changed);
				att.setFpath(savePath);
				
				attList.add(att);
				
				mfiles[i].transferTo(
						new File(savePath+"/"+fname_changed));
				
				/* MultipartFile 주요 메소드
			String cType = mfiles[i].getContentType();
			String pName = mfiles[i].getName();
			Resource res = mfiles[i].getResource();
			long fSize = mfiles[i].getSize();
			boolean empty = mfiles[i].isEmpty();
				 */
			}
			//String msg = String.format("파일(%d)개 저장성공(작성자:%s)", mfiles.length,author);
			
//			vo.setFnames(attList);
			vo.setAttach(attList);
			
			boolean inserted = svc.insert(vo);
			
			return "{\"inserted\":" + inserted +"}";
		} catch (Exception e) {
			e.printStackTrace();
			return "{\"inserted\":" + false +"}";
		}
	}
	
	@GetMapping("/download/{num}")
	public ResponseEntity<Resource> download(
										HttpServletRequest request,
										@PathVariable("num") int num)
	{
		String filename = svc.getFname(num);
		Resource resource = resourceLoader.getResource("WEB-INF/files/"+filename);
		System.out.println("파일명:"+resource.getFilename());
        String contentType = null;
        try {
            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace();
        }
 
        if(contentType == null) {
            contentType = "application/octet-stream";
        }
 
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
	}
	
	@GetMapping("/list")
	public String getList(Model model) {
		List<UploadVO> list = svc.getList();
		model.addAttribute("list", list);
		return "upload/list";
	}
	
}