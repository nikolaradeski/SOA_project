package mk.ukim.finki.soa.accreditation.model.subject

import jakarta.persistence.*
import mk.ukim.finki.soa.accreditation.model.*
import org.axonframework.modelling.command.AggregateIdentifier
import org.axonframework.spring.stereotype.Aggregate
import mk.ukim.finki.soa.accreditation.model.generalEnums.StudyCycle
import mk.ukim.finki.soa.accreditation.model.proffesorSnapShot.ProfessorInformation
import org.axonframework.commandhandling.CommandHandler
import org.axonframework.eventsourcing.EventSourcingHandler
import org.axonframework.modelling.command.AggregateLifecycle

@Aggregate(repository = "axonSubjectRepository")
@Entity
class Subject {

    @AggregateIdentifier
    @EmbeddedId
    @AttributeOverride(name = "value", column = Column(name = "code"))
    private lateinit var code: SubjectId

    // required in the aggregate state
    private lateinit var name: String
    private lateinit var abbreviation: String

    private var semester: Int? = null

    private var weeklyLecturesClasses: Int? = null
    private var weeklyAuditoriumClasses: Int? = null
    private var weeklyLabClasses: Int? = null
    private var placeholder: Boolean? = null
    private var nameEn: String? = null
    private var defaultSemester: Short? = null
    private var credits: Float? = null

    @Enumerated(EnumType.STRING)
    private lateinit var studyCycle: StudyCycle

    private var language: String? = null

    @Column(length = 8000) private var learningMethods: String? = null
    @Column(length = 8000) private var goalsDescription: String? = null
    @Column(length = 8000) private var content: String? = null
    @Column(length = 8000) private var goalsDescriptionEn: String? = null
    @Column(length = 8000) private var contentEn: String? = null
    @Column(length = 4000) private var qualityControl: String? = null

    @Embedded
    @AttributeOverride(name = "value", column = Column(name = "accreditation_id"))
    private lateinit var accreditation: AccreditationId

    @Embedded private var obligationDuration: SubjectObligationDuration? = null
    @Embedded private var dependencies: SubjectDependencies? = null
    @Embedded private var grading: SubjectGrading? = null
    @Embedded private var bibliography: SubjectBibliography? = null

    @ElementCollection
    private var notes: List<String> = emptyList()

    protected constructor()

    @CommandHandler
    constructor(command: CreateSubjectCommand) {
        require(command.code.isNotBlank()) { "Subject code must not be blank" }
        require(!command.name.isNullOrBlank()) { "Subject name is required" }
        require(!command.abbreviation.isNullOrBlank()) { "Subject abbreviation is required" }

        AggregateLifecycle.apply(SubjectCreatedEvent(command))
    }

    @EventSourcingHandler
    fun on(event: SubjectCreatedEvent) {
        this.code = event.subjectId

        // since event fields are nullable now:
        this.name = event.name ?: error("SubjectCreatedEvent.name must not be null")
        this.abbreviation = event.abbreviation ?: error("SubjectCreatedEvent.abbreviation must not be null")

        this.semester = event.semester
        this.weeklyLecturesClasses = event.weeklyLecturesClasses
        this.weeklyAuditoriumClasses = event.weeklyAuditoriumClasses
        this.weeklyLabClasses = event.weeklyLabClasses
        this.placeholder = event.placeholder
        this.nameEn = event.nameEn
        this.defaultSemester = event.defaultSemester
        this.credits = event.credits
        this.studyCycle = event.studyCycle
        this.language = event.language
        this.learningMethods = event.learningMethods
        this.goalsDescription = event.goalsDescription
        this.content = event.content
        this.goalsDescriptionEn = event.goalsDescriptionEn
        this.contentEn = event.contentEn
        this.qualityControl = event.qualityControl
        this.accreditation = event.accreditation
        this.obligationDuration = event.obligationDuration
        this.dependencies = event.dependencies
        this.grading = event.grading
        this.bibliography = event.bibliography

        this.notes = event.notes ?: emptyList()
    }


    // Then add update command handlers + @EventSourcingHandler like you did for StudyProgram:
    // UpdateSubjectNameCommand -> apply SubjectNameUpdatedEvent -> on(event){...}

    @CommandHandler
    fun handle(command: UpdateSubjectNameCommand) {
        AggregateLifecycle.apply(SubjectNameUpdatedEvent(command))
    }

    @EventSourcingHandler
    fun on(event: SubjectNameUpdatedEvent) {
        this.name = event.name
        this.nameEn = event.nameEn
    }

    @CommandHandler
    fun handle(command: UpdateSubjectAbbreviationCommand) {
        AggregateLifecycle.apply(SubjectAbbreviationUpdatedEvent(command))
    }

    @EventSourcingHandler
    fun on(event: SubjectAbbreviationUpdatedEvent) {
        this.abbreviation = event.abbreviation
    }

