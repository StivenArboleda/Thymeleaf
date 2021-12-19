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
import com.icesi.edu.Stiven.Taller1Pruebas;
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
import org.springframework.test.context.ContextConfiguration;
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

    @Test
    public void testAddBusinessEntity() {
        Businessentity be = new Businessentity();

        try {
            server.expect(ExpectedCount.once(),
                    requestTo(new URI("http://localhost:8080/api/businessRest/")))
                    .andExpect(method(HttpMethod.POST))
                    .andRespond(withSuccess(mapper.writeValueAsString(be), MediaType.APPLICATION_JSON));
        } catch (URISyntaxException | JsonProcessingException e) {
            e.printStackTrace();
            fail();
        }

        Businessentity result = delegate.saveEntity(be);

        assertNotNull(result);
        assertEquals(be.getBusinessentityid(), result.getBusinessentityid());

        server.verify();
    }

    @Test
    void testAddPerson() {
    	Person p = new Person();
    	
    	p.setFirstname("Stiven");
		p.setLastname("Arboleda");
		p.setEmailpromotion(5);
		p.setTitle("PROGRAMA STIVEN");
		
        try {
            server.expect(ExpectedCount.once(),
                    requestTo(new URI("http://localhost:8080/api/personRest/")))
                    .andExpect(method(HttpMethod.POST))
                    .andRespond(withSuccess(mapper.writeValueAsString(p), MediaType.APPLICATION_JSON));
        } catch (URISyntaxException | JsonProcessingException e) {
            e.printStackTrace();
            fail();
        }
        
        Person pResponse = delegate.addPerson(p);

        assertNotNull(pResponse);

        assertEquals(p.getBusinessentityid(), pResponse.getBusinessentityid());

        server.verify();

    }

    @Test
    void testAddCustomer() {
    	Store st = new Store();

        st.setName("Alejandro");;

        Person p = new Person();
    	
    	p.setFirstname("Stiven");
		p.setLastname("Arboleda");
		p.setEmailpromotion(5);
		p.setTitle("PROGRAMA STIVEN");
		
        Customer c = new Customer();

        c.setPersonid(p.getBusinessentityid());
        c.setPerson(p);
        c.setStoreid1(st.getBusinessentityid());
        c.setStore(st);

        try {
            server.expect(ExpectedCount.once(),
                    requestTo(new URI("http://localhost:8080/api/customerRest/")))
                    .andExpect(method(HttpMethod.POST))
                    .andRespond(withSuccess(mapper.writeValueAsString(c), MediaType.APPLICATION_JSON));
        } catch (URISyntaxException | JsonProcessingException e) {
            e.printStackTrace();
            fail();
        }

        Customer cResponse = delegate.addCustomer(c);

        assertNotNull(cResponse);

        assertEquals(c.getCustomerid(), cResponse.getCustomerid());

        server.verify();

    }

    @Test
    void testAddStore() {
        Store st = new Store();

        st.setName("Alejandro");;

        try {
            server.expect(ExpectedCount.once(),
                    requestTo(new URI("http://localhost:8080/api/storeRest/")))
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
    
    @Test
    void testDeleteStore() {
        Store s = new Store();

        s.setName("Alejandro");;

        try {
            server.expect(ExpectedCount.once(),
                    requestTo(new URI("http://localhost:8080/api/storeRest/")))
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
                    requestTo(new URI("http://localhost:8080/api/storeRest/" + s.getBusinessentityid())))
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
                    requestTo(new URI("http://localhost:8080/api/storeRest/" + s.getBusinessentityid())))
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
                    requestTo(new URI("http://localhost:8080/api/storeRest/" + s.getBusinessentityid())))
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