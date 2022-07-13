//package com.ezen.demo.controller;
//
//import java.io.File;
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Map;
//
//import javax.servlet.ServletContext;
//import javax.servlet.http.HttpServletRequest;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.core.io.Resource;
//import org.springframework.core.io.ResourceLoader;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.MediaType;
//import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.ResponseBody;
//import org.springframework.web.multipart.MultipartFile;
//
//import com.ezen.demo.mappers.UploadMapper;
//import com.ezen.demo.model.Attach;
//import com.ezen.demo.model.Upload_backup;
//
//@Controller
//@RequestMapping("/files")
//public class UploadController_backup 
//{
//   @Autowired
//   ResourceLoader resourceLoader;
//
//   @Autowired
//   UploadMapper dao;
//   
//   @GetMapping("/upload")
//   public String getForm() {
//      return "upload/upload_form";
//   }
//   
//   @PostMapping("/upload")
//   @ResponseBody
//   public String upload(@RequestParam("files")MultipartFile[] mfiles,
//		   					HttpServletRequest request,
//		   					@RequestParam("writer") String writer,
//		   					Upload_backup upload) {
//      ServletContext context = request.getServletContext();
//      String savePath = context.getRealPath("/WEB-INF/files");
//      System.out.println("savePath=" + savePath );
//
//      /* static/upload 디렉토리에 업로드하려면, 아래처럼 절대경로를 구하여 사용하면 된다
//      * Resource resource = resourceLoader.getResource("classpath:/static");
//      * String absolutePath = resource.getFile().getAbsolutePath();
//      */ 
//      try {
////    	  List<String> list = new ArrayList<>();
//    	  List<Integer> uploadList = new ArrayList<>();
//    	  List<Integer> attachList = new ArrayList<>();
//         for(int i=0;i<mfiles.length;i++) {
//        	 String[] split = mfiles[i].getOriginalFilename().split("\\.");
//        	 String fname = split[0];
//        	 String extension = split[1];
//        	 
//        	 dao.insertUpload(upload);
//        	 uploadList.add(upload.getNum());
//        	 
//        	 Attach attach = new Attach();
//        	 attach.setNum(upload.getNum());
//        	 attach.setFname(mfiles[i].getOriginalFilename());
//        	 attach.setFpath(savePath);
//        	 int attached = dao.insertAttach(attach);
//        	 
//        	 attachList.add(attached);
//        	 mfiles[i].transferTo(
//        			 new File(savePath + "/" + fname + "_" + System.nanoTime() + "." + extension));
////        	 String msg = String.format("파일명:%s, 사이즈:%d", mfiles[i].getOriginalFilename(), mfiles[i].getSize());
////        	 list.add(msg);
//            /* MultipartFile 주요 메소드
//            String cType = mfiles[i].getContentType();
//            String pName = mfiles[i].getName();
//            Resource res = mfiles[i].getResource();
//            long fSize = mfiles[i].getSize();
//            boolean empty = mfiles[i].isEmpty();
//            */
//         }
//         
////       String msg = String.format("파일(%d)개 저장성공(작성자:%s)", mfiles.length,author);
////         return list.toString();
//         return "uploadList: " + uploadList.toString() + "<br>" + "attachList: " + attachList.toString(); 
//      } catch (Exception e) {
//         e.printStackTrace();
//         return "파일 저장 실패:";
//      }
//   }
//   
//   @GetMapping("/download/{filename}")
//   public ResponseEntity<Resource> download(
//         HttpServletRequest request,
//         @PathVariable String filename){
//      Resource resource = resourceLoader.getResource("WEB-INF/files/"+filename);
//      System.out.println("파일명:"+resource.getFilename());
//        String contentType = null;
//        try {
//            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
// 
//        if(contentType == null) {
//            contentType = "application/octet-stream";
//        }
// 
//        return ResponseEntity.ok()
//                .contentType(MediaType.parseMediaType(contentType))
//                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
//                .body(resource);
//   }
//   
//   @GetMapping("/list")
//   public String getList(Model model) {
//	   List<Map> list = dao.getList();
//	   for(int i=0; i<list.size(); i++) {
//		   System.out.println(dao.getList().get(i).toString());   
//	   }
//	   model.addAttribute("list", list);
//	   return "/upload/list";
//   }
//}