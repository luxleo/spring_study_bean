package spring.prac.oop1.member.impl;

import org.springframework.stereotype.Component;
import spring.prac.oop1.member.Member;
import spring.prac.oop1.member.MemberRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class MemoryMemberRepository implements MemberRepository {
    private static Map<Long, Member> memberStore = new HashMap<>();

    public static void truncateMemberStore() {
        memberStore.clear();
    }
    @Override
    public long save(Member member) {
        Long curMemberId = member.getId();
        if(memberStore.containsKey(curMemberId))
            //TODO: exception 구현하기
            return -1;
        memberStore.put(curMemberId, member);
        return curMemberId;
    }

    @Override
    public Member findById(long id) {
        Member foundMember = memberStore.get(id);;

        //TODO: create exception for null result
        return foundMember;
    }

    @Override
    public List<Member> findAll() {
        return memberStore.entrySet().stream()
                .map(entry -> entry.getValue())
                .collect(Collectors.toList());
    }
}
