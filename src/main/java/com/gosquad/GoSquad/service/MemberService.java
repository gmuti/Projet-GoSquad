package com.gosquad.GoSquad.service;

import com.gosquad.GoSquad.entity.Member;

import java.util.List;

public interface MemberService {
    List<Member> getMembers();

    Boolean createMember(Member member);

    Boolean updateMember(Member member);

    Boolean deleteMember(String id);
}
