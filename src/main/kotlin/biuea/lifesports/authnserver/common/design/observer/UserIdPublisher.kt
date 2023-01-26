package biuea.lifesports.authnserver.common.design.observer

class UserIdPublisher: EventPublisher<Long> {
    private val observers = mutableListOf<EventSubscriber<Long>>()
    private var userId: Long = 0

    override fun publish(userId: Long) {
        this.userId = userId
        this.observers.forEach { it.subscribe(event = userId) }
    }

    override fun add(eventSubscriber: EventSubscriber<Long>) = observers.add(eventSubscriber)

    override fun delete(eventSubscriber: EventSubscriber<Long>) = observers.remove(eventSubscriber)
}