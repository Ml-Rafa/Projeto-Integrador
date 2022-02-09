package br.com.meli.wave4;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FrescosApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(FrescosApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
//        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
//        System.out.println("Password: " + encoder.encode("234"));
    }
}
