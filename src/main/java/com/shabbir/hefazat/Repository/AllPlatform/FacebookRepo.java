package com.shabbir.hefazat.Repository.AllPlatform;

import com.shabbir.hefazat.Entity.AllPlatformEntity.Facebook;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface FacebookRepo extends MongoRepository<Facebook, ObjectId> {
}
