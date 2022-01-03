package hello.core.order;

import hello.core.AppConfig;
import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OrderServiceImplTest {

    AppConfig appConfig = new AppConfig();
    OrderService orderService = appConfig.orderService();
    MemberService memberService = appConfig.memberService();
    @Test
    void 주문생성() {
        //given
        Member member = new Member( 1L,"member1", Grade.VIP);
        memberService.join(member);

        //when
        Order order = orderService.createOrder(member.getId(),"item1", 10000);

        //then
        Assertions.assertThat(order.getDiscountPrice()).isEqualTo(1000);
    }
}