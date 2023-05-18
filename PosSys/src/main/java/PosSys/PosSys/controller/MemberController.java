package PosSys.PosSys.controller;

import PosSys.PosSys.domain.Member;
import PosSys.PosSys.service.MemberService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

@Controller
@RequestMapping("/member")
public class MemberController {

    @Autowired
    private MemberService memberService;
/*
    // 회원가입 화면 보여주기
    @GetMapping("/signup")
    public String showSignupForm(Model model) {
        model.addAttribute("member", new Member());
        return "/member/login";
    }
*/
    // 회원가입 처리
    @PostMapping("/signup")
    public String signup(@RequestParam("id") String id,
                         @RequestParam("password") String password,
                         @RequestParam("name") String name,
                         @RequestParam("phoneNumber") String phoneNumber) {
        Member member = new Member(id, password, name, phoneNumber);
        memberService.signUp(member);
        return "/login";
    }

    // 로그인 화면 보여주기
    @GetMapping("/login")
    public String showLoginForm() {
        return "member/login";
    }

    // 로그인 처리
    @PostMapping("/login")
    public String login(@RequestParam("id") String id, @RequestParam("password") String password, Model model, HttpSession session) {
        Member member = memberService.login(id, password);
        if(member == null) {
            model.addAttribute("error", "아이디 또는 비밀번호가 틀렸습니다.");
            return "redirect:/";
        }
        model.addAttribute("member", member);
        session.setAttribute("id", id);
        return "home";
    }

    // 로그아웃
    @GetMapping("/logout")
    public String logout(SessionStatus status) {
        status.setComplete();
        return "login";
    }

}
