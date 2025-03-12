package dev.hafnerp.springbootjparest05_hafner.models

import dev.hafnerp.springbootjparest05_hafner.services.MailService
import dev.hafnerp.springbootjparest05_hafner.services.ParticipationService
import dev.hafnerp.springbootjparest05_hafner.services.UserService

class CompanyDTO (
    id : Long = 0,
    name : String,
    eMail : String,
    pwdToken : String,
    mails: MutableList<MailDTO> = mutableListOf(),
    type: String = "company",
    responsible: String,
    phone: String,
    ccTo: String,
    comments: String,
    participations: MutableList<Long> = mutableListOf()
) : UserDTO(id, name, eMail, pwdToken, mails, type, responsible, phone, ccTo, comments, participations) {
    companion object {
        @JvmStatic
        fun getCompanyObject(
            userRepository: UserService,
            mailRepository: MailService,
            participationService: ParticipationService,
            companyDTO: CompanyDTO
        ) : Company {
            try {
                return userRepository.getUser(companyDTO.id) as Company
            }
            catch (ignore : Exception) { }

            val company = dev.hafnerp.springbootjparest05_hafner.models.Company(
                name = companyDTO.name,
                eMail = companyDTO.eMail,
                pwdToken = companyDTO.pwdToken,
                responsible = companyDTO.responsible ?: "",
                phone = companyDTO.phone ?: "",
                ccTo = companyDTO.ccTo ?: "",
                comments = companyDTO.comments ?: ""
            )

            company.id = companyDTO.id
            company.mails = companyDTO.mails.map { m -> mailRepository.getMail(m.id) }.toMutableList()
            company.participations = companyDTO.participations?.map { p -> participationService.getParticipation(p) }?.toMutableList() ?: mutableListOf()

            return company
        }

        @JvmStatic
        fun getInstance(admin: Company) : CompanyDTO {
            return CompanyDTO(
                id = admin.id,
                name = admin.name,
                eMail = admin.pwdToken,
                pwdToken = admin.pwdToken,
                responsible = admin.responsible,
                phone = admin.phone,
                ccTo = admin.ccTo,
                comments = admin.comments,
                mails = admin.mails.map { m -> MailDTO.getInstance(m) }.toMutableList(),
                participations = admin.participations.map { p -> p.id }.toMutableList()
            )
        }
    }
}