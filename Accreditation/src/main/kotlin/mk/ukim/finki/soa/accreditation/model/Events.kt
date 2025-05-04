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
        val inEnglish: Boolean,
        val bilingual: Boolean,
        val accreditation: AccreditationId,
        val coordinator: ProfessorId,
        val studyCycle: StudyCycle,
        val subjectRestrictions: String,


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
        val nameEn: String,
) : StudyProgramEvent(studyProgramId) {
    constructor(command: UpdateStudyProgramNameCommand) : this(
            studyProgramId = command.studyProgramId,
            name = command.name,
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