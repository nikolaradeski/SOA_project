package mk.ukim.finki.soa.accreditation.service

import mk.ukim.finki.soa.accreditation.model.*
import java.util.concurrent.CompletableFuture

interface StudyProgramModificationService {
    fun createStudyProgram(command: CreateStudyProgramCommand): CompletableFuture<StudyProgramId>
    fun updateStudyProgramName(command: UpdateStudyProgramNameCommand): CompletableFuture<Any>
    fun updateStudyProgramDurationYears(command: UpdateStudyProgramDurationYearsCommand): CompletableFuture<Any>
    fun updateStudyProgramOrder(command: UpdateStudyProgramOrderCommand): CompletableFuture<Any>
    fun updateStudyProgramGeneralInformation(command: UpdateStudyProgramGeneralInformationCommand): CompletableFuture<Any>
    fun updateStudyProgramGraduationTitle(command: UpdateStudyProgramGraduationTitleCommand): CompletableFuture<Any>
    fun updateStudyProgramSubjectRestrictions(command: UpdateStudyProgramSubjectRestrictionsCommand): CompletableFuture<Any>
    fun updateStudyProgramInEnglish(command: UpdateStudyProgramEnglishAvailabilityCommand): CompletableFuture<Any>
    fun updateStudyProgramStudyCycle(command: UpdateStudyProgramStudyCycleCommand): CompletableFuture<Any>
    fun updateStudyProgramBilingual(command: UpdateStudyProgramBilingualCommand): CompletableFuture<Any>
    fun updateStudyProgramCoordinator(command: UpdateStudyProgramCoordinatorCommand): CompletableFuture<Any>
    /* ---------StudyProgramSubject-------*/
    fun createStudyProgramSubject(command: CreateStudyProgramSubjectCommand): CompletableFuture<StudyProgramSubjectId>
    fun updateMandatory(command: UpdateStudyProgramSubjectMandatoryCommand): CompletableFuture<Any>
    fun updateSemester(command: UpdateStudyProgramSubjectSemesterCommand): CompletableFuture<Any>
    fun updateOrder(command: UpdateStudyProgramSubjectOrderCommand): CompletableFuture<Any>
    fun updateSubjectGroup(command: UpdateStudyProgramSubjectSubjectGroupCommand): CompletableFuture<Any>
    fun updateDependenciesOverride(command: UpdateStudyProgramSubjectDependenciesOverrideCommand): CompletableFuture<Any>
}

interface StudyProgramViewReadService{
    fun findById(studyProgramId: StudyProgramId): StudyProgramView
    fun findAll(): List<StudyProgramView>
}