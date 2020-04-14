package org.develnext.jphp.ext.mongo;

import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoIterable;
import org.bson.types.ObjectId;
import org.develnext.jphp.ext.mongo.bind.*;
import org.develnext.jphp.ext.mongo.classes.*;
import php.runtime.env.CompileScope;
import php.runtime.ext.support.Extension;
import php.runtime.memory.support.MemoryOperation;

public class MongoExtension extends Extension {
    public static final String NS = "mongo";

    @Override
    public Status getStatus() {
        return Status.EXPERIMENTAL;
    }

    @Override
    public void onRegister(CompileScope scope) {
        MemoryOperation.register(new BasicDBObjectMemoryOperation());
        MemoryOperation.register(new DocumentMemoryOperation());
        MemoryOperation.register(new IndexOptionsMemoryOperation());
        MemoryOperation.register(new CountOptionsMemoryOperation());
        MemoryOperation.register(new ObjectIdMemoryOperation());

        registerWrapperClass(scope, ObjectId.class, WrapObjectId.class);
        registerWrapperClass(scope, MongoIterable.class, WrapMongoIterable.class);
        registerWrapperClass(scope, MongoCollection.class, WrapMongoCollection.class);
        registerWrapperClass(scope, MongoDatabase.class, WrapMongoDatabase.class);
        registerWrapperClass(scope, MongoClient.class, WrapMongoClient.class);
        // register classes ...
    }
}
