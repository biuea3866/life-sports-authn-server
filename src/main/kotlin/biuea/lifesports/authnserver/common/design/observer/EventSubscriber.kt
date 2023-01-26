package biuea.lifesports.authnserver.common.design.observer

interface EventSubscriber<T> {
    fun subscribe(event: T)
    fun getItem(): T
}