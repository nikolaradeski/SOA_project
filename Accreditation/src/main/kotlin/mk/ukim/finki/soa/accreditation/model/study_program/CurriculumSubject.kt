package mk.ukim.finki.soa.accreditation.model.study_program

import jakarta.persistence.*
import mk.ukim.finki.soa.accreditation.model.*
import org.axonframework.commandhandling.CommandHandler
import org.axonframework.modelling.command.AggregateLifecycle

//@Entity
public class CurriculumSubject {
    @EmbeddedId
    @AttributeOverride(name = "value", column = Column(name = "id"))
    private lateinit var id: StudyProgramSubjectId

    @EmbeddedId
    @AttributeOverride(name = "value", column = Column(name = "study_program"))
    private lateinit var studyProgram: StudyProgramId

    @EmbeddedId
    @AttributeOverride(name = "value", column = Column(name = "subject"))
    private lateinit var subject: SubjectId

    private var mandatory: Boolean? = null

    private var semester: Int? = null

    @Column(name = "\"order\"")
    private var order: Float? = null

    private var subjectGroup: String? = null

    @Column(length = 5000)
    private var dependenciesOverride: String? = null

    //tuka fali subject
    constructor(event: StudyProgramSubjectCreatedEvent) {
        this.id = event.studyProgramSubjectId
        this.studyProgram = event.studyProgramId
        this.mandatory = event.mandatory
        this.semester = event.semester
        this.order = event.order
        this.subjectGroup = event.subjectGroup
        this.dependenciesOverride = event.dependenciesOverride
    }



    @CommandHandler
    fun handle(command: UpdateStudyProgramSubjectMandatoryCommand) {
        val event = StudyProgramSubjectMandatoryUpdatedEvent(command)
        this.on(event)
        AggregateLifecycle.apply(event)
    }

    fun on(event: StudyProgramSubjectMandatoryUpdatedEvent) {
        this.mandatory = event.mandatory
    }

    @CommandHandler
    fun handle(command: UpdateStudyProgramSubjectSemesterCommand) {
        val event = StudyProgramSubjectSemesterUpdatedEvent(command)
        this.on(event)
        AggregateLifecycle.apply(event)
    }

    fun on(event: StudyProgramSubjectSemesterUpdatedEvent) {
        this.semester = event.semester
    }

    @CommandHandler
    fun handle(command: UpdateStudyProgramSubjectOrderCommand) {
        val event = StudyProgramSubjectOrderUpdatedEvent(command)
        this.on(event)
        AggregateLifecycle.apply(event)
    }

    fun on(event: StudyProgramSubjectOrderUpdatedEvent) {
        this.order = event.order
    }

    @CommandHandler
    fun handle(command: UpdateStudyProgramSubjectSubjectGroupCommand) {
        val event = StudyProgramSubjectSubjectGroupUpdatedEvent(command)
        this.on(event)
        AggregateLifecycle.apply(event)
    }

    fun on(event: StudyProgramSubjectSubjectGroupUpdatedEvent) {
        this.subjectGroup = event.subjectGroup
    }

    @CommandHandler
    fun handle(command: UpdateStudyProgramSubjectDependenciesOverrideCommand) {
        val event = StudyProgramSubjectDependenciesOverrideUpdatedEvent(command)
        this.on(event)
        AggregateLifecycle.apply(event)
    }

    fun on(event: StudyProgramSubjectDependenciesOverrideUpdatedEvent) {
        this.dependenciesOverride = event.dependenciesOverride
    }
}