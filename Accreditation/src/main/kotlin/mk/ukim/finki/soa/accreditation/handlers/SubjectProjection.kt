package mk.ukim.finki.soa.accreditation.handlers

import mk.ukim.finki.soa.accreditation.model.SubjectCreatedEvent
import mk.ukim.finki.soa.accreditation.model.SubjectView
import mk.ukim.finki.soa.accreditation.repository.SubjectViewJpaRepository
import org.axonframework.eventhandling.EventHandler
import org.springframework.stereotype.Component

@Component
class SubjectProjection(
    private val repo: SubjectViewJpaRepository
) {
    @EventHandler
    fun on(event: SubjectCreatedEvent) {
        val name = event.name ?: return // or error(...) if you want strict
        val abbreviation = event.abbreviation ?: return

        repo.save(
            SubjectView(
                code = event.subjectId,
                name = name,
                abbreviation = abbreviation
            )
        )
    }
}
