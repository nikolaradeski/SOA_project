package mk.ukim.finki.soa.accreditation.model.study_program

import jakarta.persistence.*
import mk.ukim.finki.soa.accreditation.model.StudyProgramSubjectId
import mk.ukim.finki.soa.accreditation.model.subject.Subject

@Entity
internal class StudyProgramSubject {
    @EmbeddedId
    @AttributeOverride(name = "value", column = Column(name = "id"))
    private lateinit var id: StudyProgramSubjectId

    //@ManyToOne
    //private val subject: Subject? = null

    @ManyToOne
    private val studyProgram: StudyProgram? = null

    private val mandatory: Boolean? = null

    private val semester: Int? = null

    @Column(name = "\"order\"")
    private var order: Float? = null

    private val subjectGroup: String? = null

    @Column(length = 5000)
    private var dependenciesOverride: String? = null

}