package mk.ukim.finki.soa.accreditation.model.proffesorSnapShot


import jakarta.persistence.*
import mk.ukim.finki.soa.accreditation.model.ProfessorId


//Ovoj Entitet Treba da e ispolnet od drug servis i nie teorecki ne go cuvame vo baza.
@Entity
data class ProfessorInformation (
        @EmbeddedId
        @AttributeOverride(name = "value", column = Column(name = "professor_id"))

        var professorId: ProfessorId,
        var professorName: String) {

}