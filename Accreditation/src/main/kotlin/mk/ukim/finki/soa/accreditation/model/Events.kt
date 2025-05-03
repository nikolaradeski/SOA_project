package mk.ukim.finki.soa.accreditation.model

abstract class AbstractEvent(open val identifier: Identifier<out Any>) {

}

abstract class StudyProgramEvent(
        open val studyProgramId: StudyProgramId
) : AbstractEvent(studyProgramId)

data class StudyProgramCreatedEvent(
        override val studyProgramId: StudyProgramId,
        val name: String
): StudyProgramEvent(studyProgramId){
    constructor(command: CreateStudyProgramCommand) : this(
            studyProgramId = StudyProgramId(),
            name = command.name
    )
}
