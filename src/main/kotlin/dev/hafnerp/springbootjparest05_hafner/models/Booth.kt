package dev.hafnerp.springbootjparest05_hafner.models

import com.fasterxml.jackson.annotation.JsonManagedReference
import jakarta.persistence.*
import lombok.*

@Entity
@Table(name = "booths")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Data
class Booth {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id : Long = 0
    @Column(name = "nrColumn")
    lateinit var nr : String
    @Column(name = "sizeColumn")
    var size : Int = 0
    @Column(name = "bigColumn")
    var big : Boolean = false
    @Column(name = "topColumn")
    var top : Int = 0
    @Column(name = "leftColumn")
    var left : Int = 0
    @Column(name = "widthColumn")
    var width : Int = 0
    @Column(name = "HeightColumn")
    var height : Int = 0
    @OneToMany(cascade = [CascadeType.ALL], mappedBy = "booth")
    @JsonManagedReference
    var participations: MutableList<Participation> = mutableListOf()

    constructor(nr: String, size: Int, big: Boolean, top: Int, left: Int, width: Int, height: Int) {
        this.nr = nr
        this.size = size
        this.big = big
        this.top = top
        this.left = left
        this.width = width
        this.height = height
    }
}