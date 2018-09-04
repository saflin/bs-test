package com.bs.assignment.data.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.time.LocalDateTime;

@MappedSuperclass
public class BaseEntity implements Serializable {

    @Id
    @GeneratedValue(generator = "uuid_generator")
    @GenericGenerator(name = "uuid_generator", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(nullable = false, updatable = false, length = 36)
    private String id;

    @Column(nullable = false, updatable = true)
    private LocalDateTime updateDateTime;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createDateTime;

    public BaseEntity() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public LocalDateTime getUpdateDateTime() {
        return updateDateTime;
    }

    public void setUpdateDateTime(LocalDateTime updateDateTime) {
        this.updateDateTime = updateDateTime;
    }

    public LocalDateTime getCreateDateTime() {
        return createDateTime;
    }

    public void setCreateDateTime(LocalDateTime createDateTime) {
        this.createDateTime = createDateTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BaseEntity that = (BaseEntity) o;

        if (!id.equals(that.id)) return false;
        if (!updateDateTime.equals(that.updateDateTime)) return false;
        return createDateTime.equals(that.createDateTime);
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + updateDateTime.hashCode();
        result = 31 * result + createDateTime.hashCode();
        return result;
    }
}
