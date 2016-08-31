package domain;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PrintHouse {

    @Id
    @GeneratedValue
    @Setter
    @Getter
    private long printHouseID;

    @Setter
    @Getter
    private String name;
}
