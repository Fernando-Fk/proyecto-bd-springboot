package com.edutrack.academico.mappers;

import com.edutrack.academico.dtos.curso.CursoDTO;
import com.edutrack.academico.entities.Curso;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-12-01T09:03:45-0400",
    comments = "version: 1.6.0.Beta1, compiler: javac, environment: Java 17.0.16 (Microsoft)"
)
@Component
public class CursoMapperImpl implements CursoMapper {

    @Override
    public CursoDTO toDto(Curso curso) {
        if ( curso == null ) {
            return null;
        }

        CursoDTO cursoDTO = new CursoDTO();

        cursoDTO.setIdCurso( curso.getIdCurso() );
        cursoDTO.setNombre( curso.getNombre() );
        cursoDTO.setProfesor( curso.getProfesor() );

        return cursoDTO;
    }
}
