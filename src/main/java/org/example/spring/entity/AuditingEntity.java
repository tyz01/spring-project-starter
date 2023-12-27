package org.example.spring.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.envers.Audited;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.time.Instant;

@Getter
@Setter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class AuditingEntity<T extends Serializable> implements BaseEntity<T>{

    @CreatedDate
    //@Audited
    private Instant createdAt;

    @LastModifiedDate
    //@Audited
    private Instant modifiedAt;

    @CreatedBy
    //@Audited
    private String createdBy;

    @LastModifiedBy
    //@Audited
    private String modifiedBy;

}
