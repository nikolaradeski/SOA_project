package mk.ukim.finki.soa.accreditation.config

import mk.ukim.finki.soa.accreditation.client.interceptors.CorrelationIdInterceptor
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class FeignConfig {
    @Bean
    fun correlationIdInterceptor(): CorrelationIdInterceptor {
        return CorrelationIdInterceptor()
    }
}
