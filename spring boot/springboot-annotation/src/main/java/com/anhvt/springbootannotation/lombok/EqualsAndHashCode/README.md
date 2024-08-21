# @EqualsAndHashCode
Annotation triển khai phương thực hashCode và equals cho đối tượng 

Các param:
* **exclude**: exclude = {""} - ngoại trừ field chỉ định, không sử dụng cùng onlyExplicitlyIncluded
* **doNotUseGetters**: doNotUseGetters = true - Trong hashCode()/equals() không sử dụng getter mà sử dụng this.field
* **of**: of = {"field", ""field"} - chỉ định fields được dùng trong hashCode()/equals()
* **callSuper**: callSuper = true - Chỉ định sử dụng hashCode()/equals() của parent class
* **cacheStrategy**:  cacheStrategy = EqualsAndHashCode.CacheStrategy.LAZY - sử dụng cache là giảm độ trễ và tải bằng cách lưu trữ tạm thời.
* **onParam**: onParam = @__({@NonNull}) - bổ sung any annotation vào param của hashCode()/equals()
* **onlyExplicitlyIncluded**: onlyExplicitlyIncluded = true - Chỉ đỉnh các fields sử dụng trong hashCode()/equals() được đánh dấu bằng @EqualsAndHashCode.Include
* 
### Cách sử dụng onlyExplicitlyIncluded
```java
@Setter
@Getter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Project {
    @EqualsAndHashCode.Include
    private String name;
    private String title;
    @EqualsAndHashCode.Include
    private int field1;
    @EqualsAndHashCode.Include
    private Date field2;
    private Instant field3;
}
```

Sau khi compile
```java
@Generated
public boolean equals(final Object o) {
    if (o == this) {
        return true;
    } else if (!(o instanceof Project)) {
        return false;
    } else {
        Project other = (Project)o;
        if (!other.canEqual(this)) {
            return false;
        } else if (this.getField1() != other.getField1()) {
            return false;
        } else {
            Object this$name = this.getName();
            Object other$name = other.getName();
            if (this$name == null) {
                if (other$name != null) {
                    return false;
                }
            } else if (!this$name.equals(other$name)) {
                return false;
            }

            Object this$field2 = this.getField2();
            Object other$field2 = other.getField2();
            if (this$field2 == null) {
                if (other$field2 != null) {
                    return false;
                }
            } else if (!this$field2.equals(other$field2)) {
                return false;
            }

            return true;
        }
    }
}

@Generated
protected boolean canEqual(final Object other) {
    return other instanceof Project;
}

@Generated
public int hashCode() {
    int PRIME = true;
    int result = 1;
    result = result * 59 + this.getField1();
    Object $name = this.getName();
    result = result * 59 + ($name == null ? 43 : $name.hashCode());
    Object $field2 = this.getField2();
    result = result * 59 + ($field2 == null ? 43 : $field2.hashCode());
    return result;
}
```

### Cách sử dụng exclude
```java
@Setter
@Getter
@EqualsAndHashCode(exclude = {"title", "field1", "field2"})
public class Project {
    private String name;
    private String title;
    private int field1;
    private Date field2;
    private Instant field3;
}
```

Sau khi compile
```java
@Generated
public boolean equals(final Object o) {
    if (o == this) {
        return true;
    } else if (!(o instanceof Project)) {
        return false;
    } else {
        Project other = (Project)o;
        if (!other.canEqual(this)) {
            return false;
        } else {
            Object this$name = this.getName();
            Object other$name = other.getName();
            if (this$name == null) {
                if (other$name != null) {
                    return false;
                }
            } else if (!this$name.equals(other$name)) {
                return false;
            }

            Object this$field3 = this.getField3();
            Object other$field3 = other.getField3();
            if (this$field3 == null) {
                if (other$field3 != null) {
                    return false;
                }
            } else if (!this$field3.equals(other$field3)) {
                return false;
            }

            return true;
        }
    }
}

@Generated
protected boolean canEqual(final Object other) {
    return other instanceof Project;
}

@Generated
public int hashCode() {
    int PRIME = true;
    int result = 1;
    Object $name = this.getName();
    result = result * 59 + ($name == null ? 43 : $name.hashCode());
    Object $field3 = this.getField3();
    result = result * 59 + ($field3 == null ? 43 : $field3.hashCode());
    return result;
}
```

### Cách dùng of
```java
@Setter
@Getter
@EqualsAndHashCode(of = {"title", "field1", "field2"})
public class Project {
    private String name;
    private String title;
    private int field1;
    private Date field2;
    private Instant field3;
}
```

Sau khi compile chỉ có title, field1, field2 được sử dụng
```java
@Generated
public boolean equals(final Object o) {
    if (o == this) {
        return true;
    } else if (!(o instanceof Project)) {
        return false;
    } else {
        Project other = (Project)o;
        if (!other.canEqual(this)) {
            return false;
        } else if (this.getField1() != other.getField1()) {
            return false;
        } else {
            Object this$title = this.getTitle();
            Object other$title = other.getTitle();
            if (this$title == null) {
                if (other$title != null) {
                    return false;
                }
            } else if (!this$title.equals(other$title)) {
                return false;
            }

            Object this$field2 = this.getField2();
            Object other$field2 = other.getField2();
            if (this$field2 == null) {
                if (other$field2 != null) {
                    return false;
                }
            } else if (!this$field2.equals(other$field2)) {
                return false;
            }

            return true;
        }
    }
}

@Generated
protected boolean canEqual(final Object other) {
    return other instanceof Project;
}

@Generated
public int hashCode() {
    int PRIME = true;
    int result = 1;
    result = result * 59 + this.getField1();
    Object $title = this.getTitle();
    result = result * 59 + ($title == null ? 43 : $title.hashCode());
    Object $field2 = this.getField2();
    result = result * 59 + ($field2 == null ? 43 : $field2.hashCode());
    return result;
}
```

### Cách sử dụng callSuper
```java
@Setter
@Getter
@EqualsAndHashCode(callSuper = true)
public class Project extends Product {
    private String name;
    private String title;
    private int field1;
    private Date field2;
    private Instant field3;
}
```

Sau khi compile hashCode()/equals() có call thêm phương thức hashCode()/equals() của super class
```java
@Generated
public boolean equals(final Object o) {
    if (o == this) {
        return true;
    } else if (!(o instanceof Project)) {
        return false;
    } else {
        Project other = (Project)o;
        if (!other.canEqual(this)) {
            return false;
        } else if (!super.equals(o)) {
            return false;
        } else if (this.getField1() != other.getField1()) {
            return false;
        } else {
            label64: {
                Object this$name = this.getName();
                Object other$name = other.getName();
                if (this$name == null) {
                    if (other$name == null) {
                        break label64;
                    }
                } else if (this$name.equals(other$name)) {
                    break label64;
                }

                return false;
            }

            label57: {
                Object this$title = this.getTitle();
                Object other$title = other.getTitle();
                if (this$title == null) {
                    if (other$title == null) {
                        break label57;
                    }
                } else if (this$title.equals(other$title)) {
                    break label57;
                }

                return false;
            }

            Object this$field2 = this.getField2();
            Object other$field2 = other.getField2();
            if (this$field2 == null) {
                if (other$field2 != null) {
                    return false;
                }
            } else if (!this$field2.equals(other$field2)) {
                return false;
            }

            Object this$field3 = this.getField3();
            Object other$field3 = other.getField3();
            if (this$field3 == null) {
                if (other$field3 != null) {
                    return false;
                }
            } else if (!this$field3.equals(other$field3)) {
                return false;
            }

            return true;
        }
    }
}

@Generated
protected boolean canEqual(final Object other) {
    return other instanceof Project;
}

@Generated
public int hashCode() {
    int PRIME = true;
    int result = super.hashCode();
    result = result * 59 + this.getField1();
    Object $name = this.getName();
    result = result * 59 + ($name == null ? 43 : $name.hashCode());
    Object $title = this.getTitle();
    result = result * 59 + ($title == null ? 43 : $title.hashCode());
    Object $field2 = this.getField2();
    result = result * 59 + ($field2 == null ? 43 : $field2.hashCode());
    Object $field3 = this.getField3();
    result = result * 59 + ($field3 == null ? 43 : $field3.hashCode());
    return result;
}
```

### Cách sử dụng cacheStrategy
```java
@Setter
@Getter
@EqualsAndHashCode(cacheStrategy = EqualsAndHashCode.CacheStrategy.LAZY)
public class Project {
    private String name;
    private String title;
    private int field1;
    private Date field2;
    private Instant field3;
}
```

Sau khi compile
```java
public class Project {
    @Generated
    private transient int $hashCodeCache;
    private String name;
    private String title;
    private int field1;
    private Date field2;
    private Instant field3;

    public Project() {
    }

    @Generated
    public void setName(final String name) {
        this.name = name;
    }

    @Generated
    public void setTitle(final String title) {
        this.title = title;
    }

    @Generated
    public void setField1(final int field1) {
        this.field1 = field1;
    }

    @Generated
    public void setField2(final Date field2) {
        this.field2 = field2;
    }

    @Generated
    public void setField3(final Instant field3) {
        this.field3 = field3;
    }

    @Generated
    public String getName() {
        return this.name;
    }

    @Generated
    public String getTitle() {
        return this.title;
    }

    @Generated
    public int getField1() {
        return this.field1;
    }

    @Generated
    public Date getField2() {
        return this.field2;
    }

    @Generated
    public Instant getField3() {
        return this.field3;
    }

    @Generated
    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        } else if (!(o instanceof Project)) {
            return false;
        } else {
            Project other = (Project)o;
            if (!other.canEqual(this)) {
                return false;
            } else if (this.getField1() != other.getField1()) {
                return false;
            } else {
                label61: {
                    Object this$name = this.getName();
                    Object other$name = other.getName();
                    if (this$name == null) {
                        if (other$name == null) {
                            break label61;
                        }
                    } else if (this$name.equals(other$name)) {
                        break label61;
                    }

                    return false;
                }

                label54: {
                    Object this$title = this.getTitle();
                    Object other$title = other.getTitle();
                    if (this$title == null) {
                        if (other$title == null) {
                            break label54;
                        }
                    } else if (this$title.equals(other$title)) {
                        break label54;
                    }

                    return false;
                }

                Object this$field2 = this.getField2();
                Object other$field2 = other.getField2();
                if (this$field2 == null) {
                    if (other$field2 != null) {
                        return false;
                    }
                } else if (!this$field2.equals(other$field2)) {
                    return false;
                }

                Object this$field3 = this.getField3();
                Object other$field3 = other.getField3();
                if (this$field3 == null) {
                    if (other$field3 != null) {
                        return false;
                    }
                } else if (!this$field3.equals(other$field3)) {
                    return false;
                }

                return true;
            }
        }
    }

    @Generated
    protected boolean canEqual(final Object other) {
        return other instanceof Project;
    }

    @Generated
    public int hashCode() {
        if (this.$hashCodeCache != 0) {
            return this.$hashCodeCache;
        } else {
            int PRIME = true;
            int result = 1;
            result = result * 59 + this.getField1();
            Object $name = this.getName();
            result = result * 59 + ($name == null ? 43 : $name.hashCode());
            Object $title = this.getTitle();
            result = result * 59 + ($title == null ? 43 : $title.hashCode());
            Object $field2 = this.getField2();
            result = result * 59 + ($field2 == null ? 43 : $field2.hashCode());
            Object $field3 = this.getField3();
            result = result * 59 + ($field3 == null ? 43 : $field3.hashCode());
            if (result == 0) {
                result = Integer.MIN_VALUE;
            }

            this.$hashCodeCache = result;
            return result;
        }
    }
}
```

### Cách sử dụng onParam. Add thêm NotNull vào param khi hashCode()/equals()
```java
@Setter
@Getter
@EqualsAndHashCode(onParam = @__({@NonNull}))
public class Project {
    private String name;
    private String title;
    private int field1;
    private Date field2;
    private Instant field3;
}
```

Sau khi compile hashCode()/equals() được thêm final @NonNull
```java
@Generated
public boolean equals(final @NonNull Object o) {
    if (o == null) {
        throw new NullPointerException("o is marked non-null but is null");
    } else if (o == this) {
        return true;
    } else if (!(o instanceof Project)) {
        return false;
    } else {
        Project other = (Project)o;
        if (!other.canEqual(this)) {
            return false;
        } else if (this.getField1() != other.getField1()) {
            return false;
        } else {
            label64: {
                Object this$name = this.getName();
                Object other$name = other.getName();
                if (this$name == null) {
                    if (other$name == null) {
                        break label64;
                    }
                } else if (this$name.equals(other$name)) {
                    break label64;
                }

                return false;
            }

            label57: {
                Object this$title = this.getTitle();
                Object other$title = other.getTitle();
                if (this$title == null) {
                    if (other$title == null) {
                        break label57;
                    }
                } else if (this$title.equals(other$title)) {
                    break label57;
                }

                return false;
            }

            Object this$field2 = this.getField2();
            Object other$field2 = other.getField2();
            if (this$field2 == null) {
                if (other$field2 != null) {
                    return false;
                }
            } else if (!this$field2.equals(other$field2)) {
                return false;
            }

            Object this$field3 = this.getField3();
            Object other$field3 = other.getField3();
            if (this$field3 == null) {
                if (other$field3 != null) {
                    return false;
                }
            } else if (!this$field3.equals(other$field3)) {
                return false;
            }

            return true;
        }
    }
}

@Generated
protected boolean canEqual(final @NonNull Object other) {
    if (other == null) {
        throw new NullPointerException("other is marked non-null but is null");
    } else {
        return other instanceof Project;
    }
}

@Generated
public int hashCode() {
    int PRIME = true;
    int result = 1;
    result = result * 59 + this.getField1();
    Object $name = this.getName();
    result = result * 59 + ($name == null ? 43 : $name.hashCode());
    Object $title = this.getTitle();
    result = result * 59 + ($title == null ? 43 : $title.hashCode());
    Object $field2 = this.getField2();
    result = result * 59 + ($field2 == null ? 43 : $field2.hashCode());
    Object $field3 = this.getField3();
    result = result * 59 + ($field3 == null ? 43 : $field3.hashCode());
    return result;
}
```

### Cách sử dụng onlyExplicitlyIncluded 
```java
@Setter
@Getter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Project {
    @EqualsAndHashCode.Exclude
    private String name;
    @EqualsAndHashCode.Exclude
    private String title;
    @EqualsAndHashCode.Include
    private int field1;
    @EqualsAndHashCode.Include
    private Date field2;
    @EqualsAndHashCode.Include
    private Instant field3;
}
```

Sau khi compile hashCode()/equals() chỉ sử dụng field Include. Không sử dụng Exclude 
```java
@Generated
public boolean equals(final Object o) {
    if (o == this) {
        return true;
    } else if (!(o instanceof Project)) {
        return false;
    } else {
        Project other = (Project)o;
        if (!other.canEqual(this)) {
            return false;
        } else if (this.getField1() != other.getField1()) {
            return false;
        } else {
            Object this$field2 = this.getField2();
            Object other$field2 = other.getField2();
            if (this$field2 == null) {
                if (other$field2 != null) {
                    return false;
                }
            } else if (!this$field2.equals(other$field2)) {
                return false;
            }

            Object this$field3 = this.getField3();
            Object other$field3 = other.getField3();
            if (this$field3 == null) {
                if (other$field3 != null) {
                    return false;
                }
            } else if (!this$field3.equals(other$field3)) {
                return false;
            }

            return true;
        }
    }
}

@Generated
protected boolean canEqual(final Object other) {
    return other instanceof Project;
}

@Generated
public int hashCode() {
    int PRIME = true;
    int result = 1;
    result = result * 59 + this.getField1();
    Object $field2 = this.getField2();
    result = result * 59 + ($field2 == null ? 43 : $field2.hashCode());
    Object $field3 = this.getField3();
    result = result * 59 + ($field3 == null ? 43 : $field3.hashCode());
    return result;
}
```