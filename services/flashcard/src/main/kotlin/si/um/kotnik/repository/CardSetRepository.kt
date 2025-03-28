package si.um.kotnik.repository

import si.um.kotnik.domain.entities.CardSet
import io.quarkus.mongodb.panache.PanacheMongoRepository
import jakarta.enterprise.context.ApplicationScoped

@ApplicationScoped
class CardSetRepository : PanacheMongoRepository<CardSet> {
    fun findByEmail(email: String): CardSet? {
        return find("email", email).firstResult()
    }
}