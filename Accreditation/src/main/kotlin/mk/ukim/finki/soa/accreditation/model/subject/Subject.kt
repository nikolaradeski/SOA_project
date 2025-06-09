package mk.ukim.finki.soa.accreditation.model.subject

import jakarta.persistence.*
import mk.ukim.finki.soa.accreditation.model.*
import org.axonframework.modelling.command.AggregateIdentifier
import org.axonframework.spring.stereotype.Aggregate
import mk.ukim.finki.soa.accreditation.model.generalEnums.StudyCycle
import org.axonframework.commandhandling.CommandHandler
import org.axonframework.modelling.command.AggregateLifecycle

@Entity
@Aggregate(repository = "axonSubjectRepository")
public class Subject {
    @AggregateIdentifier
    @EmbeddedId
    @AttributeOverride(name = "value", column = Column(name = "code"))
    private lateinit var code: SubjectId
    private lateinit var name: String
    private lateinit var abbreviation: String

    @Enumerated(EnumType.STRING)
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


    @Column(length = 8000)
    private var learningMethods: String? = null


    @Column(length = 8000)
    private var goalsDescription: String? = null

    @Column(length = 8000)
    private var content: String? = null

    @Column(length = 8000)
    private var goalsDescriptionEn: String? = null

    @Column(length = 8000)
    private var contentEn: String? = null

    @Column(length = 4000)
    private var qualityControl: String? = null


    @Embedded
    @AttributeOverride(name = "value", column = Column(name = "accreditation_id"))
    private lateinit var accreditation: AccreditationId;

    @Embedded
    private var obligationDuration: SubjectObligationDuration? = null

    @Embedded
    private var dependencies: SubjectDependencies? = null

    @Embedded
    private var grading: SubjectGrading? = null

    @Embedded
    private var bibliography: SubjectBibliography? = null

    @ElementCollection
    private var notes: List<String>? = null

//    @CommandHandler
//    constructor(command: CreateSubjectCommand) {
//        val event = SubjectCreatedEvent(command)
//        this.on(event)
//        AggregateLifecycle.apply(event)
//    }

    @CommandHandler
    constructor(command: CreateSubjectCommand) {
        val event = SubjectCreatedEvent(
            subjectId = SubjectId(),
            name = command.name,
            abbreviation = command.abbreviation,
            semester = command.semester,
            weeklyLecturesClasses = command.weeklyLecturesClasses,
            weeklyAuditoriumClasses = command.weeklyAuditoriumClasses,
            weeklyLabClasses = command.weeklyLabClasses,
            placeholder = command.placeholder,
            nameEn = command.nameEn,
            defaultSemester = command.defaultSemester,
            credits = command.credits,
            studyCycle = command.studyCycle,
            language = command.language,
            learningMethods = command.learningMethods,
            goalsDescription = command.goalsDescription,
            content = command.content,
            goalsDescriptionEn = command.goalsDescriptionEn,
            contentEn = command.contentEn,
            qualityControl = command.qualityControl,
            accreditation = command.accreditation,
            obligationDuration = command.obligationDuration,
            dependencies = command.dependencies,
            grading = command.grading,
            bibliography = command.bibliography,
            notes = command.notes
        )
        this.on(event)
        AggregateLifecycle.apply(event)
    }

    fun on(event: SubjectCreatedEvent) {
        this.code = event.subjectId
        this.name = event.name
        this.abbreviation = event.abbreviation
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
        this.notes = event.notes
    }

    @CommandHandler
    fun handle(command: UpdateSubjectNameCommand) {
        val event = SubjectNameUpdatedEvent(command.subjectId, command.name, command.nameEn)
        this.on(event)
        AggregateLifecycle.apply(event)
    }

    fun on(event: SubjectNameUpdatedEvent) {
        this.name = event.name
        this.nameEn = event.nameEn
    }

    @CommandHandler
    fun handle(command: UpdateSubjectAbbreviationCommand) {
        val event = SubjectAbbreviationUpdatedEvent(command.subjectId, command.abbreviation)
        this.on(event)
        AggregateLifecycle.apply(event)
    }

