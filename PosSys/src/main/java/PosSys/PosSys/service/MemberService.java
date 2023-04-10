package PosSys.PosSys.service;

import PosSys.PosSys.Repository.MemberRepository;
import PosSys.PosSys.domain.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class MemberService {

    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {

        this.memberRepository = memberRepository;
    }

    public void saveMember(Member member) {
        memberRepository.save(member);
    }

    public Member login(String id, String password) {
        return memberRepository.findByIdAndPassword(id, password);
    }

    public Member signUp(Member member) {
        return memberRepository.save(member);
    }

    public Member findMemberById(String id) {
        return memberRepository.findById(id);
    }

    public Member findMemberByUsername(String username) {
        return memberRepository.findById(username);
    }

    public boolean isUsernameExists(String username) {
        Member member = memberRepository.findById(username);
        return member != null;
    }
}