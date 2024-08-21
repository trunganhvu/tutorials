/**
 * Copyright 2024
 * Name: Person
 */
package com.anhvt.springbootannotation.lombok.Accessors;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * Accessors as extensions of Getter + Setter
 * fluent = true: remove prefix getName() -> name(). Otherwise fluent = false isn't remove
 * chain = true: setter return this instead of void
 * prefix = {"s"}: remove prefix in variables name. EX: sName -> Name
 *
 * @author trunganhvu
 * @date 8/9/2024
 */
@Getter
@Setter
@Accessors(fluent = true, chain = true, prefix = {"s", ""})
public class Person {
    private String name;
    private int age;
    private String sAddress;
}
