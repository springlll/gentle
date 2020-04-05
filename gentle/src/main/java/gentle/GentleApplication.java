package gentle;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import tk.mybatis.spring.annotation.MapperScan;


@MapperScan("gentle.mapper")
@ServletComponentScan
@SpringBootApplication
public class GentleApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(GentleApplication.class,"classpath*:/spring/se*.xml");
       // application.setWebEnvironment(true);
        application.run(args);
    }
}
