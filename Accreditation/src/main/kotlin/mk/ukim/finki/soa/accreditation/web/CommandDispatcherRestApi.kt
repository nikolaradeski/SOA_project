package mk.ukim.finki.soa.accreditation.web

import mk.ukim.finki.soa.accreditation.model.CreateStudyProgramCommand
import mk.ukim.finki.soa.accreditation.model.CreateStudyProgramCommandDTO
import mk.ukim.finki.soa.accreditation.service.StudyProgramModificationService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/submitCommand")
class CommandDispatcherRestApi (
    val studyProgramModificationService: StudyProgramModificationService
) {
    @PostMapping("/CreateStudyProgramCommand")
    fun createStudProgram(
            @RequestBody commandDto: CreateStudyProgramCommandDTO
    ) : ResponseEntity<Any> {
        return ResponseEntity.ok(
                studyProgramModificationService.createStudyProgram(
                        CreateStudyProgramCommand(
                                name = commandDto.name
                        )
                )
        )
    }
}