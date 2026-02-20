package mk.ukim.finki.soa.accreditation.model

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonProperty
import mk.ukim.finki.soa.accreditation.model.generalEnums.StudyCycle
import mk.ukim.finki.soa.accreditation.model.subject.SubjectBibliography
import mk.ukim.finki.soa.accreditation.model.subject.SubjectDependencies
import mk.ukim.finki.soa.accreditation.model.subject.SubjectGrading
import mk.ukim.finki.soa.accreditation.model.subject.SubjectObligationDuration

abstract class AbstractEvent(open val identifier: Identifier<out Any>) {
    @JsonProperty("_eventType")
    fun eventType(): String = this.javaClass.simpleName

    //ova kje raboti samo za Subject, nema za StudyProgram
    @JsonIgnore
    fun eventTopic(): String =
        this.javaClass.simpleName.removeSuffix("Event").replace(Regex("([a-z])([A-Z])"), "$1.$2").lowercase()

    @JsonIgnore
    open fun toExternalEvent(): Any? = null

}
/*------------------STUDY PROGRAM------------*/
abstract class StudyProgramEvent(
        open val studyProgramId: StudyProgramId
) : AbstractEvent(studyProgramId)

data class StudyProgramCreatedEvent(
    override val studyProgramId: StudyProgramId,

    val name: String,
    val nameEn: String,
    val order: Int,
    val durationYears: Int,

    val generalInformation: String?,
    val graduationTitle: String?,
    val graduationTitleEn: String?,
    val subjectRestrictions: String?,

    val inEnglish: Boolean,
    val bilingual: Boolean,

    val accreditation: AccreditationId,
    val coordinator: ProfessorId?,

    val studyCycle: StudyCycle
) : StudyProgramEvent(studyProgramId) {

    constructor(command: CreateStudyProgramCommand) : this(
        studyProgramId = StudyProgramId(command.code),

        name = command.name.takeIf { !it.isNullOrBlank() }
            ?: throw IllegalArgumentException("StudyProgram name must not be blank"),

        nameEn = command.nameEn.takeIf { it.isNullOrBlank() }
            ?: throw IllegalArgumentException("StudyProgram nameEn must not be blank"),

        order = command.order
            ?: throw IllegalArgumentException("StudyProgram order is required"),

        durationYears = command.durationYears
            ?: throw IllegalArgumentException("durationYears is required"),

        generalInformation = command.generalInformation,
        graduationTitle = command.graduationTitle,
        graduationTitleEn = command.graduationTitleEn,
        subjectRestrictions = command.subjectRestrictions,

        inEnglish = command.isItAvailableOnEnglish,
        bilingual = command.bilingual,

        accreditation = command.accreditation,
        coordinator = command.coordinator,

        studyCycle = command.studyCycle
    ) {
        require(durationYears > 0) { "durationYears must be > 0" }
    }
}



data class StudyProgramNameUpdatedEvent(
        override val studyProgramId: StudyProgramId,
        val name: String,
) : StudyProgramEvent(studyProgramId) {
    constructor(command: UpdateStudyProgramNameCommand) : this(
            studyProgramId = command.studyProgramId,
            name = command.name,
    )
}

data class StudyProgramNameEnUpdatedEvent(
    override val studyProgramId: StudyProgramId,
    val nameEn: String,
) : StudyProgramEvent(studyProgramId) {
    constructor(command: UpdateStudyProgramNameEnCommand) : this(
        studyProgramId = command.studyProgramId,
        nameEn = command.nameEn,
    )
}

data class StudyProgramOrderUpdatedEvent(
        override val studyProgramId: StudyProgramId,
        val order: Int
) : StudyProgramEvent(studyProgramId) {
    constructor(command: UpdateStudyProgramOrderCommand) : this(
            studyProgramId = command.studyProgramId,
            order = command.order
    )
}

data class StudyProgramDurationYearsUpdatedEvent(
        override val studyProgramId: StudyProgramId,
        val durationYears: Int
) : StudyProgramEvent(studyProgramId) {
    constructor(command: UpdateStudyProgramDurationYearsCommand) : this(
            studyProgramId = command.studyProgramId,
            durationYears = command.durationYears
    )
}

data class StudyProgramGeneralInformationUpdatedEvent(
        override val studyProgramId: StudyProgramId,
        val generalInformation: String
) : StudyProgramEvent(studyProgramId) {
    constructor(command: UpdateStudyProgramGeneralInformationCommand) : this(
            studyProgramId = command.studyProgramId,
            generalInformation = command.generalInformation
    )
}

