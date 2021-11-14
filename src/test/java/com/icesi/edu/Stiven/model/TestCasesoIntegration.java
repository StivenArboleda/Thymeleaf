package com.icesi.edu.Stiven.model;


import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.sql.Timestamp;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import org.hibernate.service.spi.InjectService;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.icesi.edu.Stiven.model.person.Address;
import com.icesi.edu.Stiven.model.person.Addresstype;
import com.icesi.edu.Stiven.model.person.Businessentity;
import com.icesi.edu.Stiven.model.person.Businessentityaddress;
import com.icesi.edu.Stiven.model.person.BusinessentityaddressPK;
import com.icesi.edu.Stiven.model.person.Person;
import com.icesi.edu.Stiven.model.person.Personphone;
import com.icesi.edu.Stiven.model.person.PersonphonePK;
import com.icesi.edu.Stiven.model.person.Phonenumbertype;
import com.icesi.edu.Stiven.model.person.Stateprovince;
import com.icesi.edu.Stiven.repositories.AddressRepository;
import com.icesi.edu.Stiven.repositories.BusinessEntityAddressRepository;
import com.icesi.edu.Stiven.repositories.BusinessEntityRepository;
import com.icesi.edu.Stiven.repositories.PersonPhoneRepository;
import com.icesi.edu.Stiven.repositories.PersonRepository;
import com.icesi.edu.Stiven.repositories.StateProvinceRepository;
import com.icesi.edu.Stiven.service.impl.AddressService;
import com.icesi.edu.Stiven.service.impl.BusinessEntityAddressService;
import com.icesi.edu.Stiven.service.impl.BusinessEntityService;
import com.icesi.edu.Stiven.service.impl.PersonPhoneService;
import com.icesi.edu.Stiven.service.impl.PersonService;

import org.junit.jupiter.api.TestInstance.Lifecycle;

@SpringBootTest
@TestInstance(Lifecycle.PER_CLASS)
@TestMethodOrder(OrderAnnotation.class)
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = TestCasesoIntegration.class)
class TestCasesoIntegration {
	
	//Mocks para el punto 5A
	
	@Mock
	private PersonRepository personRepo;
	@InjectMocks
	private PersonService personServi;
	@Mock
	private BusinessEntityRepository businessentityRepo;
	@InjectMocks
	private BusinessEntityService businessentityServi;
	
	//Mocks para el punto 5B
	
	@Mock
	private AddressRepository addressRepo;
	@InjectMocks
	private AddressService addressServi;
	@Mock
	private StateProvinceRepository stateprovinceRepo;
	@InjectMocks
	private StateProvinceRepository stateprovinceServi;
	
	//Mocks para el punto 5C
	
	@Mock
	private BusinessEntityAddressRepository businessentityaddresRepo;
	@InjectMocks
	private BusinessEntityAddressService businessentityaddressServi;

	//Mocks para el punto 5D
	
	@Mock
	private PersonPhoneRepository personphoneRepo;
	@InjectMocks
	private PersonPhoneService personphoneServi;
	
	public TestCasesoIntegration(PersonService personServi, BusinessEntityService businessentityServi, AddressService addressServi, 
			BusinessEntityAddressService businessentityaddressServi, PersonPhoneService personphoneServi) {
		
		this.personServi = personServi;
		this.businessentityServi = businessentityServi;
		this.addressServi = addressServi;
		this.businessentityaddressServi = businessentityaddressServi;
		this.personphoneServi = personphoneServi;
		
	}
	
