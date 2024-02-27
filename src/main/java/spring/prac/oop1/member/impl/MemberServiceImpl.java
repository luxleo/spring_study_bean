package spring.prac.oop1.member.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import spring.prac.oop1.member.Member;
import spring.prac.oop1.member.MemberRepository;
import spring.prac.oop1.member.MemberService;

import java.util.List;

@Component
public class MemberServiceImpl implements MemberService {
    private final MemberRepository memberRepository;
    @Autowired
    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public long join(Member member) {
        return memberRepository.save(member);
    }

    @Override
    public Member findMember(long member_id) {
        return memberRepository.findById(member_id);
    }

    @Override
    public List<Member> findAllMember() {
        return memberRepository.findAll();
    }
}
