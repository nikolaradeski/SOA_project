package mk.ukim.finki.soa.accreditation.web

import mk.ukim.finki.soa.accreditation.model.StudyProgramView
import mk.ukim.finki.soa.accreditation.service.StudyProgramViewReadService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/study_programs")
class StudyProgramRestApi (val studyProgramViewReadService: StudyProgramViewReadService){
    @GetMapping("/all")
    fun findAll(): List<StudyProgramView>{
        return studyProgramViewReadService.findAll()
    }
}