package com.cos.blog.test;

import com.cos.blog.model.RoleType;
import com.cos.blog.model.User;
import com.cos.blog.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.function.Supplier;

@RestController
public class DummyControllerTest {
    @Autowired //의존성 주(DI)
    private UserRepository userRepository;

    @GetMapping("/dummy/user")
    public List<User> list() {
        return userRepository.findAll();
    }

    //한페이지당 2건에 데이터를 리턴받아 볼 예정
    @GetMapping("/dummy/user/page")
    public List<User> pageList(@PageableDefault(size = 2, sort = "id", direction = Sort.Direction.DESC)Pageable pageable) {
        Page<User> pagingUsers = userRepository.findAll(pageable);
        List<User> users = pagingUsers.getContent();
        return users;
    }

    //{id} 주소로 파라메터를 전달 받을 수 있음
    //http://localhost:8080/blog/dummy/user/3
    @GetMapping("/dummy/user/{id}")
    public User detail(@PathVariable int id) {
        //데이터베이스에서 id를 못찾을 경우 user가 null이 되는 문제가 있다
        //그래서 Optional로 user객체를 감싸서 가져올테니 null체크를 해라!
//        User user = userRepository.findById(id).orElseThrow(new Supplier<IllegalArgumentException>() {
//            @Override
//            public IllegalArgumentException get() {
//                return new IllegalArgumentException("해당 유저는 없습니다. id: " + id);
//            }
//        });

        //람다
        User user = userRepository.findById(id)
                                .orElseThrow(() -> new IllegalArgumentException("해당 유저는 없습니다. id: " + id));
        //요청:웹브라우저
        //user 객체=자바 오브젝트
        //변환 (웹브라우저가 이해할 수 있는 데이) -> json(Gson라이브러리)
        //스프링부트=MessageConverter라는 얘가 응답시에 자동 작동
        //만약에 자바 오브젝트를 리턴하게 되면 MessageConverter가 Jackson라이브러리를 호출해서
        //user오브젝트를 json으로 변환해서 브라우저에게 던져줌
        return user;
    }
    @PostMapping("/dummy/join")
    //public String join(String username, String password, String email) { //key=value (야속된 규칙)
    public String join(User user) { //오브젝트도 가능
        System.out.println("username: " + user.getUsername());
        System.out.println("password: " + user.getPassword());
        System.out.println("email: " + user.getEmail());

        user.setRole(RoleType.USER);
        userRepository.save(user);
        return "회원가입이 완료되었습니다.";
    }
}
