package com.bsuir.diploma.bonup.model.photo;

import com.bsuir.diploma.bonup.model.AbstractEntity;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Lob;
import javax.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "photo")
@NoArgsConstructor @Getter @Setter
public class Photo extends AbstractEntity {

    @Column(name = "content_type")
    private String contentType;

    @Column
    @Lob
    private byte[] data;

    @Column
    private String name;

    @Column
    private Long size;

}
