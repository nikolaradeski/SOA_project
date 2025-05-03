package mk.ukim.finki.soa.accreditation.model

import jakarta.persistence.Embeddable
import mk.ukim.finki.soa.accreditation.model.study_program.StudyProgram

import java.util.*

@Embeddable
open class StudyProgramId(value: String) : Identifier<StudyProgram>(value, StudyProgram::class.java){
    constructor() : this(UUID.randomUUID().toString())

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other?.javaClass != javaClass) return false

        return this.value == (other as StudyProgramId).value
    }

    override fun hashCode(): Int {
        return value.hashCode()
    }

}