	//================================================================================================
	//======================================TEST's PARA PUNTO 5A======================================
	//================================================================================================
	
	
	/*
	 * Caso 1 para guardar una persona
	 * Agrega el titulo (puede ser opcional)
	 * Valores correctos
	 */
	@Test
	@Order(1)
	public void savePersonTest() {
		
		Businessentity be = new Businessentity();
		when(businessentityRepo.findById(1)).thenReturn(Optional.of(be));
		
		Person p = new Person();
		when(personRepo.findById(1)).thenReturn(Optional.of(p));
		
		Businessentity beObject = businessentityServi.findById(1).get();
		//Person pObject = personServi.findId(1).get();
		
		beObject.setPerson(p);		
		
		long moddifiedDate = System.currentTimeMillis();
		Timestamp realModdified = new Timestamp(moddifiedDate);
		
		String namePerson = "Jhon";
		String lastnamePerson = "Arboleda";
		String title = "Proof";
		
		p.setFirstname(namePerson);
		p.setLastname(lastnamePerson);
		p.setTitle(title);
		p.setModifieddate(realModdified);
		
		personServi.save(p);
		
		Person theProof = personServi.findId(1).get();
		//VERIFICO CON EL TITULO AGREGADO
		
		assertEquals(theProof.getFirstname(), namePerson);
		assertEquals(theProof.getLastname(), lastnamePerson);
		assertNotNull(theProof.getModifieddate());
		assertEquals(theProof.getTitle(), title);
		
		verify(personRepo).save(p);
		verify(personRepo).findById(1);
	}
	/*
	 * Segundo caso para guadar pesona
	 * Sin titulo, ya que es opcional
	 * Excepcion porque el nombre es de menos de 3 caracteres
	 */
	@Test
	@Order(2)
	public void savePersonTwoTest() {
		
		Businessentity be = new Businessentity();
		when(businessentityRepo.findById(1)).thenReturn(Optional.of(be));
		
		Person p = new Person();
		when(personRepo.findById(1)).thenReturn(Optional.of(p));
		
		Businessentity beObject = businessentityServi.findById(1).get();
		//Person pObject = personServi.findId(1).get();
		
		beObject.setPerson(p);		
		
		long moddifiedDate = System.currentTimeMillis();
		Timestamp realModdified = new Timestamp(moddifiedDate);
		
		String namePerson = "Ab";
		String lastnamePerson = "Arboleda";
		//String title = "Proof";
		
		p.setFirstname(namePerson);
		p.setLastname(lastnamePerson);
		//p.setTitle(title);
		p.setModifieddate(realModdified);
		
		personServi.save(p);
		
		Person theProof = personServi.findId(1).get();
		//VERIFICO EXCEPCION POR NOMBRE CORTO
		//TITULO OPCIONAL
		
		assertThrows(IllegalArgumentException.class, () -> 
		personServi.save(theProof));
				
		assertEquals(theProof.getLastname(), lastnamePerson);
		assertNotNull(theProof.getModifieddate());
		assertNull(theProof.getTitle());
		
		verify(personRepo).save(p);			//VERIFICA QUE REALMENTE ENTRE AL METODO
		verify(personRepo).findById(1);		//VERIFICA QUE REALMENTE ENTRE AL METODO
	}
	/*
	 * Caso 1 para editar una persona
	 * Todos los datos correctos
	 */
	@Test
	@Order(3)
	public void editPerson() {
		
		Businessentity be = new Businessentity();
		when(businessentityRepo.findById(1)).thenReturn(Optional.of(be));
		
		Person p = new Person();
		when(personRepo.findById(1)).thenReturn(Optional.of(p));
		
		Businessentity beObject = businessentityServi.findById(1).get();
		//Person pObject = personServi.findId(1).get();
		
		beObject.setPerson(p);
		
		String namePerson = "Stiven";
		String lastnamePerson = "Arboleda";
		String title = "The TITLE";
		
		p.setFirstname(namePerson);
		p.setLastname(lastnamePerson);
		p.setTitle(title);
		
		personServi.save(p);
		Person comparation = personServi.findId(1).get();
		
		//VALORES PARA EDITAR
		
		String namePerson2 = "Jhon";
		String lastnamePerson2 = "Arboleda";
		String title2 = "The TITLE";
		
		personServi.editPerson(1, "", "", null, namePerson2, lastnamePerson2, "", 
				null, "", "", null, "", title2);
		
		Person theProof = personServi.findId(1).get();
		
		assertTrue(!(theProof.getFirstname().equalsIgnoreCase(comparation.getFirstname())));
		
	}
	/*
	 * Caso 2 para editar una persona
	 * No deja agregar por apellido menor de 3 caracteres
	 */
	@Test
	@Order(4)
	public void editPersonOne() {
		
		Businessentity be = new Businessentity();
		when(businessentityRepo.findById(1)).thenReturn(Optional.of(be));
		
		Person p = new Person();
		when(personRepo.findById(1)).thenReturn(Optional.of(p));
		
		Businessentity beObject = businessentityServi.findById(1).get();
		//Person pObject = personServi.findId(1).get();
		
		beObject.setPerson(p);
		
		//PROBAR QUE NO DEJA EDITAR CON APELLIDO DE MENOS DE 3 CARACTERES
		String namePerson = "Stiven";
		String lastnamePerson = "A";
		String title = "The TITLE";
		
		p.setFirstname(namePerson);
		p.setLastname(lastnamePerson);
		p.setTitle(title);
		
		personServi.save(p);
		
		assertThrows(IllegalArgumentException.class, () ->
		personServi.editPerson(1, "", "", null, namePerson, lastnamePerson, "", null, "", "", null, "", title));
		
	}
	
	//================================================================================================
	//======================================TEST's PARA PUNTO 5B======================================
	//================================================================================================
	
