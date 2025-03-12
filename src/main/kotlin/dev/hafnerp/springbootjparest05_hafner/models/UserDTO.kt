package dev.hafnerp.springbootjparest05_hafner.models

open class UserDTO (
    val id : Long,
    val name : String,
    val eMail : String,
    val pwdToken : String,
    val mails : List<MailDTO> = mutableListOf(),
    val type : String = "company",
    var responsible: String?,
    var phone: String?,
    var ccTo: String?,
    var comments: String?,
    var participations: MutableList<Long>?
) {

}