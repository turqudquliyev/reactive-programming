package az.ingress.dao.repository;

import az.ingress.dao.entity.StudentEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface StudentRepository extends ReactiveCrudRepository<StudentEntity, Long> {
}