package com.example.furniturewebdemo1.model.audit;


import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@Setter
@Getter
public abstract class DateAudit {

    @CreatedDate
    @Column(name = "created_date", updatable = false)
    protected Date createdDate;

    @LastModifiedDate
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated_date")
    protected Date lastModifiedDate;
}
