package biuea.lifesports.authnserver

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class AuthnServerApplication

fun main(args: Array<String>) {
	runApplication<AuthnServerApplication>(*args)
}
