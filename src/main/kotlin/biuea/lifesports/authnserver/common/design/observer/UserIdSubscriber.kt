package biuea.lifesports.authnserver.common.design.observer

class UserIdSubscriber: EventSubscriber<Long> {
    var userId: Long = 0

    override fun subscribe(userId: Long) {
        this.userId = userId
    }

    override fun getItem(): Long {
        return this.userId
    }
}