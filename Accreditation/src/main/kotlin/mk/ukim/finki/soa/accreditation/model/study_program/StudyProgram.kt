package mk.ukim.finki.soa.accreditation.model.study_program

import jakarta.persistence.*
import mk.ukim.finki.soa.accreditation.model.AccreditationId
import mk.ukim.finki.soa.accreditation.model.CreateStudyProgramCommand
import mk.ukim.finki.soa.accreditation.model.StudyProgramCreatedEvent
import mk.ukim.finki.soa.accreditation.model.StudyProgramId
import mk.ukim.finki.soa.accreditation.model.generalEnums.StudyCycle
import mk.ukim.finki.soa.accreditation.model.proffesorSnapShot.ProfessorId
import org.axonframework.commandhandling.CommandHandler
import org.axonframework.modelling.command.AggregateIdentifier
import org.axonframework.modelling.command.AggregateLifecycle
import org.axonframework.spring.stereotype.Aggregate
import java.time.Duration


@Entity
@Aggregate(repository = "axonStudyProgramRepository")
public class StudyProgram {

    @AggregateIdentifier
    @EmbeddedId
    @AttributeOverride(name = "value", column = Column(name = "code"))
    private lateinit var code: StudyProgramId

    private lateinit var name: String

    private lateinit var nameEn: String

    @Embedded
    @AttributeOverride(name = "order", column = Column(name = "study_program_order"))
    private var order: Order? = null

    @Embedded
    @AttributeOverride(name = "durationYears", column = Column(name = "duration_years"))
    private var durationYears: DurationYears? = null;

    @Column(length = 8000)
    private var generalInformation: String? = null

    @Column(length = 3000)
    private var graduationTitle: String? = null

    @Column(length = 3000)
    private var graduationTitleEn: String? = null

    @Column(length = 8000)
    private var subjectRestrictions: String? = null

    private var onEnglish: Boolean = false

    @Enumerated(EnumType.STRING)
    private lateinit var studyCycle: StudyCycle

    @Embedded
    @AttributeOverride(name = "value", column = Column(name = "accreditation_id"))
    private lateinit var accreditation: AccreditationId;

    private var bilingual: Boolean = false

    //@ElementCollection
    //private var fields: List<AccreditationDescriptiveField> = emptyList()

    @Embedded
    @AttributeOverride(name = "value", column = Column(name = "professor_id"))
    private var coordinator: ProfessorId? = null

    @CommandHandler
    constructor(command: CreateStudyProgramCommand) {
        val event = StudyProgramCreatedEvent(command)
        this.on(event)
        AggregateLifecycle.apply(event)
    }

    fun on(event: StudyProgramCreatedEvent) {
        this.code = event.studyProgramId
        this.name = event.name
        this.nameEn = event.nameEn
        this.order = Order(event.order)
        this.durationYears = DurationYears(event.durationYears)
        this.generalInformation = event.generalInformation
        this.graduationTitle = event.graduationTitle
        this.graduationTitleEn = event.graduationTitleEn
        this.subjectRestrictions = event.subjectRestrictions
        this.onEnglish = event.inEnglish
        this.studyCycle = event.studyCycle
        this.accreditation = event.accreditation
        this.bilingual = event.bilingual
        this.coordinator = event.coordinator
    }

}