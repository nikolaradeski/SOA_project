package mk.ukim.finki.soa.accreditation.model

import mk.ukim.finki.soa.accreditation.model.accreditation.Accreditation
import mk.ukim.finki.soa.accreditation.model.generalEnums.StudyCycle
import mk.ukim.finki.soa.accreditation.model.proffesorSnapShot.ProfessorId
import mk.ukim.finki.soa.accreditation.model.subject.SubjectBibliography
import mk.ukim.finki.soa.accreditation.model.subject.SubjectDependencies
import mk.ukim.finki.soa.accreditation.model.subject.SubjectGrading
import mk.ukim.finki.soa.accreditation.model.subject.SubjectObligationDuration

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
)

data class UpdateStudyProgramNameEnCommand(
        val studyProgramId: StudyProgramId,
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

data class CreateStudyProgramSubjectCommand(
//        val studyProgramSubjectId: String,
        val studyProgramCode: StudyProgramId,
        val mandatory: Boolean,
        val semester: Int,
        val order: Float,
        val subjectGroup: String,
        val dependenciesOverride: String
)

data class UpdateStudyProgramSubjectMandatoryCommand(
        val studyProgramSubjectId: StudyProgramSubjectId,
        val mandatory: Boolean
)

data class UpdateStudyProgramSubjectSemesterCommand(
        val studyProgramSubjectId: StudyProgramSubjectId,
        val semester: Int
)

data class UpdateStudyProgramSubjectOrderCommand(
        val studyProgramSubjectId: StudyProgramSubjectId,
        val order: Float
)

data class UpdateStudyProgramSubjectSubjectGroupCommand(
        val studyProgramSubjectId: StudyProgramSubjectId,
        val subjectGroup: String
)

data class UpdateStudyProgramSubjectDependenciesOverrideCommand(
        val studyProgramSubjectId: StudyProgramSubjectId,
        val dependenciesOverride: String
)

//-------------------SUBJECT-----------------------

data class CreateSubjectCommand(
        val code: String,
        val name: String,
        val abbreviation: String,
        val semester: Int,
        val weeklyLecturesClasses: Int,
        val weeklyAuditoriumClasses: Int,
        val weeklyLabClasses: Int,
        val placeholder: Boolean,
        val nameEn: String,
        val defaultSemester: Short,
        val credits: Float,
        val studyCycle: StudyCycle,
        val language: String,
        val learningMethods: String,
        val goalsDescription: String,
        val content: String,
        val goalsDescriptionEn: String,
        val contentEn: String,
        val qualityControl: String,
        val accreditation: AccreditationId,
        val obligationDuration: SubjectObligationDuration,
        val dependencies: SubjectDependencies,
        val grading: SubjectGrading,
        val bibliography: SubjectBibliography,
        val notes: List<String>
){}

data class UpdateSubjectNameCommand(
        val subjectId: SubjectId,
        val name: String,
        val nameEn: String
)

data class UpdateSubjectAbbreviationCommand(
        val subjectId: SubjectId,
        val abbreviation: String
)

data class UpdateSubjectSemesterCommand(
        val subjectId: SubjectId,
        val semester: Int
)

data class UpdateSubjectWeeklyLecturesClassesCommand(
        val subjectId: SubjectId,
        val weeklyLecturesClasses: Int
)

data class UpdateSubjectWeeklyAuditoriumClassesCommand(
        val subjectId: SubjectId,
        val weeklyAuditoriumClasses: Int
)

data class UpdateSubjectWeeklyLabClassesCommand(
        val subjectId: SubjectId,
        val weeklyLabClasses: Int
)

data class UpdateSubjectPlaceholderCommand(
        val subjectId: SubjectId,
        val placeholder: Boolean
)

data class UpdateSubjectDefaultSemesterCommand(
        val subjectId: SubjectId,
        val defaultSemester: Short
)

data class UpdateSubjectCreditsCommand(
        val subjectId: SubjectId,
        val credits: Float
)

data class UpdateSubjectStudyCycleCommand(
        val subjectId: SubjectId,
        val studyCycle: StudyCycle
)

data class UpdateSubjectLanguageCommand(
        val subjectId: SubjectId,
        val language: String
)

data class UpdateSubjectLearningMethodsCommand(
        val subjectId: SubjectId,
        val learningMethods: String
)

data class UpdateSubjectGoalsDescriptionCommand(
        val subjectId: SubjectId,
        val goalsDescription: String
)

data class UpdateSubjectContentCommand(
        val subjectId: SubjectId,
        val content: String
)

data class UpdateSubjectGoalsDescriptionEnCommand(
        val subjectId: SubjectId,
        val goalsDescriptionEn: String
)

data class UpdateSubjectContentEnCommand(
        val subjectId: SubjectId,
        val contentEn: String
)

data class UpdateSubjectQualityControlCommand(
        val subjectId: SubjectId,
        val qualityControl: String
)

data class UpdateSubjectAccreditationCommand(
        val subjectId: SubjectId,
        val accreditation: AccreditationId
)

data class UpdateSubjectObligationDurationCommand(
        val subjectId: SubjectId,
        val obligationDuration: SubjectObligationDuration
)

data class UpdateSubjectDependenciesCommand(
        val subjectId: SubjectId,
        val dependencies: SubjectDependencies
)

data class UpdateSubjectGradingCommand(
        val subjectId: SubjectId,
        val grading: SubjectGrading
)

data class UpdateSubjectBibliographyCommand(
        val subjectId: SubjectId,
        val bibliography: SubjectBibliography
)

data class UpdateSubjectNotesCommand(
        val subjectId: SubjectId,
        val notes: List<String>
)





