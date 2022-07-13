package com.ezen.demo.chat;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/ws")
public class ChatController
{
   @GetMapping("")
   @ResponseBody
   public String index()
   {
      return "WebSocket Test";
   }
   
   @GetMapping("/login")
   public String loginForm() {
	   return "chat/login";
   }
   
   @PostMapping("/login")
   @ResponseBody
   public Map<String, Object> loginForm(@RequestParam("uid") String uid, HttpSession session) {
	   System.out.println(uid);
	   if(uid!=null) {
		   Map<String, Object> map = new HashMap<>();
		   session.setAttribute("uid", uid);
		   map.put("login", true);
		   map.put("uid", uid);
		   return map;
	   }
//		   return "redirect:/ws/chat";
	   return null;
   }
   
//   @RequestMapping(value="/chat", method=RequestMethod.GET)
   @GetMapping("/chat")
    public String chat(Locale locale, Model model) {
       return "chat/chat";
    }

}