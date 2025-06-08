package mk.ukim.finki.soa.accreditation

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.openfeign.EnableFeignClients

@SpringBootApplication
@EnableFeignClients
class AccreditationApplication

fun main(args: Array<String>) {
    runApplication<AccreditationApplication>(*args)
}
