package dev.hafnerp.springbootjparest05_hafner.models

import com.fasterxml.jackson.annotation.JsonBackReference
import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "mails")
class Mail(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0,

    @Column
    var whenSent: LocalDateTime? = null,

    @Column
    var subject: String,

    @Column
    var content: String,

    @ManyToOne
    @JoinColumn(name = "sender_id")
    @JsonBackReference
    var sender: User? = null,

    @ManyToOne
    @JoinColumn(name = "receiver_id")
    @JsonBackReference
    var receiver: User? = null
)