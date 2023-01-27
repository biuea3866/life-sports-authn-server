package biuea.lifesports.authnserver.domain.user.entity

import javax.persistence.*

@Entity
@Table(name = "OpenAPIToken")
class OpenAPIKey(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0L,

    val token: String
) {
}