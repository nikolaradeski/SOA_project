package mk.ukim.finki.soa.accreditation.handlers

import mk.ukim.finki.soa.accreditation.model.CurriculumSubjectId
import mk.ukim.finki.soa.accreditation.model.SubjectId
import mk.ukim.finki.soa.accreditation.model.StudyProgramId
import mk.ukim.finki.soa.accreditation.model.study_program.CurriculumSubject
import mk.ukim.finki.soa.accreditation.model.*
import mk.ukim.finki.soa.accreditation.repository.CurriculumSubjectJpaRepository
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.ArgumentCaptor
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.junit.jupiter.MockitoExtension
import java.util.*

@ExtendWith(MockitoExtension::class)
class CurriculumSubjectProjectionTest {

    @Mock lateinit var repo: CurriculumSubjectJpaRepository

    @InjectMocks lateinit var projection: CurriculumSubjectProjection

    @Test
    fun `on StudyProgramSubjectCreatedEvent saves row with composite id`() {
        val spId = StudyProgramId("SP-001")
        val subId = SubjectId("SUB-001")
        val csId = CurriculumSubjectId(spId, subId)

        val event = StudyProgramSubjectCreatedEvent(
            studyProgramId = spId,
            curriculumSubjectId = csId,
            mandatory = true,
            semester = 3,
            order = 1.0f,
            subjectGroup = "CORE",
            dependenciesOverride = "None"
        )

        val captor = ArgumentCaptor.forClass(CurriculumSubject::class.java)
        `when`(repo.save(any(CurriculumSubject::class.java))).thenAnswer { it.arguments[0] }

        projection.on(event)

        verify(repo, times(1)).save(captor.capture())
        val saved = captor.value

        assertEquals(csId, saved.id)
        assertEquals(true, saved.mandatory)
        assertEquals(3, saved.semester)
        assertEquals(1.0f, saved.order)
        assertEquals("CORE", saved.subjectGroup)
        assertEquals("None", saved.dependenciesOverride)
    }

    @Test
    fun `on mandatory updated event loads row updates mandatory and saves`() {
        val spId = StudyProgramId("SP-001")
        val subId = SubjectId("SUB-001")
        val csId = CurriculumSubjectId(spId, subId)

        val existing = CurriculumSubject(
            id = csId,
            mandatory = false,
            semester = 3,
            order = 1.0f,
            subjectGroup = "CORE",
            dependenciesOverride = null
        )

        `when`(repo.findById(csId)).thenReturn(Optional.of(existing))
        `when`(repo.save(any(CurriculumSubject::class.java))).thenAnswer { it.arguments[0] }

        val event = StudyProgramSubjectMandatoryUpdatedEvent(
            studyProgramId = spId,
            curriculumSubjectId = csId,
            mandatory = true
        )

        projection.on(event)

        assertEquals(true, existing.mandatory)
        verify(repo).save(existing)
    }

    @Test
    fun `on semester updated event loads row updates semester and saves`() {
        val spId = StudyProgramId("SP-001")
        val subId = SubjectId("SUB-001")
        val csId = CurriculumSubjectId(spId, subId)

        val existing = CurriculumSubject(
            id = csId,
            mandatory = true,
            semester = 3,
            order = 1.0f,
            subjectGroup = "CORE",
            dependenciesOverride = null
        )

        `when`(repo.findById(csId)).thenReturn(Optional.of(existing))
        `when`(repo.save(any(CurriculumSubject::class.java))).thenAnswer { it.arguments[0] }

        val event = StudyProgramSubjectSemesterUpdatedEvent(
            studyProgramId = spId,
            curriculumSubjectId = csId,
            semester = 5
        )

        projection.on(event)

        assertEquals(5, existing.semester)
        verify(repo).save(existing)
    }
}