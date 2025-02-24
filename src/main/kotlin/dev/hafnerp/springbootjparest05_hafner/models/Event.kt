package dev.hafnerp.springbootjparest05_hafner.models

import jakarta.persistence.*
import lombok.*
import java.time.LocalDate

@Entity
@Table(name = "events")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Data
class Event {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id : Long = 0

    @Column
    lateinit var _when : LocalDate
    @Column
    lateinit var label : String
    @Column
    var boothPrice : Double = 0.0
    @Column
    var bigBoothPrice : Double = 0.0
    @OneToMany(mappedBy = "event", orphanRemoval = true, cascade = arrayOf(CascadeType.ALL))
    lateinit var participations : ArrayList<Participation>
}