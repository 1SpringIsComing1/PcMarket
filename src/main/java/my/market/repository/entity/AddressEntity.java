package my.market.repository.entity;

import lombok.*;
import my.market.shared.UserDto;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table
@Entity(name = "addreses")
@Builder
public class AddressEntity implements Serializable {
    private static final long serialVersionUID = -5494728163986336988L;
    @Id
    @GeneratedValue
    private long id;
    @Column(nullable = false)
    private String addressId;
    @Column(length = 15,nullable = false)
    private String city;
    @Column(length = 15,nullable = false)
    private String postalCode;

    @Column(length = 100,nullable = false)
    private String streetName;

    @Column(length = 15,nullable = false)
    private String country;

    @Column(length = 10,nullable = false)
    private String type;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity userDetails;


}
