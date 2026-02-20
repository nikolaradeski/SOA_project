package mk.ukim.finki.soa.accreditation.service

interface EventMessagingService {
    fun send(topic: String, key: String, payload: String)
}
