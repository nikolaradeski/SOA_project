package mk.ukim.finki.soa.accreditation.model

import mk.ukim.finki.soa.accreditation.model.generalEnums.StudyCycle
import mk.ukim.finki.soa.accreditation.model.subject.SubjectBibliography
import mk.ukim.finki.soa.accreditation.model.subject.SubjectDependencies
import mk.ukim.finki.soa.accreditation.model.subject.SubjectGrading
import mk.ukim.finki.soa.accreditation.model.subject.SubjectObligationDuration

import org.axonframework.modelling.command.TargetAggregateIdentifier

/*------------------STUDY PROGRAM------------*/
data class CreateStudyProgramCommand(
        @TargetAggregateIdentifier
        var code: String,

        var name: String?,
        var nameEn: String?,

        var order: Int?,
        var durationYears: Int?,

        var generalInformation: String?,
        var graduationTitle: String?,
        var graduationTitleEn: String?,
        var subjectRestrictions: String?,

        var isItAvailableOnEnglish: Boolean,
        var bilingual: Boolean,
        var studyCycle: StudyCycle,

        var accreditation: AccreditationId,

        var coordinator: ProfessorId?
)

data class UpdateStudyProgramNameCommand(
        @TargetAggregateIdentifier
        val studyProgramId: StudyProgramId,
        val name: String,
)

data class UpdateStudyProgramNameEnCommand(
        @TargetAggregateIdentifier
        val studyProgramId: StudyProgramId,
        val nameEn: String
)

data class UpdateStudyProgramOrderCommand(
        @TargetAggregateIdentifier
        val studyProgramId: StudyProgramId,
        val order: Int
)

data class UpdateStudyProgramDurationYearsCommand(
        @TargetAggregateIdentifier
        val studyProgramId: StudyProgramId,
        val durationYears: Int
)

data class UpdateStudyProgramGeneralInformationCommand(
        @TargetAggregateIdentifier
        val studyProgramId: StudyProgramId,
        val generalInformation: String
)

data class UpdateStudyProgramGraduationTitleCommand(
        @TargetAggregateIdentifier
        val studyProgramId: StudyProgramId,
        val graduationTitle: String,
        val graduationTitleEn: String
)

data class UpdateStudyProgramEnglishAvailabilityCommand(
        @TargetAggregateIdentifier
        val studyProgramId: StudyProgramId,
        val inEnglish: Boolean
)

data class UpdateStudyProgramBilingualCommand(
        @TargetAggregateIdentifier
        val studyProgramId: StudyProgramId,
        val bilingual: Boolean
)

data class UpdateStudyProgramCoordinatorCommand(
        @TargetAggregateIdentifier
        val studyProgramId: StudyProgramId,
        val coordinator: ProfessorId
)

data class UpdateStudyProgramStudyCycleCommand(
        @TargetAggregateIdentifier
        val studyProgramId: StudyProgramId,
        val studyCycle: StudyCycle
)

data class UpdateStudyProgramSubjectRestrictionsCommand(
        @TargetAggregateIdentifier
        val studyProgramId: StudyProgramId,
        val subjectRestrictions: String
)

/*------------------STUDY PROGRAM SUBJECT------------*/

data class CreateStudyProgramSubjectCommand(
        @TargetAggregateIdentifier
//        val studyProgramSubjectId: String,
        val studyProgramCode: StudyProgramId,
        val mandatory: Boolean,
        val semester: Int,
        val order: Float,
        val subjectGroup: String,
        val dependenciesOverride: String
)

data class UpdateStudyProgramSubjectMandatoryCommand(
        @TargetAggregateIdentifier
        val studyProgramSubjectId: StudyProgramSubjectId,
        val mandatory: Boolean
)

data class UpdateStudyProgramSubjectSemesterCommand(
        @TargetAggregateIdentifier
        val studyProgramSubjectId: StudyProgramSubjectId,
        val semester: Int
)

data class UpdateStudyProgramSubjectOrderCommand(
        @TargetAggregateIdentifier
        val studyProgramSubjectId: StudyProgramSubjectId,
        val order: Float
)

data class UpdateStudyProgramSubjectSubjectGroupCommand(
        @TargetAggregateIdentifier
        val studyProgramSubjectId: StudyProgramSubjectId,
        val subjectGroup: String
)

