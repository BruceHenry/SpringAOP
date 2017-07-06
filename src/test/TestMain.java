package test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestMain {
    public static void main(String[] args) {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("test.xml");
        Cal c = (Cal) ctx.getBean("cal");
        int result = c.add(9, 8);
        System.out.print(result);

    }

}
