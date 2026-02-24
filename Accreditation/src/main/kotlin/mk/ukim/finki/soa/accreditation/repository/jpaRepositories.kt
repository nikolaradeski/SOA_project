package mk.ukim.finki.soa.accreditation.repository

import mk.ukim.finki.soa.accreditation.model.*
import mk.ukim.finki.soa.accreditation.model.study_program.CurriculumSubject
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface StudyProgramViewJpaRepository: JpaRepository<StudyProgramView, StudyProgramId> {
}

@Repository
interface SubjectViewJpaRepository : JpaRepository<SubjectView, SubjectId>

@Repository
interface CurriculumSubjectJpaRepository : JpaRepository<CurriculumSubject, CurriculumSubjectId> {

    fun findAllByIdStudyProgramAndMandatoryTrueAndSemester(
        studyProgram: StudyProgramId,
        semester: Int
    ): List<CurriculumSubject>

    fun findAllByIdStudyProgramAndMandatoryFalseAndSemester(
        studyProgram: StudyProgramId,
        semester: Int
    ): List<CurriculumSubject>

    fun findAllByIdStudyProgramAndMandatoryTrue(
        studyProgram: StudyProgramId
    ): List<CurriculumSubject>
}