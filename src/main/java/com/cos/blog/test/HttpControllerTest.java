package com.cos.blog.test;

import org.springframework.web.bind.annotation.*;

//사용자가 요청 -> 응답(html 파일)
//@Controller

//사용자가 요청 -> 응답(data)
@RestController
public class HttpControllerTest {
    //인터넷 브라우저 요청은 get요청만 가능
    //http://localhost:8080/http/get(select)
//    @GetMapping("/http/get")
//    public String getTest(@RequestParam int id, @RequestParam String username) {
//        return "get: " + id + ", " + username;
//    }

    @GetMapping("/http/get")
    public String getTest(Member m) {
        return "get: " + m.getId() + ", " + m.getUsername() + ", " + m.getPassword();
    }

    //http://localhost:8080/http/post(insert)
    @PostMapping("http/post")
    public String postTest(@RequestBody Member m) { // MessageConverter
        return "post: " + m.getId() + ", " + m.getUsername() + ", " + m.getPassword();
    }

//    @PostMapping("http/post") //text/plain, application/json
//    public String postTest(@RequestBody String text) {
//        return "post: " + text;
//    }

    //http://localhost:8080/http/put(update)
    @PutMapping("/http/put")
    public String putTest() {
        return "put";
    }

    //http://localhost:8080/http/delete(delete)
    @DeleteMapping("/http/delete")
    public String deleteTest() {
        return "delete";
    }
}
