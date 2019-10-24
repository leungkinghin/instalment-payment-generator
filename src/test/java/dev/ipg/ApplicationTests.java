package dev.ipg;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import dev.ipg.dto.GeneratePlanRequest;
import dev.ipg.dto.GeneratePlanResponse;
import dev.ipg.dto.InstalmentDetails;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ApplicationTests {

	@Autowired
	private MockMvc mvc;

	@Autowired
	private ObjectMapper objectMapper;

	@Test
	public void testGeneratePlan_Full_Criteria() throws Exception{
		GeneratePlanRequest request = mockRequest();
		MvcResult result = mvc.perform(post("/plans")
				.content(objectMapper.writeValueAsString(request))
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andReturn();
		String resultJson = result.getResponse().getContentAsString();
		List<GeneratePlanResponse> listOfResponse = objectMapper.readValue(resultJson, new TypeReference<List<GeneratePlanResponse>>(){});
		assertNotNull(listOfResponse);
		
	
		
		
		assertEquals(24 , listOfResponse.size());
		assertEquals(BigDecimal.valueOf(219.36), listOfResponse.get(0).getBorrowerPaymentAmount());
		assertEquals(BigDecimal.valueOf(5000), listOfResponse.get(0).getInitialOutstandingPrincipal());
		assertEquals(BigDecimal.valueOf(198.53), listOfResponse.get(0).getPrincipal());
		assertEquals(BigDecimal.valueOf(20.83), listOfResponse.get(0).getInterest());
		assertEquals(LocalDateTime.of(2019, 1, 1, 0, 0, 0), listOfResponse.get(0).getDate());
		assertEquals(BigDecimal.valueOf(4801.47), listOfResponse.get(0).getRemainingOutstandingPrincipal());
		
		assertEquals(BigDecimal.valueOf(219.36), listOfResponse.get(1).getBorrowerPaymentAmount());
		assertEquals(BigDecimal.valueOf(4801.47), listOfResponse.get(1).getInitialOutstandingPrincipal());
		assertEquals(BigDecimal.valueOf(199.35), listOfResponse.get(1).getPrincipal());
		assertEquals(BigDecimal.valueOf(20.01), listOfResponse.get(1).getInterest());
		assertEquals(LocalDateTime.of(2019, 2, 1, 0, 0, 0), listOfResponse.get(1).getDate());
		assertEquals(BigDecimal.valueOf(4602.12), listOfResponse.get(1).getRemainingOutstandingPrincipal());
		
		assertEquals(BigDecimal.valueOf(219.28), listOfResponse.get(23).getBorrowerPaymentAmount());
		assertEquals(BigDecimal.valueOf(218.37), listOfResponse.get(23).getInitialOutstandingPrincipal());
		assertEquals(BigDecimal.valueOf(218.37), listOfResponse.get(23).getPrincipal());
		assertEquals(LocalDateTime.of(2020, 12, 1, 0, 0, 0), listOfResponse.get(23).getDate());
		assertEquals(BigDecimal.valueOf(0.91), listOfResponse.get(23).getInterest());
		assertEquals(0, BigDecimal.ZERO.compareTo(listOfResponse.get(23).getRemainingOutstandingPrincipal()));
		
	}

	
	@Test
	public void testGeneratePlan_MonthEnd_StartDate() throws Exception{
		GeneratePlanRequest request = mockMonthEndRequest();
		MvcResult result = mvc.perform(post("/plans")
				.content(objectMapper.writeValueAsString(request))
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andReturn();
		String resultJson = result.getResponse().getContentAsString();
		List<GeneratePlanResponse> listOfResponse = objectMapper.readValue(resultJson, new TypeReference<List<GeneratePlanResponse>>(){});
		assertNotNull(listOfResponse);
				
		assertEquals(24 , listOfResponse.size());
		assertEquals(BigDecimal.valueOf(219.36), listOfResponse.get(0).getBorrowerPaymentAmount());
		assertEquals(BigDecimal.valueOf(5000), listOfResponse.get(0).getInitialOutstandingPrincipal());
		assertEquals(BigDecimal.valueOf(198.53), listOfResponse.get(0).getPrincipal());
		assertEquals(BigDecimal.valueOf(20.83), listOfResponse.get(0).getInterest());
		assertEquals(LocalDateTime.of(2019, 1, 31, 0, 0, 0), listOfResponse.get(0).getDate());
		assertEquals(BigDecimal.valueOf(4801.47), listOfResponse.get(0).getRemainingOutstandingPrincipal());
		
		assertEquals(BigDecimal.valueOf(219.36), listOfResponse.get(1).getBorrowerPaymentAmount());
		assertEquals(BigDecimal.valueOf(4801.47), listOfResponse.get(1).getInitialOutstandingPrincipal());
		assertEquals(BigDecimal.valueOf(199.35), listOfResponse.get(1).getPrincipal());
		assertEquals(BigDecimal.valueOf(20.01), listOfResponse.get(1).getInterest());
		assertEquals(LocalDateTime.of(2019, 2, 28, 0, 0, 0), listOfResponse.get(1).getDate());
		assertEquals(BigDecimal.valueOf(4602.12), listOfResponse.get(1).getRemainingOutstandingPrincipal());
		
		assertEquals(BigDecimal.valueOf(219.28), listOfResponse.get(23).getBorrowerPaymentAmount());
		assertEquals(BigDecimal.valueOf(218.37), listOfResponse.get(23).getInitialOutstandingPrincipal());
		assertEquals(BigDecimal.valueOf(218.37), listOfResponse.get(23).getPrincipal());
		assertEquals(LocalDateTime.of(2020, 12, 31, 0, 0, 0), listOfResponse.get(23).getDate());
		assertEquals(BigDecimal.valueOf(0.91), listOfResponse.get(23).getInterest());
		assertEquals(0, BigDecimal.ZERO.compareTo(listOfResponse.get(23).getRemainingOutstandingPrincipal()));
		
	}
	
	@Test
	public void testGeneratePlan_Without_Duration() throws Exception{
		GeneratePlanRequest request = mockRequest();
		request.setDuration(null);
		mvc.perform(post("/plans")
				.content(objectMapper.writeValueAsString(request))
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isBadRequest());				
	}
	
	@Test
	public void testGeneratePlan_With_Negative_Duration() throws Exception{
		GeneratePlanRequest request = mockRequest();
		request.setDuration(-5);
		mvc.perform(post("/plans")
				.content(objectMapper.writeValueAsString(request))
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isBadRequest());				
	}
	
	@Test
	public void testGeneratePlan_Without_Interest() throws Exception{
		GeneratePlanRequest request = mockRequest();
		request.setNominalRate(null);
		mvc.perform(post("/plans")
				.content(objectMapper.writeValueAsString(request))
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isBadRequest());				
	}
	
	@Test
	public void testGeneratePlan_Negative_Interest() throws Exception{
		GeneratePlanRequest request = mockRequest();
		request.setNominalRate(BigDecimal.valueOf(-1));
		mvc.perform(post("/plans")
				.content(objectMapper.writeValueAsString(request))
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isBadRequest());				
	}
	
	@Test
	public void testGeneratePlan_Zero_Interest() throws Exception{
		GeneratePlanRequest request = mockRequest();
		request.setNominalRate(BigDecimal.ZERO);
		mvc.perform(post("/plans")
				.content(objectMapper.writeValueAsString(request))
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isBadRequest());				
	}
	
	@Test
	public void testGeneratePlan_Without_LoanAmount() throws Exception{
		GeneratePlanRequest request = mockRequest();
		request.setLoanAmount(null);
		mvc.perform(post("/plans")
				.content(objectMapper.writeValueAsString(request))
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isBadRequest());				
	}
	
	@Test
	public void testGeneratePlan_Zero_LoanAmount() throws Exception{
		GeneratePlanRequest request = mockRequest();
		request.setLoanAmount(BigDecimal.ZERO);
		mvc.perform(post("/plans")
				.content(objectMapper.writeValueAsString(request))
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isBadRequest());				
	}
	
	private GeneratePlanRequest mockRequest() {
		GeneratePlanRequest request = new GeneratePlanRequest();
		request.setDuration(24);
		request.setLoanAmount(BigDecimal.valueOf(5000));
		request.setNominalRate(BigDecimal.valueOf(5));
		request.setStartDate(LocalDateTime.of(2019, 1, 1, 0, 0, 0));
		return request;
	}
	
	private GeneratePlanRequest mockMonthEndRequest() {
		GeneratePlanRequest request = new GeneratePlanRequest();
		request.setDuration(24);
		request.setLoanAmount(BigDecimal.valueOf(5000));
		request.setNominalRate(BigDecimal.valueOf(5));
		request.setStartDate(LocalDateTime.of(2019, 1, 31, 0, 0, 0));
		return request;
	}
}
