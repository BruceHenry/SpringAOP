package xmlConfig;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class xmlConfigMain {
    public static void main(String[] args) {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("xmlConfig.xml");
        Cal c = (Cal) ctx.getBean("cal");
        int result = c.add(5, 8);
        System.out.print(result);

    }

}
