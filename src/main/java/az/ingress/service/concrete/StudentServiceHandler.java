package az.ingress.service.concrete;

import az.ingress.dao.entity.StudentEntity;
import az.ingress.dao.repository.StudentRepository;
import az.ingress.exception.NotFoundException;
import az.ingress.model.request.StudentRequest;
import az.ingress.model.response.StudentResponse;
import az.ingress.service.abstraction.StudentService;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static az.ingress.mapper.StudentMapper.STUDENT_MAPPER;
import static az.ingress.model.constant.ExceptionConstant.STUDENT_NOT_FOUND;
import static lombok.AccessLevel.PRIVATE;

@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = PRIVATE)
public class StudentServiceHandler implements StudentService {
    StudentRepository studentRepository;

    public Mono<Void> save(StudentRequest request) {
        var student = STUDENT_MAPPER.mapRequestToEntity(request);
        return studentRepository.save(student).then();
    }

    public Flux<StudentResponse> getAll() {
        var students = studentRepository.findAll();
        return students.map(STUDENT_MAPPER::mapEntityToResponse);
    }

    public Mono<StudentResponse> getById(Long id) {
        var student = fetchIfExist(id);
        return student.map(STUDENT_MAPPER::mapEntityToResponse);
    }

    public Mono<Void> updateById(Long id, StudentRequest request) {
        var student = fetchIfExist(id);
        return student.flatMap(entity -> update(entity, request)).then();
    }

    public Mono<Void> deleteById(Long id) {
        fetchIfExist(id);
        return studentRepository.deleteById(id).then();
    }

    private Mono<Void> update(StudentEntity entity, StudentRequest request) {
        entity.setFirstName(request.getFirstName());
        entity.setLastName(request.getLastName());
        entity.setAge(request.getAge());
        return studentRepository.save(entity).then();
    }

    private Mono<StudentEntity> fetchIfExist(Long id) {
        return studentRepository.findById(id)
                .switchIfEmpty(Mono.error(new NotFoundException(STUDENT_NOT_FOUND)));
    }
}