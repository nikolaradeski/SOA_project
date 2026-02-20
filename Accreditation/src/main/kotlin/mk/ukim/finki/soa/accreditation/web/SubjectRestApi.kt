package mk.ukim.finki.soa.accreditation.web

import io.swagger.v3.oas.annotations.tags.Tag
import mk.ukim.finki.soa.accreditation.repository.SubjectViewJpaRepository
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/subjects")
@Tag(name = "Subject Read API")
class SubjectRestApi(
    private val repo: SubjectViewJpaRepository
) {
    @GetMapping("/all")
    fun all() = repo.findAll()
}
