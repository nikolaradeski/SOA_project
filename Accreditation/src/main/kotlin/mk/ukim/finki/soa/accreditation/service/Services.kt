package mk.ukim.finki.soa.accreditation.service

import mk.ukim.finki.soa.accreditation.model.*
import java.util.concurrent.CompletableFuture

interface StudyProgramModificationService {
    fun createStudyProgram(command: CreateStudyProgramCommand): CompletableFuture<StudyProgramId>
    fun updateName(command: UpdateStudyProgramNameCommand): CompletableFuture<Any>
    fun updateDurationYears(command: UpdateStudyProgramDurationYearsCommand): CompletableFuture<Any>
    fun updateOrder(command: UpdateStudyProgramOrderCommand): CompletableFuture<Any>
    fun updateGeneralInformation(command: UpdateStudyProgramGeneralInformationCommand): CompletableFuture<Any>
    fun updateGraduationTitle(command: UpdateStudyProgramGraduationTitleCommand): CompletableFuture<Any>
    fun updateSubjectRestrictions(command: UpdateStudyProgramSubjectRestrictionsCommand): CompletableFuture<Any>
    fun updateInEnglish(command: UpdateStudyProgramEnglishAvailabilityCommand): CompletableFuture<Any>
    fun updateStudyCycle(command: UpdateStudyProgramStudyCycleCommand): CompletableFuture<Any>
    fun updateBilingual(command: UpdateStudyProgramBilingualCommand): CompletableFuture<Any>
    fun updateCoordinator(command: UpdateStudyProgramCoordinatorCommand): CompletableFuture<Any>
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