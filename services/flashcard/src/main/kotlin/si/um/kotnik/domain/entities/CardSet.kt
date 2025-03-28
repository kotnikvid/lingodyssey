package si.um.kotnik.domain.entities

import io.quarkus.mongodb.panache.common.MongoEntity
import org.bson.codecs.pojo.annotations.BsonId
import org.bson.types.ObjectId

@MongoEntity
@NoArg
data class CardSet(
    @BsonId
    var id: ObjectId? = null,
    var name: String = "",
    var userEmail: String = "",
    var languageCode: String = "",

    var flashCards: MutableList<FlashCard> = mutableListOf(),

    var attempts: MutableList<Attempt> = mutableListOf(),
)