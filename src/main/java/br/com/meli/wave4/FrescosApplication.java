package br.com.meli.wave4;

import br.com.meli.wave4.entities.DeliveryDates;
import br.com.meli.wave4.repositories.DeliveryDatesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.Column;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class FrescosApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(FrescosApplication.class, args);
    }

    @Autowired
    DeliveryDatesRepository deliveryDatesRepository;

    @Override
    public void run(String... args) throws Exception {
//        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
//        System.out.println("Password: " + encoder.encode("234"));

        List<DeliveryDates> deliveryDatesList = Arrays.asList(
                //São Paulo

                DeliveryDates.builder().deliveryLocation("São Paulo")
                    .dateTime(LocalDateTime.now().plusHours(3).truncatedTo(ChronoUnit.HOURS)).dateIsAvailable(true).build(),
                DeliveryDates.builder().deliveryLocation("São Paulo")
                    .dateTime(LocalDateTime.now().plusHours(4).truncatedTo(ChronoUnit.HOURS)).dateIsAvailable(true).build(),
                DeliveryDates.builder().deliveryLocation("São Paulo")
                    .dateTime(LocalDateTime.now().plusHours(5).truncatedTo(ChronoUnit.HOURS)).dateIsAvailable(true).build(),
                DeliveryDates.builder().deliveryLocation("São Paulo")
                    .dateTime(LocalDateTime.now().plusHours(6).truncatedTo(ChronoUnit.HOURS)).dateIsAvailable(true).build(),
                DeliveryDates.builder().deliveryLocation("São Paulo")
                    .dateTime(LocalDateTime.now().plusDays(1).plusHours(3).truncatedTo(ChronoUnit.HOURS)).dateIsAvailable(true).build(),
                DeliveryDates.builder().deliveryLocation("São Paulo")
                    .dateTime(LocalDateTime.now().plusDays(1).plusHours(4).truncatedTo(ChronoUnit.HOURS)).dateIsAvailable(true).build(),
                DeliveryDates.builder().deliveryLocation("São Paulo")
                    .dateTime(LocalDateTime.now().plusDays(1).plusHours(5).truncatedTo(ChronoUnit.HOURS)).dateIsAvailable(true).build(),
                DeliveryDates.builder().deliveryLocation("São Paulo")
                    .dateTime(LocalDateTime.now().plusDays(1).plusHours(6).truncatedTo(ChronoUnit.HOURS)).dateIsAvailable(true).build(),
                DeliveryDates.builder().deliveryLocation("São Paulo")
                    .dateTime(LocalDateTime.now().plusDays(2).plusHours(3).truncatedTo(ChronoUnit.HOURS)).dateIsAvailable(true).build(),
                DeliveryDates.builder().deliveryLocation("São Paulo")
                    .dateTime(LocalDateTime.now().plusDays(2).plusHours(4).truncatedTo(ChronoUnit.HOURS)).dateIsAvailable(true).build(),
                DeliveryDates.builder().deliveryLocation("São Paulo")
                    .dateTime(LocalDateTime.now().plusDays(2).plusHours(5).truncatedTo(ChronoUnit.HOURS)).dateIsAvailable(true).build(),
                DeliveryDates.builder().deliveryLocation("São Paulo")
                    .dateTime(LocalDateTime.now().plusDays(2).plusHours(6).truncatedTo(ChronoUnit.HOURS)).dateIsAvailable(true).build(),
                DeliveryDates.builder().deliveryLocation("São Paulo")
                    .dateTime(LocalDateTime.now().plusDays(3).plusHours(3).truncatedTo(ChronoUnit.HOURS)).dateIsAvailable(true).build(),
                DeliveryDates.builder().deliveryLocation("São Paulo")
                    .dateTime(LocalDateTime.now().plusDays(3).plusHours(4).truncatedTo(ChronoUnit.HOURS)).dateIsAvailable(true).build(),
                DeliveryDates.builder().deliveryLocation("São Paulo")
                    .dateTime(LocalDateTime.now().plusDays(3).plusHours(5).truncatedTo(ChronoUnit.HOURS)).dateIsAvailable(true).build(),
                DeliveryDates.builder().deliveryLocation("São Paulo")
                    .dateTime(LocalDateTime.now().plusDays(3).plusHours(6).truncatedTo(ChronoUnit.HOURS)).dateIsAvailable(true).build(),
                //Santa Catarina
                DeliveryDates.builder().deliveryLocation("Santa Catarina")
                        .dateTime(LocalDateTime.now().plusHours(3).truncatedTo(ChronoUnit.HOURS)).dateIsAvailable(true).build(),
                DeliveryDates.builder().deliveryLocation("Santa Catarina")
                        .dateTime(LocalDateTime.now().plusHours(4).truncatedTo(ChronoUnit.HOURS)).dateIsAvailable(true).build(),
                DeliveryDates.builder().deliveryLocation("Santa Catarina")
                        .dateTime(LocalDateTime.now().plusHours(5).truncatedTo(ChronoUnit.HOURS)).dateIsAvailable(true).build(),
                DeliveryDates.builder().deliveryLocation("Santa Catarina")
                        .dateTime(LocalDateTime.now().plusHours(6).truncatedTo(ChronoUnit.HOURS)).dateIsAvailable(true).build(),
                DeliveryDates.builder().deliveryLocation("Santa Catarina")
                        .dateTime(LocalDateTime.now().plusDays(1).plusHours(3).truncatedTo(ChronoUnit.HOURS)).dateIsAvailable(true).build(),
                DeliveryDates.builder().deliveryLocation("Santa Catarina")
                        .dateTime(LocalDateTime.now().plusDays(1).plusHours(4).truncatedTo(ChronoUnit.HOURS)).dateIsAvailable(true).build(),
                DeliveryDates.builder().deliveryLocation("Santa Catarina")
                        .dateTime(LocalDateTime.now().plusDays(1).plusHours(5).truncatedTo(ChronoUnit.HOURS)).dateIsAvailable(true).build(),
                DeliveryDates.builder().deliveryLocation("Santa Catarina")
                        .dateTime(LocalDateTime.now().plusDays(1).plusHours(6).truncatedTo(ChronoUnit.HOURS)).dateIsAvailable(true).build(),
                DeliveryDates.builder().deliveryLocation("Santa Catarina")
                        .dateTime(LocalDateTime.now().plusDays(2).plusHours(3).truncatedTo(ChronoUnit.HOURS)).dateIsAvailable(true).build(),
                DeliveryDates.builder().deliveryLocation("Santa Catarina")
                        .dateTime(LocalDateTime.now().plusDays(2).plusHours(4).truncatedTo(ChronoUnit.HOURS)).dateIsAvailable(true).build(),
                DeliveryDates.builder().deliveryLocation("Santa Catarina")
                        .dateTime(LocalDateTime.now().plusDays(2).plusHours(5).truncatedTo(ChronoUnit.HOURS)).dateIsAvailable(true).build(),
                DeliveryDates.builder().deliveryLocation("Santa Catarina")
                        .dateTime(LocalDateTime.now().plusDays(2).plusHours(6).truncatedTo(ChronoUnit.HOURS)).dateIsAvailable(true).build(),
                DeliveryDates.builder().deliveryLocation("Santa Catarina")
                        .dateTime(LocalDateTime.now().plusDays(3).plusHours(3).truncatedTo(ChronoUnit.HOURS)).dateIsAvailable(true).build(),
                DeliveryDates.builder().deliveryLocation("Santa Catarina")
                        .dateTime(LocalDateTime.now().plusDays(3).plusHours(4).truncatedTo(ChronoUnit.HOURS)).dateIsAvailable(true).build(),
                DeliveryDates.builder().deliveryLocation("Santa Catarina")
                        .dateTime(LocalDateTime.now().plusDays(3).plusHours(5).truncatedTo(ChronoUnit.HOURS)).dateIsAvailable(true).build(),
                DeliveryDates.builder().deliveryLocation("Santa Catarina")
                        .dateTime(LocalDateTime.now().plusDays(3).plusHours(6).truncatedTo(ChronoUnit.HOURS)).dateIsAvailable(true).build(),
                //Bahia
                DeliveryDates.builder().deliveryLocation("Bahia")
                        .dateTime(LocalDateTime.now().plusHours(3).truncatedTo(ChronoUnit.HOURS)).dateIsAvailable(true).build(),
                DeliveryDates.builder().deliveryLocation("Bahia")
                        .dateTime(LocalDateTime.now().plusHours(4).truncatedTo(ChronoUnit.HOURS)).dateIsAvailable(true).build(),
                DeliveryDates.builder().deliveryLocation("Bahia")
                        .dateTime(LocalDateTime.now().plusHours(5).truncatedTo(ChronoUnit.HOURS)).dateIsAvailable(true).build(),
                DeliveryDates.builder().deliveryLocation("Bahia")
                        .dateTime(LocalDateTime.now().plusHours(6).truncatedTo(ChronoUnit.HOURS)).dateIsAvailable(true).build(),
                DeliveryDates.builder().deliveryLocation("Bahia")
                        .dateTime(LocalDateTime.now().plusDays(1).plusHours(3).truncatedTo(ChronoUnit.HOURS)).dateIsAvailable(true).build(),
                DeliveryDates.builder().deliveryLocation("Bahia")
                        .dateTime(LocalDateTime.now().plusDays(1).plusHours(4).truncatedTo(ChronoUnit.HOURS)).dateIsAvailable(true).build(),
                DeliveryDates.builder().deliveryLocation("Bahia")
                        .dateTime(LocalDateTime.now().plusDays(1).plusHours(5).truncatedTo(ChronoUnit.HOURS)).dateIsAvailable(true).build(),
                DeliveryDates.builder().deliveryLocation("Bahia")
                        .dateTime(LocalDateTime.now().plusDays(1).plusHours(6).truncatedTo(ChronoUnit.HOURS)).dateIsAvailable(true).build(),
                DeliveryDates.builder().deliveryLocation("Bahia")
                        .dateTime(LocalDateTime.now().plusDays(2).plusHours(3).truncatedTo(ChronoUnit.HOURS)).dateIsAvailable(true).build(),
                DeliveryDates.builder().deliveryLocation("Bahia")
                        .dateTime(LocalDateTime.now().plusDays(2).plusHours(4).truncatedTo(ChronoUnit.HOURS)).dateIsAvailable(true).build(),
                DeliveryDates.builder().deliveryLocation("Bahia")
                        .dateTime(LocalDateTime.now().plusDays(2).plusHours(5).truncatedTo(ChronoUnit.HOURS)).dateIsAvailable(true).build(),
                DeliveryDates.builder().deliveryLocation("Bahia")
                        .dateTime(LocalDateTime.now().plusDays(2).plusHours(6).truncatedTo(ChronoUnit.HOURS)).dateIsAvailable(true).build(),
                DeliveryDates.builder().deliveryLocation("Bahia")
                        .dateTime(LocalDateTime.now().plusDays(3).plusHours(3).truncatedTo(ChronoUnit.HOURS)).dateIsAvailable(true).build(),
                DeliveryDates.builder().deliveryLocation("Bahia")
                        .dateTime(LocalDateTime.now().plusDays(3).plusHours(4).truncatedTo(ChronoUnit.HOURS)).dateIsAvailable(true).build(),
                DeliveryDates.builder().deliveryLocation("Bahia")
                        .dateTime(LocalDateTime.now().plusDays(3).plusHours(5).truncatedTo(ChronoUnit.HOURS)).dateIsAvailable(true).build(),
                DeliveryDates.builder().deliveryLocation("Bahia")
                        .dateTime(LocalDateTime.now().plusDays(3).plusHours(6).truncatedTo(ChronoUnit.HOURS)).dateIsAvailable(true).build(),
                //Minas Gerais
                DeliveryDates.builder().deliveryLocation("Minas Gerais")
                    .dateTime(LocalDateTime.now().plusHours(3).truncatedTo(ChronoUnit.HOURS)).dateIsAvailable(true).build(),
                DeliveryDates.builder().deliveryLocation("Minas Gerais")
                        .dateTime(LocalDateTime.now().plusHours(4).truncatedTo(ChronoUnit.HOURS)).dateIsAvailable(true).build(),
                DeliveryDates.builder().deliveryLocation("Minas Gerais")
                        .dateTime(LocalDateTime.now().plusHours(5).truncatedTo(ChronoUnit.HOURS)).dateIsAvailable(true).build(),
                DeliveryDates.builder().deliveryLocation("Minas Gerais")
                        .dateTime(LocalDateTime.now().plusHours(6).truncatedTo(ChronoUnit.HOURS)).dateIsAvailable(true).build(),
                DeliveryDates.builder().deliveryLocation("Minas Gerais")
                        .dateTime(LocalDateTime.now().plusDays(1).plusHours(3).truncatedTo(ChronoUnit.HOURS)).dateIsAvailable(true).build(),
                DeliveryDates.builder().deliveryLocation("Minas Gerais")
                        .dateTime(LocalDateTime.now().plusDays(1).plusHours(4).truncatedTo(ChronoUnit.HOURS)).dateIsAvailable(true).build(),
                DeliveryDates.builder().deliveryLocation("Minas Gerais")
                        .dateTime(LocalDateTime.now().plusDays(1).plusHours(5).truncatedTo(ChronoUnit.HOURS)).dateIsAvailable(true).build(),
                DeliveryDates.builder().deliveryLocation("Minas Gerais")
                        .dateTime(LocalDateTime.now().plusDays(1).plusHours(6).truncatedTo(ChronoUnit.HOURS)).dateIsAvailable(true).build(),
                DeliveryDates.builder().deliveryLocation("Minas Gerais")
                        .dateTime(LocalDateTime.now().plusDays(2).plusHours(3).truncatedTo(ChronoUnit.HOURS)).dateIsAvailable(true).build(),
                DeliveryDates.builder().deliveryLocation("Minas Gerais")
                        .dateTime(LocalDateTime.now().plusDays(2).plusHours(4).truncatedTo(ChronoUnit.HOURS)).dateIsAvailable(true).build(),
                DeliveryDates.builder().deliveryLocation("Minas Gerais")
                        .dateTime(LocalDateTime.now().plusDays(2).plusHours(5).truncatedTo(ChronoUnit.HOURS)).dateIsAvailable(true).build(),
                DeliveryDates.builder().deliveryLocation("Minas Gerais")
                        .dateTime(LocalDateTime.now().plusDays(2).plusHours(6).truncatedTo(ChronoUnit.HOURS)).dateIsAvailable(true).build(),
                DeliveryDates.builder().deliveryLocation("Minas Gerais")
                        .dateTime(LocalDateTime.now().plusDays(3).plusHours(3).truncatedTo(ChronoUnit.HOURS)).dateIsAvailable(true).build(),
                DeliveryDates.builder().deliveryLocation("Minas Gerais")
                        .dateTime(LocalDateTime.now().plusDays(3).plusHours(4).truncatedTo(ChronoUnit.HOURS)).dateIsAvailable(true).build(),
                DeliveryDates.builder().deliveryLocation("Minas Gerais")
                        .dateTime(LocalDateTime.now().plusDays(3).plusHours(5).truncatedTo(ChronoUnit.HOURS)).dateIsAvailable(true).build(),
                DeliveryDates.builder().deliveryLocation("Minas Gerais")
                        .dateTime(LocalDateTime.now().plusDays(3).plusHours(6).truncatedTo(ChronoUnit.HOURS)).dateIsAvailable(true).build(),
                //Paraíba
                DeliveryDates.builder().deliveryLocation("Paraíba")
                        .dateTime(LocalDateTime.now().plusHours(3).truncatedTo(ChronoUnit.HOURS)).dateIsAvailable(true).build(),
                DeliveryDates.builder().deliveryLocation("Paraíba")
                        .dateTime(LocalDateTime.now().plusHours(4).truncatedTo(ChronoUnit.HOURS)).dateIsAvailable(true).build(),
                DeliveryDates.builder().deliveryLocation("Paraíba")
                        .dateTime(LocalDateTime.now().plusHours(5).truncatedTo(ChronoUnit.HOURS)).dateIsAvailable(true).build(),
                DeliveryDates.builder().deliveryLocation("Paraíba")
                        .dateTime(LocalDateTime.now().plusHours(6).truncatedTo(ChronoUnit.HOURS)).dateIsAvailable(true).build(),
                DeliveryDates.builder().deliveryLocation("Paraíba")
                        .dateTime(LocalDateTime.now().plusDays(1).plusHours(3).truncatedTo(ChronoUnit.HOURS)).dateIsAvailable(true).build(),
                DeliveryDates.builder().deliveryLocation("Paraíba")
                        .dateTime(LocalDateTime.now().plusDays(1).plusHours(4).truncatedTo(ChronoUnit.HOURS)).dateIsAvailable(true).build(),
                DeliveryDates.builder().deliveryLocation("Paraíba")
                        .dateTime(LocalDateTime.now().plusDays(1).plusHours(5).truncatedTo(ChronoUnit.HOURS)).dateIsAvailable(true).build(),
                DeliveryDates.builder().deliveryLocation("Paraíba")
                        .dateTime(LocalDateTime.now().plusDays(1).plusHours(6).truncatedTo(ChronoUnit.HOURS)).dateIsAvailable(true).build(),
                DeliveryDates.builder().deliveryLocation("Paraíba")
                        .dateTime(LocalDateTime.now().plusDays(2).plusHours(3).truncatedTo(ChronoUnit.HOURS)).dateIsAvailable(true).build(),
                DeliveryDates.builder().deliveryLocation("Paraíba")
                        .dateTime(LocalDateTime.now().plusDays(2).plusHours(4).truncatedTo(ChronoUnit.HOURS)).dateIsAvailable(true).build(),
                DeliveryDates.builder().deliveryLocation("Paraíba")
                        .dateTime(LocalDateTime.now().plusDays(2).plusHours(5).truncatedTo(ChronoUnit.HOURS)).dateIsAvailable(true).build(),
                DeliveryDates.builder().deliveryLocation("Paraíba")
                        .dateTime(LocalDateTime.now().plusDays(2).plusHours(6).truncatedTo(ChronoUnit.HOURS)).dateIsAvailable(true).build(),
                DeliveryDates.builder().deliveryLocation("Paraíba")
                        .dateTime(LocalDateTime.now().plusDays(3).plusHours(3).truncatedTo(ChronoUnit.HOURS)).dateIsAvailable(true).build(),
                DeliveryDates.builder().deliveryLocation("Paraíba")
                        .dateTime(LocalDateTime.now().plusDays(3).plusHours(4).truncatedTo(ChronoUnit.HOURS)).dateIsAvailable(true).build(),
                DeliveryDates.builder().deliveryLocation("Paraíba")
                        .dateTime(LocalDateTime.now().plusDays(3).plusHours(5).truncatedTo(ChronoUnit.HOURS)).dateIsAvailable(true).build(),
                DeliveryDates.builder().deliveryLocation("Paraíba")
                        .dateTime(LocalDateTime.now().plusDays(3).plusHours(6).truncatedTo(ChronoUnit.HOURS)).dateIsAvailable(true).build(),
                //Paraná
                DeliveryDates.builder().deliveryLocation("Paraná")
                        .dateTime(LocalDateTime.now().plusHours(3).truncatedTo(ChronoUnit.HOURS)).dateIsAvailable(true).build(),
                DeliveryDates.builder().deliveryLocation("Paraná")
                        .dateTime(LocalDateTime.now().plusHours(4).truncatedTo(ChronoUnit.HOURS)).dateIsAvailable(true).build(),
                DeliveryDates.builder().deliveryLocation("Paraná")
                        .dateTime(LocalDateTime.now().plusHours(5).truncatedTo(ChronoUnit.HOURS)).dateIsAvailable(true).build(),
                DeliveryDates.builder().deliveryLocation("Paraná")
                        .dateTime(LocalDateTime.now().plusHours(6).truncatedTo(ChronoUnit.HOURS)).dateIsAvailable(true).build(),
                DeliveryDates.builder().deliveryLocation("Paraná")
                        .dateTime(LocalDateTime.now().plusDays(1).plusHours(3).truncatedTo(ChronoUnit.HOURS)).dateIsAvailable(true).build(),
                DeliveryDates.builder().deliveryLocation("Paraná")
                        .dateTime(LocalDateTime.now().plusDays(1).plusHours(4).truncatedTo(ChronoUnit.HOURS)).dateIsAvailable(true).build(),
                DeliveryDates.builder().deliveryLocation("Paraná")
                        .dateTime(LocalDateTime.now().plusDays(1).plusHours(5).truncatedTo(ChronoUnit.HOURS)).dateIsAvailable(true).build(),
                DeliveryDates.builder().deliveryLocation("Paraná")
                        .dateTime(LocalDateTime.now().plusDays(1).plusHours(6).truncatedTo(ChronoUnit.HOURS)).dateIsAvailable(true).build(),
                DeliveryDates.builder().deliveryLocation("Paraná")
                        .dateTime(LocalDateTime.now().plusDays(2).plusHours(3).truncatedTo(ChronoUnit.HOURS)).dateIsAvailable(true).build(),
                DeliveryDates.builder().deliveryLocation("Paraná")
                        .dateTime(LocalDateTime.now().plusDays(2).plusHours(4).truncatedTo(ChronoUnit.HOURS)).dateIsAvailable(true).build(),
                DeliveryDates.builder().deliveryLocation("Paraná")
                        .dateTime(LocalDateTime.now().plusDays(2).plusHours(5).truncatedTo(ChronoUnit.HOURS)).dateIsAvailable(true).build(),
                DeliveryDates.builder().deliveryLocation("Paraná")
                        .dateTime(LocalDateTime.now().plusDays(2).plusHours(6).truncatedTo(ChronoUnit.HOURS)).dateIsAvailable(true).build(),
                DeliveryDates.builder().deliveryLocation("Paraná")
                        .dateTime(LocalDateTime.now().plusDays(3).plusHours(3).truncatedTo(ChronoUnit.HOURS)).dateIsAvailable(true).build(),
                DeliveryDates.builder().deliveryLocation("Paraná")
                        .dateTime(LocalDateTime.now().plusDays(3).plusHours(4).truncatedTo(ChronoUnit.HOURS)).dateIsAvailable(true).build(),
                DeliveryDates.builder().deliveryLocation("Paraná")
                        .dateTime(LocalDateTime.now().plusDays(3).plusHours(5).truncatedTo(ChronoUnit.HOURS)).dateIsAvailable(true).build(),
                DeliveryDates.builder().deliveryLocation("Paraná")
                        .dateTime(LocalDateTime.now().plusDays(3).plusHours(6).truncatedTo(ChronoUnit.HOURS)).dateIsAvailable(true).build()
        );

        deliveryDatesRepository.saveAll(deliveryDatesList);

    }
}
