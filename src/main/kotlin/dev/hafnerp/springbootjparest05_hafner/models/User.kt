package dev.hafnerp.springbootjparest05_hafner.models

import jakarta.persistence.*
import lombok.*

@Entity
@Table(name = "users")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "user_type", discriminatorType = DiscriminatorType.STRING)
@DiscriminatorValue("USER")
@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Data
class User {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id : Long = 0
    @Column(name = "I_AM_THE_NAME", unique = true)
    lateinit var name : String
    @Column
    lateinit var eMail : String
    @Column
    lateinit var pwdToken : String
}

@Entity
@DiscriminatorValue("ADMIN")
class Admin : User() {}

@Entity
@DiscriminatorValue("COMPANY")
class Company : User() {
    @Column
    lateinit var responsible : String
    @Column
    lateinit var phone : String
    @Column
    lateinit var ccTo : String
    @Column
    lateinit var comments : String
}