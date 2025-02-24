package dev.hafnerp.springbootjparest05_hafner.models

import jakarta.persistence.*
import lombok.*

@Entity
@Table(name = "participations")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Data
class Participation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id : Long = 0
    @Column
    var price : Double = 0.0
    @Column
    var paidUntilNow : Double = 0.0
    @Column
    lateinit var comments : String
    @Column
    var IF : Boolean = false
    @Column
    var IT : Boolean = false
    @Column
    var BT : Boolean = false
    @Column
    var IR : Boolean = false
    @Column
    var cntTables : Int = 0
    @Column
    var needsPower : Boolean = false
    @Column
    var ownBooth : Boolean = false
    @Column
    var needsSpace : Int = 0
    @ManyToOne
    lateinit var booth : Booth
    @ManyToOne
    lateinit var event : Event
}