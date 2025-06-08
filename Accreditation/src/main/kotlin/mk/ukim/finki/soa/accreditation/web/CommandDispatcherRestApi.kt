package mk.ukim.finki.soa.accreditation.web

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import mk.ukim.finki.soa.accreditation.model.*
import mk.ukim.finki.soa.accreditation.service.StudyProgramModificationService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


//http://localhost:8080/swagger-ui/index.html
@RestController
@RequestMapping("/studyProgram/submitCommand")
@Tag(
        name = "Study Program Command API",
        description = "Handles commands for creating and modifying study programs."
)
class CommandDispatcherRestApi (
    val studyProgramModificationService: StudyProgramModificationService
) {


    @Operation(
            summary = "Submit a command to create a new Study Program",
            description = "Create a new Study program by submitting a command."
    )
    @PostMapping("/CreateStudyProgramCommand")
    fun createStudProgram(
            @RequestBody commandDto: CreateStudyProgramCommand
    ) : ResponseEntity<Any> {
        //Ne e podobro da se dodade Application Service Layer za preveduvanja na dto vo domain objekti ??
        //A vo ovoj slucaj poradi sto isti bi bile Dto komandata so soodvetnata samo ja prativ soodvetna komanda.
        return ResponseEntity.ok(
                studyProgramModificationService.createStudyProgram(commandDto)
        )
    }

    @Operation(
            summary = "Update the name of a study program",
            description = "Changes the name attribute for the given study program. The english name should be send for updated as well"
    )
    @PostMapping("/UpdateStudyProgramNameCommand")
    fun updateName(@RequestBody command: UpdateStudyProgramNameCommand): ResponseEntity<Any> {
        return ResponseEntity.ok(studyProgramModificationService.updateStudyProgramName(command))
    }

    @Operation(
            summary = "Update the duration (in years) of a study program",
            description = "Changes the duration in years for the given study program."
    )
    @PostMapping("/UpdateStudyProgramDurationYearsCommand")
    fun updateDurationYears(@RequestBody command: UpdateStudyProgramDurationYearsCommand): ResponseEntity<Any> {
        return ResponseEntity.ok(studyProgramModificationService.updateStudyProgramDurationYears(command))
    }

    @Operation(
            summary = "Update the order of a study program",
            description = "Changes the display or ranking order of the study program."
    )
    @PostMapping("/UpdateStudyProgramOrderCommand")
    fun updateOrder(@RequestBody command: UpdateStudyProgramOrderCommand): ResponseEntity<Any> {
        return ResponseEntity.ok(studyProgramModificationService.updateStudyProgramOrder(command))
    }

    @Operation(
            summary = "Update general information for a study program",
            description = "Changes the general information or description of the study program."
    )
    @PostMapping("/UpdateStudyProgramGeneralInformationCommand")
    fun updateGeneralInformation(@RequestBody command: UpdateStudyProgramGeneralInformationCommand): ResponseEntity<Any> {
        return ResponseEntity.ok(studyProgramModificationService.updateStudyProgramGeneralInformation(command))
    }

    @Operation(
            summary = "Update the graduation title of a study program",
            description = "Changes the title students receive upon graduating from the study program. The english version should be sent as well"
    )
    @PostMapping("/UpdateStudyProgramGraduationTitleCommand")
    fun updateGraduationTitle(@RequestBody command: UpdateStudyProgramGraduationTitleCommand): ResponseEntity<Any> {
        return ResponseEntity.ok(studyProgramModificationService.updateStudyProgramGraduationTitle(command))
    }


    @Operation(
            summary = "Update subject restrictions for a study program",
            description = "Changes any subject-level restrictions for the study program."
    )
    @PostMapping("/UpdateStudyProgramSubjectRestrictionsCommand")
    fun updateSubjectRestrictions(@RequestBody command: UpdateStudyProgramSubjectRestrictionsCommand): ResponseEntity<Any> {
        return ResponseEntity.ok(studyProgramModificationService.updateStudyProgramSubjectRestrictions(command))
    }

    @Operation(
            summary = "Toggle English availability of a study program",
            description = "Updates whether the study program is available in English."
    )
    @PostMapping("/UpdateStudyProgramInEnglishCommand")
    fun updateInEnglish(@RequestBody command: UpdateStudyProgramEnglishAvailabilityCommand): ResponseEntity<Any> {
        return ResponseEntity.ok(studyProgramModificationService.updateStudyProgramInEnglish(command))
    }

    @Operation(
            summary = "Update the study cycle of a program",
            description = "Changes the study cycle (UNDERGRADUATE, MASTER, PHD) of the program."
    )
    @PostMapping("/UpdateStudyProgramCycleCommand")
    fun updateStudyCycle(@RequestBody command: UpdateStudyProgramStudyCycleCommand): ResponseEntity<Any> {
        return ResponseEntity.ok(studyProgramModificationService.updateStudyProgramStudyCycle(command))
    }

    @Operation(
            summary = "Update bilingual availability",
            description = "Changes whether the study program is bilingual or not."
    )
    @PostMapping("/UpdateStudyProgramBilingualCommand")
    fun updateBilingual(@RequestBody command: UpdateStudyProgramBilingualCommand): ResponseEntity<Any> {
        return ResponseEntity.ok(studyProgramModificationService.updateStudyProgramBilingual(command))
    }

    @Operation(
            summary = "Update the coordinator professor",
            description = "Changes the professor ID responsible for coordinating the study program."
    )
    @PostMapping("/UpdateStudyProgramCoordinatorCommand")
    fun updateCoordinator(@RequestBody command: UpdateStudyProgramCoordinatorCommand): ResponseEntity<Any> {
        return ResponseEntity.ok(studyProgramModificationService.updateStudyProgramCoordinator(command))
    }

    //----------------------------StudyProgramSubject----------------------------------

    @Operation(
        summary = "Create a new Study Program Subject",
        description = "Submit a command to create a new Study Program Subject."
    )
    @PostMapping("/CreateStudyProgramSubjectCommand")
    fun createStudyProgramSubject(@RequestBody command: CreateStudyProgramSubjectCommand): ResponseEntity<Any> {
        return ResponseEntity.ok(studyProgramModificationService.createStudyProgramSubject(command))
    }

    @Operation(
        summary = "Update the mandatory flag of a study program subject",
        description = "Changes the mandatory flag for the given study program subject."
    )
    @PostMapping("/UpdateStudyProgramSubjectMandatoryCommand")
    fun updateMandatory(@RequestBody command: UpdateStudyProgramSubjectMandatoryCommand): ResponseEntity<Any> {
        return ResponseEntity.ok(studyProgramModificationService.updateMandatory(command))
    }

    @Operation(
        summary = "Update the semester of a study program subject",
        description = "Changes the semester for the given study program subject."
    )
    @PostMapping("/UpdateStudyProgramSubjectSemesterCommand")
    fun updateSemester(@RequestBody command: UpdateStudyProgramSubjectSemesterCommand): ResponseEntity<Any> {
        return ResponseEntity.ok(studyProgramModificationService.updateSemester(command))
    }

    @Operation(
        summary = "Update the order of a study program subject",
        description = "Changes the display order for the given study program subject."
    )
    @PostMapping("/UpdateStudyProgramSubjectOrderCommand")
    fun updateOrder(@RequestBody command: UpdateStudyProgramSubjectOrderCommand): ResponseEntity<Any> {
        return ResponseEntity.ok(studyProgramModificationService.updateOrder(command))
    }

    @Operation(
        summary = "Update the subject group of a study program subject",
        description = "Changes the subject group for the given study program subject."
    )
    @PostMapping("/UpdateStudyProgramSubjectSubjectGroupCommand")
    fun updateSubjectGroup(@RequestBody command: UpdateStudyProgramSubjectSubjectGroupCommand): ResponseEntity<Any> {
        return ResponseEntity.ok(studyProgramModificationService.updateSubjectGroup(command))
    }

    @Operation(
        summary = "Update the dependencies override for a study program subject",
        description = "Changes the dependency override string for the given study program subject."
    )
    @PostMapping("/UpdateStudyProgramSubjectDependenciesOverrideCommand")
    fun updateDependenciesOverride(@RequestBody command: UpdateStudyProgramSubjectDependenciesOverrideCommand): ResponseEntity<Any> {
        return ResponseEntity.ok(studyProgramModificationService.updateDependenciesOverride(command))
    }

}