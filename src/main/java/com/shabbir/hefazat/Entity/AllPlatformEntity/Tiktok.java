package com.shabbir.hefazat.Entity.AllPlatformEntity;

import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document("tiktok")
public class Tiktok {
    @Id
    private ObjectId id;
    private String accountName;
    private String email;
    private String birthday;
    private String phone;
    private String platformName;
}
