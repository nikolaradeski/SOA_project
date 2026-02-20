package mk.ukim.finki.soa.accreditation

import mk.ukim.finki.soa.accreditation.model.*
import mk.ukim.finki.soa.accreditation.model.generalEnums.StudyCycle
import mk.ukim.finki.soa.accreditation.model.study_program.StudyProgram
import org.axonframework.test.aggregate.AggregateTestFixture
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class StudyProgramAggregateTest {

    private lateinit var fixture: AggregateTestFixture<StudyProgram>

    @BeforeEach
    fun setup() {
        fixture = AggregateTestFixture(StudyProgram::class.java)
    }

    @Test
    fun `create study program emits StudyProgramCreatedEvent`() {
        val cmd = CreateStudyProgramCommand(
            code = "SP-001",
            name = "Software Engineering",
            nameEn = "Software Engineering",
            order = 1,
            durationYears = 4,
            generalInformation = "Demo",
            graduationTitle = "Dipl. Ing.",
            graduationTitleEn = "BSc",
            subjectRestrictions = "None",
            isItAvailableOnEnglish = true,
            bilingual = false,
            studyCycle = StudyCycle.UNDERGRADUATE,
            accreditation = AccreditationId("ACC-001"),
            coordinator = ProfessorId("PROF-001")
        )

        // IMPORTANT:
        // This assumes your StudyProgramCreatedEvent uses StudyProgramId(command.code).
        val expectedEvent = StudyProgramCreatedEvent(
            studyProgramId = StudyProgramId("SP-001"),
            name = "Software Engineering",
            nameEn = "Software Engineering",
            order = 1,
            durationYears = 4,
            generalInformation = cmd.generalInformation,
            graduationTitle = cmd.graduationTitle,
            graduationTitleEn = cmd.graduationTitleEn,
            inEnglish = cmd.isItAvailableOnEnglish,
            bilingual = cmd.bilingual,
            accreditation = cmd.accreditation,
            coordinator = cmd.coordinator,
            studyCycle = cmd.studyCycle,
            subjectRestrictions = cmd.subjectRestrictions
        )

        fixture.givenNoPriorActivity()
            .`when`(cmd)
            .expectSuccessfulHandlerExecution()
            .expectEvents(expectedEvent)
    }

    @Test
    fun `update name emits StudyProgramNameUpdatedEvent`() {
        val created = StudyProgramCreatedEvent(
            studyProgramId = StudyProgramId("SP-001"),
            name = "Old name",
            nameEn = "Old name",
            order = 1,
            durationYears = 4,
            generalInformation = "Demo",
            graduationTitle = "Dipl. Ing.",
            graduationTitleEn = "BSc",
            inEnglish = true,
            bilingual = false,
            accreditation = AccreditationId("ACC-001"),
            coordinator = ProfessorId("PROF-001"),
            studyCycle = StudyCycle.UNDERGRADUATE,
            subjectRestrictions = "None"
        )

        val cmd = UpdateStudyProgramNameCommand(
            studyProgramId = StudyProgramId("SP-001"),
            name = "New name"
        )

        val expected = StudyProgramNameUpdatedEvent(
            studyProgramId = StudyProgramId("SP-001"),
            name = "New name"
        )

        fixture.given(created)
            .`when`(cmd)
            .expectSuccessfulHandlerExecution()
            .expectEvents(expected)
    }

    @Test
    fun `update duration years emits StudyProgramDurationYearsUpdatedEvent`() {
        val created = StudyProgramCreatedEvent(
            studyProgramId = StudyProgramId("SP-001"),
            name = "Old name",
            nameEn = "Old name",
            order = 1,
            durationYears = 4,
            generalInformation = "Demo",
            graduationTitle = "Dipl. Ing.",
            graduationTitleEn = "BSc",
            inEnglish = true,
            bilingual = false,
            accreditation = AccreditationId("ACC-001"),
            coordinator = ProfessorId("PROF-001"),
            studyCycle = StudyCycle.UNDERGRADUATE,
            subjectRestrictions = "None"
        )

        val cmd = UpdateStudyProgramDurationYearsCommand(
            studyProgramId = StudyProgramId("SP-001"),
            durationYears = 3
        )

        val expected = StudyProgramDurationYearsUpdatedEvent(
            studyProgramId = StudyProgramId("SP-001"),
            durationYears = 3
        )

        fixture.given(created)
            .`when`(cmd)
            .expectSuccessfulHandlerExecution()
            .expectEvents(expected)
    }
}