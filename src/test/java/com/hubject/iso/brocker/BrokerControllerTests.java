package com.hubject.iso.brocker;

import com.hubject.iso.brocker.model.SignedContratDataReference;
import com.hubject.iso.brocker.service.RequestBrokerService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.mockito.ArgumentMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class BrokerControllerTests {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    RequestBrokerService requestBrokerService;

    @Test
    public void simpleGet() throws Exception {
        Mockito.when(requestBrokerService.getContractLocation(anyString(), anyString(), anyString(), anyString())).thenReturn(
                Optional.of(new SignedContratDataReference("testAlias", "1", "1", "1", new Date(), "1")));
        this.mockMvc.perform(get("/broker/alias/emaid/pcid/exi1")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("testAlias")));
    }

    @Test
    public void shortGet() throws Exception {
        final List retrunList = new ArrayList<>();
        retrunList.add(new SignedContratDataReference("testAlias", "1", "1", "1", new Date(), "1"));
        Mockito.when(requestBrokerService.getContractLocations(anyString(), anyString())).thenReturn(retrunList);
        this.mockMvc.perform(get("/broker/alias/emaid")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("testAlias")));
    }

}
