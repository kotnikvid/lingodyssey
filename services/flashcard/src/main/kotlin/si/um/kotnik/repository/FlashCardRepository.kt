package si.um.kotnik.repository

import si.um.kotnik.domain.entities.FlashCard
import io.quarkus.mongodb.panache.PanacheMongoRepository
import jakarta.enterprise.context.ApplicationScoped

@ApplicationScoped
class FlashCardRepository : PanacheMongoRepository<FlashCard> {
}