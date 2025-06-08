package mk.ukim.finki.soa.accreditation.repository

interface EventMessagingRepository {
    fun send(topic: String, key: String, payload: String)
}
