package mk.ukim.finki.soa.accreditation.service

import mk.ukim.finki.soa.accreditation.model.CreateStudyProgramCommand
import mk.ukim.finki.soa.accreditation.model.StudyProgramId
import java.util.concurrent.CompletableFuture

interface StudyProgramModificationService {
    fun createStudyProgram(command: CreateStudyProgramCommand): CompletableFuture<StudyProgramId>
}