package az.ingress.controller;

import az.ingress.model.request.StudentRequest;
import az.ingress.model.response.StudentResponse;
import az.ingress.service.abstraction.StudentService;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static lombok.AccessLevel.PRIVATE;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;

@RestController
@RequestMapping("/v1/students")
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = PRIVATE)
public class StudentController {
    StudentService studentService;

    @PostMapping
    @ResponseStatus(CREATED)
    public Mono<Void> create(@RequestBody StudentRequest request) {
        return studentService.save(request);
    }

    @GetMapping
    public Flux<StudentResponse> retrieveAll() {
        return studentService.getAll();
    }

    @GetMapping("/{id}")
    public Mono<StudentResponse> retrieveById(@PathVariable Long id) {
        return studentService.getById(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(NO_CONTENT)
    public Mono<Void> updateById(@PathVariable Long id,
                                 @RequestBody StudentRequest request) {
        return studentService.updateById(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(NO_CONTENT)
    public Mono<Void> deleteById(@PathVariable Long id) {
        return studentService.deleteById(id);
    }
}