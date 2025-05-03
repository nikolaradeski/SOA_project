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
    @AttributeOverride(name = "value", column = Column(name = "id"))
    private lateinit var id: StudyProgramId

    //private lateinit var code: Code

    private lateinit var name: String

    private var nameEn: String? = null

    //tuka mozhe private valobj Order
    //
    //private var order: Float? = null

    //tuka mozhe private valobj Year
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

    private var onEnglish: Boolean = false

    //@Enumerated(EnumType.STRING)
    //private var studyCycle: StudyCycle? = null

    //@ManyToOne
    //private var accreditation: Accreditation? = null

    private var bilingual: Boolean = false

    //@ElementCollection
    //private var fields: List<AccreditationDescriptiveField> = emptyList()

    //@ManyToOne
    //private var coordinator: Professor? = null

    @CommandHandler
    constructor(command: CreateStudyProgramCommand) {
        val event = StudyProgramCreatedEvent(
                studyProgramId = StudyProgramId(),
                name = command.name
        )
        this.on(event)
        AggregateLifecycle.apply(event)
    }

    fun on(event: StudyProgramCreatedEvent) {
        this.id = event.studyProgramId
        this.name = event.name
    }

}