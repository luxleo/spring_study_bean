package spring.prac.oop1.member;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import spring.prac.oop1.config.AppConfigPlain;
import spring.prac.oop1.member.impl.MemberServiceImpl;
import spring.prac.oop1.member.impl.MemoryMemberRepository;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
@Slf4j
class MemberServiceTest {

    @BeforeEach
    void init() {
        long memberId = 0;
        Member m1 = Member.builder()
                .id(memberId++)
                .grade(Grade.BASIC)
                .name("m1")
                .build();
        Member m2 = Member.builder()
                .id(memberId++)
                .grade(Grade.BASIC)
                .name("m2")
                .build();

        MemoryMemberRepository memberRepository = new MemoryMemberRepository();
        MemberServiceImpl memberService = new MemberServiceImpl(memberRepository);

        //when
        memberService.join(m1);
        memberService.join(m2);
    }

    @AfterEach
    void destroy() {
        MemoryMemberRepository.truncateMemberStore();
    }
    @Test
    @DisplayName("DIP 원칙이 지켜지지 않고 있다. -> 따라서 OCP도 위반한다.")
    /**
     * 인터페이스를 멤버변수 타입으로 지정하여 DIP를 지키려고 하였다.
     * 하지만 구현체가 변경되면 호출할때마다 따로 지정해주어야한다.
     * 해결책은 구성과 기능의 역할을 분리하는 것이다.
     */
    void memberCrudV1() {

        // given
        long memberId = 2;

        MemoryMemberRepository memberRepository = new MemoryMemberRepository();
        MemberServiceImpl memberService = new MemberServiceImpl(memberRepository);

        //then
        Member findMember = memberService.findMember(memberId);
        log.info("findMember = {}",findMember);

        assertThat(findMember.getId()).isEqualTo(memberId);
    }

    @Test
    @DisplayName("구성영역에서 DI를 통하여 조립해주어 실행영역과 구분하여 DIP, OCP, SRP만족")
    /**
     *
     */
    void memberCrudV2() {
        // given
        AppConfigPlain appConfig = new AppConfigPlain();
        MemberService memberService = appConfig.memberService();

        // when
        memberService.join(new Member(3L, "m3", Grade.VIP));

        // then
        List<Member> findMembers = memberService.findAllMember();
        assertThat(findMembers.size())
                .isEqualTo(3);

        for (Member member :
                findMembers) {
            log.info(member.toString());
        }
    }
}