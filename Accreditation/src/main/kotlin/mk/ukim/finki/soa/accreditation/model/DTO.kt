package mk.ukim.finki.soa.accreditation.model

import mk.ukim.finki.soa.accreditation.model.generalEnums.StudyCycle

/*------------------STUDY PROGRAM------------*/
data class CreateStudyProgramCommandDTO(
        var name: String,
        var nameEn: String,
        var order: Float,
        var durationYears: Int,
        var generalInformation: String,
        var graduationTitle: String,
        var graduationTitleEn: String,
        var subjectRestrictions: String,
        var isItAvailableOnEnglish: Boolean,
        var bilingual: Boolean,
        var studyCycle : StudyCycle,
        var accreditation: AccreditationId,
        var coordinator: ProfessorId
)

data class UpdateStudyProgramNameCommandDTO(
        val studyProgramId: StudyProgramId,
        val name: String,
)

data class UpdateStudyProgramNameEnglishCommandDTO(
        val studyProgramId: StudyProgramId,
        val nameEn: String
)

data class UpdateStudyProgramOrderCommandDTO(
        val studyProgramId: StudyProgramId,
        val order: Int
)

data class UpdateStudyProgramDurationYearsCommandDTO(
        val studyProgramId: StudyProgramId,
        val durationYears: Int
)

data class UpdateStudyProgramGeneralInformationCommandDTO(
        val studyProgramId: StudyProgramId,
        val generalInformation: String
)

data class UpdateStudyProgramGraduationTitleCommandDTO(
        val studyProgramId: StudyProgramId,
        val graduationTitle: String,
        val graduationTitleEn: String
)

data class UpdateStudyProgramEnglishAvailabilityCommandDTO(
        val studyProgramId: StudyProgramId,
        val inEnglish: Boolean
)

data class UpdateStudyProgramBilingualCommandDTO(
        val studyProgramId: StudyProgramId,
        val bilingual: Boolean
)

data class UpdateStudyProgramCoordinatorCommandDTO(
        val studyProgramId: StudyProgramId,
        val coordinator: ProfessorId
)

data class UpdateStudyProgramStudyCycleCommandDTO(
        val studyProgramId: StudyProgramId,
        val studyCycle: StudyCycle
)

data class UpdateStudyProgramSubjectRestrictionsCommandDTO(
        val studyProgramId: StudyProgramId,
        val subjectRestrictions: String
)
/*------------------STUDY PROGRAM SUBJECT------------*/
data class CreateStudyProgramSubjectCommandDTO(
        var studyProgramCode: String,
        var mandatory: Boolean,
        var semester: Int,
        var order: Float,
        var subjectGroup: String,
        var dependenciesOverride: String
)

data class UpdateStudyProgramSubjectMandatoryCommandDTO(
        val studyProgramSubjectId: String,
        val mandatory: Boolean
)

data class UpdateStudyProgramSubjectSemesterCommandDTO(
        val studyProgramSubjectId: String,
        val semester: Int
)

data class UpdateStudyProgramSubjectOrderCommandDTO(
        val studyProgramSubjectId: String,
        val order: Float
)

data class UpdateStudyProgramSubjectSubjectGroupCommandDTO(
        val studyProgramSubjectId: String,
        val subjectGroup: String
)

data class UpdateStudyProgramSubjectDependenciesOverrideCommandDTO(
        val studyProgramSubjectId: String,
        val dependenciesOverride: String
)
/*------------------STUDY PROGRAM SUBJECT------------*/

data class SubjectCreatedExternalEvent(
        val code: SubjectId,
        val name: String,
        val credits: Float?,
)

