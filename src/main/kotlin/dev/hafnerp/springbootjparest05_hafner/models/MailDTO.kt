package dev.hafnerp.springbootjparest05_hafner.models

import dev.hafnerp.springbootjparest05_hafner.services.MailService
import dev.hafnerp.springbootjparest05_hafner.services.UserService
import java.time.LocalDateTime

class MailDTO(
    var id: Long,
    var whenSent: LocalDateTime?,
    var subject: String,
    var content: String,
    var sender: Long?,
    var receivers: MutableList<Long>
) {
    companion object {
        @JvmStatic
        fun getMailObject(
            mailRepository: MailService,
            userRepository: UserService,
            mailDTO: MailDTO
        ) : Mail {
            try {
                return mailRepository.getMail(mailDTO.id)
            }
            catch (ignore : Exception) { }

            return Mail(
                id = 0,
                whenSent = mailDTO.whenSent,
                subject = mailDTO.subject,
                content = mailDTO.content,
                sender = if (mailDTO.sender != null) userRepository.getUser(mailDTO.sender!!) else null,
                receivers = mailDTO.receivers.map { r -> userRepository.getUser(r) as Company }.toMutableList()
            )
        }

        @JvmStatic
        fun getInstance(mail : Mail) : MailDTO {
            return MailDTO(
                id = mail.id,
                whenSent = mail.whenSent,
                subject = mail.subject,
                content = mail.content,
                sender = mail.sender?.id,
                receivers = mail.receivers.map { rec -> rec.id }.toMutableList()
            )
        }
    }
}