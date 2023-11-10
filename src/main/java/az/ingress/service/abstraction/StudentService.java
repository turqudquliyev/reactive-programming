package az.ingress.service.abstraction;

import az.ingress.model.request.StudentRequest;
import az.ingress.model.response.StudentResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface StudentService {
    Mono<Void> save(StudentRequest request);

    Flux<StudentResponse> getAll();

    Mono<StudentResponse> getById(Long id);

    Mono<Void> updateById(Long id, StudentRequest request);

    Mono<Void> deleteById(Long id);
}