data class StudyProgramGraduationTitleUpdatedEvent(
        override val studyProgramId: StudyProgramId,
        val graduationTitle: String,
        val graduationTitleEn: String
) : StudyProgramEvent(studyProgramId) {
    constructor(command: UpdateStudyProgramGraduationTitleCommand) : this(
            studyProgramId = command.studyProgramId,
            graduationTitle = command.graduationTitle,
            graduationTitleEn = command.graduationTitleEn
    )
}

data class StudyProgramEnglishAvailabilityUpdatedEvent(
        override val studyProgramId: StudyProgramId,
        val inEnglish: Boolean
) : StudyProgramEvent(studyProgramId) {
    constructor(command: UpdateStudyProgramEnglishAvailabilityCommand) : this(
            studyProgramId = command.studyProgramId,
            inEnglish = command.inEnglish
    )
}

data class StudyProgramBilingualUpdatedEvent(
        override val studyProgramId: StudyProgramId,
        val bilingual: Boolean
) : StudyProgramEvent(studyProgramId) {
    constructor(command: UpdateStudyProgramBilingualCommand) : this(
            studyProgramId = command.studyProgramId,
            bilingual = command.bilingual
    )
}


data class StudyProgramCoordinatorUpdatedEvent(
        override val studyProgramId: StudyProgramId,
        val coordinator: ProfessorId
) : StudyProgramEvent(studyProgramId) {
    constructor(command: UpdateStudyProgramCoordinatorCommand) : this(
            studyProgramId = command.studyProgramId,
            coordinator = command.coordinator
    )
}

data class StudyProgramStudyCycleUpdatedEvent(
        override val studyProgramId: StudyProgramId,
        val studyCycle: StudyCycle
) : StudyProgramEvent(studyProgramId) {
    constructor(command: UpdateStudyProgramStudyCycleCommand) : this(
            studyProgramId = command.studyProgramId,
            studyCycle = command.studyCycle
    )
}

data class StudyProgramSubjectRestrictionsUpdatedEvent(
        override val studyProgramId: StudyProgramId,
        val subjectRestrictions: String
) : StudyProgramEvent(studyProgramId) {
    constructor(command: UpdateStudyProgramSubjectRestrictionsCommand) : this(
            studyProgramId = command.studyProgramId,
            subjectRestrictions = command.subjectRestrictions
    )
}
/*------------------STUDY PROGRAM SUBJECT------------*/

abstract class StudyProgramSubjectEvent(
    open val studyProgramSubjectId: StudyProgramSubjectId
) : AbstractEvent(studyProgramSubjectId)

data class StudyProgramSubjectCreatedEvent(
    override val studyProgramSubjectId: StudyProgramSubjectId,
    val studyProgramId: StudyProgramId,
    val mandatory: Boolean,
    val semester: Int,
    val order: Float,
    val subjectGroup: String,
    val dependenciesOverride: String,
) : StudyProgramSubjectEvent(studyProgramSubjectId) {

    constructor(command: CreateStudyProgramSubjectCommand) : this(
        studyProgramSubjectId = StudyProgramSubjectId(),
        studyProgramId = command.studyProgramCode,
        mandatory = command.mandatory,
        semester = command.semester,
        order = command.order,
        subjectGroup = command.subjectGroup,
        dependenciesOverride = command.dependenciesOverride
    )
}

data class StudyProgramSubjectMandatoryUpdatedEvent(
    override val studyProgramSubjectId: StudyProgramSubjectId,
    val mandatory: Boolean
) : StudyProgramSubjectEvent(studyProgramSubjectId) {
    constructor(command: UpdateStudyProgramSubjectMandatoryCommand) : this(
        studyProgramSubjectId = command.studyProgramSubjectId,
        mandatory = command.mandatory
    )
}

data class StudyProgramSubjectSemesterUpdatedEvent(
    override val studyProgramSubjectId: StudyProgramSubjectId,
    val semester: Int
) : StudyProgramSubjectEvent(studyProgramSubjectId) {
    constructor(command: UpdateStudyProgramSubjectSemesterCommand) : this(
        studyProgramSubjectId = command.studyProgramSubjectId,
        semester = command.semester
    )
}

data class StudyProgramSubjectOrderUpdatedEvent(
    override val studyProgramSubjectId: StudyProgramSubjectId,
    val order: Float
) : StudyProgramSubjectEvent(studyProgramSubjectId) {
    constructor(command: UpdateStudyProgramSubjectOrderCommand) : this(
        studyProgramSubjectId = command.studyProgramSubjectId,
        order = command.order
    )
}

data class StudyProgramSubjectSubjectGroupUpdatedEvent(
    override val studyProgramSubjectId: StudyProgramSubjectId,
    val subjectGroup: String
) : StudyProgramSubjectEvent(studyProgramSubjectId) {
    constructor(command: UpdateStudyProgramSubjectSubjectGroupCommand) : this(
        studyProgramSubjectId = command.studyProgramSubjectId,
        subjectGroup = command.subjectGroup
    )
}

