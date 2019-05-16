package atshunhengli.com;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

public class ServletInitializer extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        //传入SpringBoot应用的主程序
        return application.sources(CallbackApplication_9002.class);
    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(CallbackApplication_9002.class, args);
    }

}