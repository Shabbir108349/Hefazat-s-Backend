package com.shabbir.hefazat.Entity;

import com.shabbir.hefazat.Entity.AllPlatformEntity.Facebook;
import com.shabbir.hefazat.Entity.AllPlatformEntity.Google;
import com.shabbir.hefazat.Entity.AllPlatformEntity.Tiktok;
import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Data
@Document("users")
public class User {
    @Id
    private ObjectId id;
    @Indexed(unique = true)
    private String username;
    private String password;
    @DBRef
    private List<Google> googleList = new ArrayList<>();
    @DBRef
    private List<Facebook> facebookList = new ArrayList<>();
    @DBRef
    private List<Tiktok> tiktokList = new ArrayList<>();
}
