package com.shabbir.hefazat.Repository.AllPlatform;

import com.shabbir.hefazat.Entity.AllPlatformEntity.Google;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface GoogleRepo extends MongoRepository<Google, ObjectId> {
}

