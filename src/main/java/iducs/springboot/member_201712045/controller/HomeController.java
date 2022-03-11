package iducs.springboot.member_201712045.controller;
import iducs.springboot.member_201712045.service.BloggerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller // Spring Web MVC 컨트롤러
// @EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class})
// @RestController //Restuful 웹 서비스 컨트롤러
public class HomeController {
    BloggerService bloggerService;
    @Autowired // Spring Framework 가 주입함.
    public HomeController(BloggerService bloggerService) {
        this.bloggerService=bloggerService; // 오른쪽 memberService 객체는 등록된 객체를 주입
    }

    @GetMapping("")  // url : http://<server_ip>:<port>/
    public String gohome(){
        return "main/index"; //index.html 파일을 view or 텝플릿으로 사용함
    }

    @GetMapping("/logout")
    public String logoutMember(HttpServletRequest request){
        HttpSession session = request.getSession();
        if(session != null){
            session.invalidate(); //현재 session 객체를 무효화
        }
        return "main/index";
    }
}
