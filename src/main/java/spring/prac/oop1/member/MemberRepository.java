package spring.prac.oop1.member;

import java.util.List;

public interface MemberRepository {
    long save(Member member);

    Member findById(long id);

    List<Member> findAll();
}
