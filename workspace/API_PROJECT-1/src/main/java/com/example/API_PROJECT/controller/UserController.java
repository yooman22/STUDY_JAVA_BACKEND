package com.example.API_PROJECT.controller;

import com.example.API_PROJECT.SecretKey.JwtTokenProvider;
import com.example.API_PROJECT.data.dto.*;
import com.example.API_PROJECT.service.UserService;

import ch.qos.logback.classic.Logger;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;





import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
//import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;



@RestController // srping framework Controller 로 인식 함
@RequiredArgsConstructor
@Controller
@Slf4j
public class UserController {
	
	private UserService userService;
	
	private JwtTokenProvider tokenProvider = new JwtTokenProvider(userService);
	
	//private HashMap<String,UserDTO> UserMap;
	
	@Autowired
	public UserController(UserService userService) {
		this.userService = userService;
	}
	
	@GetMapping(value = "api/user/{id}")
	public UserDTO getUser(@PathVariable String id) {
		return userService.getUser(id);
	}
	
	@PostMapping(value = "api/user")
	public String createUser(@RequestBody UserDTO userDTO) {
		
		String id = userDTO.getId();
		String password = userDTO.getPassword();
		
		// 임시 데이터 TOKEN
	    String tokenValue = tokenProvider.createToken(id, password);
	    //String tokenValue = "";
		System.out.print("TOKEN = " + tokenValue);

		return  userService.createUser(id, password,tokenValue);
	}
	
	@RequestMapping(value = "api/user/login")
	public String LoginUser(@RequestBody UserDTO userDTO, HttpServletRequest request) {
		
		String token = request.getHeader("X-AUTH-TOKEN");
		
	    String result = userService.Login(userDTO.getId(),userDTO.getPassword());
	    
	    if(result.equals("OK"))
	    {
	    	if(tokenProvider.validateToken(token)) {
	    		return "OK";
	    	}else {
	    		return "로그아웃 처리 되었습니다.";
	    	}
	    }else {
	    	return "PASSWORD가 틀립니다.";
	    }
	}
	
	@RequestMapping("api/user/logout")
	public String UserLogout(@RequestBody UserDTO userDTO, HttpServletRequest request) {
			
	    HttpSession session = request.getSession();
	    session.invalidate(); // 세션 삭제
	    
	    return "OK";
	}
	
	@RequestMapping(value = "api/user/deleteaccount")
	public String DeleteAccount(@RequestBody UserDTO userDTO, HttpServletRequest request) {
	
		HttpSession session = request.getSession();
		session.invalidate();
		
		return  userService.deleteAccount(userDTO.getId(), userDTO.getPassword());
		
	}
	
	@PostMapping(value = "api/token")
	public boolean validateToken(@RequestBody String token) throws ParseException
	{
		JSONParser parser = new JSONParser();
		JSONObject obj = (JSONObject)parser.parse(token);
		
		String toToken = (String)obj.get("token");
		
		return tokenProvider.validateToken(toToken);
	}
	
	
	
	@GetMapping("/session-info")
    public String sessionInfo(HttpServletRequest request){
        HttpSession session = request.getSession();
        if (session == null){
            return "세션이 없습니다.";
        }

        session.getAttributeNames().asIterator()
                .forEachRemaining(name -> log.info("session name={}, value={}",name, session.getAttribute(name) ));

        log.info("sessionId={}", session.getId());
        log.info("maxInactiveInterval={}", session.getMaxInactiveInterval());
        log.info("creationTime={}", new Date(session.getCreationTime()));
        log.info("lastAccessTjme={}",new Date(session.getLastAccessedTime()));
        log.info("isNew={}", session.isNew());
        return "세션 출력";
    }
	
	@GetMapping(value = "/UserALL")
	public List<UserDTO> getUserALL(){
		
		return userService.getUserALL();
	}
	
	

	
//	@PostConstruct
//	public void init()
//	{
//		UserMap = new HashMap<String,UserDTO>();
//		UserMap.put("1", new UserDTO("yooman1", "dkssid12!@A"));
//		UserMap.put("2", new UserDTO("yooman2", "dkssid12!@A"));
//		UserMap.put("3", new UserDTO("yooman3", "dkssid12!@A"));
//	}
	
	// GET 방식 : 데이터를 조회 할때 사용
	// POST 방식 : 데이터를 수정 할때 사용
	// PUT 방식 : 데이터를 생성 할때 사용
	// DELETE 방식 : 데이터를 삭제 할때 사용
	
//	@GetMapping("/user/{idx}")
//	public UserDTO getUserDTO(@PathVariable("idx") String idx) {
//		return UserMap.get(idx);
//	}
//	
//	@GetMapping("/user/all")
//	public List<UserDTO> getUserDtoList(){
//		return new ArrayList<UserDTO>(UserMap.values());
//	}
//	
//	@PutMapping("/user/{idx}")
//	public void PutUserDto(@PathVariable("idx") String idx ,@RequestParam("id") String id, @RequestParam("password") String password ) {
//		UserDTO user = new UserDTO(id, password);
//		UserMap.put(idx, user);
//	}
//	
//	@PostMapping("/user/{idx}")
//	public void PostUserDto(@PathVariable("idx") String idx ,@RequestParam("id") String id, @RequestParam("password") String password ){
//		UserMap.put(idx,new UserDTO(id, password) );
//	}
//	
//	@DeleteMapping("/user/{idx}")
//	public void deleteUserDto(@PathVariable("idx") String idx, @RequestParam("id") String id, @RequestParam("password") String password) {
//		UserMap.remove(idx);
//	}
	

	@RequestMapping("/")
    public String example() {

              return "{ 'id': 'sharplee7', \n"

	                    + "'name': 'Duke, Lee',\n"
	
	                    + " 'age': 'N/A' }";

    }
	
	@GetMapping("/{id}/{password}")
    public String test(@PathVariable("id") String id , @PathVariable("password") String password) {
//        UserDto res = userService1.UserServiceMethod(id,password);

        //return "hello "+res.getId() + res.getPassword() ;
        return "hello";
	}
	
	
}