	/*
	 * Caso 1 para guardar una dirección
	 * Datos correctos
	 */
	@Test
	@Order(5)
	public void saveAddress() {
		
		Address a = new Address();
		when(addressRepo.findById(1)).thenReturn(Optional.of(a));
		
		Stateprovince sp = new Stateprovince();
		when(stateprovinceRepo.findById(1)).thenReturn(Optional.of(sp));
		
		//Address aObject = addressServi.findById(1).get();
		
		a.setStateprovince(sp);
		
		//VALORES PARA GUARDAR
		
		String address1 = "cra 46";
		String city = "Cali";
		String codePostal = "322";
		
		a.setAddressline1(address1);
		a.setCity(city);
		a.setPostalcode(codePostal);
		
		addressServi.save(a);
		
		Address theProof = addressServi.findById(1).get();
		
		assertEquals(a.getAddressline1(), theProof.getAddressline1());
		assertEquals(a.getCity(), theProof.getCity());
		assertEquals(a.getPostalcode(), theProof.getPostalcode());
		assertNotEquals(a.getCity(), "Medellin");
		assertNotEquals(a.getPostalcode(), "NO ES");
		assertNotEquals(a.getAddressline1(), "Universidad ICESI");
		assertFalse(a.getStateprovince() == null);
		
		verify(addressServi).save(a);
		verify(addressServi).findById(1);
	}
	/*
	 * Caso 2 para guardar una direccion
	 * No existe el state province para guardar
	 * Lanza excepcion por falta de state province
	 */
	@Test
	@Order(6)
	public void saveAddressOne() {
		
		Address a = new Address();
		when(addressRepo.findById(1)).thenReturn(Optional.of(a));
		
		Stateprovince sp = new Stateprovince();
		when(stateprovinceRepo.findById(1)).thenReturn(Optional.of(sp));
				
		//a.setStateprovince(sp); //NO SETEO EL ESTADO DE PROVINCIA
		
		//VALORES PARA GUARDAR
		
		String address1 = "cra 46";
		String city = "Cali";
		String codePostal = "322";
		
		a.setAddressline1(address1);
		a.setCity(city);
		a.setPostalcode(codePostal);
		
		addressServi.save(a);
		
		Address theProof = addressServi.findById(1).get();
		
		assertThrows(IllegalArgumentException.class, ()->
				addressServi.save(theProof));
		
		verify(addressServi).save(a);
		verify(addressServi).findById(1);
	}
	/*
	 * Caso 3 para guardar
	 * Algun datos vacio de los que son necesarios
	 * Lanza exception
	 */
	@Test
	@Order(7)
	public void saveAddressTwo() {
		
		Address a = new Address();
		when(addressRepo.findById(1)).thenReturn(Optional.of(a));
		
		Stateprovince sp = new Stateprovince();
		when(stateprovinceRepo.findById(1)).thenReturn(Optional.of(sp));
		
		//Address aObject = addressServi.findById(1).get();
		
		a.setStateprovince(sp);
		
		//VALORES PARA GUARDAR
		
		String address1 = "cra 46";
		String city = "Cali";
		String codePostal = ""; //vacio
		
		a.setAddressline1(address1);
		a.setCity(city);
		a.setPostalcode(codePostal);
		
		addressServi.save(a);
		
		Address theProof = addressServi.findById(1).get();
		
		assertThrows(IllegalArgumentException.class, ()->  //FALTA CODIGO POSTAL
				addressServi.save(theProof));
		
		verify(addressServi).save(a);
		verify(addressServi).findById(1);
	}
	/*
	 * Caso 1 para editar persona
	 * Campo necesario vacio
	 * Lanza excepcion
	 */
	@Test
	@Order(8)
	public void editAddress() {
		
		Address a = new Address();
		when(addressRepo.findById(1)).thenReturn(Optional.of(a));
		
		Stateprovince sp = new Stateprovince();
		when(stateprovinceRepo.findById(1)).thenReturn(Optional.of(sp));
		
		//Address aObject = addressServi.findById(1).get();
		
		a.setStateprovince(sp);
		
		//VALORES PARA EDITAR MALOS
		
		String address1 = "cra 46";
		String city = "";			//VERIFICAR EXCEPTION POR CAMPO VACIO
		String codePostal = "322";
		
		a.setAddressline1(address1);
		a.setCity(city);
		a.setPostalcode(codePostal);
		
		addressServi.save(a);
		
		Address theProof = addressServi.findById(null).get();
		
		assertThrows(IllegalArgumentException.class, ()->
				addressServi.editAddress(1, address1, "", city, null, codePostal, null, ""));
		assertNotEquals(a.getAddressline1(), theProof.getAddressline1());
		assertNotEquals(a.getPostalcode(), theProof.getPostalcode());
	}
	/*
	 * Caso 2 para editar person
	 * Campo necesario vacio
	 * Lanza excepcion
	 */
	@Test
	@Order(9)
	public void editAddressOne() {
		
		Address a = new Address();
		when(addressRepo.findById(1)).thenReturn(Optional.of(a));
		
		Stateprovince sp = new Stateprovince();
		when(stateprovinceRepo.findById(1)).thenReturn(Optional.of(sp));
		
		//Address aObject = addressServi.findById(1).get();
		
		a.setStateprovince(sp);
		
		//VALORES PARA EDITAR MALOS
		
		String address1 = "";   	//VERIFICAR EXCEPTION POR CAMPO VACIO
		String city = "Cali";		
		String codePostal = "322";
		
		a.setAddressline1(address1);
		a.setCity(city);
		a.setPostalcode(codePostal);
		
		addressServi.save(a);
		
		Address theProof = addressServi.findById(null).get();
		
		assertThrows(IllegalArgumentException.class, ()->
				addressServi.editAddress(1, address1, "", city, null, codePostal, null, ""));
		assertNotEquals(a.getAddressline1(), theProof.getAddressline1());
		assertNotEquals(a.getPostalcode(), theProof.getPostalcode());
	}
	/*
	 * Caso 3 para editar person
	 * Campo necesario vacio
	 * Lanza excepcion
	 */
	@Test
	@Order(10)
	public void editAddressTwo() {
		
		Address a = new Address();
		when(addressRepo.findById(1)).thenReturn(Optional.of(a));
		
		Stateprovince sp = new Stateprovince();
		when(stateprovinceRepo.findById(1)).thenReturn(Optional.of(sp));
		
		//Address aObject = addressServi.findById(1).get();
		
		a.setStateprovince(sp);
		
		//VALORES PARA EDITAR MALOS
		
		String address1 = "cra 46";
		String city = "Cali";			
		String codePostal = ""; 	//VERIFICAR EXCEPTION POR CAMPO VACIO
		
		a.setAddressline1(address1);
		a.setCity(city);
		a.setPostalcode(codePostal);
		
		addressServi.save(a);
		
		Address theProof = addressServi.findById(null).get();
		
		assertThrows(IllegalArgumentException.class, ()->
				addressServi.editAddress(1, address1, "", city, null, codePostal, null, ""));
		assertNotEquals(a.getAddressline1(), theProof.getAddressline1());
		assertNotEquals(a.getPostalcode(), theProof.getPostalcode());
	}
	/*
	 * Caso 4 para editar direccion
	 * Campos llenos y buenos
	 */
	@Test
	@Order(11)
	public void editAddressThree() {
		
		Address a = new Address();
		when(addressRepo.findById(1)).thenReturn(Optional.of(a));
		
		Stateprovince sp = new Stateprovince();
		when(stateprovinceRepo.findById(1)).thenReturn(Optional.of(sp));
		
		//Address aObject = addressServi.findById(1).get();
		
		a.setStateprovince(sp);
			
		//VALORES PARA EDITAR BUENOS
		String address11 = "UNIVERSIDAD ICESI";
		String city1 = "Bogota";
		String codePostal1 = "00054";
		
		a.setAddressline1(address11);
		a.setCity(city1);
		a.setPostalcode(codePostal1);
		
		addressServi.save(a);
		
		Address theProof = addressServi.findById(1).get();
		
		assertEquals(a.getAddressline1(), theProof.getAddressline1());
		assertEquals(a.getPostalcode(), theProof.getPostalcode());
		assertEquals(a.getCity(), "Bogota");
		assertNotEquals(a.getAddressline1(), "cra 46");
		assertTrue(a.getStateprovince() != null);
		
		//VALORES NUEVOS PARA COMPARAR
		String address12 = "cra46";
		String city2 = "Cali";
		String codePostal2 = "322";
		
		addressServi.editAddress(1, address12, "", city2, null, codePostal2, null, "");
		addressServi.save(a);
		
		Address theProof2 = addressServi.findById(1).get();
		
		assertEquals(a.getAddressline1(), theProof2.getAddressline1());
		assertEquals(a.getPostalcode(), theProof2.getPostalcode());
		assertEquals(a.getCity(), "Cali");
		assertNotEquals(a.getAddressline1(), "UNIVERSIDAD ICESI");
		
	}
	
