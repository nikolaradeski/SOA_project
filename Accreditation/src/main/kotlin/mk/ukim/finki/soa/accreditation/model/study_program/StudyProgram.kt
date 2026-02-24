package mk.ukim.finki.soa.accreditation.model.study_program

import jakarta.persistence.*
import mk.ukim.finki.soa.accreditation.model.*
import mk.ukim.finki.soa.accreditation.model.generalEnums.StudyCycle
import org.axonframework.commandhandling.CommandHandler
import org.axonframework.eventsourcing.EventSourcingHandler
import org.axonframework.modelling.command.AggregateIdentifier
import org.axonframework.modelling.command.AggregateLifecycle
import org.axonframework.spring.stereotype.Aggregate

@Entity
@Table(name = "study_program")
@Aggregate(repository = "axonStudyProgramRepository")
class StudyProgram : LabeledEntity {

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
    private var durationYears: DurationYears? = null

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
    private lateinit var accreditation: AccreditationId

    private var bilingual: Boolean = false

    @Embedded
    @AttributeOverride(name = "value", column = Column(name = "professor_id"))
    private var coordinator: ProfessorId? = null

    // JPA needs no-arg constructor
    protected constructor()

    @EventSourcingHandler
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
    constructor(command: CreateStudyProgramCommand) {
        require(command.code.isNotBlank()) { "StudyProgram code must not be blank" }

        // these are nullable now -> validate here OR let the event constructor validate
        require(!command.name.isNullOrBlank()) { "StudyProgram name must not be blank" }
        require(!command.nameEn.isNullOrBlank()) { "StudyProgram nameEn must not be blank" }
        require(command.durationYears != null && command.durationYears!! > 0) { "durationYears must be > 0" }
        require(command.order != null) { "order is required" }

        AggregateLifecycle.apply(StudyProgramCreatedEvent(command))
    }

    @CommandHandler
    fun handle(command: UpdateStudyProgramNameCommand) {
        require(command.name.isNotBlank()) { "Name must not be blank" }
        AggregateLifecycle.apply(StudyProgramNameUpdatedEvent(command))
    }

    @EventSourcingHandler
    fun on(event: StudyProgramNameUpdatedEvent) {
        this.name = event.name
    }

    @CommandHandler
    fun handle(command: UpdateStudyProgramNameEnCommand) {
        require(command.nameEn.isNotBlank()) { "English name must not be blank" }
        AggregateLifecycle.apply(StudyProgramNameEnUpdatedEvent(command))
    }

    @EventSourcingHandler
    fun on(event: StudyProgramNameEnUpdatedEvent) {
        this.nameEn = event.nameEn
    }

    @CommandHandler
    fun handle(command: UpdateStudyProgramOrderCommand) {
        require(command.order > 0) { "Order must be > 0" }
        AggregateLifecycle.apply(StudyProgramOrderUpdatedEvent(command))
    }

    @EventSourcingHandler
    fun on(event: StudyProgramOrderUpdatedEvent) {
        this.order = Order(event.order)
    }

    @CommandHandler
    fun handle(command: UpdateStudyProgramDurationYearsCommand) {
        require(command.durationYears > 0) { "durationYears must be > 0" }
        AggregateLifecycle.apply(StudyProgramDurationYearsUpdatedEvent(command))
    }

    @EventSourcingHandler
    fun on(event: StudyProgramDurationYearsUpdatedEvent) {
        this.durationYears = DurationYears(event.durationYears)
    }

    @CommandHandler
    fun handle(command: UpdateStudyProgramGeneralInformationCommand) {
        AggregateLifecycle.apply(StudyProgramGeneralInformationUpdatedEvent(command))
    }

    @EventSourcingHandler
    fun on(event: StudyProgramGeneralInformationUpdatedEvent) {
        this.generalInformation = event.generalInformation
    }

    @CommandHandler
    fun handle(command: UpdateStudyProgramGraduationTitleCommand) {
        AggregateLifecycle.apply(StudyProgramGraduationTitleUpdatedEvent(command))
    }

    @EventSourcingHandler
    fun on(event: StudyProgramGraduationTitleUpdatedEvent) {
        this.graduationTitle = event.graduationTitle
        this.graduationTitleEn = event.graduationTitleEn
    }

    @CommandHandler
    fun handle(command: UpdateStudyProgramSubjectRestrictionsCommand) {
        AggregateLifecycle.apply(StudyProgramSubjectRestrictionsUpdatedEvent(command))
    }

    @EventSourcingHandler
    fun on(event: StudyProgramSubjectRestrictionsUpdatedEvent) {
        this.subjectRestrictions = event.subjectRestrictions
    }

    @CommandHandler
    fun handle(command: UpdateStudyProgramEnglishAvailabilityCommand) {
        AggregateLifecycle.apply(StudyProgramEnglishAvailabilityUpdatedEvent(command))
    }

    @EventSourcingHandler
    fun on(event: StudyProgramEnglishAvailabilityUpdatedEvent) {
        this.inEnglish = event.inEnglish
    }

    @CommandHandler
    fun handle(command: UpdateStudyProgramBilingualCommand) {
        AggregateLifecycle.apply(StudyProgramBilingualUpdatedEvent(command))
    }

