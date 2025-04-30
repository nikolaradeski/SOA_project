package mk.ukim.finki.soa.accreditation.repository

import jakarta.persistence.EntityManager
import jakarta.persistence.PersistenceContext
import mk.ukim.finki.soa.accreditation.model.StudyProgramDetailsId
import mk.ukim.finki.soa.accreditation.model.study_program.StudyProgramDetails
import org.axonframework.common.jpa.SimpleEntityManagerProvider
import org.axonframework.eventhandling.EventBus
import org.axonframework.messaging.annotation.ParameterResolverFactory
import org.axonframework.modelling.command.GenericJpaRepository
import org.axonframework.modelling.command.Repository
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration("RepositoriesConfiguration")
class AxonRepositoriesConfiguration(@PersistenceContext val entityManager: EntityManager) {
    @Bean("axonStudyProgramDetailsRepository")
    fun expenseGenericJpaRepository(
            eventBus: EventBus,
            parameterResolverFactory: ParameterResolverFactory
    ): Repository<StudyProgramDetails> {
        return GenericJpaRepository.builder(StudyProgramDetails::class.java)
                .entityManagerProvider(SimpleEntityManagerProvider(entityManager))
                .parameterResolverFactory(parameterResolverFactory)
                .eventBus(eventBus)
                .identifierConverter { StudyProgramDetailsId(it) }
                .build()
    }

}
