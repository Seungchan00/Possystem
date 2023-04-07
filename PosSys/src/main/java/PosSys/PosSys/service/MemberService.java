package PosSys.PosSys.service;

import PosSys.PosSys.domain.Member;

public interface MemberService {
    void createMember(Member member);

    Member LoginMember(String username, String password) throws Exception;
}
