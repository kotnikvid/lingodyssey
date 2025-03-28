package si.um.kotnik.domain.entities

import io.quarkus.mongodb.panache.common.MongoEntity
import org.bson.codecs.pojo.annotations.BsonId
import org.bson.types.ObjectId
import java.time.LocalDateTime

@MongoEntity
@NoArg
data class Attempt(
    @BsonId
    var id: ObjectId? = null,
    var userEmail: String = "",
    var startedAt: LocalDateTime? = null,
    var endedAt: LocalDateTime? = null,
    var hasPassed: Boolean = false,

    var cardSet: ObjectId? = null,
)