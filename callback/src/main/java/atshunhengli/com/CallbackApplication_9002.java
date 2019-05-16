package atshunhengli.com;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@MapperScan(value = "atshunhengli.com.mapper")
@SpringBootApplication
@ServletComponentScan
public class CallbackApplication_9002 {

	public static void main(String[] args) {
		SpringApplication.run(CallbackApplication_9002.class, args);
	}
}
