package com.icesi.edu.Stiven.Test;


import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.when;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.icesi.edu.Stiven.Taller1Pruebas;
import com.icesi.edu.Stiven.DAO.AddressDAO;
import com.icesi.edu.Stiven.DAO.AddressTypeDAO;
import com.icesi.edu.Stiven.DAO.BusinessEntityAddressDAO;
import com.icesi.edu.Stiven.DAO.BusinessEntityDAO;
import com.icesi.edu.Stiven.DAO.PersonDAO;
import com.icesi.edu.Stiven.DAO.StateProvinceDAO;
import com.icesi.edu.Stiven.model.person.Address;
import com.icesi.edu.Stiven.model.person.Businessentity;
import com.icesi.edu.Stiven.model.person.Businessentityaddress;
import com.icesi.edu.Stiven.model.person.Person;
import com.icesi.edu.Stiven.model.person.Stateprovince;
import com.icesi.edu.Stiven.service.impl.AddressService;
import com.icesi.edu.Stiven.service.impl.AddressTypeService;
import com.icesi.edu.Stiven.service.impl.BusinessEntityAddressService;
import com.icesi.edu.Stiven.service.impl.BusinessEntityService;
import com.icesi.edu.Stiven.service.impl.PersonService;
import com.icesi.edu.Stiven.service.impl.StateProvinceService;


@ExtendWith(SpringExtension.class)
@DataJpaTest
@ContextConfiguration(classes = Taller1Pruebas.class)
public class PruebasIntegracionDAOS {
	
	@Mock
	private static AddressDAO adRepository;
	
	@InjectMocks
	private static AddressService addressService;
	
	@Mock
	private static StateProvinceDAO spRepository;
	
	@InjectMocks
	private static StateProvinceService stateService;
	
	@Mock
	private static BusinessEntityAddressDAO beaRepository;
	
	@InjectMocks
	private static BusinessEntityAddressService beAdressService;
	
	@Mock
	private static BusinessEntityDAO beRepository;
	
	@InjectMocks
	private static BusinessEntityService beService;
	
	@Mock
	private static AddressTypeDAO adtRepository;
	
	@InjectMocks
	private static AddressTypeService adtService;
	
	@Mock
	private static PersonDAO personRepository;
	
	@InjectMocks
	private static PersonService personService;
	
	private static Address ad;
	private static Stateprovince sp;
	private static Businessentity be;
	private static Businessentityaddress bea;
	private static Person person;
	
	private static LocalDate time;
	private static String date;
	
	@BeforeAll
	public static void setUp() {
		date = Timestamp.from(Instant.now()) + "";
		time = LocalDate.now();
		
		addressService = new AddressService(adRepository, spRepository);
		ad = new Address();
		
		stateService = new StateProvinceService(spRepository);
		sp = new Stateprovince();
		
		beAdressService = new BusinessEntityAddressService(beaRepository, adRepository, adtRepository, beRepository);
		be = new Businessentity();
		
		personService = new PersonService(personRepository, beRepository);
		person = new Person();
	}

	@BeforeEach
	public void init() {
		when(adRepository.findById(1)).thenReturn(ad);
		when(spRepository.findById(1)).thenReturn(sp);
		when(beRepository.findById(1)).thenReturn(be);
		when(beaRepository.findById(1)).thenReturn(bea);
		when(personRepository.findById(1)).thenReturn(person);
	}

	@Test
	@Order(1)
	public void saveAddress() {
		
		List<Address> addresses = adRepository.findAll();
		assertThat(addresses.size(), equalTo(1));
		
		ad.setStateprovince(sp);
		ad.setAddressline1("Calle");
		ad.setCity("Cali");
		ad.setPostalcode("2203");
		ad.setModifieddate(time);
				
		adRepository.save(ad);
		
		addresses = adRepository.findAll();
		assertThat(addresses.size(), equalTo(2));
	}
	
	
	@Test
	@Order(2)
	public void findByIDAddress() {
		Address saved = adRepository.findById(22);
		assertThat(saved, is(nullValue()));
		
	}
	
