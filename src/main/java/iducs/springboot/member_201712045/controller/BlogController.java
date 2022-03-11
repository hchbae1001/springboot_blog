package iducs.springboot.member_201712045.controller;

import iducs.springboot.member_201712045.domain.Blog;
import iducs.springboot.member_201712045.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.net.URLDecoder;
import java.nio.file.Files;
import java.sql.Timestamp;
import java.util.List;

@Controller
@RequestMapping("/blogs")
public class BlogController {

    private BlogService blogService;

    @Autowired
    public BlogController(BlogService blogService)  { // 생성자를 활용한 주입
        this.blogService = blogService;
    }

    @Value("${spring.servlet.multipart.location}")
    private String uploadPath;

    @GetMapping("/")
    public String getBlogs(Model model, HttpServletRequest request) {
        String search = request.getParameter("search");
        List<Blog> blogList;
        if(search == null || search ==""){
            blogList = blogService.getBlogs();
        }else{
            blogList = blogService.getBlogsByTitle(search);
        }
        model.addAttribute("blogList", blogList);
        return "blogs/list-view";
    }

    @GetMapping("/{id}")
    public String getBlog(@PathVariable("id") Long id, Model model) {
        model.addAttribute("blog", blogService.getBlog(id));
        return "blogs/detail-form";
    }

    @GetMapping("/register-form")
    public String newBlog(Model model) {
        return "blogs/register-form";
    }

    @PostMapping("/")
    @Transactional
    public String postBlog(
            MultipartHttpServletRequest request,
            Model model) throws IllegalStateException, IOException {
        Blog blog = new Blog();
        blog.setTitle(request.getParameter("title"));
        blog.setContent(request.getParameter("content"));
        blog.setBlogger(request.getParameter("blogger"));
        blog.setRegDateTime(Timestamp.valueOf(request.getParameter("regDateTime")));

        MultipartFile file = request.getFile("filepath");
        if (!file.getOriginalFilename().isEmpty()) {
            file.transferTo(new File(uploadPath, file.getOriginalFilename()));
            blog.setFilepath(file.getOriginalFilename());
        } else {
            model.addAttribute("message", "파일 업로드 실패");
            return "errors/message";
        }
        if(blogService.postBlog(blog) > 0)
            return "redirect:/blogs";
        else {
            model.addAttribute("message", "블로그 등록 실패");
            return "errors/message";
        }
    }

    @GetMapping("/update-form")
    public String updateForm(Long id, Model model) {
        System.out.println(id.hashCode());
        Blog blog = blogService.getBlog(Long.valueOf(id));
        model.addAttribute("blog", blog);
        return "blogs/update-form";
    }

    @PutMapping("/{id}")
    @Transactional
    public String putBlog(
            @PathVariable long id,
            @RequestParam final String title,
            @RequestParam final String content,
            @RequestParam final String blogger,
            @RequestParam Timestamp regDateTime,
            @RequestParam("filepath") MultipartFile file,
            Model model) throws IllegalStateException, IOException {
        Blog blog = new Blog();
        blog.setId(id);
        blog.setTitle(title);
        blog.setContent(content);
        blog.setBlogger(blogger);
        blog.setRegDateTime(regDateTime);

        if(!file.getOriginalFilename().isEmpty()) {
            file.transferTo(new File(uploadPath, file.getOriginalFilename()));
            blog.setFilepath(file.getOriginalFilename());
        } else {
            blog.setFilepath(file.getOriginalFilename());
        }
        if(blogService.updateBlog(blog) > 0)
            return "redirect:blogs/" + id;
        else
            return "errors/message";
    }

    @DeleteMapping("/{id}")
    public String deleteBlog(@PathVariable Long id, Model model) {
        int count = blogService.deleteBlog(id);
        if(count > 0)
            return "redirect:/blogs";
        else
            return "errors/message";
    }

    @GetMapping("/getImage")
    public ResponseEntity<byte[]> getImage(@RequestParam("filepath") String fileName) {
        ResponseEntity<byte[]> result = null;
        try {
            String srcFileName = URLDecoder.decode(fileName, "UTF-8");
            File file = new File(uploadPath + File.separator + srcFileName);
            HttpHeaders header = new HttpHeaders();
            header.add("Content-Type", Files.probeContentType(file.toPath()));
            result = new ResponseEntity<>(FileCopyUtils.copyToByteArray(file), header, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return result;
    }

}
