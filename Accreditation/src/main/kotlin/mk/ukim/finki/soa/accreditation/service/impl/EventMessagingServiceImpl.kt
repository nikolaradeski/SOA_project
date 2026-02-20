package mk.ukim.finki.soa.accreditation.service.impl

import mk.ukim.finki.soa.accreditation.repository.EventMessagingRepository
import mk.ukim.finki.soa.accreditation.service.EventMessagingService
import org.springframework.stereotype.Service

@Service
class EventMessagingServiceImpl(val eventMessagingRepository: EventMessagingRepository) : EventMessagingService {
    override fun send(topic: String, key: String, payload: String) {
        eventMessagingRepository.send(topic, key, payload)
    }
}
