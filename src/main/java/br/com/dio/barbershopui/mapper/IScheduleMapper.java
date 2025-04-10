package br.com.dio.barbershopui.mapper;

import br.com.dio.barbershopui.controller.request.SaveScheduleRequest;
import br.com.dio.barbershopui.controller.response.ClientScheduleAppointmentResponse;
import br.com.dio.barbershopui.controller.response.SaveScheduleResponse;
import br.com.dio.barbershopui.controller.response.ScheduleAppointmentMonthResponse;
import br.com.dio.barbershopui.entity.ScheduleEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import java.util.List;
import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(componentModel = SPRING)
public interface IScheduleMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "client.id", source = "clientId")
    ScheduleEntity toEntity(SaveScheduleRequest request);

    @Mapping(target = "clientId", source = "client.id")
    SaveScheduleResponse toSaveResponse(ScheduleEntity entity);

    @Mapping(target = "scheduledAppointments", expression = "java(toClientMonthResponse(entities))")
    ScheduleAppointmentMonthResponse toMonthResponse(int year, int month, List<ScheduleEntity> entities);

    List<ClientScheduleAppointmentResponse> toClientMonthResponse(List<ScheduleEntity> entities);

    @Mapping(target = "clientId", source = "client.id")
    @Mapping(target = "clientName", source = "client.name")
    @Mapping(target = "day", expression = "java(entity.getStartAt().getDayOfMonth())")
    ClientScheduleAppointmentResponse toClientMonthResponse(ScheduleEntity entity);
}
