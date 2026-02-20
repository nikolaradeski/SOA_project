package mk.ukim.finki.soa.accreditation

import mk.ukim.finki.soa.accreditation.model.*
import mk.ukim.finki.soa.accreditation.model.generalEnums.StudyCycle
import mk.ukim.finki.soa.accreditation.repository.StudyProgramViewJpaRepository
import org.axonframework.commandhandling.gateway.CommandGateway
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class StudyProgramProjectionIT {

    @Autowired lateinit var commandGateway: CommandGateway
    @Autowired lateinit var viewRepo: StudyProgramViewJpaRepository

    @Test
    fun `creating study program persists StudyProgramView`() {
        val id = StudyProgramId("SP-INT-1")

        val cmd = CreateStudyProgramCommand(
            code = id.value,
            name = "SE",
            nameEn = "SE",
            order = 1,
            durationYears = 4,
            generalInformation = "Demo",
            graduationTitle = "Title",
            graduationTitleEn = "TitleEn",
            subjectRestrictions = "None",
            isItAvailableOnEnglish = true,
            bilingual = false,
            studyCycle = StudyCycle.UNDERGRADUATE,
            accreditation = AccreditationId("ACC-001"),
            coordinator = ProfessorId("PROF-001")
        )

        // wait for command handling to finish
        commandGateway.sendAndWait<Any>(cmd)

        val view = viewRepo.findById(id).orElseThrow()
        assertEquals("SE", view.name)
    }
}
