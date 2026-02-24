package mk.ukim.finki.soa.accreditation.web

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import mk.ukim.finki.soa.accreditation.model.*
import mk.ukim.finki.soa.accreditation.service.StudyProgramModificationService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/curriculumSubject/submitCommand")
@Tag(
    name = "Curriculum Subject Command API",
    description = "Handles commands for adding/updating curriculum subjects inside a study program."
)
class CurriculumSubjectCommandDispatcherRestApi(
    private val studyProgramModificationService: StudyProgramModificationService
) {

    @Operation(
        summary = "Add a subject to a study program curriculum",
        description = "Creates a CurriculumSubject relation (studyProgramId + subjectId) with mandatory/semester/order/etc."
    )
    @PostMapping("/CreateStudyProgramSubjectCommand")
    fun create(@RequestBody cmd: CreateStudyProgramSubjectCommand): ResponseEntity<Any> {
        return ResponseEntity.ok(studyProgramModificationService.createStudyProgramSubject(cmd))
    }

    @Operation(
        summary = "Update mandatory flag for a curriculum subject",
        description = "Updates whether the subject is mandatory in a given study program."
    )
    @PostMapping("/UpdateStudyProgramSubjectMandatoryCommand")
    fun updateMandatory(@RequestBody cmd: UpdateStudyProgramSubjectMandatoryCommand): ResponseEntity<Any> {
        return ResponseEntity.ok(studyProgramModificationService.updateMandatory(cmd))
    }

    @Operation(
        summary = "Update semester for a curriculum subject",
        description = "Updates the semester placement for the subject in a given study program."
    )
    @PostMapping("/UpdateStudyProgramSubjectSemesterCommand")
    fun updateSemester(@RequestBody cmd: UpdateStudyProgramSubjectSemesterCommand): ResponseEntity<Any> {
        return ResponseEntity.ok(studyProgramModificationService.updateSemester(cmd))
    }

    @Operation(
        summary = "Update order for a curriculum subject",
        description = "Updates the ordering number for the subject in a given study program and semester."
    )
    @PostMapping("/UpdateStudyProgramSubjectOrderCommand")
    fun updateOrder(@RequestBody cmd: UpdateStudyProgramSubjectOrderCommand): ResponseEntity<Any> {
        return ResponseEntity.ok(studyProgramModificationService.updateOrder(cmd))
    }

    @Operation(
        summary = "Update subject group for a curriculum subject",
        description = "Updates the subjectGroup field for the subject in a given study program."
    )
    @PostMapping("/UpdateStudyProgramSubjectSubjectGroupCommand")
    fun updateSubjectGroup(@RequestBody cmd: UpdateStudyProgramSubjectSubjectGroupCommand): ResponseEntity<Any> {
        return ResponseEntity.ok(studyProgramModificationService.updateSubjectGroup(cmd))
    }

    @Operation(
        summary = "Update dependencies override for a curriculum subject",
        description = "Updates the dependenciesOverride string for a subject inside a study program."
    )
    @PostMapping("/UpdateStudyProgramSubjectDependenciesOverrideCommand")
    fun updateDependenciesOverride(@RequestBody cmd: UpdateStudyProgramSubjectDependenciesOverrideCommand): ResponseEntity<Any> {
        return ResponseEntity.ok(studyProgramModificationService.updateDependenciesOverride(cmd))
    }
}