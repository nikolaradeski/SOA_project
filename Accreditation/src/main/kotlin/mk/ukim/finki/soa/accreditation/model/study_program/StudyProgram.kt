package mk.ukim.finki.soa.accreditation.model.study_program

import jakarta.persistence.*
import mk.ukim.finki.soa.accreditation.model.CreateStudyProgramCommand
import mk.ukim.finki.soa.accreditation.model.StudyProgramCreatedEvent
import mk.ukim.finki.soa.accreditation.model.StudyProgramId
import org.axonframework.commandhandling.CommandHandler
import org.axonframework.modelling.command.AggregateIdentifier
import org.axonframework.modelling.command.AggregateLifecycle
import org.axonframework.spring.stereotype.Aggregate


@Entity
@Aggregate(repository = "axonStudyProgramRepository")
public class StudyProgram {

    @AggregateIdentifier
    @EmbeddedId
    @AttributeOverride(name = "id", column = Column(name = "id"))
    private lateinit var id: StudyProgramId
//
//    @Embedded
//    @AttributeOverride(name = "program_code", column = Column(name = "study_program_code"))
//    private lateinit var studyProgramId: StudyProgramId

    private var nameEn: String? = null

    private var order: Float? = null

    private var durationYears: Int? = null

    private var durationSemesters: Int? = null

    @Column(length = 8000)
    private var generalInformation: String? = null

    @Column(length = 3000)
    private var graduationTitle: String? = null

    @Column(length = 3000)
    private var graduationTitleEn: String? = null

    @Column(length = 8000)
    private var subjectRestrictions: String? = null

    private var bilingual: Boolean = false

    @CommandHandler
    constructor(command: CreateStudyProgramCommand) {
        val event = StudyProgramCreatedEvent(
            studyProgramId = StudyProgramId()
        )
        this.on(event)
        AggregateLifecycle.apply(event)
    }

    fun on(event: StudyProgramCreatedEvent) {
        this.id = event.studyProgramId
    }
}