package br.com.meli.wave4.integration_tests;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
@SpringBootTest
@AutoConfigureMockMvc
public class SchedulingControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private MockMvc mock;

    @Test
    void loginClientShouldReturnStatusOk() throws Exception {

        String payloadJson = "{\n" +
                "    \"username\": \"gabriela\",\n" +
                "    \"password\": \"123\"" +
                "}\n";
        this.mockMvc.perform(MockMvcRequestBuilders.post("/auth")
                .contentType(MediaType.APPLICATION_JSON)
                .content(payloadJson))
                .andDo(print())
                .andReturn().getResponse();
    }

    @Test
    void loginFail() throws Exception {
        String payloadJson = "{\n" +
                "    \"username\": \"rodrigo\",\n" +
                "    \"password\": \"451\"" +
                "}\n";
        this.mockMvc.perform(MockMvcRequestBuilders.post("/auth")
                .contentType(MediaType.APPLICATION_JSON)
                .content(payloadJson))
                .andDo(print())
                .andExpect(status().isForbidden());
    }

}
