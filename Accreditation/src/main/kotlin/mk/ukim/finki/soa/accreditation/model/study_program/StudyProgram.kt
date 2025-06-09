package mk.ukim.finki.soa.accreditation.model.study_program

import jakarta.persistence.*
import mk.ukim.finki.soa.accreditation.model.*
import mk.ukim.finki.soa.accreditation.model.generalEnums.StudyCycle
import mk.ukim.finki.soa.accreditation.model.proffesorSnapShot.ProfessorId
import org.axonframework.commandhandling.CommandHandler
import org.axonframework.modelling.command.AggregateIdentifier
import org.axonframework.modelling.command.AggregateLifecycle
import org.axonframework.spring.stereotype.Aggregate
import java.time.Duration


@Entity
@Aggregate(repository = "axonStudyProgramRepository")
public class StudyProgram : LabeledEntity {

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

    private var inEnglish: Boolean = false

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
        val event = StudyProgramCreatedEvent(
                studyProgramId = StudyProgramId(),
                name = command.name,
                nameEn = command.nameEn,
                order = command.order,
                durationYears = command.durationYears,
                generalInformation = command.generalInformation,
                graduationTitle = command.graduationTitle,
                graduationTitleEn = command.graduationTitleEn,
                subjectRestrictions = command.subjectRestrictions,
                inEnglish = command.isItAvailableOnEnglish,
                studyCycle = command.studyCycle,
                accreditation = command.accreditation,
                bilingual = command.bilingual,
                coordinator = command.coordinator
        )
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
        this.inEnglish = event.inEnglish
        this.studyCycle = event.studyCycle
        this.accreditation = event.accreditation
        this.bilingual = event.bilingual
        this.coordinator = event.coordinator
    }


    @CommandHandler
    fun handle(command: UpdateStudyProgramNameCommand) {
        val event = StudyProgramNameUpdatedEvent(command)
        this.on(event)
        AggregateLifecycle.apply(event)
    }

    fun on(event: StudyProgramNameUpdatedEvent) {
        this.name = event.name
    }

    @CommandHandler
    fun handle(command: UpdateStudyProgramNameEnCommand) {
        val event = StudyProgramNameEnUpdatedEvent(command)
        this.on(event)
        AggregateLifecycle.apply(event)
    }

    fun on(event: StudyProgramNameEnUpdatedEvent) {
        this.nameEn = event.nameEn
    }

    @CommandHandler
    fun handle(command: UpdateStudyProgramOrderCommand) {
        val event = StudyProgramOrderUpdatedEvent(command)
        this.on(event)
        AggregateLifecycle.apply(event)
    }

    fun on(event: StudyProgramOrderUpdatedEvent) {
        this.order = Order(event.order)
    }

    @CommandHandler
    fun handle(command: UpdateStudyProgramDurationYearsCommand) {
        val event = StudyProgramDurationYearsUpdatedEvent(command)
        this.on(event)
        AggregateLifecycle.apply(event)
    }

    fun on(event: StudyProgramDurationYearsUpdatedEvent) {
        this.durationYears = DurationYears(event.durationYears)
    }

    @CommandHandler
    fun handle(command: UpdateStudyProgramGeneralInformationCommand) {
        val event = StudyProgramGeneralInformationUpdatedEvent(command)
        this.on(event)
        AggregateLifecycle.apply(event)
    }

    fun on(event: StudyProgramGeneralInformationUpdatedEvent) {
        this.generalInformation = event.generalInformation
    }

    @CommandHandler
    fun handle(command: UpdateStudyProgramGraduationTitleCommand) {
        val event = StudyProgramGraduationTitleUpdatedEvent(command)
        this.on(event)
        AggregateLifecycle.apply(event)
    }

    fun on(event: StudyProgramGraduationTitleUpdatedEvent) {
        this.graduationTitle = event.graduationTitle
        this.graduationTitleEn = event.graduationTitleEn
    }

    @CommandHandler
    fun handle(command: UpdateStudyProgramSubjectRestrictionsCommand) {
        val event = StudyProgramSubjectRestrictionsUpdatedEvent(command)
        this.on(event)
        AggregateLifecycle.apply(event)
    }

    fun on(event: StudyProgramSubjectRestrictionsUpdatedEvent) {
        this.subjectRestrictions = event.subjectRestrictions
    }

    @CommandHandler
    fun handle(command: UpdateStudyProgramEnglishAvailabilityCommand) {
        val event = StudyProgramEnglishAvailabilityUpdatedEvent(command)
        this.on(event)
        AggregateLifecycle.apply(event)
    }

    fun on(event: StudyProgramEnglishAvailabilityUpdatedEvent) {
        this.inEnglish = event.inEnglish
    }

    @CommandHandler
    fun handle(command: UpdateStudyProgramBilingualCommand) {
        val event = StudyProgramBilingualUpdatedEvent(command)
        this.on(event)
        AggregateLifecycle.apply(event)
    }

    fun on(event: StudyProgramBilingualUpdatedEvent) {
        this.bilingual = event.bilingual
    }

    @CommandHandler
    fun handle(command: UpdateStudyProgramCoordinatorCommand) {
        val event = StudyProgramCoordinatorUpdatedEvent(command)
        this.on(event)
        AggregateLifecycle.apply(event)
    }

    fun on(event: StudyProgramCoordinatorUpdatedEvent) {
        this.coordinator = event.coordinator
    }

    @CommandHandler
    fun handle(command: UpdateStudyProgramStudyCycleCommand) {
        val event = StudyProgramStudyCycleUpdatedEvent(command)
        this.on(event)
        AggregateLifecycle.apply(event)
    }

    fun on(event: StudyProgramStudyCycleUpdatedEvent) {
        this.studyCycle = event.studyCycle
    }
    /*--------------STUDY PROGRAM SUBJECT--------*/
    @CommandHandler
    fun handle(command: CreateStudyProgramSubjectCommand) {
        val event = StudyProgramSubjectCreatedEvent(
                studyProgramSubjectId = StudyProgramSubjectId(),
                studyProgramId = command.studyProgramCode,
                mandatory = command.mandatory,
                semester = command.semester,
                order = command.order,
                subjectGroup = command.subjectGroup,
                dependenciesOverride = command.dependenciesOverride
        )
        this.on(event)
        AggregateLifecycle.apply(event)
    }

    fun on(event: StudyProgramSubjectCreatedEvent) {

    }

    override fun getId(): Identifier<out Any> {
        TODO("Not yet implemented")
    }

    override fun getLabel(): String {
        TODO("Not yet implemented")
    }

}