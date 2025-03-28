package si.um.kotnik.repository

import si.um.kotnik.domain.entities.Attempt
import io.quarkus.mongodb.panache.PanacheMongoRepository
import jakarta.enterprise.context.ApplicationScoped

@ApplicationScoped
class AttemptRepository : PanacheMongoRepository<Attempt> {
}