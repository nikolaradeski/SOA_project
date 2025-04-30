package mk.ukim.finki.soa.accreditation.model

abstract class AbstractEvent(open val identifier: Identifier<out Any>) {

}

abstract class StudyProgramDetailsEvent(
        open val studyProgramDetailsId: StudyProgramDetailsId
) : AbstractEvent(studyProgramDetailsId)

data class StudyProgramDetailsCreatedEvent(
        override val studyProgramDetailsId: StudyProgramDetailsId,
        val studyProgramId: StudyProgramId
) : StudyProgramDetailsEvent(studyProgramDetailsId) {
    constructor(command: CreateStudyProgramDetailsCommand) : this(
            studyProgramDetailsId = StudyProgramDetailsId(),
            studyProgramId = command.studyProgramId
    )
}
