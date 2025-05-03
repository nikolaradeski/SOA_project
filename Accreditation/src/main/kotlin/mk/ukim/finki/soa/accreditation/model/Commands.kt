package mk.ukim.finki.soa.accreditation.model

import mk.ukim.finki.soa.accreditation.model.accreditation.Accreditation
import mk.ukim.finki.soa.accreditation.model.generalEnums.StudyCycle
import mk.ukim.finki.soa.accreditation.model.proffesorSnapShot.ProfessorId

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