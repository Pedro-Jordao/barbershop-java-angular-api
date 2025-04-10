package br.com.dio.barbershopui.controller;

import br.com.dio.barbershopui.controller.request.SaveClientRequest;
import br.com.dio.barbershopui.controller.request.UpdateClientRequest;
import br.com.dio.barbershopui.controller.response.ClientDetailResponse;
import br.com.dio.barbershopui.controller.response.ListClientResponse;
import br.com.dio.barbershopui.controller.response.SaveClientResponse;
import br.com.dio.barbershopui.controller.response.UpdateClientResponse;
import br.com.dio.barbershopui.mapper.IClientMapper;
import br.com.dio.barbershopui.service.IClientService;
import br.com.dio.barbershopui.service.query.IClientQueryService;
import jakarta.validation.Path;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("clients")
@AllArgsConstructor
public class ClientController {

    private final IClientMapper mapper;
    private final IClientService service;
    private final IClientQueryService queryService;


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    SaveClientResponse save(@RequestBody @Valid final SaveClientRequest request){

        var entity = mapper.toEntity(request);
        service.save(entity);
        return mapper.toSaveResponse(entity);
    }

    @PutMapping("{id}")
    UpdateClientResponse update(@PathVariable final long id, @RequestBody @Valid final UpdateClientRequest request){

        var entity = mapper.toEntity(id, request);
        service.update(entity);
        return mapper.toUpdateResponse(entity);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void delete (@PathVariable final long id){
        service.delete(id);
    }

    @GetMapping("{id}")
    ClientDetailResponse findById(@PathVariable final long id){
        var entity = queryService.findById(id);
        return mapper.toDetailResponse(entity);
    }

    @GetMapping
    List<ListClientResponse> list(){
        var entities = queryService.list();
        return mapper.toListResponse(entities);
    }
}
