package mk.ukim.finki.soa.accreditation.model

import jakarta.persistence.AttributeOverride
import jakarta.persistence.Column
import jakarta.persistence.EmbeddedId
import jakarta.persistence.Entity
import jakarta.persistence.Table
import org.hibernate.annotations.Immutable

@Entity
@Table(name = "study_program")
@Immutable
data class StudyProgramView(

        @EmbeddedId
        @AttributeOverride(name = "value", column = Column(name = "code"))
        val code: StudyProgramId,
        val name: String,
        val nameEn: String
)

@Entity
@Table(name = "subject")
@Immutable
data class SubjectView(
        @EmbeddedId
        @AttributeOverride(name = "value", column = Column(name = "code"))
        val code: SubjectId,

        val name: String,
        val abbreviation: String
)
