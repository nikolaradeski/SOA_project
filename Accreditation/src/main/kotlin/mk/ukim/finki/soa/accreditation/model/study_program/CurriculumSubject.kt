package mk.ukim.finki.soa.accreditation.model.study_program

import jakarta.persistence.*
import mk.ukim.finki.soa.accreditation.model.CurriculumSubjectId
import mk.ukim.finki.soa.accreditation.model.SubjectId
import mk.ukim.finki.soa.accreditation.model.StudyProgramId
import org.hibernate.annotations.Immutable

@Entity
@Table(name = "curriculum_subject")
class CurriculumSubject(

    @EmbeddedId
    var id: CurriculumSubjectId,

    @Column(nullable = false)
    var mandatory: Boolean = false,

    var semester: Int? = null,

    @Column(name = "\"order\"")
    var order: Float? = null,

    var subjectGroup: String? = null,

    @Column(length = 5000)
    var dependenciesOverride: String? = null
) {
    protected constructor() : this(
        id = CurriculumSubjectId(StudyProgramId(""), SubjectId("")),
        mandatory = false
    )

    val studyProgram: StudyProgramId get() = id.studyProgram
    val subject: SubjectId get() = id.subject
}