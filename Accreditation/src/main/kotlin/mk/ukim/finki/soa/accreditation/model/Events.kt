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


abstract class StudyProgramEvent(
    open val studyProgramId: StudyProgramId
) : AbstractEvent(studyProgramId)

data class StudyProgramCreatedEvent(
    override val studyProgramId: StudyProgramId
): StudyProgramEvent(studyProgramId){
    constructor(command: CreateStudyProgramCommand) : this(
        studyProgramId = StudyProgramId()
    )
}