package mk.ukim.finki.soa.accreditation.model

import mk.ukim.finki.soa.accreditation.model.generalEnums.StudyCycle
import mk.ukim.finki.soa.accreditation.model.proffesorSnapShot.ProfessorId

/*------------------STUDY PROGRAM------------*/
data class CreateStudyProgramCommandDTO(
        var code: String,
        var name: String,
        var nameEn: String,
        var order: Float,
        var durationYears: Int,
        var generalInformation: String,
        var graduationTitle: String,
        var graduationTitleEn: String,

        var isItAvailableOnEnglish: Boolean,
        var studyCycle : StudyCycle,

        var accreditation: AccreditationId,

        var coordinator: ProfessorId
)
/*------------------STUDY PROGRAM SUBJECT------------*/