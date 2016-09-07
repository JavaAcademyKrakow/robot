package domain;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Representation of {@link PrintHouse} in database
 * It contains PrintHouse name which can be common for different Books
 * Do not use CascadeType or fetchType.Eager due to performance issue
 */
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

    @Override
    public String toString () {
        return new StringBuilder("PrintHouse{name=")
                .append(name)
                .append("}").toString();
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }

    @Override
    public boolean equals(Object printObj) {
        if (this == printObj)
            return true;
        if (printObj == null || getClass() != printObj.getClass())
            return false;
        PrintHouse ph = (PrintHouse)printObj;
        return name.equals(ph.name);
    }
}
