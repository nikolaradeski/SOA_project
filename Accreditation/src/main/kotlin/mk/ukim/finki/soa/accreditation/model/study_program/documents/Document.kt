package mk.ukim.finki.soa.accreditation.model.study_program.documents

import jakarta.persistence.*
import mk.ukim.finki.soa.accreditation.model.DocumentId
import mk.ukim.finki.soa.accreditation.model.study_program.StudyProgram
import java.time.LocalDateTime

@Entity
internal class Document {
    @EmbeddedId
    @AttributeOverride(name = "value", column = Column(name = "id"))
    private lateinit var id: DocumentId

    private lateinit var name: String

    @Enumerated(EnumType.STRING)
    private val type: DocumentTypes? = null

    @ManyToOne
    private val studyProgram: StudyProgram? = null

    private val document: ByteArray? = null

    private val creationDate:LocalDateTime = LocalDateTime.now()

}