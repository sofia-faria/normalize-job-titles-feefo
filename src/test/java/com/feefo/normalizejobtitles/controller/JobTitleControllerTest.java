package com.feefo.normalizejobtitles.controller;

import com.feefo.normalizejobtitles.model.JobTitle;
import com.feefo.normalizejobtitles.service.JobTitleService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@WebMvcTest(JobTitleController.class)
public class JobTitleControllerTest {

    @Autowired
    private JobTitleController jobTitleController;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private JobTitleService jobTitleService;

   @Test
   public void normalizeTitleTest() throws Exception{

       JobTitle jobNormalized1 = new JobTitle();
       jobNormalized1.setJob("Software engineer");

       JobTitle jobNormalized2 = new JobTitle();
       jobNormalized2.setJob("Accountant");

       when(jobTitleService.saveJob(any(JobTitle.class))).thenReturn(jobNormalized1);
       when(jobTitleService.saveJob(any(JobTitle.class))).thenReturn(jobNormalized2);

       String job = "{\"job\":\"Java Engineer\"}";

       mockMvc.perform(MockMvcRequestBuilders.post("api/jobtitle/normalize")
               .content(job)
               .contentType(MediaType.APPLICATION_JSON))
               .andExpect(status().isOk())
               .andExpect(MockMvcResultMatchers.jsonPath("id").exists())
               .andExpect(MockMvcResultMatchers.jsonPath("job").value("Software engineer"));

   }
}