data class StudyProgramSubjectDependenciesOverrideUpdatedEvent(
    override val studyProgramSubjectId: StudyProgramSubjectId,
    val dependenciesOverride: String
) : StudyProgramSubjectEvent(studyProgramSubjectId) {
    constructor(command: UpdateStudyProgramSubjectDependenciesOverrideCommand) : this(
        studyProgramSubjectId = command.studyProgramSubjectId,
        dependenciesOverride = command.dependenciesOverride
    )
}

/*------------------------------SUBJECT-------------------------*/

abstract class SubjectEvent(
    open val subjectId: SubjectId
) : AbstractEvent(subjectId)

data class SubjectCreatedEvent(
    override val subjectId: SubjectId,
    val name: String?,
    val abbreviation: String?,
    val semester: Int?,
    val weeklyLecturesClasses: Int?,
    val weeklyAuditoriumClasses: Int?,
    val weeklyLabClasses: Int?,
    val placeholder: Boolean?,
    val nameEn: String?,
    val defaultSemester: Short?,
    val credits: Float?,
    val studyCycle: StudyCycle,
    val language: String?,
    val learningMethods: String?,
    val goalsDescription: String?,
    val content: String?,
    val goalsDescriptionEn: String?,
    val contentEn: String?,
    val qualityControl: String?,
    val accreditation: AccreditationId,
    val obligationDuration: SubjectObligationDuration?,
    val dependencies: SubjectDependencies?,
    val grading: SubjectGrading?,
    val bibliography: SubjectBibliography?,
    val notes: List<String>?
) : SubjectEvent(subjectId) {

    constructor(command: CreateSubjectCommand) : this(
        subjectId = SubjectId(command.code),
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
}

data class SubjectNameUpdatedEvent(
    override val subjectId: SubjectId,
    val name: String,
    val nameEn: String?
) : SubjectEvent(subjectId) {
    constructor(command: UpdateSubjectNameCommand) : this(
        subjectId = command.subjectId,
        name = command.name,
        nameEn = command.nameEn
    )
}

data class SubjectAbbreviationUpdatedEvent(
    override val subjectId: SubjectId,
    val abbreviation: String
) : SubjectEvent(subjectId) {
    constructor(command: UpdateSubjectAbbreviationCommand) : this(
        subjectId = command.subjectId,
        abbreviation = command.abbreviation
    )
}

data class SubjectSemesterUpdatedEvent(
    override val subjectId: SubjectId,
    val semester: Int?
) : SubjectEvent(subjectId) {
    constructor(command: UpdateSubjectSemesterCommand) : this(
        subjectId = command.subjectId,
        semester = command.semester
    )
}

data class SubjectWeeklyLecturesUpdatedEvent(
    override val subjectId: SubjectId,
    val weeklyLecturesClasses: Int?
) : SubjectEvent(subjectId) {
    constructor(command: UpdateSubjectWeeklyLecturesClassesCommand) : this(
        subjectId = command.subjectId,
        weeklyLecturesClasses = command.weeklyLecturesClasses
    )
}

data class SubjectWeeklyAuditoriumUpdatedEvent(
    override val subjectId: SubjectId,
    val weeklyAuditoriumClasses: Int?
) : SubjectEvent(subjectId) {
    constructor(command: UpdateSubjectWeeklyAuditoriumClassesCommand) : this(
        subjectId = command.subjectId,
        weeklyAuditoriumClasses = command.weeklyAuditoriumClasses
    )
}

data class SubjectWeeklyLabUpdatedEvent(
    override val subjectId: SubjectId,
    val weeklyLabClasses: Int?
) : SubjectEvent(subjectId) {
    constructor(command: UpdateSubjectWeeklyLabClassesCommand) : this(
        subjectId = command.subjectId,
        weeklyLabClasses = command.weeklyLabClasses
    )
}

data class SubjectPlaceholderUpdatedEvent(
    override val subjectId: SubjectId,
    val placeholder: Boolean?
) : SubjectEvent(subjectId) {
    constructor(command: UpdateSubjectPlaceholderCommand) : this(
        subjectId = command.subjectId,
        placeholder = command.placeholder
    )
}

data class SubjectDefaultSemesterUpdatedEvent(
    override val subjectId: SubjectId,
    val defaultSemester: Short?
) : SubjectEvent(subjectId) {
    constructor(command: UpdateSubjectDefaultSemesterCommand) : this(
        subjectId = command.subjectId,
        defaultSemester = command.defaultSemester
    )
}

data class SubjectCreditsUpdatedEvent(
    override val subjectId: SubjectId,
    val credits: Float?
) : SubjectEvent(subjectId) {
    constructor(command: UpdateSubjectCreditsCommand) : this(
        subjectId = command.subjectId,
        credits = command.credits
    )
}

data class SubjectStudyCycleUpdatedEvent(
    override val subjectId: SubjectId,
    val studyCycle: StudyCycle
) : SubjectEvent(subjectId) {
    constructor(command: UpdateSubjectStudyCycleCommand) : this(
        subjectId = command.subjectId,
        studyCycle = command.studyCycle
    )
}

data class SubjectLanguageUpdatedEvent(
    override val subjectId: SubjectId,
    val language: String?
) : SubjectEvent(subjectId) {
    constructor(command: UpdateSubjectLanguageCommand) : this(
        subjectId = command.subjectId,
        language = command.language
    )
}

data class SubjectLearningMethodsUpdatedEvent(
    override val subjectId: SubjectId,
    val learningMethods: String?
) : SubjectEvent(subjectId) {
    constructor(command: UpdateSubjectLearningMethodsCommand) : this(
        subjectId = command.subjectId,
        learningMethods = command.learningMethods
    )
}

data class SubjectGoalsDescriptionUpdatedEvent(
    override val subjectId: SubjectId,
    val goalsDescription: String?
) : SubjectEvent(subjectId) {
    constructor(command: UpdateSubjectGoalsDescriptionCommand) : this(
        subjectId = command.subjectId,
        goalsDescription = command.goalsDescription
    )
}

data class SubjectContentUpdatedEvent(
    override val subjectId: SubjectId,
    val content: String?
) : SubjectEvent(subjectId) {
    constructor(command: UpdateSubjectContentCommand) : this(
        subjectId = command.subjectId,
        content = command.content
    )
}

data class SubjectGoalsDescriptionEnUpdatedEvent(
    override val subjectId: SubjectId,
    val goalsDescriptionEn: String?
) : SubjectEvent(subjectId) {
    constructor(command: UpdateSubjectGoalsDescriptionEnCommand) : this(
        subjectId = command.subjectId,
        goalsDescriptionEn = command.goalsDescriptionEn
    )
}

data class SubjectContentEnUpdatedEvent(
    override val subjectId: SubjectId,
    val contentEn: String?
) : SubjectEvent(subjectId) {
    constructor(command: UpdateSubjectContentEnCommand) : this(
        subjectId = command.subjectId,
        contentEn = command.contentEn
    )
}

data class SubjectQualityControlUpdatedEvent(
    override val subjectId: SubjectId,
    val qualityControl: String?
) : SubjectEvent(subjectId) {
    constructor(command: UpdateSubjectQualityControlCommand) : this(
        subjectId = command.subjectId,
        qualityControl = command.qualityControl
    )
}

data class SubjectAccreditationUpdatedEvent(
    override val subjectId: SubjectId,
    val accreditation: AccreditationId
) : SubjectEvent(subjectId) {
    constructor(command: UpdateSubjectAccreditationCommand) : this(
        subjectId = command.subjectId,
        accreditation = command.accreditation
    )
}

data class SubjectObligationDurationUpdatedEvent(
    override val subjectId: SubjectId,
    val obligationDuration: SubjectObligationDuration?
) : SubjectEvent(subjectId) {
    constructor(command: UpdateSubjectObligationDurationCommand) : this(
        subjectId = command.subjectId,
        obligationDuration = command.obligationDuration
    )
}

data class SubjectDependenciesUpdatedEvent(
    override val subjectId: SubjectId,
    val dependencies: SubjectDependencies?
) : SubjectEvent(subjectId) {
    constructor(command: UpdateSubjectDependenciesCommand) : this(
        subjectId = command.subjectId,
        dependencies = command.dependencies
    )
}

data class SubjectGradingUpdatedEvent(
    override val subjectId: SubjectId,
    val grading: SubjectGrading?
) : SubjectEvent(subjectId) {
    constructor(command: UpdateSubjectGradingCommand) : this(
        subjectId = command.subjectId,
        grading = command.grading
    )
}

data class SubjectBibliographyUpdatedEvent(
    override val subjectId: SubjectId,
    val bibliography: SubjectBibliography?
) : SubjectEvent(subjectId) {
    constructor(command: UpdateSubjectBibliographyCommand) : this(
        subjectId = command.subjectId,
        bibliography = command.bibliography
    )
}

data class SubjectNotesUpdatedEvent(
    override val subjectId: SubjectId,
    val notes: List<String>?
) : SubjectEvent(subjectId) {
    constructor(command: UpdateSubjectNotesCommand) : this(
        subjectId = command.subjectId,
        notes = command.notes
    )
}