    fun on(event: SubjectAbbreviationUpdatedEvent) {
        this.abbreviation = event.abbreviation
    }

    @CommandHandler
    fun handle(command: UpdateSubjectSemesterCommand) {
        val event = SubjectSemesterUpdatedEvent(command.subjectId, command.semester)
        this.on(event)
        AggregateLifecycle.apply(event)
    }

    fun on(event: SubjectSemesterUpdatedEvent) {
        this.semester = event.semester
    }

    @CommandHandler
    fun handle(command: UpdateSubjectWeeklyLecturesClassesCommand) {
        val event = SubjectWeeklyLecturesUpdatedEvent(command.subjectId, command.weeklyLecturesClasses)
        this.on(event)
        AggregateLifecycle.apply(event)
    }

    fun on(event: SubjectWeeklyLecturesUpdatedEvent) {
        this.weeklyLecturesClasses = event.weeklyLecturesClasses
    }

    @CommandHandler
    fun handle(command: UpdateSubjectWeeklyAuditoriumClassesCommand) {
        val event = SubjectWeeklyAuditoriumUpdatedEvent(command.subjectId, command.weeklyAuditoriumClasses)
        this.on(event)
        AggregateLifecycle.apply(event)
    }

    fun on(event: SubjectWeeklyAuditoriumUpdatedEvent) {
        this.weeklyAuditoriumClasses = event.weeklyAuditoriumClasses
    }

    @CommandHandler
    fun handle(command: UpdateSubjectWeeklyLabClassesCommand) {
        val event = SubjectWeeklyLabUpdatedEvent(command.subjectId, command.weeklyLabClasses)
        this.on(event)
        AggregateLifecycle.apply(event)
    }

    fun on(event: SubjectWeeklyLabUpdatedEvent) {
        this.weeklyLabClasses = event.weeklyLabClasses
    }

    @CommandHandler
    fun handle(command: UpdateSubjectPlaceholderCommand) {
        val event = SubjectPlaceholderUpdatedEvent(command.subjectId, command.placeholder)
        this.on(event)
        AggregateLifecycle.apply(event)
    }

    fun on(event: SubjectPlaceholderUpdatedEvent) {
        this.placeholder = event.placeholder
    }

    @CommandHandler
    fun handle(command: UpdateSubjectDefaultSemesterCommand) {
        val event = SubjectDefaultSemesterUpdatedEvent(command.subjectId, command.defaultSemester)
        this.on(event)
        AggregateLifecycle.apply(event)
    }

    fun on(event: SubjectDefaultSemesterUpdatedEvent) {
        this.defaultSemester = event.defaultSemester
    }

    @CommandHandler
    fun handle(command: UpdateSubjectCreditsCommand) {
        val event = SubjectCreditsUpdatedEvent(command.subjectId, command.credits)
        this.on(event)
        AggregateLifecycle.apply(event)
    }

    fun on(event: SubjectCreditsUpdatedEvent) {
        this.credits = event.credits
    }

    @CommandHandler
    fun handle(command: UpdateSubjectStudyCycleCommand) {
        val event = SubjectStudyCycleUpdatedEvent(command.subjectId, command.studyCycle)
        this.on(event)
        AggregateLifecycle.apply(event)
    }

    fun on(event: SubjectStudyCycleUpdatedEvent) {
        this.studyCycle = event.studyCycle
    }

    @CommandHandler
    fun handle(command: UpdateSubjectLanguageCommand) {
        val event = SubjectLanguageUpdatedEvent(command.subjectId, command.language)
        this.on(event)
        AggregateLifecycle.apply(event)
    }

    fun on(event: SubjectLanguageUpdatedEvent) {
        this.language = event.language
    }

    @CommandHandler
    fun handle(command: UpdateSubjectLearningMethodsCommand) {
        val event = SubjectLearningMethodsUpdatedEvent(command.subjectId, command.learningMethods)
        this.on(event)
        AggregateLifecycle.apply(event)
    }

    fun on(event: SubjectLearningMethodsUpdatedEvent) {
        this.learningMethods = event.learningMethods
    }

