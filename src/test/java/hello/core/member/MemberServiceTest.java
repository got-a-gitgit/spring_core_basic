package hello.core.member;

import hello.core.AppConfig;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {

    @Test
    void join() {
        //given
        AppConfig appConfig = new AppConfig();
        MemberService memberService = appConfig.memberService();
        Member member = new Member(1L,"geonho",Grade.VIP);

        //when
        memberService.join(member);

        //then
        Assertions.assertThat(memberService.findMember(member.getId())).isEqualTo(member);
    }

}