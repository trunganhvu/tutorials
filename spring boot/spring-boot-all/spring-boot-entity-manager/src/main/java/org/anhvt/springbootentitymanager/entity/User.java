package org.anhvt.springbootentitymanager.entity;

import jakarta.persistence.ColumnResult;
import jakarta.persistence.ConstructorResult;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.SqlResultSetMapping;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "orders")
@Data
@NoArgsConstructor
@AllArgsConstructor
@SqlResultSetMapping(name = "User-Count", classes = {
        @ConstructorResult(targetClass = Long.class, columns = {
                @ColumnResult(name = "id", type = Long.class)
        })
})
public class User {

    @Id
    private Long id;
    private String name;
}
