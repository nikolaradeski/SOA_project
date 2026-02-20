package mk.ukim.finki.soa.accreditation.web

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import mk.ukim.finki.soa.accreditation.model.*
import mk.ukim.finki.soa.accreditation.service.SubjectModificationService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/subject/submitCommand")
@Tag(
    name = "Subject Command API",
    description = "Handles commands for creating and modifying subjects."
)
class SubjectCommandDispatcherRestApi(
    private val subjectModificationService: SubjectModificationService
) {

    @Operation(
        summary = "Submit a command to create a new Subject",
        description = "Create a new subject by submitting a command."
    )
    @PostMapping("/CreateSubjectCommand")
    fun createSubject(@RequestBody command: CreateSubjectCommand): ResponseEntity<Any> =
        ResponseEntity.ok(subjectModificationService.createSubject(command))

    @Operation(summary = "Update subject name", description = "Updates name and english name.")
    @PostMapping("/UpdateSubjectNameCommand")
    fun updateName(@RequestBody command: UpdateSubjectNameCommand): ResponseEntity<Any> =
        ResponseEntity.ok(subjectModificationService.updateSubjectName(command))

    @Operation(summary = "Update subject abbreviation", description = "Updates abbreviation.")
    @PostMapping("/UpdateSubjectAbbreviationCommand")
    fun updateAbbreviation(@RequestBody command: UpdateSubjectAbbreviationCommand): ResponseEntity<Any> =
        ResponseEntity.ok(subjectModificationService.updateSubjectAbbreviation(command))

    @Operation(summary = "Update subject semester", description = "Updates semester.")
    @PostMapping("/UpdateSubjectSemesterCommand")
    fun updateSemester(@RequestBody command: UpdateSubjectSemesterCommand): ResponseEntity<Any> =
        ResponseEntity.ok(subjectModificationService.updateSubjectSemester(command))

    @Operation(summary = "Update weekly lectures classes", description = "Updates weekly lectures classes.")
    @PostMapping("/UpdateSubjectWeeklyLecturesClassesCommand")
    fun updateWeeklyLectures(@RequestBody command: UpdateSubjectWeeklyLecturesClassesCommand): ResponseEntity<Any> =
        ResponseEntity.ok(subjectModificationService.updateWeeklyLectures(command))

    @Operation(summary = "Update weekly auditorium classes", description = "Updates weekly auditorium classes.")
    @PostMapping("/UpdateSubjectWeeklyAuditoriumClassesCommand")
    fun updateWeeklyAuditorium(@RequestBody command: UpdateSubjectWeeklyAuditoriumClassesCommand): ResponseEntity<Any> =
        ResponseEntity.ok(subjectModificationService.updateWeeklyAuditorium(command))

    @Operation(summary = "Update weekly lab classes", description = "Updates weekly lab classes.")
    @PostMapping("/UpdateSubjectWeeklyLabClassesCommand")
    fun updateWeeklyLab(@RequestBody command: UpdateSubjectWeeklyLabClassesCommand): ResponseEntity<Any> =
        ResponseEntity.ok(subjectModificationService.updateWeeklyLab(command))

    @Operation(summary = "Update placeholder flag", description = "Updates placeholder.")
    @PostMapping("/UpdateSubjectPlaceholderCommand")
    fun updatePlaceholder(@RequestBody command: UpdateSubjectPlaceholderCommand): ResponseEntity<Any> =
        ResponseEntity.ok(subjectModificationService.updatePlaceholder(command))

    @Operation(summary = "Update default semester", description = "Updates default semester.")
    @PostMapping("/UpdateSubjectDefaultSemesterCommand")
    fun updateDefaultSemester(@RequestBody command: UpdateSubjectDefaultSemesterCommand): ResponseEntity<Any> =
        ResponseEntity.ok(subjectModificationService.updateDefaultSemester(command))

    @Operation(summary = "Update credits", description = "Updates credits.")
    @PostMapping("/UpdateSubjectCreditsCommand")
    fun updateCredits(@RequestBody command: UpdateSubjectCreditsCommand): ResponseEntity<Any> =
        ResponseEntity.ok(subjectModificationService.updateCredits(command))

    @Operation(summary = "Update study cycle", description = "Updates study cycle.")
    @PostMapping("/UpdateSubjectStudyCycleCommand")
    fun updateStudyCycle(@RequestBody command: UpdateSubjectStudyCycleCommand): ResponseEntity<Any> =
        ResponseEntity.ok(subjectModificationService.updateStudyCycle(command))

    @Operation(summary = "Update language", description = "Updates language.")
    @PostMapping("/UpdateSubjectLanguageCommand")
    fun updateLanguage(@RequestBody command: UpdateSubjectLanguageCommand): ResponseEntity<Any> =
        ResponseEntity.ok(subjectModificationService.updateLanguage(command))

    @Operation(summary = "Update learning methods", description = "Updates learning methods.")
    @PostMapping("/UpdateSubjectLearningMethodsCommand")
    fun updateLearningMethods(@RequestBody command: UpdateSubjectLearningMethodsCommand): ResponseEntity<Any> =
        ResponseEntity.ok(subjectModificationService.updateLearningMethods(command))

    @Operation(summary = "Update goals description", description = "Updates goals description.")
    @PostMapping("/UpdateSubjectGoalsDescriptionCommand")
    fun updateGoalsDescription(@RequestBody command: UpdateSubjectGoalsDescriptionCommand): ResponseEntity<Any> =
        ResponseEntity.ok(subjectModificationService.updateGoalsDescription(command))

    @Operation(summary = "Update content", description = "Updates content.")
    @PostMapping("/UpdateSubjectContentCommand")
    fun updateContent(@RequestBody command: UpdateSubjectContentCommand): ResponseEntity<Any> =
        ResponseEntity.ok(subjectModificationService.updateContent(command))

    @Operation(summary = "Update goals description EN", description = "Updates goals description EN.")
    @PostMapping("/UpdateSubjectGoalsDescriptionEnCommand")
    fun updateGoalsDescriptionEn(@RequestBody command: UpdateSubjectGoalsDescriptionEnCommand): ResponseEntity<Any> =
        ResponseEntity.ok(subjectModificationService.updateGoalsDescriptionEn(command))

    @Operation(summary = "Update content EN", description = "Updates content EN.")
    @PostMapping("/UpdateSubjectContentEnCommand")
    fun updateContentEn(@RequestBody command: UpdateSubjectContentEnCommand): ResponseEntity<Any> =
        ResponseEntity.ok(subjectModificationService.updateContentEn(command))

    @Operation(summary = "Update quality control", description = "Updates quality control.")
    @PostMapping("/UpdateSubjectQualityControlCommand")
    fun updateQualityControl(@RequestBody command: UpdateSubjectQualityControlCommand): ResponseEntity<Any> =
        ResponseEntity.ok(subjectModificationService.updateQualityControl(command))

    @Operation(summary = "Update accreditation", description = "Updates accreditation id.")
    @PostMapping("/UpdateSubjectAccreditationCommand")
    fun updateAccreditation(@RequestBody command: UpdateSubjectAccreditationCommand): ResponseEntity<Any> =
        ResponseEntity.ok(subjectModificationService.updateAccreditation(command))

    @Operation(summary = "Update obligation duration", description = "Updates obligation duration.")
    @PostMapping("/UpdateSubjectObligationDurationCommand")
    fun updateObligationDuration(@RequestBody command: UpdateSubjectObligationDurationCommand): ResponseEntity<Any> =
        ResponseEntity.ok(subjectModificationService.updateObligationDuration(command))

    @Operation(summary = "Update dependencies", description = "Updates dependencies.")
    @PostMapping("/UpdateSubjectDependenciesCommand")
    fun updateDependencies(@RequestBody command: UpdateSubjectDependenciesCommand): ResponseEntity<Any> =
        ResponseEntity.ok(subjectModificationService.updateDependencies(command))

    @Operation(summary = "Update grading", description = "Updates grading.")
    @PostMapping("/UpdateSubjectGradingCommand")
    fun updateGrading(@RequestBody command: UpdateSubjectGradingCommand): ResponseEntity<Any> =
        ResponseEntity.ok(subjectModificationService.updateGrading(command))

    @Operation(summary = "Update bibliography", description = "Updates bibliography.")
    @PostMapping("/UpdateSubjectBibliographyCommand")
    fun updateBibliography(@RequestBody command: UpdateSubjectBibliographyCommand): ResponseEntity<Any> =
        ResponseEntity.ok(subjectModificationService.updateBibliography(command))

    @Operation(summary = "Update notes", description = "Updates notes list.")
    @PostMapping("/UpdateSubjectNotesCommand")
    fun updateNotes(@RequestBody command: UpdateSubjectNotesCommand): ResponseEntity<Any> =
        ResponseEntity.ok(subjectModificationService.updateNotes(command))
}
