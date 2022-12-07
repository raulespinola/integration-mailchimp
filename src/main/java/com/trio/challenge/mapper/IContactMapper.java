package com.trio.challenge.mapper;

import com.trio.challenge.integration.client.mailchimp.dto.SyncContactDto;
import com.trio.challenge.model.SyncContactModel;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface IContactMapper {

    SyncContactModel dtoToModel (SyncContactDto syncContactDto);
    SyncContactDto modelToDto (SyncContactModel syncContactModel);
}
