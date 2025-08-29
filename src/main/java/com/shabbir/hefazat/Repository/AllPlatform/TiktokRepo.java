package com.shabbir.hefazat.Repository.AllPlatform;

import com.shabbir.hefazat.Entity.AllPlatformEntity.Tiktok;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TiktokRepo extends MongoRepository<Tiktok, ObjectId> {
    
}