	//================================================================================================
	//======================================TEST's PARA PUNTO 5C======================================
	//================================================================================================
	
	/*
	 * CASO 1 para guardar una direccion de entidad de negocio
	 * todos los datos bien
	 * Guarda normal
	 */
	@Test
	@Order(12)
	public void saveBusinessentityAddress() {
		
		BusinessentityaddressPK bsaPK = new BusinessentityaddressPK(); //LLAVE FORANEA PARA direccion de entidad negocio
		Addresstype aType = new Addresstype();
		
		Businessentity be = new Businessentity();
		when(businessentityRepo.findById(1)).thenReturn(Optional.of(be));
		
		Address a = new Address();
		when(addressRepo.findById(1)).thenReturn(Optional.of(a));
		
		Businessentityaddress bsa = new Businessentityaddress();
		when(businessentityaddressServi.findById(bsaPK)).thenReturn(Optional.of(bsa));
		
		//bsa.setId(bsaPK);		//LLAVE FORANEA
		
		bsa.setBusinessentity(be);	//ENTIDAD DE NEGOCIO
		bsa.setAddress(a);			//DIRECCION
		bsa.setAddresstype(aType);	//TIPO DE DIRECCION	
		
		
		bsaPK.setAddressid(22);
		bsaPK.setAddresstypeid(03);
		bsaPK.setBusinessentityid(10);
		
		bsa.setId(bsaPK);
		
		businessentityaddressServi.save(bsa);
		
		Businessentityaddress theProof = businessentityaddressServi.findById(bsaPK).get();
		
		assertNotNull(theProof.getId());
		assertNotNull(bsa.getId());
		
		assertEquals(bsa.getId().getAddressid(), theProof.getId().getAddressid());
		assertEquals(bsa.getId().getAddresstypeid(), theProof.getId().getAddresstypeid());
		assertEquals(bsa.getId().getBusinessentityid(), theProof.getId().getBusinessentityid());
		
	}
	
