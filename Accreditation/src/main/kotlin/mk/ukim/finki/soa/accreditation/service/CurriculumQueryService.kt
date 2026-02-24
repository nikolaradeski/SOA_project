package mk.ukim.finki.soa.accreditation.service.read

import mk.ukim.finki.soa.accreditation.model.StudyProgramId
import mk.ukim.finki.soa.accreditation.model.study_program.CurriculumSubject
import mk.ukim.finki.soa.accreditation.repository.CurriculumSubjectJpaRepository
import org.springframework.stereotype.Service

@Service
class CurriculumQueryService(
    private val curriculumRepo: CurriculumSubjectJpaRepository
) {

    fun sortedForStudyProgram(studyProgramId: StudyProgramId): List<CurriculumSubject> {
        val rows = curriculumRepo.findAllByIdStudyProgram(studyProgramId)

        return rows.sortedWith(
            compareBy<CurriculumSubject> { it.semester ?: Int.MAX_VALUE }
                .thenBy { if (it.mandatory) 0 else 100 }
                .thenBy { it.order ?: Float.MAX_VALUE }
                .thenBy { it.subject.value }
        )
    }

    fun prettyPrintBySemester(studyProgramId: StudyProgramId): String {
        val rows = sortedForStudyProgram(studyProgramId)
            .filter { it.semester != null }
            .groupBy { it.semester!! }
            .toSortedMap()

        val sb = StringBuilder()
        for ((sem, list) in rows) {
            sb.appendLine("------Semester $sem------")
            sb.appendLine("-------Mandatory------")
            list.filter { it.mandatory }.forEach {
                sb.appendLine("${it.subject.value} (order=${it.order ?: "-"})")
            }
            sb.appendLine("-------Non-mandatory------")
            list.filter { !it.mandatory }.forEach {
                sb.appendLine("${it.subject.value} (order=${it.order ?: "-"})")
            }
            sb.appendLine()
        }
        return sb.toString()
    }
}