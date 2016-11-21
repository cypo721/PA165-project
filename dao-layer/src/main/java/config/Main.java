package config;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Created by pato on 30.10.2016.
 */
public class Main {


    public static void main(String ... args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(PersistenceSampleApplicationContext.class);

    }
}
