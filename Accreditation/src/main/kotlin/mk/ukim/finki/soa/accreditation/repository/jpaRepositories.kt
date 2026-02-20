package mk.ukim.finki.soa.accreditation.repository

import mk.ukim.finki.soa.accreditation.model.StudyProgramId
import mk.ukim.finki.soa.accreditation.model.StudyProgramView
import mk.ukim.finki.soa.accreditation.model.SubjectId
import mk.ukim.finki.soa.accreditation.model.SubjectView
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface StudyProgramViewJpaRepository: JpaRepository<StudyProgramView, StudyProgramId> {
}

@Repository
interface SubjectViewJpaRepository : JpaRepository<SubjectView, SubjectId>
