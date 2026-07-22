package com.example.javashopspring.mapper;

import com.example.javashopspring.dto.voucherDTO.CreateVoucherCommand;
import com.example.javashopspring.dto.voucherDTO.VoucherDto;
import com.example.javashopspring.models.Voucher;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface VoucherMapper {
    VoucherDto toRespone(Voucher voucher);
    Voucher toEntity(CreateVoucherCommand request);
}
