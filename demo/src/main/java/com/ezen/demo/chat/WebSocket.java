package com.ezen.demo.chat;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpSession;
import javax.websocket.EndpointConfig;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
@ServerEndpoint(value="/websocket", configurator=HttpSessionConfigurator.class)
public class WebSocket 
{
    /* 웹소켓 세션 보관용 ArrayList */
//    private static List<Session> sessionList = new ArrayList<>();
    private static Map<String, Session> sessionMap = new HashMap<>();
    
    /* 웹소켓 사용자 접속시 호출됨  */
    @OnOpen
    public void handleOpen(Session session, EndpointConfig config) {
        if (session != null) {
//            String sessionId = session.getId();
            
            HttpSession httpSession = (HttpSession)config.getUserProperties().get("session");
            String uid = (String) httpSession.getAttribute("uid");
            
            sessionMap.put(uid, session);
            
//            String sessionId = sessionMap.get(uid).getId();
            
            System.out.println("client is connected. sessionId == [" + uid + "]");
//            sessionList.add(session);
            
            /* 웹소켓에 접속한 모든 이용자에게 메시지 전송 */
            //JSON방식 수정부분
            Map<String, String> map = new HashMap<>();
            map.put("from", uid);
            map.put("contents", "Connected");
            
            try {
            	String jsStr = new ObjectMapper().writeValueAsString(map);
            	sendMessageToAll(jsStr);
            } catch(JsonProcessingException e) {
            	e.printStackTrace();
            }
//            sendMessageToAll("--> [USER-" + sessionId + "] is connected. ");
//            sendMessageToAll("--> ["+uid+"] is connected. ");
        }
    }

    /* 웹소켓 이용자로부터 메시지가 전달된 경우 실행됨 */
    @OnMessage
    public void handleMessage(String message, Session session) {
        if (session != null) {
        	// 메시지 수신자 확인
        	try {
				Map<String, String >map = new ObjectMapper().readValue(message, Map.class);
				String receiver = map.get("to");
				if(receiver!=null || receiver.length()!=0) {
					Session ss = sessionMap.get(receiver);
					if(ss!=null) {
						ss.getAsyncRemote().sendText(message);
						session.getAsyncRemote().sendText(message);
						return;
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
        	/* 웹소켓에 접속한 모든 이용자에게 메시지 전송 */
        	sendMessageToAll(message);
        }
    }

    /* 웹소켓 이용자가 연결을 해제하는 경우 실행됨 */
    @OnClose
    public void handleClose(Session session) {
        if (session != null) {
//            String sessionId = session.getId();
        	String uid = getUserBySession(session);
            System.out.println("client is disconnected. sessionId == [" + uid + "]");
            
            Map<String, String> map = new HashMap<>();
            map.put("from", uid);
            map.put("to", "");
            map.put("contents", "Disconnected");
            
            try {
            	String jsStr = new ObjectMapper().writeValueAsString(map);
            	sendMessageToAll(jsStr);
            } catch(JsonProcessingException e) {
            	e.printStackTrace();
            }
            
            /* 웹소켓에 접속한 모든 이용자에게 메시지 전송 */
//            sendMessageToAll("***** ["+ uid + "] is disconnected. *****");
        }
    }

    /* 웹소켓 에러 발생시 실행됨 */
    @OnError
    public void handleError(Throwable t) {
        t.printStackTrace();
    }
    
    
    /* 웹소켓에 접속한 모든 이용자에게 메시지 전송 */
    private boolean sendMessageToAll(String message) {
    	if (sessionMap == null) {
            return false;
        }

        int sessionCount = sessionMap.size();
        if (sessionCount < 1) {
            return false;
        }
        
        Set<String> keys = sessionMap.keySet();
        Iterator<String> itr= keys.iterator();
        
        while(itr.hasNext()) {
        	String key = itr.next();
        	Session ss = sessionMap.get(key);
        	if(ss == null || !ss.isOpen()) {
        		continue;
        	}
        	ss.getAsyncRemote().sendText(message);
        }
        return true;
    }
    
    private String getUserBySession(Session ss) {
    	Set<String> keys = sessionMap.keySet();
    	Iterator<String> itr = keys.iterator();
    	
    	while(itr.hasNext()) {
    		String key = itr.next();
    		Session _ss = 	sessionMap.get(key);
    		if(_ss == ss) return key;
    	}
    	return null;
    }
}