    @CommandHandler
    fun handle(command: UpdateSubjectSemesterCommand) {
        AggregateLifecycle.apply(SubjectSemesterUpdatedEvent(command))
    }

    @EventSourcingHandler
    fun on(event: SubjectSemesterUpdatedEvent) {
        this.semester = event.semester
    }

    @CommandHandler
    fun handle(command: UpdateSubjectWeeklyLecturesClassesCommand) {
        AggregateLifecycle.apply(SubjectWeeklyLecturesUpdatedEvent(command))
    }

    @EventSourcingHandler
    fun on(event: SubjectWeeklyLecturesUpdatedEvent) {
        this.weeklyLecturesClasses = event.weeklyLecturesClasses
    }

    @CommandHandler
    fun handle(command: UpdateSubjectWeeklyAuditoriumClassesCommand) {
        AggregateLifecycle.apply(SubjectWeeklyAuditoriumUpdatedEvent(command))
    }

    @EventSourcingHandler
    fun on(event: SubjectWeeklyAuditoriumUpdatedEvent) {
        this.weeklyAuditoriumClasses = event.weeklyAuditoriumClasses
    }

    @CommandHandler
    fun handle(command: UpdateSubjectWeeklyLabClassesCommand) {
        AggregateLifecycle.apply(SubjectWeeklyLabUpdatedEvent(command))
    }

    @EventSourcingHandler
    fun on(event: SubjectWeeklyLabUpdatedEvent) {
        this.weeklyLabClasses = event.weeklyLabClasses
    }

    @CommandHandler
    fun handle(command: UpdateSubjectPlaceholderCommand) {
        AggregateLifecycle.apply(SubjectPlaceholderUpdatedEvent(command))
    }

    @EventSourcingHandler
    fun on(event: SubjectPlaceholderUpdatedEvent) {
        this.placeholder = event.placeholder
    }

    @CommandHandler
    fun handle(command: UpdateSubjectDefaultSemesterCommand) {
        AggregateLifecycle.apply(SubjectDefaultSemesterUpdatedEvent(command))
    }

    @EventSourcingHandler
    fun on(event: SubjectDefaultSemesterUpdatedEvent) {
        this.defaultSemester = event.defaultSemester
    }

    @CommandHandler
    fun handle(command: UpdateSubjectCreditsCommand) {
        AggregateLifecycle.apply(SubjectCreditsUpdatedEvent(command))
    }

    @EventSourcingHandler
    fun on(event: SubjectCreditsUpdatedEvent) {
        this.credits = event.credits
    }

    @CommandHandler
    fun handle(command: UpdateSubjectStudyCycleCommand) {
        AggregateLifecycle.apply(SubjectStudyCycleUpdatedEvent(command))
    }

    @EventSourcingHandler
    fun on(event: SubjectStudyCycleUpdatedEvent) {
        this.studyCycle = event.studyCycle
    }

    @CommandHandler
    fun handle(command: UpdateSubjectLanguageCommand) {
        AggregateLifecycle.apply(SubjectLanguageUpdatedEvent(command))
    }

    @EventSourcingHandler
    fun on(event: SubjectLanguageUpdatedEvent) {
        this.language = event.language
    }

    @CommandHandler
    fun handle(command: UpdateSubjectLearningMethodsCommand) {
        AggregateLifecycle.apply(SubjectLearningMethodsUpdatedEvent(command))
    }

    @EventSourcingHandler
    fun on(event: SubjectLearningMethodsUpdatedEvent) {
        this.learningMethods = event.learningMethods
    }

    @CommandHandler
    fun handle(command: UpdateSubjectGoalsDescriptionCommand) {
        AggregateLifecycle.apply(SubjectGoalsDescriptionUpdatedEvent(command))
    }

    @EventSourcingHandler
    fun on(event: SubjectGoalsDescriptionUpdatedEvent) {
        this.goalsDescription = event.goalsDescription
    }

    @CommandHandler
    fun handle(command: UpdateSubjectContentCommand) {
        AggregateLifecycle.apply(SubjectContentUpdatedEvent(command))
    }

    @EventSourcingHandler
    fun on(event: SubjectContentUpdatedEvent) {
        this.content = event.content
    }

    @CommandHandler
    fun handle(command: UpdateSubjectGoalsDescriptionEnCommand) {
        AggregateLifecycle.apply(SubjectGoalsDescriptionEnUpdatedEvent(command))
    }

    @EventSourcingHandler
    fun on(event: SubjectGoalsDescriptionEnUpdatedEvent) {
        this.goalsDescriptionEn = event.goalsDescriptionEn
    }

    @CommandHandler
    fun handle(command: UpdateSubjectContentEnCommand) {
        AggregateLifecycle.apply(SubjectContentEnUpdatedEvent(command))
    }

