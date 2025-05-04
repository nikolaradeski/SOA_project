package mk.ukim.finki.soa.accreditation.repository

import mk.ukim.finki.soa.accreditation.model.StudyProgramId
import mk.ukim.finki.soa.accreditation.model.StudyProgramView
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface StudyProgramViewJpaRepository: JpaRepository<StudyProgramView, StudyProgramId> {
}