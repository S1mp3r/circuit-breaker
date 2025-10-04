package com.rafael.publication.mapper;

import org.mapstruct.Mapper;

import com.rafael.publication.domain.Publication;
import com.rafael.publication.entity.PublicationEntity;

@Mapper(componentModel = "spring")
public interface PublicationMapper {
    
    PublicationEntity toPublicationEntity(Publication publication);

    Publication toPublication(PublicationEntity publication);
}