	/*
	 * CASO 2 para guardar una direccion de entidad de negocio
	 * Falta una llave foránea
	 * Lanza exception
	 */
	@Test
	@Order(13)
	public void saveBusinessentityAddressOne() {
		
		BusinessentityaddressPK bsaPK = new BusinessentityaddressPK(); //LLAVE FORANEA PARA direccion de entidad negocio
		Addresstype aType = new Addresstype();
		
		Businessentity be = new Businessentity();
		when(businessentityRepo.findById(1)).thenReturn(Optional.of(be));
		
		Address a = new Address();
		when(addressRepo.findById(1)).thenReturn(Optional.of(a));
		
		Businessentityaddress bsa = new Businessentityaddress();
		when(businessentityaddressServi.findById(bsaPK)).thenReturn(Optional.of(bsa));
		
		//bsa.setId(bsaPK);		//LLAVE FORANEA
		
		bsa.setBusinessentity(be);	//ENTIDAD DE NEGOCIO
		bsa.setAddress(a);			//DIRECCION
		bsa.setAddresstype(aType);	//TIPO DE DIRECCION	
		
		
		bsaPK.setAddressid(null); 	//LLAVE NULL PARA LANZAR EXCEPTION
		bsaPK.setAddresstypeid(03);
		bsaPK.setBusinessentityid(10);
		
		bsa.setId(bsaPK);
		
		businessentityaddressServi.save(bsa);
		
		Businessentityaddress theProof = businessentityaddressServi.findById(bsaPK).get();
		
		assertNotNull(theProof.getId());
		assertNotNull(bsa.getId());
		
		assertThrows(NullPointerException.class, ()->
				businessentityaddressServi.save(theProof));
	}
	/*
	 * CASO 3 para guardar una direccion de entidad de negocio
	 * Falta una llave foránea
	 * Lanza exception
	 */
	@Test
	@Order(14)
	public void saveBusinessentityAddressTwo() {
		
		BusinessentityaddressPK bsaPK = new BusinessentityaddressPK(); //LLAVE FORANEA PARA direccion de entidad negocio
		Addresstype aType = new Addresstype();
		
		Businessentity be = new Businessentity();
		when(businessentityRepo.findById(1)).thenReturn(Optional.of(be));
		
		Address a = new Address();
		when(addressRepo.findById(1)).thenReturn(Optional.of(a));
		
		Businessentityaddress bsa = new Businessentityaddress();
		when(businessentityaddressServi.findById(bsaPK)).thenReturn(Optional.of(bsa));
		
		//bsa.setId(bsaPK);		//LLAVE FORANEA
		
		bsa.setBusinessentity(be);	//ENTIDAD DE NEGOCIO
		bsa.setAddress(a);			//DIRECCION
		bsa.setAddresstype(aType);	//TIPO DE DIRECCION	
		
		
		bsaPK.setAddressid(22); 	
		bsaPK.setAddresstypeid(null); //LLAVE NULL PARA LANZAR EXCEPTION
		bsaPK.setBusinessentityid(10);
		
		bsa.setId(bsaPK);
		
		businessentityaddressServi.save(bsa);
		
		Businessentityaddress theProof = businessentityaddressServi.findById(bsaPK).get();
		
		assertNotNull(theProof.getId());
		assertNotNull(bsa.getId());
		
		assertThrows(NullPointerException.class, ()->
				businessentityaddressServi.save(theProof));
	}
	
