package hello.core.discount;

import hello.core.AppConfig;
import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import hello.core.order.Order;
import hello.core.order.OrderService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class RateDiscountPolicyTest {


    @Test
    @DisplayName("vip는 10% 할인이 적용되어야 한다.")
    void vip_o(){
        //given
        Member memberVip = new Member(1L,"memberVip", Grade.VIP);
        AppConfig appConfig = new AppConfig();
        MemberService memberService = appConfig.memberService();
        memberService.join(memberVip);
        OrderService orderService = appConfig.orderService();

        //when
        Order order = orderService.createOrder(memberVip.getId(),"desk",100000);
        int discountPrice=order.getDiscountPrice();

        //then
        assertThat(discountPrice).isEqualTo(10000);
    }

    @Test
    @DisplayName("vip가 아니면 할인이 적용되지 않는다.")
    void vip_x(){
        //given
        Member memberVip = new Member(2L,"memberBasic", Grade.BASIC);

        //when
        int discountPrice = new RateDiscountPolicy().discount(memberVip,10000);

        //then
        assertThat(discountPrice).isEqualTo(0);
    }
}