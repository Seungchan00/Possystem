package PosSys.PosSys.controller;

import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j
public class HomeController {

    @RequestMapping({"/", "/login"}) // 첫번째 화면
    public String home(HttpSession session) {
        String memberId = (String) session.getAttribute("id");
        if (memberId != null) { // 세션에 id가 있는 경우 바로 home 페이지로 이동
            return "home";
        }
        return "login"; // 세션에 id가 없는 경우 로그인 페이지로 이동
    }

}
