package mk.ukim.finki.soa.accreditation.web

import io.swagger.v3.oas.annotations.Operation
import mk.ukim.finki.soa.accreditation.model.CreateStudyProgramCommand
import mk.ukim.finki.soa.accreditation.model.CreateStudyProgramCommandDTO
import mk.ukim.finki.soa.accreditation.service.StudyProgramModificationService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


//http://localhost:8080/swagger-ui/index.html
@RestController
@RequestMapping("/submitCommand")
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
}