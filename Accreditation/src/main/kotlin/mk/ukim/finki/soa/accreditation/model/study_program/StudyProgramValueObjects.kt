package mk.ukim.finki.soa.accreditation.model.study_program

import jakarta.persistence.Embeddable

@Embeddable
data class DurationYears(
        var durationYears: Int = 0
) {
    fun toSemesters(): Int {
        return durationYears * 2
    }
}

@Embeddable
class Order(var order: Int = 1){




}