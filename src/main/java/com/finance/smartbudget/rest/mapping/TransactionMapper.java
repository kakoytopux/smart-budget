package com.finance.smartbudget.rest.mapping;

import com.finance.smartbudget.rest.dto.TransactionDto;
import com.finance.smartbudget.rest.model.Transaction;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


@Mapper(componentModel = "spring")
public interface TransactionMapper {

    @Mapping(source = "user.username", target = "username") // Map username from User entity
    TransactionDto toDto(Transaction transaction);

    @Mapping(target = "user", ignore = true) // Ignore user during DTO to entity mapping
    Transaction toEntity(TransactionDto transactionDto);

}