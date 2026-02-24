package mk.ukim.finki.soa.accreditation.model

import jakarta.persistence.AttributeOverride
import jakarta.persistence.Column
import jakarta.persistence.Embeddable
import jakarta.persistence.Embedded
import mk.ukim.finki.soa.accreditation.model.accreditation.Accreditation
import mk.ukim.finki.soa.accreditation.model.study_program.StudyProgram
import mk.ukim.finki.soa.accreditation.model.study_program.CurriculumSubject
import mk.ukim.finki.soa.accreditation.model.study_program.documents.Document
import mk.ukim.finki.soa.accreditation.model.subject.Subject
import java.io.Serializable

import java.util.*

@Embeddable
open class AccreditationId(value: String) : Identifier<Accreditation>(value, Accreditation::class.java){
    constructor() : this(UUID.randomUUID().toString())

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other?.javaClass != javaClass) return false

        return this.value == (other as AccreditationId).value
    }

    override fun hashCode(): Int {
        return value.hashCode()
    }

}

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

@Embeddable
internal class DocumentId(value: String) : Identifier<Document>(value, Document::class.java){
    constructor() : this(UUID.randomUUID().toString())

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other?.javaClass != javaClass) return false

        return this.value == (other as DocumentId).value
    }

    override fun hashCode(): Int {
        return value.hashCode()
    }
}

@Embeddable
open class SubjectId(value: String) : Identifier<Subject>(value, Subject::class.java){
    constructor() : this(UUID.randomUUID().toString())

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other?.javaClass != javaClass) return false

        return this.value == (other as SubjectId).value
    }

    override fun hashCode(): Int {
        return value.hashCode()
    }
}

@Embeddable
data class ProfessorId(
    @Column(name = "professor_id")
    val value: String
) {
    constructor() : this(UUID.randomUUID().toString())

    override fun toString(): String = value
}

@Embeddable
data class CurriculumSubjectId(
    @Embedded
    @AttributeOverride(name = "value", column = Column(name = "study_program"))
    val studyProgram: StudyProgramId,

    @Embedded
    @AttributeOverride(name = "value", column = Column(name = "subject"))
    val subject: SubjectId
) : Serializable