	/*
	 * CASO 4 para guardar una direccion de entidad de negocio
	 * Falta una llave foránea
	 * Lanza exception
	 */
	@Test
	@Order(14)
	public void saveBusinessentityAddressThree() {
		
		BusinessentityaddressPK bsaPK = new BusinessentityaddressPK(); //LLAVE FORANEA PARA direccion de entidad negocio
		Addresstype aType = new Addresstype();
		
		Businessentity be = new Businessentity();
		when(businessentityRepo.findById(1)).thenReturn(Optional.of(be));
		
		Address a = new Address();
		when(addressRepo.findById(1)).thenReturn(Optional.of(a));
		
		Businessentityaddress bsa = new Businessentityaddress();
		when(businessentityaddressServi.findById(bsaPK)).thenReturn(Optional.of(bsa));
		
		//bsa.setId(bsaPK);		//LLAVE FORANEA
		
		bsa.setBusinessentity(be);	//ENTIDAD DE NEGOCIO
		bsa.setAddress(a);			//DIRECCION
		bsa.setAddresstype(aType);	//TIPO DE DIRECCION	
		
		
		bsaPK.setAddressid(22); 	
		bsaPK.setAddresstypeid(03); 
		bsaPK.setBusinessentityid(null); //LLAVE NULL PARA LANZAR EXCEPTION
		
		bsa.setId(bsaPK);
		
		businessentityaddressServi.save(bsa);
		
		Businessentityaddress theProof = businessentityaddressServi.findById(bsaPK).get();
		
		assertNotNull(theProof.getId());
		assertNotNull(bsa.getId());
		
		assertThrows(NullPointerException.class, ()->
				businessentityaddressServi.save(theProof));
	}
	
	
	/*
	 * CASO 1 para editar una direccion de entidad de negocio
	 * DATOS COMPLETOS
	 * EDITA NORMAL
	 */
	@Test
	@Order(15)
	public void editBusinessentityAddress() {
		
		BusinessentityaddressPK bsaPK = new BusinessentityaddressPK(); //LLAVE FORANEA PARA direccion de entidad negocio
		Addresstype aType = new Addresstype();
		
		Businessentity be = new Businessentity();
		when(businessentityRepo.findById(1)).thenReturn(Optional.of(be));
		
		Address a = new Address();
		when(addressRepo.findById(1)).thenReturn(Optional.of(a));
		
		Businessentityaddress bsa = new Businessentityaddress();
		when(businessentityaddressServi.findById(bsaPK)).thenReturn(Optional.of(bsa));
		
		//bsa.setId(bsaPK);		//LLAVE FORANEA
		
		bsa.setBusinessentity(be);	//ENTIDAD DE NEGOCIO
		bsa.setAddress(a);			//DIRECCION
		bsa.setAddresstype(aType);	//TIPO DE DIRECCION	
		
		
		bsaPK.setAddressid(22); 	
		bsaPK.setAddresstypeid(03); 
		bsaPK.setBusinessentityid(10); //LLAVE NULL PARA LANZAR EXCEPTION
		
		bsa.setId(bsaPK);
		
		businessentityaddressServi.save(bsa);
		
		Businessentityaddress theProof = businessentityaddressServi.findById(bsaPK).get();
		
		assertEquals(bsa.getId().getAddressid(), theProof.getId().getAddressid());
		assertEquals(bsa.getId().getAddresstypeid(), theProof.getId().getAddresstypeid());
		assertEquals(bsa.getId().getBusinessentityid(), theProof.getId().getBusinessentityid());
		assertEquals(theProof.getId().getAddressid(), 22);
		assertNotEquals(theProof.getId().getBusinessentityid(), 15);
		
		//VALORES PARA COMPARAR
		
		bsaPK.setAddressid(100); 	
		bsaPK.setAddresstypeid(50); 
		bsaPK.setBusinessentityid(40);
		
		bsa.setId(bsaPK);
		
		businessentityaddressServi.save(bsa);
		
		Businessentityaddress theProof2 = businessentityaddressServi.findById(bsaPK).get();
		
		assertEquals(bsa.getId().getAddressid(), theProof2.getId().getAddressid());
		assertEquals(bsa.getId().getAddresstypeid(), theProof2.getId().getAddresstypeid());
		assertEquals(bsa.getId().getBusinessentityid(), theProof2.getId().getBusinessentityid());
		assertEquals(theProof.getId().getAddressid(), 50);
		assertEquals(theProof.getId().getBusinessentityid(), 40);
		
	}
	//================================================================================================
	//======================================TEST's PARA PUNTO 5D======================================
	//================================================================================================
	
