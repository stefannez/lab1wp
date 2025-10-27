package mk.ukim.finki.wp.lab1wp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@ServletComponentScan
public class Lab1wpApplication {

    public static void main(String[] args) {
        SpringApplication.run(Lab1wpApplication.class, args);
    }

}
