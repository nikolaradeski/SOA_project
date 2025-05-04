package mk.ukim.finki.soa.accreditation.model

import mk.ukim.finki.soa.accreditation.model.accreditation.Accreditation
import mk.ukim.finki.soa.accreditation.model.generalEnums.StudyCycle
import mk.ukim.finki.soa.accreditation.model.proffesorSnapShot.ProfessorId

/*------------------STUDY PROGRAM------------*/
data class CreateStudyProgramCommand(
        var code: String,
        var name: String,
        var nameEn: String,
        var order: Int,
        var durationYears: Int,
        var generalInformation: String,
        var graduationTitle: String,
        var graduationTitleEn: String,
        var subjectRestrictions: String,


        //Subject Restrictions for??? Mozebi ke treba StudyProgramSubjectRestriction.

        var isItAvailableOnEnglish: Boolean,
        var bilingual: Boolean,
        var studyCycle : StudyCycle,

        var accreditation: AccreditationId,

        var coordinator: ProfessorId




        ) {



}

data class UpdateStudyProgramNameCommand(
        val studyProgramId: StudyProgramId,
        val name: String,
        val nameEn: String
)


data class UpdateStudyProgramOrderCommand(
        val studyProgramId: StudyProgramId,
        val order: Int
)

data class UpdateStudyProgramDurationYearsCommand(
        val studyProgramId: StudyProgramId,
        val durationYears: Int
)

data class UpdateStudyProgramGeneralInformationCommand(
        val studyProgramId: StudyProgramId,
        val generalInformation: String
)

data class UpdateStudyProgramGraduationTitleCommand(
        val studyProgramId: StudyProgramId,
        val graduationTitle: String,
        val graduationTitleEn: String
)

data class UpdateStudyProgramEnglishAvailabilityCommand(
        val studyProgramId: StudyProgramId,
        val inEnglish: Boolean
)

data class UpdateStudyProgramBilingualCommand(
        val studyProgramId: StudyProgramId,
        val bilingual: Boolean
)

data class UpdateStudyProgramCoordinatorCommand(
        val studyProgramId: StudyProgramId,
        val coordinator: ProfessorId
)

data class UpdateStudyProgramStudyCycleCommand(
        val studyProgramId: StudyProgramId,
        val studyCycle: StudyCycle
)

data class UpdateStudyProgramSubjectRestrictionsCommand(
        val studyProgramId: StudyProgramId,
        val subjectRestrictions: String
)
/*------------------STUDY PROGRAM SUBJECT------------*/