    @EventSourcingHandler
    fun on(event: StudyProgramBilingualUpdatedEvent) {
        this.bilingual = event.bilingual
    }

    @CommandHandler
    fun handle(command: UpdateStudyProgramCoordinatorCommand) {
        AggregateLifecycle.apply(StudyProgramCoordinatorUpdatedEvent(command))
    }

    @EventSourcingHandler
    fun on(event: StudyProgramCoordinatorUpdatedEvent) {
        this.coordinator = event.coordinator
    }

    @CommandHandler
    fun handle(command: UpdateStudyProgramStudyCycleCommand) {
        AggregateLifecycle.apply(StudyProgramStudyCycleUpdatedEvent(command))
    }

    @EventSourcingHandler
    fun on(event: StudyProgramStudyCycleUpdatedEvent) {
        this.studyCycle = event.studyCycle
    }

    override fun getId(): Identifier<out Any> = code
    override fun getLabel(): String = name

    @ElementCollection
    @CollectionTable(
        name = "study_program_curriculum_subjects",
        joinColumns = [JoinColumn(name = "study_program_code", referencedColumnName = "code")]
    )
    private val curriculumSubjectIds: MutableSet<CurriculumSubjectId> = mutableSetOf()

    // ---------- CREATE ----------
    @CommandHandler
    fun handle(cmd: CreateStudyProgramSubjectCommand) {
        val csId = CurriculumSubjectId(cmd.studyProgramId, cmd.subjectId)

        require(cmd.semester > 0) { "semester must be > 0" }
        require(cmd.order > 0) { "order must be > 0" }
        require(!curriculumSubjectIds.contains(csId)) { "Curriculum subject already exists for this study program" }

        AggregateLifecycle.apply(StudyProgramSubjectCreatedEvent(cmd))
    }

    @EventSourcingHandler
    fun on(event: StudyProgramSubjectCreatedEvent) {
        curriculumSubjectIds.add(event.curriculumSubjectId)
    }


    // ---------- UPDATE: mandatory ----------
    @CommandHandler
    fun handle(cmd: UpdateStudyProgramSubjectMandatoryCommand) {
        val csId = CurriculumSubjectId(cmd.studyProgramId, cmd.subjectId)
        require(curriculumSubjectIds.contains(csId)) { "Curriculum subject does not exist for this study program" }

        AggregateLifecycle.apply(StudyProgramSubjectMandatoryUpdatedEvent(cmd))
    }

    @EventSourcingHandler
    fun on(event: StudyProgramSubjectMandatoryUpdatedEvent) {
        // no state needed besides existence tracking
    }


    // ---------- UPDATE: semester ----------
    @CommandHandler
    fun handle(cmd: UpdateStudyProgramSubjectSemesterCommand) {
        val csId = CurriculumSubjectId(cmd.studyProgramId, cmd.subjectId)
        require(curriculumSubjectIds.contains(csId)) { "Curriculum subject does not exist for this study program" }
        require(cmd.semester > 0) { "semester must be > 0" }

        AggregateLifecycle.apply(StudyProgramSubjectSemesterUpdatedEvent(cmd))
    }

    @EventSourcingHandler
    fun on(event: StudyProgramSubjectSemesterUpdatedEvent) {
        // no state needed besides existence tracking
    }


    // ---------- UPDATE: order ----------
    @CommandHandler
    fun handle(cmd: UpdateStudyProgramSubjectOrderCommand) {
        val csId = CurriculumSubjectId(cmd.studyProgramId, cmd.subjectId)
        require(curriculumSubjectIds.contains(csId)) { "Curriculum subject does not exist for this study program" }
        require(cmd.order > 0) { "order must be > 0" }

        AggregateLifecycle.apply(StudyProgramSubjectOrderUpdatedEvent(cmd))
    }

    @EventSourcingHandler
    fun on(event: StudyProgramSubjectOrderUpdatedEvent) {
        // no state needed besides existence tracking
    }


    // ---------- UPDATE: subjectGroup ----------
    @CommandHandler
    fun handle(cmd: UpdateStudyProgramSubjectSubjectGroupCommand) {
        val csId = CurriculumSubjectId(cmd.studyProgramId, cmd.subjectId)
        require(curriculumSubjectIds.contains(csId)) { "Curriculum subject does not exist for this study program" }
        require(cmd.subjectGroup.isNotBlank()) { "subjectGroup must not be blank" }

        AggregateLifecycle.apply(StudyProgramSubjectSubjectGroupUpdatedEvent(cmd))
    }

    @EventSourcingHandler
    fun on(event: StudyProgramSubjectSubjectGroupUpdatedEvent) {
        // no state needed besides existence tracking
    }


    // ---------- UPDATE: dependenciesOverride ----------
    @CommandHandler
    fun handle(cmd: UpdateStudyProgramSubjectDependenciesOverrideCommand) {
        val csId = CurriculumSubjectId(cmd.studyProgramId, cmd.subjectId)
        require(curriculumSubjectIds.contains(csId)) { "Curriculum subject does not exist for this study program" }

        AggregateLifecycle.apply(StudyProgramSubjectDependenciesOverrideUpdatedEvent(cmd))
    }

    @EventSourcingHandler
    fun on(event: StudyProgramSubjectDependenciesOverrideUpdatedEvent) {
        // no state needed besides existence tracking
    }


}
