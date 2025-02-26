package dev.hafnerp.springbootjparest05_hafner.models

import com.fasterxml.jackson.annotation.JsonBackReference
import com.fasterxml.jackson.annotation.JsonManagedReference
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
    @Column(name = "IfColumn")
    var IF : Boolean = false
    @Column(name = "ItColumn")
    var IT : Boolean = false
    @Column(name = "BTColumn")
    var BT : Boolean = false
    @Column(name = "IRColumn")
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
    @JoinColumn(name = "booth_id")
    @JsonBackReference
    lateinit var booth : Booth
    @ManyToOne
    @JoinColumn(name = "event_id")
    @JsonBackReference
    lateinit var event : Event
    @ManyToOne
    @JoinColumn(name = "company_id")
    @JsonBackReference
    lateinit var company : Company

    constructor(
        price: Double,
        paidUntilNow: Double,
        comments: String,
        IF: Boolean,
        IT: Boolean,
        BT: Boolean,
        IR: Boolean,
        cntTables: Int,
        needsPower: Boolean,
        needsSpace: Int
    ) {
        this.price = price
        this.paidUntilNow = paidUntilNow
        this.comments = comments
        this.IF = IF
        this.IT = IT
        this.BT = BT
        this.IR = IR
        this.cntTables = cntTables
        this.needsPower = needsPower
        this.needsSpace = needsSpace
    }
}