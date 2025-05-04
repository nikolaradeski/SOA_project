package mk.ukim.finki.soa.accreditation.service.impl

import mk.ukim.finki.soa.accreditation.model.StudyProgramId
import mk.ukim.finki.soa.accreditation.model.StudyProgramView
import mk.ukim.finki.soa.accreditation.repository.StudyProgramViewJpaRepository
import mk.ukim.finki.soa.accreditation.service.StudyProgramViewReadService
import org.springframework.stereotype.Service

@Service
class StudyProgramViewReadServiceImpl (val jpaRepository: StudyProgramViewJpaRepository): StudyProgramViewReadService{
    override fun findById(studyProgramId: StudyProgramId): StudyProgramView {
        return jpaRepository.findById(studyProgramId).orElseThrow()
    }

    override fun findAll(): List<StudyProgramView> {
        return jpaRepository.findAll()
    }

}