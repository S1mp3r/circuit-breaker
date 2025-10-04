package com.rafael.publication.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.rafael.publication.domain.Publication;
import com.rafael.publication.entity.PublicationEntity;
import com.rafael.publication.model.PublicationRequest;

@Mapper(componentModel = "spring")
public interface PublicationMapper {
    
    PublicationEntity toPublicationEntity(Publication publication);
    
    @Mapping(target = "comments", ignore = true)
    Publication toPublication(PublicationEntity publication);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "comments", ignore = true)
    Publication toPublication(PublicationRequest publication);
}
