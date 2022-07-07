package com.cos.blog.service;

import com.cos.blog.config.auth.PrincipalDetail;
import com.cos.blog.model.RoleType;
import com.cos.blog.model.User;
import com.cos.blog.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BCryptPasswordEncoder encoder;

    @Transactional
    public void 회원가입(User user) {
        String rawPassowrd = user.getPassword();
        String encPassword = encoder.encode(rawPassowrd);
        user.setPassword(encPassword);
        user.setRole(RoleType.USER);
        userRepository.save(user);
    }

    @Transactional
    public void 회원수정(User user) {
        //수정시에는 영속성 컨텍스트 User 오브젝트를 영속화 시키고, 영속화된 User 오브젝트를 수정
        //select를 해서 User오브젝트를 DB 부터 가져오는 이유는 영속화를 하기 위해서
        //영속화된 오브젝트를 변경하면 자동으로 DB에 update문을 날려주기 때문이다
        User persistance = userRepository.findById(user.getId())
                .orElseThrow(() -> new IllegalArgumentException("회원 찾기 실패"));
        String rawPassword = user.getPassword();
        String encPassword = encoder.encode(rawPassword);
        persistance.setPassword(encPassword);
        persistance.setEmail(user.getEmail());
        //회원수정 함수 종료시 = 서비스 종료 = 트랜잭션 종료 = commi이 자동으로 된다
        //영속화된 prsistance 객체의 변화가 감지되면 더티체킹이 되어 update문을 날려줌
        PrincipalDetail principalDetail = new PrincipalDetail(persistance);
        Authentication authentication =
                new UsernamePasswordAuthenticationToken(
                        principalDetail,
                        null,
                        principalDetail.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

//    @Transactional(readOnly = true) //select할 때 트랜잭션 시작, 서비스 종료시에 트랜잭션 종료 (정합성)
//    public User 로그인(User user) {
//        return userRepository.findByUsernameAndPassword(user.getUsername(), user.getPassword());
//    }
}
