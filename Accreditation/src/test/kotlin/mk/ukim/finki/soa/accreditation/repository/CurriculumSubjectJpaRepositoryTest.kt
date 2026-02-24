package mk.ukim.finki.soa.accreditation.repository

import mk.ukim.finki.soa.accreditation.model.CurriculumSubjectId
import mk.ukim.finki.soa.accreditation.model.SubjectId
import mk.ukim.finki.soa.accreditation.model.StudyProgramId
import mk.ukim.finki.soa.accreditation.model.study_program.CurriculumSubject
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest

@DataJpaTest
class CurriculumSubjectJpaRepositoryTest @Autowired constructor(
    val repo: CurriculumSubjectJpaRepository
) {

    private fun cs(
        sp: String,
        sub: String,
        mandatory: Boolean,
        semester: Int,
        order: Float = 1f,
        group: String = "CORE",
        dep: String? = null
    ): CurriculumSubject =
        CurriculumSubject(
            id = CurriculumSubjectId(StudyProgramId(sp), SubjectId(sub)),
            mandatory = mandatory,
            semester = semester,
            order = order,
            subjectGroup = group,
            dependenciesOverride = dep
        )

    @Test
    fun `save and load by composite id`() {
        val entity = cs("SP-001", "SUB-001", mandatory = true, semester = 3)
        repo.save(entity)

        val loaded = repo.findById(entity.id)
        assertTrue(loaded.isPresent)
        assertEquals(StudyProgramId("SP-001"), loaded.get().studyProgram)
        assertEquals(SubjectId("SUB-001"), loaded.get().subject)
        assertEquals(true, loaded.get().mandatory)
        assertEquals(3, loaded.get().semester)
    }

    @Test
    fun `find mandatory subjects for a study program in a semester`() {
        repo.saveAll(
            listOf(
                cs("SP-001", "SUB-001", mandatory = true, semester = 3),
                cs("SP-001", "SUB-002", mandatory = true, semester = 3),
                cs("SP-001", "SUB-003", mandatory = true, semester = 5),
                cs("SP-001", "SUB-004", mandatory = false, semester = 3),
                cs("SP-002", "SUB-005", mandatory = true, semester = 3) // other program
            )
        )

        // Requires the repo method: findAllByIdStudyProgramAndMandatoryTrueAndSemester
        val res = repo.findAllByIdStudyProgramAndMandatoryTrueAndSemester(StudyProgramId("SP-001"), 3)

        assertEquals(2, res.size)

        assertTrue(res.all { it.mandatory })
        assertTrue(res.all { it.semester == 3 })
        assertTrue(res.all { it.studyProgram == StudyProgramId("SP-001") })

        val expectedSubjects = setOf(
            SubjectId("SUB-001"),
            SubjectId("SUB-002")
        )

        val actualSubjects = res.map { it.subject }.toSet()

        assertEquals(expectedSubjects, actualSubjects)
    }

    @Test
    fun `find non-mandatory subjects for a study program in a semester`() {
        repo.saveAll(
            listOf(
                cs("SP-001", "SUB-001", mandatory = false, semester = 3),
                cs("SP-001", "SUB-002", mandatory = false, semester = 3),
                cs("SP-001", "SUB-003", mandatory = false, semester = 5),
                cs("SP-001", "SUB-004", mandatory = true, semester = 3)
            )
        )

        val res = repo.findAllByIdStudyProgramAndMandatoryFalseAndSemester(StudyProgramId("SP-001"), 3)

        println(res.map { "${it.studyProgram.value} ${it.subject.value} mandatory=${it.mandatory} semester=${it.semester}" })

        assertEquals(2, res.size)
        assertTrue(res.all { !it.mandatory })
        assertTrue(res.all { it.semester == 3 })
        assertEquals(
            setOf(SubjectId("SUB-001"), SubjectId("SUB-002")),
            res.map { it.subject }.toSet()
        )
    }

    @Test
    fun `mandatory query without semester returns all mandatory across semesters`() {
        repo.saveAll(
            listOf(
                cs("SP-001", "SUB-001", mandatory = true, semester = 1),
                cs("SP-001", "SUB-002", mandatory = true, semester = 2),
                cs("SP-001", "SUB-003", mandatory = false, semester = 1)
            )
        )

        val res = repo.findAllByIdStudyProgramAndMandatoryTrue(StudyProgramId("SP-001"))

        assertEquals(2, res.size)
        assertTrue(res.all { it.mandatory })
        assertEquals(
            setOf(SubjectId("SUB-001"), SubjectId("SUB-002")),
            res.map { it.subject }.toSet()
        )
    }

    @Test
    fun `same subject can exist in multiple programs without collision`() {
        repo.saveAll(
            listOf(
                cs("SP-001", "SUB-001", mandatory = true, semester = 3),
                cs("SP-002", "SUB-001", mandatory = false, semester = 3)
            )
        )

        val a = repo.findById(CurriculumSubjectId(StudyProgramId("SP-001"), SubjectId("SUB-001")))
        val b = repo.findById(CurriculumSubjectId(StudyProgramId("SP-002"), SubjectId("SUB-001")))

        assertTrue(a.isPresent)
        assertTrue(b.isPresent)
        assertNotEquals(a.get().mandatory, b.get().mandatory)
    }
}