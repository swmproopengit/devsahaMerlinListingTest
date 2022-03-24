package com.devsahamerlin.agency.dealer.controller;

import com.devsahamerlin.agency.dto.DealerRequestDto;
import com.devsahamerlin.agency.dto.DealerResponseDto;
import com.devsahamerlin.agency.exceptions.DealerNotFoundException;
import com.devsahamerlin.agency.restcontroller.DealerRestController;
import com.devsahamerlin.agency.servicesimplementation.DealerServiceImplementation;
import com.devsahamerlin.agency.utillities.JsonUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.UUID;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;

@RunWith(SpringRunner.class)
@WebMvcTest(DealerRestController.class)
public class DealerControllerTest {

    private static final String HOST = "http://127.0.0.1:8081/api/v1/dealers";
    private UUID uuid = UUID.randomUUID();

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DealerServiceImplementation service;

    @Test
    public void createDealer_whenPostMethod() throws Exception {

        DealerRequestDto dealer = new DealerRequestDto();
        dealer.setName("Test Name");
        dealer.setListingLimit(10);
        DealerResponseDto dealerResponseDto = new DealerResponseDto(uuid, "Test Name",10);

        given(service.addDealer(dealer)).willReturn(dealerResponseDto);

        mockMvc.perform(post(HOST+"/save")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JsonUtil.toJson(dealer)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.listingLimit", is(dealer.getListingLimit())));
    }

    @Test
    public void getUserById_whenGetMethod() throws Exception {

        DealerResponseDto dealerResponseDto = new DealerResponseDto(uuid, "Test Name",10);

        given(service.getDealerById(uuid)).willReturn(dealerResponseDto);

        mockMvc.perform(get(HOST+"/" + uuid)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("name", is(dealerResponseDto.getName())));
    }

    @Test
    public void should_throw_exception_when_dealer_doesnt_exist() throws Exception {

        DealerResponseDto dealerResponseDto = new DealerResponseDto(uuid, "Test Name",10);

        Mockito.doThrow(new DealerNotFoundException(dealerResponseDto.getId())).when(service).getDealerById(dealerResponseDto.getId());

        mockMvc.perform(get(HOST+"/" + dealerResponseDto.getId())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    public void updateDealerLimit_whenPutListingLimit() throws Exception {

        DealerResponseDto dealerResponseDto = new DealerResponseDto(uuid, "Test Name",10);
        given(service.updateDealerLimit(dealerResponseDto.getListingLimit(), dealerResponseDto.getId())).willReturn("Updated successfully");

        ObjectMapper mapper = new ObjectMapper();

        mockMvc.perform(put(HOST+"/limits/" + dealerResponseDto.getId()+"?listingLimit="+19)
                        .content(mapper.writeValueAsString(dealerResponseDto.getListingLimit()))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isAccepted());
    }

    @Test
    public void getDealerLimit_whenGiveDealerId() throws Exception {

        DealerResponseDto dealerResponseDto = new DealerResponseDto(uuid, "Test Name",10);
        given(service.getDealerLimit(dealerResponseDto.getId())).willReturn(dealerResponseDto.getListingLimit());

        ObjectMapper mapper = new ObjectMapper();

        mockMvc.perform(get(HOST+"/limits/" + dealerResponseDto.getId())
                        .content(mapper.writeValueAsString(dealerResponseDto.getListingLimit()))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }


}
