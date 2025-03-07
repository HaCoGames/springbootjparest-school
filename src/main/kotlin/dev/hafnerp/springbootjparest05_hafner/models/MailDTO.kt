package dev.hafnerp.springbootjparest05_hafner.models

import dev.hafnerp.springbootjparest05_hafner.services.UserServiceImpl
import jakarta.persistence.Column
import jakarta.persistence.Table
import java.time.LocalDateTime

@Table(name = "mails")
class MailDTO {
    @Column
    var whenSent: LocalDateTime? = null

    @Column
    lateinit var subject: String
    @Column
    lateinit var content: String

    @Column(name = "sender_id")
    var sender_id: Long? = null
    @Column(name = "receiver_id")
    var receiver_id: Long? = null

    // Pass UserServiceImpl as a parameter
    public fun getMailObject(userService: UserServiceImpl): Mail {
        val _sender = userService.getUser(sender_id)
        val _receivers:Company = userService.getUser(receiver_id) as Company

        println(_sender)
        println(_receivers)

        return Mail(
            whenSent = whenSent ?: LocalDateTime.now(),  // Ensure it's not null
            subject = subject,
            content = content,
            sender = _sender,
            receivers = mutableListOf(_receivers)
        )
    }
}