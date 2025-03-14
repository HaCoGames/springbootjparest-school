package dev.hafnerp.springbootjparest05_hafner.models

import com.fasterxml.jackson.annotation.JsonManagedReference
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
class Event(
    @Column
    var _when: LocalDate,
    @Column
    var label: String,
    @Column var boothPrice: Double,
    @Column var bigBoothPrice: Double,
    @OneToMany(cascade = [CascadeType.ALL], mappedBy = "event")
    @JsonManagedReference var participations: MutableList<Participation>
) {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id : Long = 0

}