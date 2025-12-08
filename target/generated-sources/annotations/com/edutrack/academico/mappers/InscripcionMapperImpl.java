package com.edutrack.academico.mappers;

import com.edutrack.academico.dtos.inscripcion.InscripcionDTO;
import com.edutrack.academico.entities.Inscripcion;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-12-08T13:04:43-0400",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.16 (Microsoft)"
)
@Component
public class InscripcionMapperImpl implements InscripcionMapper {

    @Override
    public InscripcionDTO toDto(Inscripcion inscripcion) {
        if ( inscripcion == null ) {
            return null;
        }

        InscripcionDTO inscripcionDTO = new InscripcionDTO();

        inscripcionDTO.setIdInscripcion( inscripcion.getIdInscripcion() );
        inscripcionDTO.setFecha( inscripcion.getFecha() );

        mapExtraData( inscripcion, inscripcionDTO );

        return inscripcionDTO;
    }
}
