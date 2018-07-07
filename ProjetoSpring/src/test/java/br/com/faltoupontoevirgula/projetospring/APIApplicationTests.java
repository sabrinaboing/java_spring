/*
package br.com.faltoupontoevirgula.projetospring;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import br.com.faltoupontoevirgula.projetospring.api.PacienteControllerAPI;



@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class APIApplicationTests {

    @Autowired
    private PacienteControllerAPI pacienteControllerAPI;
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void contextLoads() {
        assertThat(pacienteControllerAPI).isNotNull();
    }

    @Test
    public void pacienteControllerAPIPOSTGETTest() throws Exception {
        mockMvc.perform(post("/api/pacientes")
                .content("{"nome":"zezinho", "sexo":"Masculino"}")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        mockMvc.perform(get("/api/pacientes"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].nome", is("zezinho")))
                .andExpect(jsonPath("$[0].sexo", is("Masculino")));
    }

    @Test
    public void pacienteControllerAPIPOSTPUTTest() throws Exception {
        mockMvc.perform(post("/api/pacientes")
                .content("{"nome":"Maria", "sexo":"Feminino"}")
                .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk());

        mockMvc.perform(put("/api/pacientes/1")
                .content("{"nome":"Maria da Silva", "sexo":"Feminino"}")
                .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk());

        mockMvc.perform(get("/api/pacientes"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].nome", is("Maria da Silva")))
                .andExpect(jsonPath("$[0].sexo", is("Feminino")));
    }

    @After
    public void cleanDb() throws Exception {
        MvcResult result = 
                mockMvc.perform(get("/api/pacientes"))
                .andExpect(status().isOk())
                .andReturn();

        String resultStr = result.getResponse().getContentAsString();

        JSONArray objArrayJson = new JSONArray(resultStr);

        int size = objArrayJson.length();

        for(int i=0;i<size;i++) {
            JSONObject objJson = objArrayJson.getJSONObject(i);

            mockMvc.perform(delete("/api/pacientes/" + objJson.getString("id")))
            .andExpect(status().isOk());
        }
    }
}
*/