    @EventSourcingHandler
    fun on(event: SubjectContentEnUpdatedEvent) {
        this.contentEn = event.contentEn
    }

    @CommandHandler
    fun handle(command: UpdateSubjectQualityControlCommand) {
        AggregateLifecycle.apply(SubjectQualityControlUpdatedEvent(command))
    }

    @EventSourcingHandler
    fun on(event: SubjectQualityControlUpdatedEvent) {
        this.qualityControl = event.qualityControl
    }

    @CommandHandler
    fun handle(command: UpdateSubjectAccreditationCommand) {
        AggregateLifecycle.apply(SubjectAccreditationUpdatedEvent(command))
    }

    @EventSourcingHandler
    fun on(event: SubjectAccreditationUpdatedEvent) {
        this.accreditation = event.accreditation
    }

    @CommandHandler
    fun handle(command: UpdateSubjectObligationDurationCommand) {
        AggregateLifecycle.apply(SubjectObligationDurationUpdatedEvent(command))
    }

    @EventSourcingHandler
    fun on(event: SubjectObligationDurationUpdatedEvent) {
        this.obligationDuration = event.obligationDuration
    }

    @CommandHandler
    fun handle(command: UpdateSubjectDependenciesCommand) {
        AggregateLifecycle.apply(SubjectDependenciesUpdatedEvent(command))
    }

    @EventSourcingHandler
    fun on(event: SubjectDependenciesUpdatedEvent) {
        this.dependencies = event.dependencies
    }

    @CommandHandler
    fun handle(command: UpdateSubjectGradingCommand) {
        AggregateLifecycle.apply(SubjectGradingUpdatedEvent(command))
    }

    @EventSourcingHandler
    fun on(event: SubjectGradingUpdatedEvent) {
        this.grading = event.grading
    }

    @CommandHandler
    fun handle(command: UpdateSubjectBibliographyCommand) {
        AggregateLifecycle.apply(SubjectBibliographyUpdatedEvent(command))
    }

    @EventSourcingHandler
    fun on(event: SubjectBibliographyUpdatedEvent) {
        this.bibliography = event.bibliography
    }

    @CommandHandler
    fun handle(command: UpdateSubjectNotesCommand) {
        AggregateLifecycle.apply(SubjectNotesUpdatedEvent(command))
    }

    @EventSourcingHandler
    fun on(event: SubjectNotesUpdatedEvent) {
        this.notes = event.notes ?: emptyList()
    }
//
//    fun updateName(value: String) {
//        this.name = value
//    }
//
//    fun updateAbbreviation(value: String) {
//        this.abbreviation = value
//    }
//
//    fun updateSemester(value: Int?) {
//        this.semester = value
//    }
//
//    fun updateWeeklyLecturesClasses(value: Int?) {
//        this.weeklyLecturesClasses = value
//    }
//
//    fun updateWeeklyAuditoriumClasses(value: Int?) {
//        this.weeklyAuditoriumClasses = value
//    }
//
//    fun updateWeeklyLabClasses(value: Int?) {
//        this.weeklyLabClasses = value
//    }
//
//    fun updatePlaceholder(value: Boolean?) {
//        this.placeholder = value
//    }
//
//    fun updateNameEn(value: String?) {
//        this.nameEn = value
//    }
//
//    fun updateDefaultSemester(value: Short?) {
//        this.defaultSemester = value
//    }
//
//    fun updateCredits(value: Float?) {
//        this.credits = value
//    }
//
//    fun updateStudyCycle(value: StudyCycle) {
//        this.studyCycle = value
//    }
//
//    fun updateLanguage(value: String?) {
//        this.language = value
//    }
//
//    fun updateLearningMethods(value: String?) {
//        this.learningMethods = value
//    }
//
//    fun updateGoalsDescription(value: String?) {
//        this.goalsDescription = value
//    }
//
//    fun updateContent(value: String?) {
//        this.content = value
//    }
//
//    fun updateGoalsDescriptionEn(value: String?) {
//        this.goalsDescriptionEn = value
//    }
//
//    fun updateContentEn(value: String?) {
//        this.contentEn = value
//    }
//
//    fun updateQualityControl(value: String?) {
//        this.qualityControl = value
//    }
//
//    fun updateAccreditation(value: AccreditationId) {
//        this.accreditation = value
//    }
//
//    fun updateObligationDuration(value: SubjectObligationDuration?) {
//        this.obligationDuration = value
//    }
//
//    fun updateDependencies(value: SubjectDependencies?) {
//        this.dependencies = value
//    }
//
//    fun updateGrading(value: SubjectGrading?) {
//        this.grading = value
//    }
//
//    fun updateBibliography(value: SubjectBibliography?) {
//        this.bibliography = value
//    }
//
//    fun updateNotes(value: List<String>?) {
//        this.notes = value
//    }
}