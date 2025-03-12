package dev.hafnerp.springbootjparest05_hafner.models

import dev.hafnerp.springbootjparest05_hafner.services.MailService
import dev.hafnerp.springbootjparest05_hafner.services.UserService

class AdminDTO (
    id : Long,
    name : String,
    eMail : String,
    pwdToken : String,
    mails: MutableList<MailDTO> = mutableListOf(),
    type: String =  "admin"
) : UserDTO(id = id, name = name, eMail = eMail, pwdToken = pwdToken, mails = mails, type, null, null, null, null, null) {
    companion object {
        @JvmStatic
        fun getAdminObject(
            userRepository: UserService,
            mailRepository: MailService,
            adminDTO: AdminDTO
        ) : Admin {
            try {
                return userRepository.getUser(adminDTO.id) as Admin
            }
            catch (ignore : Exception) { }

            val admin = dev.hafnerp.springbootjparest05_hafner.models.Admin(
                name = adminDTO.name,
                eMail = adminDTO.eMail,
                pwdToken = adminDTO.pwdToken
            )

            admin.id = adminDTO.id
            admin.mails = adminDTO.mails.map { m -> mailRepository.getMail(m.id) }.toMutableList()

            return admin
        }

        @JvmStatic
        fun getInstance(admin: Admin) : AdminDTO {
            return AdminDTO(
                id = admin.id,
                name = admin.name,
                eMail = admin.eMail,
                pwdToken = admin.pwdToken,
                mails = admin.mails.map { m -> MailDTO.getInstance(m) }.toMutableList()
            )
        }
    }
}