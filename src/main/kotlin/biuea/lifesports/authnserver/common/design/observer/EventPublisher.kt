package biuea.lifesports.authnserver.common.design.observer

interface EventPublisher<T> {
    fun publish(event: T)

    fun add(eventSubscriber: EventSubscriber<T>): Boolean

    fun delete(eventSubscriber: EventSubscriber<T>): Boolean
}