data class UpdateStudyProgramSubjectDependenciesOverrideCommand(
        @TargetAggregateIdentifier
        val studyProgramSubjectId: StudyProgramSubjectId,
        val dependenciesOverride: String
)

//-------------------SUBJECT-----------------------

data class CreateSubjectCommand(
        @TargetAggregateIdentifier
        val code: String,

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
){}

data class UpdateSubjectNameCommand(
        @TargetAggregateIdentifier
        val subjectId: SubjectId,
        val name: String,
        val nameEn: String
)

data class UpdateSubjectAbbreviationCommand(
        @TargetAggregateIdentifier
        val subjectId: SubjectId,
        val abbreviation: String
)

data class UpdateSubjectSemesterCommand(
        @TargetAggregateIdentifier
        val subjectId: SubjectId,
        val semester: Int
)

data class UpdateSubjectWeeklyLecturesClassesCommand(
        @TargetAggregateIdentifier
        val subjectId: SubjectId,
        val weeklyLecturesClasses: Int
)

data class UpdateSubjectWeeklyAuditoriumClassesCommand(
        @TargetAggregateIdentifier
        val subjectId: SubjectId,
        val weeklyAuditoriumClasses: Int
)

data class UpdateSubjectWeeklyLabClassesCommand(
        @TargetAggregateIdentifier
        val subjectId: SubjectId,
        val weeklyLabClasses: Int
)

data class UpdateSubjectPlaceholderCommand(
        @TargetAggregateIdentifier
        val subjectId: SubjectId,
        val placeholder: Boolean
)

data class UpdateSubjectDefaultSemesterCommand(
        @TargetAggregateIdentifier
        val subjectId: SubjectId,
        val defaultSemester: Short
)

data class UpdateSubjectCreditsCommand(
        @TargetAggregateIdentifier
        val subjectId: SubjectId,
        val credits: Float
)

data class UpdateSubjectStudyCycleCommand(
        @TargetAggregateIdentifier
        val subjectId: SubjectId,
        val studyCycle: StudyCycle
)

data class UpdateSubjectLanguageCommand(
        @TargetAggregateIdentifier
        val subjectId: SubjectId,
        val language: String
)

data class UpdateSubjectLearningMethodsCommand(
        @TargetAggregateIdentifier
        val subjectId: SubjectId,
        val learningMethods: String
)

data class UpdateSubjectGoalsDescriptionCommand(
        @TargetAggregateIdentifier
        val subjectId: SubjectId,
        val goalsDescription: String
)

data class UpdateSubjectContentCommand(
        @TargetAggregateIdentifier
        val subjectId: SubjectId,
        val content: String
)

data class UpdateSubjectGoalsDescriptionEnCommand(
        @TargetAggregateIdentifier
        val subjectId: SubjectId,
        val goalsDescriptionEn: String
)

data class UpdateSubjectContentEnCommand(
        @TargetAggregateIdentifier
        val subjectId: SubjectId,
        val contentEn: String
)

data class UpdateSubjectQualityControlCommand(
        @TargetAggregateIdentifier
        val subjectId: SubjectId,
        val qualityControl: String
)

data class UpdateSubjectAccreditationCommand(
        @TargetAggregateIdentifier
        val subjectId: SubjectId,
        val accreditation: AccreditationId
)

data class UpdateSubjectObligationDurationCommand(
        @TargetAggregateIdentifier
        val subjectId: SubjectId,
        val obligationDuration: SubjectObligationDuration
)

data class UpdateSubjectDependenciesCommand(
        @TargetAggregateIdentifier
        val subjectId: SubjectId,
        val dependencies: SubjectDependencies
)

data class UpdateSubjectGradingCommand(
        @TargetAggregateIdentifier
        val subjectId: SubjectId,
        val grading: SubjectGrading
)

data class UpdateSubjectBibliographyCommand(
        @TargetAggregateIdentifier
        val subjectId: SubjectId,
        val bibliography: SubjectBibliography
)

data class UpdateSubjectNotesCommand(
        @TargetAggregateIdentifier
        val subjectId: SubjectId,
        val notes: List<String>
)





