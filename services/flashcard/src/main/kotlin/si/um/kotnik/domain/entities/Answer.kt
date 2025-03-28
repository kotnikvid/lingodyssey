package si.um.kotnik.domain.entities

import io.quarkus.mongodb.panache.common.MongoEntity
import org.bson.codecs.pojo.annotations.BsonId
import org.bson.types.ObjectId

@MongoEntity
@NoArg
data class Answer(
    @BsonId
    var id: ObjectId? = null,
    var body: String = "",
    var isCorrect: Boolean = false,
)
