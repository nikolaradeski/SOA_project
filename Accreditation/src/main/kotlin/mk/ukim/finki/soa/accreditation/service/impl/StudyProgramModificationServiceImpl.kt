package mk.ukim.finki.soa.accreditation.service.impl

import mk.ukim.finki.soa.accreditation.model.CreateStudyProgramCommand
import mk.ukim.finki.soa.accreditation.model.StudyProgramId
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

}