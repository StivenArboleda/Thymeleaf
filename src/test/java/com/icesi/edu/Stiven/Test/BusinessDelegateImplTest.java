package com.icesi.edu.Stiven.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.icesi.edu.Stiven.controller.BusinessDelegate;
import com.icesi.edu.Stiven.model.person.Businessentity;
import com.icesi.edu.Stiven.model.person.Person;
import com.icesi.edu.Stiven.model.sales.Customer;
import com.icesi.edu.Stiven.model.sales.Store;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.web.client.ExpectedCount;
import org.springframework.test.web.client.MockRestServiceServer;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class BusinessDelegateImplTest {

    @Autowired
    private BusinessDelegate delegate;

    private MockRestServiceServer server;

    private final ObjectMapper mapper = new ObjectMapper();

    @BeforeEach
    public void createServer() {
        server = MockRestServiceServer.createServer(delegate.getRestTemplate());

    }

//    @Test
//    public void testAddBusinessEntity() {
//        Integer testId = 1;
//
//        Businessentity be = new Businessentity();
//        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
//		Date dateBe;
//		try {
//			dateBe = df.parse("22/03/2020");
//			long time1 = dateBe.getTime();
//			Timestamp ModiDate = new Timestamp(time1);
//			be.setModifieddate(LocalDate.now());
//		} catch (ParseException e1) {
//			e1.printStackTrace();
//		}
//
//        try {
//            server.expect(ExpectedCount.once(),
//                    requestTo(new URI("http://localhost:8080/api/personRest/")))
//                    .andExpect(method(HttpMethod.POST))
//                    .andRespond(withSuccess(mapper.writeValueAsString(be), MediaType.APPLICATION_JSON));
//        } catch (URISyntaxException | JsonProcessingException e) {
//            e.printStackTrace();
//            fail();
//        }
//
//        Businessentity result = delegate.saveEntity(be);
//
//        assertNotNull(result);
//        assertEquals(be.getBusinessentityid(), result.getBusinessentityid());
//
//        server.verify();
//    }

//    @Test
//    void testAddPerson() {
//    	Person p = new Person();
//    	
//    	p.setFirstname("Stiven");
//		p.setLastname("Arboleda");
//		p.setEmailpromotion(5);
//		p.setTitle("PROGRAMA STIVEN");
//		
//		DateFormat df1 = new SimpleDateFormat("yyyy-mm-dd");
//		Date date1;
//
//		try {
//			
//			date1 = df1.parse("2020-07-22");
//			long time1 = date1.getTime();
//			LocalDate ModiDate = LocalDate.now();
//			p.setModifieddate(ModiDate);
//		} catch (ParseException e1) {
//			e1.printStackTrace();
//		}
//
//        try {
//            server.expect(ExpectedCount.once(),
//                    requestTo(new URI("http://localhost:8080/api/personRest/add/")))
//                    .andExpect(method(HttpMethod.POST))
//                    .andRespond(withSuccess(mapper.writeValueAsString(p), MediaType.APPLICATION_JSON));
//        } catch (URISyntaxException | JsonProcessingException e) {
//            e.printStackTrace();
//            fail();
//        }
//        
//        Person pResponse = delegate.addPerson(p);
//
//        assertNotNull(pResponse);
//
//        assertEquals(p.getBusinessentityid(), pResponse.getBusinessentityid());
//
//        server.verify();
//
//    }

    @Test
    void testAddStore() {
        Store st = new Store();

        st.setName("Alejandro");;

        try {
            server.expect(ExpectedCount.once(),
                    requestTo(new URI("http://localhost:8080/api/storeRest/add/")))
                    .andExpect(method(HttpMethod.POST))
                    .andRespond(withSuccess(mapper.writeValueAsString(st), MediaType.APPLICATION_JSON));
        } catch (URISyntaxException | JsonProcessingException e) {
            e.printStackTrace();
            fail();
        }

        Store stResponse = delegate.addStore(st);

        assertNotNull(stResponse);

        assertEquals(st.getBusinessentityid(), stResponse.getBusinessentityid());

        server.verify();
    }

//    @Test
//    void testAddCustomer() {
//    	Store st = new Store();
//
//        st.setName("Alejandro");;
//
//        try {
//            server.expect(ExpectedCount.once(),
//                    requestTo(new URI("http://localhost:8080/api/storeRest/add/")))
//                    .andExpect(method(HttpMethod.POST))
//                    .andRespond(withSuccess(mapper.writeValueAsString(st), MediaType.APPLICATION_JSON));
//        } catch (URISyntaxException | JsonProcessingException e) {
//            e.printStackTrace();
//            fail();
//        }
//        
//        Person p = new Person();
//    	
//    	p.setFirstname("Stiven");
//		p.setLastname("Arboleda");
//		p.setEmailpromotion(5);
//		p.setTitle("PROGRAMA STIVEN");
//		
//		DateFormat df1 = new SimpleDateFormat("yyyy-mm-dd");
//		Date date1;
//
//		try {
//			
//			date1 = df1.parse("2020-07-22");
//			long time1 = date1.getTime();
//			LocalDate ModiDate = LocalDate.now();
//			p.setModifieddate(ModiDate);
//		} catch (ParseException e1) {
//			e1.printStackTrace();
//		}
//
//        try {
//            server.expect(ExpectedCount.once(),
//                    requestTo(new URI("http://localhost:8080/api/personRest/add/")))
//                    .andExpect(method(HttpMethod.POST))
//                    .andRespond(withSuccess(mapper.writeValueAsString(p), MediaType.APPLICATION_JSON));
//        } catch (URISyntaxException | JsonProcessingException e) {
//            e.printStackTrace();
//            fail();
//        }
//        
//        Person pResponse = delegate.addPerson(p);
//
//        Store stResponse = delegate.addStore(st);
//        
//        Customer c = new Customer();
//
//        c.setPersonid(pResponse.getBusinessentityid());
//        c.setPerson(pResponse);
//        c.setStoreid1(stResponse.getBusinessentityid());
//        c.setStore(stResponse);
//
//        try {
//            server.expect(ExpectedCount.once(),
//                    requestTo(new URI("http://localhost:8080/api/customerRest/add/")))
//                    .andExpect(method(HttpMethod.POST))
//                    .andRespond(withSuccess(mapper.writeValueAsString(c), MediaType.APPLICATION_JSON));
//        } catch (URISyntaxException | JsonProcessingException e) {
//            e.printStackTrace();
//            fail();
//        }
//
//        Customer thResponse = delegate.addCustomer(c);
//
//        assertNotNull(thResponse);
//
//        assertEquals(c.getCustomerid(), thResponse.getCustomerid());
//
//        server.verify();
//
//    }

    @Test
    void testDeleteStore() {
        Store s = new Store();

        s.setName("Alejandro");;

        try {
            server.expect(ExpectedCount.once(),
                    requestTo(new URI("http://localhost:8080/api/storeRest/add/")))
                    .andExpect(method(HttpMethod.POST))
                    .andRespond(withSuccess(mapper.writeValueAsString(s), MediaType.APPLICATION_JSON));
        } catch (URISyntaxException | JsonProcessingException e) {
            e.printStackTrace();
            fail();
        }
        delegate.addStore(s);
        server.reset();

        try {
            server.expect(ExpectedCount.once(),
                    requestTo(new URI("http://localhost:8080/api/storeRest/delete/" + s.getBusinessentityid())))
                    .andExpect(method(HttpMethod.DELETE))
                    .andRespond(withSuccess(mapper.writeValueAsString(s), MediaType.APPLICATION_JSON));
        } catch (URISyntaxException | JsonProcessingException e) {
            e.printStackTrace();
            fail();
        }

        delegate.deleteStore(s);

        server.verify();

    }

    @Test
    void testEditStore() {
        Store s = new Store();
        try {
            server.expect(ExpectedCount.once(),
                    requestTo(new URI("http://localhost:8080/api/storeRest/edit/" + s.getBusinessentityid())))
                    .andExpect(method(HttpMethod.PUT))
                    .andRespond(withSuccess(mapper.writeValueAsString(s), MediaType.APPLICATION_JSON));
        } catch (URISyntaxException | JsonProcessingException e) {
            e.printStackTrace();
            fail();
        }

        delegate.editStore(s.getBusinessentityid(), s);

        server.verify();

    }

    @Test
    void testGetStore() {
        Store s = new Store();

        s.setBusinessentityid(1);;

        try {
            server.expect(ExpectedCount.once(),
                    requestTo(new URI("http://localhost:8080/api/storeRest/find/" + s.getBusinessentityid())))
                    .andExpect(method(HttpMethod.GET))
                    .andRespond(withSuccess(mapper.writeValueAsString(s), MediaType.APPLICATION_JSON));
        } catch (URISyntaxException | JsonProcessingException e) {
            e.printStackTrace();
            fail();
        }

        Store sResponse = delegate.getFindByIdStore(s.getBusinessentityid());

        assertNotNull(sResponse);

        assertEquals(s.getBusinessentityid(), sResponse.getBusinessentityid());

        server.verify();
    }

}