package br.com.meli.wave4.integration_tests;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.junit.jupiter.api.Assertions;
import org.springframework.web.bind.MethodArgumentNotValidException;
import java.util.Arrays;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
@SpringBootTest
@AutoConfigureMockMvc
public class InboundOrderControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    public  void testPostRegisterInboundOrderShouldReturnInboundOrderDTO() throws Exception {

        String payloadJson = "{\n" +
                "    \"orderDate\": \"2022-01-31\",\n" +
                "    \"sectionCode\": 1,\n" +
                "    \"warehouseCode\": 1,\n" +
                "    \"sellerId\": 1,\n" +
                "    \"batchStock\": [\n" +
                "        {\n" +
                "            \"productId\": 2,\n" +
                "            \"currentTemperature\": 30,\n" +
                "            \"minimumTemperature\": 5,\n" +
                "            \"initialQuantity\": 90,\n" +
                "            \"currentQuantity\": 70,\n" +
                "            \"manufacturingDate\": \"2022-01-31\",\n" +
                "            \"manufacturingTime\": \"2022-01-31T14:51:16.366Z\",\n" +
                "            \"dueDate\": \"2024-01-31\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"productId\": 2,\n" +
                "            \"currentTemperature\": 3,\n" +
                "            \"minimumTemperature\": -5,\n" +
                "            \"initialQuantity\": 70,\n" +
                "            \"currentQuantity\": 70,\n" +
                "            \"manufacturingDate\": \"2022-01-31\",\n" +
                "            \"manufacturingTime\": \"2022-01-31T14:51:16.366Z\",\n" +
                "            \"dueDate\": \"2023-01-11\"\n" +
                "        }\n" +
                "    ]\n" +
                "}\n";

        MvcResult response = this.mockMvc.perform(MockMvcRequestBuilders
                .post("/register-inbound-order")
                .contentType(MediaType.APPLICATION_JSON)
                .content(payloadJson))
                .andExpect(status().isCreated())
                .andReturn();
    }

}