	/*
	 * CASO 1 guardar telefono de persona
	 * Todos los datos bien
	 * Guarda normal
	 */
	@Test
	@Order(16)
	public void savePersonphone() {
		
		PersonphonePK ppPK = new PersonphonePK();
		Phonenumbertype pny = new Phonenumbertype();
				
		Businessentity be = new Businessentity();
		when(businessentityRepo.findById(1)).thenReturn(Optional.of(be));		
		
		Personphone pp = new Personphone();
		when(personphoneRepo.findById(ppPK)).thenReturn(Optional.of(pp));
		
		
		//COMO SETEO LA ENTIDAD DE NEGOCIO???
		pp.setPhonenumbertype(pny);	//TIPO TELEFONO
					
		ppPK.setBusinessentityid(22);
		ppPK.setPhonenumbertypeid(03);
		ppPK.setPhonenumber("3016927932");
		
		pp.setId(ppPK);
		
		personphoneServi.save(pp);
		
		Personphone theProof = personphoneServi.findById(ppPK).get();
		
		assertNotNull(theProof.getId());
		assertNotNull(pp.getId());
		
		assertEquals(pp.getId().getBusinessentityid(), theProof.getId().getBusinessentityid());
		assertEquals(pp.getId().getPhonenumber(), theProof.getId().getPhonenumber());
		assertEquals(pp.getId().getPhonenumbertypeid(), theProof.getId().getPhonenumbertypeid());
	}
	
	/*
	 * CASO 2 guardar telefono de persona
	 * Falta una llave foránea
	 * Lanza exception
	 */
	@Test
	@Order(17)
	public void savePersonphoneOne() {
		
		PersonphonePK ppPK = new PersonphonePK();
		Phonenumbertype pny = new Phonenumbertype();
				
		Businessentity be = new Businessentity();
		when(businessentityRepo.findById(1)).thenReturn(Optional.of(be));		
		
		Personphone pp = new Personphone();
		when(personphoneRepo.findById(ppPK)).thenReturn(Optional.of(pp));
		

		pp.setPhonenumbertype(pny);
					
		ppPK.setBusinessentityid(null);
		ppPK.setPhonenumbertypeid(03);
		ppPK.setPhonenumber("3016927932");
		
		pp.setId(ppPK);
		
		personphoneServi.save(pp);
		
		Personphone theProof = personphoneServi.findById(ppPK).get();
		
		assertNotNull(theProof.getId());
		assertNotNull(pp.getId());
		
		assertThrows(NullPointerException.class, ()->
				personphoneServi.save(theProof));
		
	}
	
	/*
	 * CASO 3 guardar telefono de persona
	 * Falta una llave foránea
	 * Lanza exception
	 */
	@Test
	@Order(18)
	public void savePersonphoneTwo() {
		
		PersonphonePK ppPK = new PersonphonePK();
		Phonenumbertype pny = new Phonenumbertype();
				
		Businessentity be = new Businessentity();
		when(businessentityRepo.findById(1)).thenReturn(Optional.of(be));		
		
		Personphone pp = new Personphone();
		when(personphoneRepo.findById(ppPK)).thenReturn(Optional.of(pp));
		
		
		pp.setPhonenumbertype(pny);
					
		ppPK.setBusinessentityid(22);
		ppPK.setPhonenumbertypeid(null);
		ppPK.setPhonenumber("3016927932");
		
		pp.setId(ppPK);
		
		personphoneServi.save(pp);
		
		Personphone theProof = personphoneServi.findById(ppPK).get();
		
		assertNotNull(theProof.getId());
		assertNotNull(pp.getId());
		
		assertThrows(NullPointerException.class, ()->
				personphoneServi.save(theProof));
		
	}
	
	/*
	 * CASO 4 guardar telefono de persona
	 * Falta una llave foránea
	 * Lanza exception
	 */
	@Test
	@Order(19)
	public void savePersonphoneThree() {
		
		PersonphonePK ppPK = new PersonphonePK();
		Phonenumbertype pny = new Phonenumbertype();
				
		Businessentity be = new Businessentity();
		when(businessentityRepo.findById(1)).thenReturn(Optional.of(be));		
		
		Personphone pp = new Personphone();
		when(personphoneRepo.findById(ppPK)).thenReturn(Optional.of(pp));
		

		pp.setPhonenumbertype(pny);	
					
		ppPK.setBusinessentityid(22);
		ppPK.setPhonenumbertypeid(03);
		ppPK.setPhonenumber("3016927932");
		
		pp.setId(null);
		
		personphoneServi.save(pp);
		
		Personphone theProof = personphoneServi.findById(ppPK).get();
		
		assertNotNull(theProof.getId());
		assertNotNull(pp.getId());
		
		assertThrows(NullPointerException.class, ()->
				personphoneServi.save(theProof));
		
	}
	
