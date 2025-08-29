package com.shabbir.hefazat.Entity.AllPlatformEntity;

import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document("facebook")
public class
Facebook {
    @Id
    private ObjectId id;
    private String accountName;
    private String firstname;
    private String lastname;
    private String birthday;
    private String phone;
    private String email;
}
