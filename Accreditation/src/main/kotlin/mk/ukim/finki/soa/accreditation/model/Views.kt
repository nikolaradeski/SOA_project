package mk.ukim.finki.soa.accreditation.model

import jakarta.persistence.AttributeOverride
import jakarta.persistence.Column
import jakarta.persistence.EmbeddedId
import jakarta.persistence.Entity
import jakarta.persistence.Table

import jakarta.persistence.*
import mk.ukim.finki.soa.accreditation.model.generalEnums.StudyCycle
import mk.ukim.finki.soa.accreditation.model.study_program.CurriculumSubject
import org.hibernate.annotations.Immutable

//@Entity
//@Table(name = "study_program")
//@Immutable
//class StudyProgramView(
//
//        @EmbeddedId
//        @AttributeOverride(
//                name = "value",
//                column = Column(name = "code")
//        )
//        val code: StudyProgramId,
//
//        @Column(nullable = false)
//        val name: String,
//
//        @Column(nullable = false)
//        val nameEn: String,
//
//        @Column(nullable = false)
//        val duration_years: Int,
//
//        @Column(nullable = false)
//        val inEnglish: Boolean,
//
//        @Column(nullable = false)
//        val bilingual: Boolean,
//
//        @Enumerated(EnumType.STRING)
//        val studyCycle: StudyCycle
//)

@Entity
@Table(name = "study_program")
@Immutable
class StudyProgramView(

        @EmbeddedId
        @AttributeOverride(name = "value", column = Column(name = "code"))
        val code: StudyProgramId,

        @Column(nullable = false)
        val name: String,

        @Column(nullable = false)
        val nameEn: String,

        @Column(name = "duration_years", nullable = false)
        val duration_years: Int,

        @Column(nullable = false)
        val inEnglish: Boolean,

        @Column(nullable = false)
        val bilingual: Boolean,

        @Enumerated(EnumType.STRING)
        @Column(nullable = false)
        val studyCycle: StudyCycle,

        // Unidirectional OneToMany:
        // Joins study_program.code -> curriculum_subject.study_program
        @OneToMany(fetch = FetchType.EAGER)
        @JoinColumn(
                name = "study_program",              // column on curriculum_subject
                referencedColumnName = "code",       // column on study_program
                insertable = false,
                updatable = false
        )
        val allSubjects: List<CurriculumSubject> = emptyList()
) {
        val sortedSubjects: List<CurriculumSubject>
                get() = allSubjects.sortedWith(
                        compareBy<CurriculumSubject> { it.semester ?: Int.MAX_VALUE }
                                .thenBy { if (it.mandatory) 0 else 1 }          // mandatory first
                                .thenBy { it.order ?: Float.MAX_VALUE }         // order asc
                                .thenBy { it.subject.toString() }               // stable tie-breaker
                )
}


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
