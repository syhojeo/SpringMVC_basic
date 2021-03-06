package kr.co.ezenac.controller;



import java.net.URLDecoder;
import java.net.URLEncoder;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TestController {
	
	@GetMapping("/save_cookie")
	//public String save_cookie() {
	public String save_cookie(HttpServletResponse response) {
		try {
			//UTF-8 로 인코딩
			String data1 = URLEncoder.encode("문자열1", "UTF-8");
			String data2 = URLEncoder.encode("문자열2", "UTF-8");
			
			Cookie cookie1 = new Cookie("cookie1", data1);
			Cookie cookie2 = new Cookie("cookie2", data2);
			
			//1일 쿠키 만료기간
			cookie1.setMaxAge(24 * 60 * 60);
			cookie2.setMaxAge(24 * 60 * 60);
			
			response.addCookie(cookie1);
			response.addCookie(cookie2);
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return "save_cookie";
	}
	
	//쿠키를 한번 준뒤로는 client의 request에 쿠키값이 저장되어있다
	@GetMapping("/load_cookie")
	public String load_cookie(HttpServletRequest request) {
		try {
			Cookie[] cookies = request.getCookies();
			
			for (Cookie cookie : cookies) {
				String str = URLDecoder.decode(cookie.getValue(), "UTF-8");
				
				if (cookie.getName().equals("cookie1")) {
					System.out.printf("cookie1 : %s\n", str);
				} else if (cookie.getName().contentEquals("cookie2")) {
					System.out.printf("cookie2 : %s\n", str);
				}
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	
		return "load_cookie";
	}
	
	//@CookieValue 어노테이션 사용하기
	@GetMapping("/load_cookie2")
	public String load_cookie2(@CookieValue("cookie1") String cookie1, 
			@CookieValue("cookie2") String cookie2) {
		System.out.printf("cookie1 : %s\n", cookie1);
		System.out.printf("cookie2 : %s\n", cookie2);
		
		return "load_cookie2";
	}
}
