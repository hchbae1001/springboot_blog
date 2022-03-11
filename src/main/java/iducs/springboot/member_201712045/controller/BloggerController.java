package iducs.springboot.member_201712045.controller;

import iducs.springboot.member_201712045.domain.Blogger;
import iducs.springboot.member_201712045.service.BloggerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/bloggers")
public class BloggerController {
    // Controller :Post, Get, Put, Delete 메소드
    // Repository or DAO : Create, Read(One, List), Update, Delete
    HttpSession session = null;
    BloggerService bloggerService;
    @Autowired // Spring Framework 가 주입함.
    public BloggerController(BloggerService bloggerService) {
        this.bloggerService = bloggerService; // 오른쪽 memberService 객체는 등록된 객체를 주입
    }
    @GetMapping("/login-form")
    public String signinForm(){
        return "bloggers/login-form"; // login-form.html로 이동
    }

    @PostMapping("/login") //정보를 추가 PostMapping(보안에 좋음), 수정은 PutMapping, 삭제는 DeleteMapping
    public String loginMember(HttpServletRequest request, Model model){
        session = request.getSession();
        String email = request.getParameter("email");
        String pw = request.getParameter("pw");
        // 입력한 email/pw가 서버 쪽에 존재하면 해당 레코드를 객체로 반환, 그렇지 않은 경우 null
        Blogger retBlogger = null;
        if((retBlogger = bloggerService.getMemberByEmail(email)) != null && pw.equals(retBlogger.getPw())) {
            session.setAttribute("id", retBlogger.getId());
            session.setAttribute("email", retBlogger.getEmail());
            session.setAttribute("name", retBlogger.getName());
            return "main/index";
        } else {
            model.addAttribute("message", "계정 정보를 확인하시오");
            return "errors/message";
        }
    }
    @GetMapping("/register-form")
    public String signupForm(){
        return "bloggers/register-form"; // register-form.html로 이동
    }

    @PostMapping("/register") //정보를 추가 PostMapping(보안에 좋음), 수정은 PutMapping, 삭제는 DeleteMapping
    public String postMember(@Valid Blogger blogger, Errors errors, Model model) {
        if(errors.hasErrors()) {
            model.addAttribute("message", errors.getFieldValue("email"));
            return "errors/message";
        }
        if(bloggerService.postMember(blogger) > 0) {
            model.addAttribute("bloggers", blogger);
            return "bloggers/login-form";
        } else {
            model.addAttribute("message", "등록을 실패하였습니다.");
            return "errors/message";
        }
    }

    @GetMapping("/{id}")
    public String getMember(@PathVariable("id") Long id, Model model) {
        Blogger blogger = bloggerService.getMember(id);
        model.addAttribute("blogger", blogger);
        return "bloggers/detail-form";
    }
    @PutMapping("/{id}")
    public String putMember(@PathVariable("id") Long id, @Valid Blogger blogger, Model model) {
        if(bloggerService.putMember(blogger) > 0) {
            model.addAttribute("blogger", blogger);
            return "redirect:/bloggers/" + id; // @GetMapping("/bloggers/{id}") 호출
        } else {
            model.addAttribute("message", "업데이트를 실패하였습니다.");
            return "errors/message";
        }
    }
    @GetMapping("/admin")
    public String getMembers(HttpServletRequest request, Model model) {
        if(session != null && ((String) session.getAttribute("email")).equals("admin201712045@induk.ac.kr")) {
            List<Blogger> bloggerList = bloggerService.getMembers();
            model.addAttribute("bloggerList", bloggerList);
            return "bloggers/list-view";
        } else {
            model.addAttribute("message", "관리자로 로그인하시오.");
            return "errors/message";
        }
    }
    @DeleteMapping("/{id}")
    public String deleteMember(@Valid Blogger blogger, Model model) {
        if(bloggerService.deleteMember(blogger) > 0) {
            if(((String) session.getAttribute("email")).equals("admin201712045@induk.ac.kr")){
                return "redirect:/bloggers/admin";
            }else{
                return "redirect:/logout";
            }
        } else {
            model.addAttribute("message", "탈퇴를 실패하였습니다.");
            return "errors/message";
        }
    }
}