	@Test
	@Order(3)
	public void getAllAddresses() {

		List<Address> addresses = adRepository.findAll(); 
		assertThat(addresses.size(), equalTo(0));
	}

	
	@Test
	@Order(4)
	public void updateAddress() {
		Address ad = adRepository.findById(1);
		ad.setCity("Cali/Bogota");
		adRepository.update(ad);
		
		assertThat(adRepository.findById(1).getCity(), equalTo("Cali/Bogota"));
	}
	
	@Test
	@Order(5)
	public void findByDate() {
		List<Address> addresses = adRepository.findByDate(date);

		assertThat(addresses.size(), equalTo(0));
	}
	
	//=======================================================================
	//=======================================================================
	//BusinessEntity
	//=======================================================================
	//=======================================================================
	
	@Test
	@Order(6)
	public void saveBusinessentity() {
		be = new Businessentity();
		beRepository.save(be);
		List<Businessentity> entities = beRepository.findAll(); 
		assertThat(entities.size(), equalTo(0));
	}
	
	
	//@Test
	@Order(7)
	public void findByIDBusinessentity() {
		Businessentity saved = beRepository.findById(33);
		assertThat(saved, is(nullValue()));
		
	}
	
	@Test
	@Order(8)
	public void getBusinessentity() {

		List<Businessentity> entities = beRepository.findAll(); 
		assertThat(entities.size(), equalTo(0));
	}

	
	@Test
	@Order(9)
	public void updateBusinessentity() {
		Businessentity be = beRepository.findById(1);
		be.setModifieddate(time);
		beRepository.update(be);
		
		assertThat(beRepository.findById(1).getModifieddate(), equalTo(time));
	}
	
	//==============================================
	//==============================================
	// 					PERSON
	//==============================================
	//==============================================
	
	@Test
	@Order(10)
	public void savePerson() {
		
		beRepository.save(be);
		person.setBusinessentity(beRepository.findById(2));
		person.setFirstname("Alejandro");
		person.setLastname("Garcia");
		person.setTitle("Abogado");
		person.setModifieddate(LocalDate.now());
		
		personRepository.save(person);
		List<Person> persons = personRepository.findAll(); 
		assertThat(persons.size(), equalTo(2));
	}
	
	
	@Test
	@Order(11)
	public void findByIDPerson() {
		Person peSaved = personRepository.findById(22);
		assertThat(peSaved, is(nullValue()));
		
	}
	
	@Test
	@Order(12)
	public void getPersons() {

		List<Person> persons = personRepository.findAll(); 
		assertThat(persons.size(), equalTo(1));
	}

	
	@Test
	@Order(13)
	public void updatePerson() {
		person = personRepository.findById(1);
		person.setFirstname("Stiven");
		personRepository.update(person);
		
		assertThat(personRepository.findById(1).getFirstname(), equalTo("Stiven"));
	}
	
	
	@Test
	@Order(14)
	public void searchByTitle() {
		String title = "Ingeniero";
		List<Person> persons = personRepository.findByTitle(title);
		assertThat(persons.size(), equalTo(1));
	}
	
	@Test
	@Order(15)
	public void searchByDate() {
		List<Person> persons = personRepository.findByDateInterval("2021-11-28", "2021-11-30"); 
		assertThat(persons.size(), equalTo(0));
	}
	
	//==============================================
	//==============================================
	// 					STATE PROVINCE
	//==============================================
	//==============================================
	
	@Test
	@Order(16)
	public void saveStateProvince() {
		Stateprovince sp = new Stateprovince();
		sp.setName("Valle del Cauca");
		spRepository.save(sp);
		List<Stateprovince> states = spRepository.findAll(); 
		assertThat(states.size(), equalTo(2));
	}
	
	
	@Test
	@Order(17)
	public void findByIDStateProvince() {
		Stateprovince spSaved = spRepository.findById(03);
		assertThat(spSaved, is(nullValue()));
		
	}
	
	@Test
	@Order(18)
	public void geStateProvince() {

		List<Stateprovince> states = spRepository.findAll(); 
		assertThat(states.size(), equalTo(1));
	}

	
	@Test
	@Order(19)
	public void updateStateProvince() {
		Stateprovince sp = spRepository.findById(1);
		sp.setName("Valle");
		spRepository.update(sp);
		
		assertThat(spRepository.findById(1).getName(), equalTo("Valle"));
	}

}
