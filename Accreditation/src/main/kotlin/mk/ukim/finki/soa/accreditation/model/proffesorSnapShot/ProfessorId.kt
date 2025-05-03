package mk.ukim.finki.soa.accreditation.model.proffesorSnapShot

import jakarta.persistence.Embeddable
import mk.ukim.finki.soa.accreditation.model.Identifier
import mk.ukim.finki.soa.accreditation.model.StudyProgramId
import mk.ukim.finki.soa.accreditation.model.study_program.StudyProgram
import java.util.*

@Embeddable
open class ProfessorId(value: String) {


    lateinit var value : String;


    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other?.javaClass != javaClass) return false

        return this.value == (other as StudyProgramId).value
    }

    override fun hashCode(): Int {
        return value.hashCode()
    }

}