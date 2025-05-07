package mk.ukim.finki.soa.accreditation.service.impl

import mk.ukim.finki.soa.accreditation.model.*
import mk.ukim.finki.soa.accreditation.service.StudyProgramModificationService
import org.axonframework.commandhandling.gateway.CommandGateway
import org.springframework.stereotype.Service
import java.util.concurrent.CompletableFuture

@Service
class StudyProgramModificationServiceImpl(
        val commandGateway: CommandGateway
) : StudyProgramModificationService {
    override fun createStudyProgram(command: CreateStudyProgramCommand): CompletableFuture<StudyProgramId> {
        return commandGateway.send(command);
    }

    override fun updateName(command: UpdateStudyProgramNameCommand): CompletableFuture<Any> {
        return commandGateway.send(command)
    }

    override fun updateDurationYears(command: UpdateStudyProgramDurationYearsCommand): CompletableFuture<Any> {
        return commandGateway.send(command)
    }

    override fun updateOrder(command: UpdateStudyProgramOrderCommand): CompletableFuture<Any> {
        return commandGateway.send(command)
    }

    override fun updateGeneralInformation(command: UpdateStudyProgramGeneralInformationCommand): CompletableFuture<Any> {
        return commandGateway.send(command)
    }

    override fun updateGraduationTitle(command: UpdateStudyProgramGraduationTitleCommand): CompletableFuture<Any> {
        return commandGateway.send(command)
    }

    override fun updateSubjectRestrictions(command: UpdateStudyProgramSubjectRestrictionsCommand): CompletableFuture<Any> {
        return commandGateway.send(command)
    }

    override fun updateInEnglish(command: UpdateStudyProgramEnglishAvailabilityCommand): CompletableFuture<Any> {
        return commandGateway.send(command)
    }

    override fun updateStudyCycle(command: UpdateStudyProgramStudyCycleCommand): CompletableFuture<Any> {
        return commandGateway.send(command)
    }

    override fun updateBilingual(command: UpdateStudyProgramBilingualCommand): CompletableFuture<Any> {
        return commandGateway.send(command)
    }

    override fun updateCoordinator(command: UpdateStudyProgramCoordinatorCommand): CompletableFuture<Any> {
        return commandGateway.send(command)
    }

//-------------------------------------StudyProgramSubject-----------------------------------------------

    override fun createStudyProgramSubject(command: CreateStudyProgramSubjectCommand): CompletableFuture<StudyProgramSubjectId> {
        return commandGateway.send(command);
    }

    override fun updateMandatory(command: UpdateStudyProgramSubjectMandatoryCommand): CompletableFuture<Any> {
        return commandGateway.send(command);
    }

    override fun updateSemester(command: UpdateStudyProgramSubjectSemesterCommand): CompletableFuture<Any> {
        return commandGateway.send(command);
    }

    override fun updateOrder(command: UpdateStudyProgramSubjectOrderCommand): CompletableFuture<Any> {
        return commandGateway.send(command);
    }

    override fun updateSubjectGroup(command: UpdateStudyProgramSubjectSubjectGroupCommand): CompletableFuture<Any> {
        return commandGateway.send(command);
    }

    override fun updateDependenciesOverride(command: UpdateStudyProgramSubjectDependenciesOverrideCommand): CompletableFuture<Any> {
        return commandGateway.send(command);
    }

}

