package com.edutrack.academico.mappers;

import com.edutrack.academico.dtos.estudiante.EstudianteDTO;
import com.edutrack.academico.entities.Estudiante;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-12-01T13:29:41-0400",
    comments = "version: 1.6.0.Beta1, compiler: Eclipse JDT (IDE) 3.44.0.v20251118-1623, environment: Java 21.0.9 (Eclipse Adoptium)"
)
@Component
public class EstudianteMapperImpl implements EstudianteMapper {

    @Override
    public EstudianteDTO toDto(Estudiante estudiante) {
        if ( estudiante == null ) {
            return null;
        }

        EstudianteDTO estudianteDTO = new EstudianteDTO();

        estudianteDTO.setApellido( estudiante.getApellido() );
        estudianteDTO.setEmail( estudiante.getEmail() );
        if ( estudiante.getEstado() != null ) {
            estudianteDTO.setEstado( estudiante.getEstado().name() );
        }
        estudianteDTO.setIdEstudiante( estudiante.getIdEstudiante() );
        estudianteDTO.setNombre( estudiante.getNombre() );

        mapEstado( estudiante, estudianteDTO );

        return estudianteDTO;
    }

    @Override
    public Estudiante toEntity(EstudianteDTO dto) {
        if ( dto == null ) {
            return null;
        }

        Estudiante estudiante = new Estudiante();

        estudiante.setApellido( dto.getApellido() );
        estudiante.setEmail( dto.getEmail() );
        estudiante.setIdEstudiante( dto.getIdEstudiante() );
        estudiante.setNombre( dto.getNombre() );

        estudiante.setEstado( toEstadoEnum(dto.getEstado()) );

        return estudiante;
    }
}
