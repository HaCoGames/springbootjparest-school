package dev.hafnerp.springbootjparest05_hafner.models

import com.fasterxml.jackson.annotation.JsonManagedReference
import jakarta.persistence.*

@Entity
@Table(name = "users")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "user_type", discriminatorType = DiscriminatorType.STRING)
@DiscriminatorValue("USER")
class User() {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id : Long = 0
    @Column(name = "I_AM_THE_NAME", unique = true)
    lateinit var name : String
    @Column
    lateinit var eMail : String
    @Column
    lateinit var pwdToken : String

    constructor(pwdToken: String, eMail: String, name: String) : this() {
        this.pwdToken = pwdToken
        this.eMail = eMail
        this.name = name
    }
}

@Entity
@DiscriminatorValue("ADMIN")
class Admin(
    pwdToken: String,
    eMail: String,
    name: String
) : User(pwdToken, eMail, name) {

    @OneToMany(cascade = [CascadeType.ALL], mappedBy = "sender")
    var mails: MutableList<Mail> = mutableListOf()
}

@Entity
@DiscriminatorValue("COMPANY")
class Company(
    pwdToken: String,
    eMail: String,
    name: String,
    @Column
    var responsible: String,
    @Column
    var phone: String,
    @Column
    var ccTo: String,
    @Column
    var comments: String
) : User(pwdToken, eMail, name) {

    @OneToMany(cascade = [CascadeType.ALL], mappedBy = "receiver")
    @JsonManagedReference
    var mails: MutableList<Mail> = mutableListOf()

    @OneToMany(cascade = [CascadeType.ALL], mappedBy = "company")
    @JsonManagedReference
    var participations: MutableList<Participation> = mutableListOf()
}