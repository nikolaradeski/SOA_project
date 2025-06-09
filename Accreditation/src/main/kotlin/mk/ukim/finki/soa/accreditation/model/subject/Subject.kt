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

    @CommandHandler
    constructor(command: CreateSubjectCommand) {
        val event = SubjectCreatedEvent(command)
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

    fun updateName(value: String) {
        this.name = value
    }

    fun updateAbbreviation(value: String) {
        this.abbreviation = value
    }

    fun updateSemester(value: Int?) {
        this.semester = value
    }

    fun updateWeeklyLecturesClasses(value: Int?) {
        this.weeklyLecturesClasses = value
    }

    fun updateWeeklyAuditoriumClasses(value: Int?) {
        this.weeklyAuditoriumClasses = value
    }

    fun updateWeeklyLabClasses(value: Int?) {
        this.weeklyLabClasses = value
    }

    fun updatePlaceholder(value: Boolean?) {
        this.placeholder = value
    }

    fun updateNameEn(value: String?) {
        this.nameEn = value
    }

    fun updateDefaultSemester(value: Short?) {
        this.defaultSemester = value
    }

    fun updateCredits(value: Float?) {
        this.credits = value
    }

    fun updateStudyCycle(value: StudyCycle) {
        this.studyCycle = value
    }

    fun updateLanguage(value: String?) {
        this.language = value
    }

    fun updateLearningMethods(value: String?) {
        this.learningMethods = value
    }

    fun updateGoalsDescription(value: String?) {
        this.goalsDescription = value
    }

    fun updateContent(value: String?) {
        this.content = value
    }

    fun updateGoalsDescriptionEn(value: String?) {
        this.goalsDescriptionEn = value
    }

    fun updateContentEn(value: String?) {
        this.contentEn = value
    }

    fun updateQualityControl(value: String?) {
        this.qualityControl = value
    }

    fun updateAccreditation(value: AccreditationId) {
        this.accreditation = value
    }

    fun updateObligationDuration(value: SubjectObligationDuration?) {
        this.obligationDuration = value
    }

    fun updateDependencies(value: SubjectDependencies?) {
        this.dependencies = value
    }

    fun updateGrading(value: SubjectGrading?) {
        this.grading = value
    }

    fun updateBibliography(value: SubjectBibliography?) {
        this.bibliography = value
    }

    fun updateNotes(value: List<String>?) {
        this.notes = value
    }

}