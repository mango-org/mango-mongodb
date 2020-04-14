package org.develnext.jphp.ext.mongo.bind;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import org.bson.conversions.Bson;
import org.develnext.jphp.zend.ext.json.JsonFunctions;
import php.runtime.Memory;
import php.runtime.common.HintType;
import php.runtime.env.Environment;
import php.runtime.env.TraceInfo;
import php.runtime.memory.support.MemoryOperation;
import php.runtime.reflection.ParameterEntity;

public class BasicDBObjectMemoryOperation extends MemoryOperation<BasicDBObject> {

    @Override
    public Class<?>[] getOperationClasses() {
        return new Class[] { BasicDBObject.class, Bson.class, DBObject.class };
    }

    @Override
    public BasicDBObject convert(Environment env, TraceInfo trace, Memory arg) throws Throwable {
        if (arg.isNull()) return null;
        return BasicDBObject.parse(JsonFunctions.json_encode(arg));
    }

    @Override
    public Memory unconvert(Environment env, TraceInfo trace, BasicDBObject arg) throws Throwable {
        if (arg == null) return Memory.NULL;
        return JsonFunctions.json_decode(env, arg.toJson(), true);
    }

    @Override
    public void applyTypeHinting(ParameterEntity parameter) {
        parameter.setType(HintType.ITERABLE);
    }
}
