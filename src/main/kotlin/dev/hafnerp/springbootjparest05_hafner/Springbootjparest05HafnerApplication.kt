package dev.hafnerp.springbootjparest05_hafner

import dev.hafnerp.springbootjparest05_hafner.models.*
import dev.hafnerp.springbootjparest05_hafner.repositories.BoothRepository
import dev.hafnerp.springbootjparest05_hafner.repositories.EventRepository
import dev.hafnerp.springbootjparest05_hafner.repositories.MailRepository
import dev.hafnerp.springbootjparest05_hafner.repositories.ParticipationRepository
import dev.hafnerp.springbootjparest05_hafner.repositories.UserRepository
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.stereotype.Component
import java.time.LocalDateTime

@SpringBootApplication
class Springbootjparest05HafnerApplication

fun main(args: Array<String>) {
	runApplication<Springbootjparest05HafnerApplication>(*args)
}

@Component
class DemoDataRunner(
	private val participationRepository: ParticipationRepository,
	private val eventRepository: EventRepository,
	private val boothRepository: BoothRepository,
	private val mailRepository: MailRepository,
	private val userRepository: UserRepository
) : CommandLineRunner {
	override fun run(vararg args: String?) {
		val admin1 = Admin(
			name = "Charly Eder",
			eMail = "mail@example.com",
			pwdToken = "I_AM_A_TOKEN"
		)

		val savedAdmin = userRepository.save(admin1)

		val company1 = Company(
			name = "New Company",
			eMail = "info@company.com",
			pwdToken = "I_AM_ANOTHER_TOKEN",
			responsible = "Dr. String",
			phone = "00000",
			ccTo = "newMail@company.com",
			comments = "Nothing to say"
		)

		val savedCompany1 = userRepository.save(company1)

		val booth1 = Booth(
			nr = "AAAA",
			size = 20,
			big = false,
			top = 2,
			left = 5,
			width = 10,
			height = 12
		)

		val booth2 = Booth(
			nr = "BBBB",
			size = 15,
			big = false,
			top = 2,
			left = 3,
			width = 9,
			height = 8
		)

		val savedBooth1 = boothRepository.save(booth1)
		val savedBooth2 = boothRepository.save(booth2)

		val participation1 = Participation(
			price = 10.00,
			paidUntilNow = 0.0,
			comments = "This is a nice company!",
			IF = false,
			IT = false,
			BT = false,
			IR = false,
			cntTables = 2,
			needsPower = false,
			needsSpace = 20
		)

		participation1.booth = savedBooth1
		participation1.company = company1

		val participation2 = Participation(
			price = 10.00,
			paidUntilNow = 0.0,
			comments = "Another nice company that is not Tesla!",
			IF = false,
			IT = false,
			BT = false,
			IR = false,
			cntTables = 2,
			needsPower = false,
			needsSpace = 20
		)

		participation2.booth = savedBooth2
		participation2.company = company1

		val savedParticipation1 = participationRepository.save(participation1)
		val savedParticipation2 = participationRepository.save(participation2)

		val mail1 = Mail(
			whenSent = LocalDateTime.now(),
			subject = "Nothing!",
			content = "I am the content",
			sender = admin1
		)

		mail1.receivers.add(savedCompany1)

		val savedMail1 = mailRepository.save(mail1)
	}
}