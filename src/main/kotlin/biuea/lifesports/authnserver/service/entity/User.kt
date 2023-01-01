package biuea.lifesports.authnserver.service.entity

import java.io.Serializable
import java.time.ZonedDateTime
import javax.persistence.*

@Entity
@Table(name = "Users")
class User(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0L,

    var email: String,

    var password: String,

    var name: String? = null,

    var phone: String? = null,

    @Column(name = "created_date", nullable = false)
    var createdDate: ZonedDateTime,

    @Column(name = "updated_date")
    var updatedDate: ZonedDateTime? = null
): Serializable {
}