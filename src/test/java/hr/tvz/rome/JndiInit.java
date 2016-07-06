package hr.tvz.rome;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.mock.jndi.SimpleNamingContextBuilder;

/**
 * Created by marko on 7/6/2016.
 */
public class JndiInit {

    public static void initializeJndi() throws Exception {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("jndi.xml");
        SimpleNamingContextBuilder builder = new SimpleNamingContextBuilder();
        builder.bind("jdbc/RomeDatabase", applicationContext.getBean("dataSource"));
        builder.activate();
    }

}
