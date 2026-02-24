package mk.ukim.finki.soa.accreditation.handlers

import mk.ukim.finki.soa.accreditation.model.CurriculumSubjectId
import mk.ukim.finki.soa.accreditation.model.StudyProgramSubjectCreatedEvent
import mk.ukim.finki.soa.accreditation.model.StudyProgramSubjectMandatoryUpdatedEvent
import mk.ukim.finki.soa.accreditation.model.StudyProgramSubjectSemesterUpdatedEvent
import mk.ukim.finki.soa.accreditation.model.study_program.*
import mk.ukim.finki.soa.accreditation.repository.CurriculumSubjectJpaRepository
import org.axonframework.eventhandling.EventHandler
import org.springframework.stereotype.Component

@Component
class CurriculumSubjectProjection(
    private val repo: CurriculumSubjectJpaRepository
) {

    @EventHandler
    fun on(event: StudyProgramSubjectCreatedEvent) {
        val entity = CurriculumSubject(
            id = event.curriculumSubjectId,
            mandatory = event.mandatory,
            semester = event.semester,
            order = event.order,
            subjectGroup = event.subjectGroup,
            dependenciesOverride = event.dependenciesOverride
        )
        repo.save(entity)
    }

    @EventHandler
    fun on(event: StudyProgramSubjectMandatoryUpdatedEvent) {
        val row = repo.findById(event.curriculumSubjectId).orElseThrow()
        row.mandatory = event.mandatory
        repo.save(row)
    }

    @EventHandler
    fun on(event: StudyProgramSubjectSemesterUpdatedEvent) {
        val row = repo.findById(event.curriculumSubjectId).orElseThrow()
        row.semester = event.semester
        repo.save(row)
    }
}