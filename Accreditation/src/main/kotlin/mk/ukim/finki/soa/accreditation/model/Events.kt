package mk.ukim.finki.soa.accreditation.model

import mk.ukim.finki.soa.accreditation.model.generalEnums.StudyCycle
import mk.ukim.finki.soa.accreditation.model.proffesorSnapShot.ProfessorId

abstract class AbstractEvent(open val identifier: Identifier<out Any>) {

}

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
