package spring.prac.oop1.member;

import java.util.List;

public interface MemberService {
    public abstract long join(Member member);

    Member findMember(long member_id);

    List<Member> findAllMember();
}
