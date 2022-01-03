package hello.core.singleton;

import hello.core.AppConfig;
import hello.core.discount.DiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.MemberRepository;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.Bean;

import static org.assertj.core.api.Assertions.assertThat;

public class SingletonWhyNot {
    @Test
    @DisplayName("스프링 없는 순수한 DI 컨테이너")
    void pureContainer() {

        //given
        AppConfig2 appConfig = new AppConfig2();

        //when
        MemberService memberService1 = appConfig.memberService();
        MemberService memberService2 = appConfig.memberService();


        //then
        System.out.println("memberService1 = " + memberService1);
        System.out.println("memberService2 = " + memberService2);

        assertThat(memberService1).isNotSameAs(memberService2);

    }


    static class AppConfig2{
        public MemberService memberService(){
            return new MemberServiceImpl(memberRepository());
        }

//        public OrderService orderService(){
//            return new OrderServiceImpl(
//                    memberRepository(),
//                    discountPolicy()
//            );
//        }

        public MemberRepository memberRepository(){
            return new MemoryMemberRepository();
        }

//        public DiscountPolicy discountPolicy(){
//            return new RateDiscountPolicy();
//        }

    }
}
