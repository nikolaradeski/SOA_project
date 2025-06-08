package mk.ukim.finki.soa.accreditation.model

import mk.ukim.finki.soa.accreditation.model.generalEnums.StudyCycle
import mk.ukim.finki.soa.accreditation.model.proffesorSnapShot.ProfessorId

abstract class AbstractEvent(open val identifier: Identifier<out Any>) {

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
        val generalInformation: String,
        val graduationTitle: String,
        val graduationTitleEn: String,
        val subjectRestrictions: String,
        val inEnglish: Boolean,
        val studyCycle: StudyCycle,
        val accreditation: AccreditationId,
        val bilingual: Boolean,
        val coordinator: ProfessorId,



): StudyProgramEvent(studyProgramId){
    constructor(command: CreateStudyProgramCommand) : this(
            studyProgramId = StudyProgramId(),
            name = command.name,
            nameEn = command.nameEn,
            order = command.order,
            durationYears = command.durationYears,
            generalInformation = command.generalInformation,
            graduationTitle = command.graduationTitle,
            graduationTitleEn = command.graduationTitleEn,
            inEnglish = command.isItAvailableOnEnglish,
            bilingual = command.bilingual,
            accreditation = command.accreditation,
            coordinator = command.coordinator,
            studyCycle = command.studyCycle,
            subjectRestrictions = command.subjectRestrictions,
    )
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

data class StudyProgramNameEnglishUpdatedEvent(
        override val studyProgramId: StudyProgramId,
        val nameEn: String,
) : StudyProgramEvent(studyProgramId) {
    constructor(command: UpdateStudyProgramNameEnglishCommand) : this(
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
