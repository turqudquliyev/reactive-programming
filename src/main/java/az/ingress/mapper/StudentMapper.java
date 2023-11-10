package az.ingress.mapper;

import az.ingress.dao.entity.StudentEntity;
import az.ingress.model.request.StudentRequest;
import az.ingress.model.response.StudentResponse;

public enum StudentMapper {
    STUDENT_MAPPER;

    public StudentEntity mapRequestToEntity(StudentRequest request) {
        return StudentEntity.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .age(request.getAge())
                .build();
    }

    public StudentResponse mapEntityToResponse(StudentEntity entity) {
        return StudentResponse.builder()
                .id(entity.getId())
                .firstName(entity.getFirstName())
                .lastName(entity.getLastName())
                .age(entity.getAge())
                .build();
    }
}