    @CommandHandler
    fun handle(command: UpdateSubjectGoalsDescriptionCommand) {
        val event = SubjectGoalsDescriptionUpdatedEvent(command.subjectId, command.goalsDescription)
        this.on(event)
        AggregateLifecycle.apply(event)
    }

    fun on(event: SubjectGoalsDescriptionUpdatedEvent) {
        this.goalsDescription = event.goalsDescription
    }

    @CommandHandler
    fun handle(command: UpdateSubjectContentCommand) {
        val event = SubjectContentUpdatedEvent(command.subjectId, command.content)
        this.on(event)
        AggregateLifecycle.apply(event)
    }

    fun on(event: SubjectContentUpdatedEvent) {
        this.content = event.content
    }

    @CommandHandler
    fun handle(command: UpdateSubjectGoalsDescriptionEnCommand) {
        val event = SubjectGoalsDescriptionEnUpdatedEvent(command.subjectId, command.goalsDescriptionEn)
        this.on(event)
        AggregateLifecycle.apply(event)
    }

    fun on(event: SubjectGoalsDescriptionEnUpdatedEvent) {
        this.goalsDescriptionEn = event.goalsDescriptionEn
    }

    @CommandHandler
    fun handle(command: UpdateSubjectContentEnCommand) {
        val event = SubjectContentEnUpdatedEvent(command.subjectId, command.contentEn)
        this.on(event)
        AggregateLifecycle.apply(event)
    }

    fun on(event: SubjectContentEnUpdatedEvent) {
        this.contentEn = event.contentEn
    }

    @CommandHandler
    fun handle(command: UpdateSubjectQualityControlCommand) {
        val event = SubjectQualityControlUpdatedEvent(command.subjectId, command.qualityControl)
        this.on(event)
        AggregateLifecycle.apply(event)
    }

    fun on(event: SubjectQualityControlUpdatedEvent) {
        this.qualityControl = event.qualityControl
    }

    @CommandHandler
    fun handle(command: UpdateSubjectAccreditationCommand) {
        val event = SubjectAccreditationUpdatedEvent(command.subjectId, command.accreditation)
        this.on(event)
        AggregateLifecycle.apply(event)
    }

    fun on(event: SubjectAccreditationUpdatedEvent) {
        this.accreditation = event.accreditation
    }

    @CommandHandler
    fun handle(command: UpdateSubjectObligationDurationCommand) {
        val event = SubjectObligationDurationUpdatedEvent(command.subjectId, command.obligationDuration)
        this.on(event)
        AggregateLifecycle.apply(event)
    }

    fun on(event: SubjectObligationDurationUpdatedEvent) {
        this.obligationDuration = event.obligationDuration
    }

    @CommandHandler
    fun handle(command: UpdateSubjectDependenciesCommand) {
        val event = SubjectDependenciesUpdatedEvent(command.subjectId, command.dependencies)
        this.on(event)
        AggregateLifecycle.apply(event)
    }

    fun on(event: SubjectDependenciesUpdatedEvent) {
        this.dependencies = event.dependencies
    }

    @CommandHandler
    fun handle(command: UpdateSubjectGradingCommand) {
        val event = SubjectGradingUpdatedEvent(command.subjectId, command.grading)
        this.on(event)
        AggregateLifecycle.apply(event)
    }

    fun on(event: SubjectGradingUpdatedEvent) {
        this.grading = event.grading
    }

    @CommandHandler
    fun handle(command: UpdateSubjectBibliographyCommand) {
        val event = SubjectBibliographyUpdatedEvent(command.subjectId, command.bibliography)
        this.on(event)
        AggregateLifecycle.apply(event)
    }

    fun on(event: SubjectBibliographyUpdatedEvent) {
        this.bibliography = event.bibliography
    }

    @CommandHandler
    fun handle(command: UpdateSubjectNotesCommand) {
        val event = SubjectNotesUpdatedEvent(command.subjectId, command.notes)
        this.on(event)
        AggregateLifecycle.apply(event)
    }

    fun on(event: SubjectNotesUpdatedEvent) {
        this.notes = event.notes
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