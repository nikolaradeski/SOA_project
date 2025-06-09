package mk.ukim.finki.soa.accreditation.repository

import jakarta.persistence.EntityManager
import jakarta.persistence.PersistenceContext
import mk.ukim.finki.soa.accreditation.model.StudyProgramId
import mk.ukim.finki.soa.accreditation.model.study_program.StudyProgram
import org.axonframework.common.jpa.SimpleEntityManagerProvider
import org.axonframework.eventhandling.EventBus
import org.axonframework.messaging.annotation.ParameterResolverFactory
import org.axonframework.modelling.command.GenericJpaRepository
import org.axonframework.modelling.command.Repository
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration("RepositoriesConfiguration")
class AxonRepositoriesConfiguration(@PersistenceContext val entityManager: EntityManager) {
    @Bean("axonStudyProgramRepository")
    fun expenseGenericJpaRepository(
            eventBus: EventBus,
            parameterResolverFactory: ParameterResolverFactory
    ): Repository<StudyProgram> {
        return GenericJpaRepository.builder(StudyProgram::class.java)
                .entityManagerProvider(SimpleEntityManagerProvider(entityManager))
                .parameterResolverFactory(parameterResolverFactory)
                .eventBus(eventBus)
                .identifierConverter { StudyProgramId(it) }
                .build()
    }
}
