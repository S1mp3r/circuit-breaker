package com.rafael.publication.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.rafael.publication.domains.Publication;
import com.rafael.publication.entities.PublicationEntity;
import com.rafael.publication.models.PublicationRequest;

@Mapper(componentModel = "spring")
public interface PublicationMapper {
    
    PublicationEntity toPublicationEntity(Publication publication);
    
    @Mapping(target = "comments", ignore = true)
    Publication toPublication(PublicationEntity publication);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "comments", ignore = true)
    Publication toPublication(PublicationRequest publication);
}
