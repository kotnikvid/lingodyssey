package si.um.kotnik.domain.entities

import io.quarkus.mongodb.panache.common.MongoEntity
import org.bson.codecs.pojo.annotations.BsonId
import org.bson.types.ObjectId

@MongoEntity
@NoArg
data class FlashCard(
    @BsonId
    var id: ObjectId? = null,
    var title: String = "",
    var body: String = "",
    var pointsAwarded: Int = 0,

    var answers: MutableList<Answer> = mutableListOf(),

    var cardSet: ObjectId? = null,
)