	/*
	 * CASO 5 guardar telefono de persona
	 * Tamaño del es menor a 10 digitos
	 * Lanza exception
	 */
	@Test
	@Order(20)
	public void savePersonphoneFour() {
		
		PersonphonePK ppPK = new PersonphonePK();
		Phonenumbertype pny = new Phonenumbertype();
				
		Businessentity be = new Businessentity();
		when(businessentityRepo.findById(1)).thenReturn(Optional.of(be));		
		
		Personphone pp = new Personphone();
		when(personphoneRepo.findById(ppPK)).thenReturn(Optional.of(pp));
		
		
		pp.setPhonenumbertype(pny);	
					
		ppPK.setBusinessentityid(22);
		ppPK.setPhonenumbertypeid(03);
		ppPK.setPhonenumber("3016922"); //MENOR 10 DIGITOS
		
		pp.setId(ppPK);
		
		personphoneServi.save(pp);
		
		Personphone theProof = personphoneServi.findById(ppPK).get();
		
		assertNotNull(theProof.getId());
		assertNotNull(pp.getId());
		
		assertThrows(NumberFormatException.class, ()->
				personphoneServi.save(theProof));
		
	}
	/*
	 * CASO 6 guardar telefono de persona
	 * Tamaño del es mayor a 10 digitos
	 * Lanza exception
	 */
	@Test
	@Order(21)
	public void savePersonphoneFive() {
		
		PersonphonePK ppPK = new PersonphonePK();
		Phonenumbertype pny = new Phonenumbertype();
				
		Businessentity be = new Businessentity();
		when(businessentityRepo.findById(1)).thenReturn(Optional.of(be));		
		
		Personphone pp = new Personphone();
		when(personphoneRepo.findById(ppPK)).thenReturn(Optional.of(pp));
		
		
		
		pp.setPhonenumbertype(pny);	
					
		ppPK.setBusinessentityid(22);
		ppPK.setPhonenumbertypeid(03);
		ppPK.setPhonenumber("301692793222"); //MAYOR 10 DIGITOS
		
		pp.setId(ppPK);
		
		personphoneServi.save(pp);
		
		Personphone theProof = personphoneServi.findById(ppPK).get();
		
		assertNotNull(theProof.getId());
		assertNotNull(pp.getId());
		
		assertThrows(NumberFormatException.class, ()->
				personphoneServi.save(theProof));
		
	}
	@Test
	@Order(22)
	public void editPersonphone() {
		
		PersonphonePK ppPK = new PersonphonePK();
		Phonenumbertype pny = new Phonenumbertype();
				
		Businessentity be = new Businessentity();
		when(businessentityRepo.findById(1)).thenReturn(Optional.of(be));		
		
		Personphone pp = new Personphone();
		when(personphoneRepo.findById(ppPK)).thenReturn(Optional.of(pp));
		
		
		
		pp.setPhonenumbertype(pny);	
					
		ppPK.setBusinessentityid(22);
		ppPK.setPhonenumbertypeid(03);
		ppPK.setPhonenumber("3016927932"); 
		
		pp.setId(ppPK);
		
		personphoneServi.save(pp);
		
		Personphone theProof = personphoneServi.findById(ppPK).get();
		
		assertNotNull(theProof.getId());
		assertNotNull(pp.getId());
		
		assertEquals(pp.getId().getBusinessentityid(), theProof.getId().getBusinessentityid());
		assertEquals(pp.getId().getPhonenumber(), theProof.getId().getPhonenumber());
		assertEquals(pp.getId().getPhonenumbertypeid(), theProof.getId().getPhonenumbertypeid());
		
		//VALORES NUEVOS PARA EDITAR
		ppPK.setBusinessentityid(520);
		ppPK.setPhonenumbertypeid(30);
		ppPK.setPhonenumber("456287981"); 
		
		pp.setId(ppPK);
		
		personphoneServi.save(pp);
		
		Personphone theProof2 = personphoneServi.findById(ppPK).get();
		
		assertNotNull(theProof.getId());
		assertNotNull(pp.getId());
		
		assertEquals(pp.getId().getBusinessentityid(), theProof2.getId().getBusinessentityid());
		assertEquals(pp.getId().getPhonenumber(), theProof2.getId().getPhonenumber());
		assertEquals(pp.getId().getPhonenumbertypeid(), theProof2.getId().getPhonenumbertypeid());
		assertNotEquals(theProof.getId().getPhonenumbertypeid(), theProof2.getId().getPhonenumbertypeid());
		
	}
	
}
