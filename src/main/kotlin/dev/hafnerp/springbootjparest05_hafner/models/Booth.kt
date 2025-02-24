package dev.hafnerp.springbootjparest05_hafner.models

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
    @Column
    lateinit var nr : String
    @Column
    var size : Int = 0
    @Column
    var big : Boolean = false
    @Column
    var top : Int = 0
    @Column
    var left : Int = 0
    @Column
    var width : Int = 0
    @Column
    var height : Int = 0
    @ManyToOne
    lateinit var participation : Participation;
}