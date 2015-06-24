
package com.ghag.rnd.rest.tests;



import org.junit.Test;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class TestRestUsingRestTemplate {
	
	//List<HttpMessageConverter<?>> list = new ArrayList<HttpMessageConverter<?>>();
	//list.add(new MappingJacksonHttpMessageConverter());
	//restTemplate.setMessageConverters(list);

	
	@Test
	public void testHttpGetWithStrings(){
		RestTemplate restTemplate = new RestTemplate();

		HttpHeaders headers = new HttpHeaders();
		headers.set("Content-Type", "application/json");
		
		HttpEntity<String> entity = new HttpEntity<String>(null,headers);
		
		String url = "http://localhost:8080/parcels";
		ResponseEntity<String> resp = restTemplate.exchange(url,HttpMethod.GET, entity, String.class, new Object[] {});
		System.out.println("response received in junit is \r\n"+resp.getBody());
		
	}
	
	/*
	public PatientVO insertPatient(){
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.set("Content-Type", "application/json");

		PatientVO pat = new PatientVO();
		setPatientFields(pat);
		
		//insert
		HttpEntity<PatientVO> entity = new HttpEntity<PatientVO>(pat,headers);
		String url = "http://localhost:9080/PMS-WebApp/restapp/patient";
		ResponseEntity<PatientVO> resp = restTemplate.exchange(url,HttpMethod.POST, entity, PatientVO.class, new Object[] {});
		System.out.println(resp.getBody());
		
		PatientVO retpat = (PatientVO)resp.getBody();

		return retpat;
	}
	
	
	
	public void delete(long patientid){
		RestTemplate restTemplate = new RestTemplate();

		HttpHeaders headers = new HttpHeaders();
		headers.set("Content-Type", "application/json");

		String url = "http://localhost:9080/PMS-WebApp/restapp/patient/"+patientid;
		HttpEntity<PatientVO> entity = new HttpEntity<PatientVO>(null,headers);
		ResponseEntity<PatientVO> resp = restTemplate.exchange(url,HttpMethod.DELETE, entity, PatientVO.class, new Object[] {});
		System.out.println(resp.getBody());

	}
	
	
	public void update(){
		//GET
		String url = "http://localhost:9080/PMS-WebApp/restapp/patient/"+retpat.getPatientId();
		HttpEntity<PatientVO> entity = new HttpEntity<PatientVO>(null,headers);
		ResponseEntity<PatientVO> resp = restTemplate.exchange(url,HttpMethod.GET, entity, PatientVO.class, new Object[] {});
		System.out.println(resp.getBody());
		
		retpat = (PatientVO)resp.getBody();
		
		//UPDATE
		retpat.setMiddleName("up-midname");
		retpat.setFirstName("up-firstname");
		retpat.setEthnicity("up-VeryEthnic");
		retpat.setPharmacyByPreferredPharmacyId1(new PharmacyVO(1L));
		retpat.setPharmacyByPreferredPharmacyId2(new PharmacyVO(1L));
		retpat.setPharmacyByPreferredPharmacyId3(new PharmacyVO(1L));
		retpat.getNextOfKin().setFirstName("up-nkinfirst");
		retpat.getNextOfKin().getAddress().setWorkPhone(new BigInteger("1231234567"));
		retpat.getAdditionalInfo().setMotherFirstName("up-momfirst");
		retpat.getNote().setNote("updated-note");
		retpat.setRace("White");
		
		url = "http://localhost:9080/PMS-WebApp/restapp/patient/"+retpat.getPatientId();
		entity = new HttpEntity<PatientVO>(retpat,headers);
		resp = restTemplate.exchange(url,HttpMethod.PUT, entity, PatientVO.class, new Object[] {});
		System.out.println(resp.getBody());
		
		retpat = (PatientVO)resp.getBody();	
	}
	
	*/
	
	

}
