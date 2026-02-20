package mk.ukim.finki.soa.accreditation.service

import mk.ukim.finki.soa.accreditation.model.*
import org.axonframework.commandhandling.gateway.CommandGateway
import org.springframework.stereotype.Service
import java.util.concurrent.CompletableFuture

@Service
class SubjectModificationService(
    private val commandGateway: CommandGateway
) {
    fun createSubject(command: CreateSubjectCommand): CompletableFuture<Any> =
        commandGateway.send(command)

    fun updateSubjectName(command: UpdateSubjectNameCommand) = commandGateway.send<Any>(command)
    fun updateSubjectAbbreviation(command: UpdateSubjectAbbreviationCommand) = commandGateway.send<Any>(command)
    fun updateSubjectSemester(command: UpdateSubjectSemesterCommand) = commandGateway.send<Any>(command)
    fun updateWeeklyLectures(command: UpdateSubjectWeeklyLecturesClassesCommand) = commandGateway.send<Any>(command)
    fun updateWeeklyAuditorium(command: UpdateSubjectWeeklyAuditoriumClassesCommand) = commandGateway.send<Any>(command)
    fun updateWeeklyLab(command: UpdateSubjectWeeklyLabClassesCommand) = commandGateway.send<Any>(command)
    fun updatePlaceholder(command: UpdateSubjectPlaceholderCommand) = commandGateway.send<Any>(command)
    fun updateDefaultSemester(command: UpdateSubjectDefaultSemesterCommand) = commandGateway.send<Any>(command)
    fun updateCredits(command: UpdateSubjectCreditsCommand) = commandGateway.send<Any>(command)
    fun updateStudyCycle(command: UpdateSubjectStudyCycleCommand) = commandGateway.send<Any>(command)
    fun updateLanguage(command: UpdateSubjectLanguageCommand) = commandGateway.send<Any>(command)
    fun updateLearningMethods(command: UpdateSubjectLearningMethodsCommand) = commandGateway.send<Any>(command)
    fun updateGoalsDescription(command: UpdateSubjectGoalsDescriptionCommand) = commandGateway.send<Any>(command)
    fun updateContent(command: UpdateSubjectContentCommand) = commandGateway.send<Any>(command)
    fun updateGoalsDescriptionEn(command: UpdateSubjectGoalsDescriptionEnCommand) = commandGateway.send<Any>(command)
    fun updateContentEn(command: UpdateSubjectContentEnCommand) = commandGateway.send<Any>(command)
    fun updateQualityControl(command: UpdateSubjectQualityControlCommand) = commandGateway.send<Any>(command)
    fun updateAccreditation(command: UpdateSubjectAccreditationCommand) = commandGateway.send<Any>(command)
    fun updateObligationDuration(command: UpdateSubjectObligationDurationCommand) = commandGateway.send<Any>(command)
    fun updateDependencies(command: UpdateSubjectDependenciesCommand) = commandGateway.send<Any>(command)
    fun updateGrading(command: UpdateSubjectGradingCommand) = commandGateway.send<Any>(command)
    fun updateBibliography(command: UpdateSubjectBibliographyCommand) = commandGateway.send<Any>(command)
    fun updateNotes(command: UpdateSubjectNotesCommand) = commandGateway.send<Any>(command)
}

