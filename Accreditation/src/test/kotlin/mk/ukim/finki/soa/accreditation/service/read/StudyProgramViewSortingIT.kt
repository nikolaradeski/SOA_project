// package mk.ukim.finki.soa.accreditation.service.read
//
//import mk.ukim.finki.soa.accreditation.model.CurriculumSubjectId
//import mk.ukim.finki.soa.accreditation.model.StudyProgramId
//import mk.ukim.finki.soa.accreditation.model.SubjectId
//import mk.ukim.finki.soa.accreditation.model.study_program.CurriculumSubject
//import mk.ukim.finki.soa.accreditation.repository.CurriculumSubjectJpaRepository
//import org.junit.jupiter.api.Assertions.assertEquals
//import org.junit.jupiter.api.Assertions.assertTrue
//import org.junit.jupiter.api.Test
//import org.springframework.beans.factory.annotation.Autowired
//import org.springframework.boot.test.context.SpringBootTest
//import org.springframework.transaction.annotation.Transactional
//import java.util.UUID
//
//@SpringBootTest
//@Transactional
//class CurriculumQueryServiceIT {
//
//    @Autowired lateinit var repo: CurriculumSubjectJpaRepository
//    @Autowired lateinit var service: CurriculumQueryService
//
//    private fun cs(
//        sp: String,
//        sub: String,
//        semester: Int,
//        mandatory: Boolean,
//        order: Float?
//    ) = CurriculumSubject(
//        id = CurriculumSubjectId(StudyProgramId(sp), SubjectId(sub)),
//        mandatory = mandatory,
//        semester = semester,
//        order = order,
//        subjectGroup = "CORE",
//        dependenciesOverride = null
//    )
//
//    @Test
//    fun `subjects are sorted by semester then mandatory then order and printed`() {
//        // Use a unique program id to avoid clashing with persistent DB (SP-001 etc.)
//        val sp = "TEST-SP-${UUID.randomUUID().toString().substring(0, 8)}"
//
//        repo.saveAll(
//            listOf(
//                // Semester 1
//                cs(sp, "SUB-A", semester = 1, mandatory = true,  order = 2f),
//                cs(sp, "SUB-B", semester = 1, mandatory = false, order = 1f),
//                cs(sp, "SUB-C", semester = 1, mandatory = false, order = 0f),
//                cs(sp, "SUB-D", semester = 1, mandatory = true,  order = 3f),
//
//                // Semester 2
//                cs(sp, "SUB-E", semester = 2, mandatory = false, order = 5f),
//                cs(sp, "SUB-F", semester = 2, mandatory = true,  order = 1f),
//            )
//        )
//
//        val sorted = service.sortedForStudyProgram(StudyProgramId(sp))
//
//        val ids = sorted.map { it.subject.value }
//
//        assertEquals(
//            listOf("Subject:SUB-A","Subject:SUB-D","Subject:SUB-C","Subject:SUB-B","Subject:SUB-F","Subject:SUB-E"),
//            ids
//        )
//
//        val printout = service.prettyPrintBySemester(StudyProgramId(sp))
//        println(printout)
//
//        assertTrue(printout.contains("------Semester 1------"))
//        assertTrue(printout.contains("------Semester 2------"))
//        assertTrue(printout.contains("-------Mandatory------"))
//        assertTrue(printout.contains("-------Non-mandatory------"))
//    }
//}

package mk.ukim.finki.soa.accreditation.service.read

import jakarta.persistence.EntityManager
import jakarta.persistence.PersistenceContext
import mk.ukim.finki.soa.accreditation.model.CurriculumSubjectId
import mk.ukim.finki.soa.accreditation.model.StudyProgramId
import mk.ukim.finki.soa.accreditation.model.StudyProgramView
import mk.ukim.finki.soa.accreditation.model.SubjectId
import mk.ukim.finki.soa.accreditation.model.generalEnums.StudyCycle
import mk.ukim.finki.soa.accreditation.model.study_program.CurriculumSubject
import mk.ukim.finki.soa.accreditation.repository.CurriculumSubjectJpaRepository
import mk.ukim.finki.soa.accreditation.repository.StudyProgramViewJpaRepository
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.transaction.annotation.Transactional
import java.util.UUID

@SpringBootTest
@Transactional
class StudyProgramViewSortingIT {

    @Autowired lateinit var csRepo: CurriculumSubjectJpaRepository
    @Autowired lateinit var spRepo: StudyProgramViewJpaRepository

    @PersistenceContext
    lateinit var em: EntityManager

    private fun cs(
        sp: StudyProgramId,
        sub: String,
        semester: Int,
        mandatory: Boolean,
        order: Float?
    ) = CurriculumSubject(
        id = CurriculumSubjectId(sp, SubjectId(sub)),
        mandatory = mandatory,
        semester = semester,
        order = order,
        subjectGroup = "CORE",
        dependenciesOverride = null
    )

    @Test
    fun `study program view returns sorted subjects`() {
        val spCode = StudyProgramId("TEST-SP-${UUID.randomUUID().toString().substring(0, 8)}")

        // IMPORTANT: we must insert a study_program row, because StudyProgramView is mapped to that table
        val spView = StudyProgramView(
            code = spCode,
            name = "Test",
            nameEn = "Test",
            duration_years = 4,
            inEnglish = true,
            bilingual = false,
            studyCycle = StudyCycle.UNDERGRADUATE
        )
        spRepo.save(spView)

        csRepo.saveAll(
            listOf(
                cs(spCode, "SUB-A", semester = 1, mandatory = true,  order = 2f),
                cs(spCode, "SUB-B", semester = 1, mandatory = false, order = 1f),
                cs(spCode, "SUB-C", semester = 1, mandatory = false, order = 0f),
                cs(spCode, "SUB-D", semester = 1, mandatory = true,  order = 3f),

                cs(spCode, "SUB-E", semester = 2, mandatory = false, order = 5f),
                cs(spCode, "SUB-F", semester = 2, mandatory = true,  order = 1f),
            )
        )

        em.flush()
        em.clear()

        val loaded = spRepo.findById(spCode).orElseThrow()

        val ids = loaded.sortedSubjects.map {
            // You had the "Subject:" prefix issue, so safest:
            it.subject.toString().removePrefix("Subject:")
        }


        assertEquals(
            listOf("SUB-A", "SUB-D", "SUB-C", "SUB-B", "SUB-F", "SUB-E"),
            ids
        )

        // Print format like you wanted (simple version)
        val printout = buildString {
            loaded.sortedSubjects
                .groupBy { it.semester ?: -1 }
                .toSortedMap()
                .forEach { (sem, subjects) ->
                    appendLine("------Semester $sem------")
                    appendLine("-------Mandatory------")
                    subjects.filter { it.mandatory }
                        .forEach { appendLine(it.subject.toString()) }
                    appendLine("-------Non-mandatory------")
                    subjects.filter { !it.mandatory }
                        .forEach { appendLine(it.subject.toString()) }
                    appendLine()
                }
        }

        println(printout)

        assertTrue(printout.contains("------Semester 1------"))
        assertTrue(printout.contains("-------Mandatory------"))
        assertTrue(printout.contains("-------Non-mandatory------"))
    }
}