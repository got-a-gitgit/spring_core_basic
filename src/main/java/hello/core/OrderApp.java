package hello.core;

import hello.core.member.*;
import hello.core.order.Order;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class OrderApp {
    public static void main(String[] args) {
        //given
//        AppConfig appConfig = new AppConfig();
//        MemberService memberService = appConfig.memberService();
//        OrderService orderService = appConfig.orderService();
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
        MemberService memberService = ac.getBean("memberService",MemberService.class);
        OrderService orderService = ac.getBean("orderService", OrderService.class);
        long memberId = 1L;
        Member member = new Member(memberId,"geonho",Grade.VIP);
        memberService.join(member);

//        Member foundMember = memberService.findMember(memberId);
//        System.out.println(foundMember.getId() + foundMember.getName() + foundMember.getGrade() );
        //when
        Order order = orderService.createOrder(memberId, "item1",10000);


        //then
        System.out.println(order);
            }
        }