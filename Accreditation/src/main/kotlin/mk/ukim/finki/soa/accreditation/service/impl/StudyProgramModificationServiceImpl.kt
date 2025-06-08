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

    override fun updateStudyProgramName(command: UpdateStudyProgramNameCommand): CompletableFuture<Any> {
        return commandGateway.send(command)
    }

    override fun updateStudyProgramDurationYears(command: UpdateStudyProgramDurationYearsCommand): CompletableFuture<Any> {
        return commandGateway.send(command)
    }

    override fun updateStudyProgramOrder(command: UpdateStudyProgramOrderCommand): CompletableFuture<Any> {
        return commandGateway.send(command)
    }

    override fun updateStudyProgramGeneralInformation(command: UpdateStudyProgramGeneralInformationCommand): CompletableFuture<Any> {
        return commandGateway.send(command)
    }

    override fun updateStudyProgramGraduationTitle(command: UpdateStudyProgramGraduationTitleCommand): CompletableFuture<Any> {
        return commandGateway.send(command)
    }

    override fun updateStudyProgramSubjectRestrictions(command: UpdateStudyProgramSubjectRestrictionsCommand): CompletableFuture<Any> {
        return commandGateway.send(command)
    }

    override fun updateStudyProgramInEnglish(command: UpdateStudyProgramEnglishAvailabilityCommand): CompletableFuture<Any> {
        return commandGateway.send(command)
    }

    override fun updateStudyProgramStudyCycle(command: UpdateStudyProgramStudyCycleCommand): CompletableFuture<Any> {
        return commandGateway.send(command)
    }

    override fun updateStudyProgramBilingual(command: UpdateStudyProgramBilingualCommand): CompletableFuture<Any> {
        return commandGateway.send(command)
    }

    override fun updateStudyProgramCoordinator(command: UpdateStudyProgramCoordinatorCommand): CompletableFuture<Any> {
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

