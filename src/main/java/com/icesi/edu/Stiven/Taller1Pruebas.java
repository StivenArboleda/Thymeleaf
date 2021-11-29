package com.icesi.edu.Stiven;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.util.Date;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import com.icesi.edu.Stiven.model.person.Address;
import com.icesi.edu.Stiven.model.person.Addresstype;
import com.icesi.edu.Stiven.model.person.Businessentity;
import com.icesi.edu.Stiven.model.person.Businessentityaddress;
import com.icesi.edu.Stiven.model.person.Businessentitycontact;
import com.icesi.edu.Stiven.model.person.Contacttype;
import com.icesi.edu.Stiven.model.person.Person;
import com.icesi.edu.Stiven.model.person.Stateprovince;
import com.icesi.edu.Stiven.model.person.UserModel;
import com.icesi.edu.Stiven.model.person.UserType;
import com.icesi.edu.Stiven.service.impl.AddressService;
import com.icesi.edu.Stiven.service.impl.AddressTypeService;
import com.icesi.edu.Stiven.service.impl.BusinessEntityAddressService;
import com.icesi.edu.Stiven.service.impl.BusinessEntityContactService;
import com.icesi.edu.Stiven.service.impl.BusinessEntityService;
import com.icesi.edu.Stiven.service.impl.ContactTypeService;
import com.icesi.edu.Stiven.service.impl.PersonService;
import com.icesi.edu.Stiven.service.impl.StateProvinceService;
import com.icesi.edu.Stiven.service.impl.UserService;
import com.icesi.edu.Stiven.service.inter.IAddressService;
import com.icesi.edu.Stiven.service.inter.IAddressTypeService;
import com.icesi.edu.Stiven.service.inter.IBusinessEntityAddressService;
import com.icesi.edu.Stiven.service.inter.IBusinessEntityContactService;
import com.icesi.edu.Stiven.service.inter.IBusinessEntityService;
import com.icesi.edu.Stiven.service.inter.IContactTypeService;
import com.icesi.edu.Stiven.service.inter.IPersonService;
import com.icesi.edu.Stiven.service.inter.IStateProvinceService;


@SpringBootApplication
@ComponentScan(basePackages = {"com.icesi.edu.Stiven"})
public class Taller1Pruebas {

	public static void main(String[] args) {
		ConfigurableApplicationContext c = SpringApplication.run(Taller1Pruebas.class, args);
		
		UserService u = c.getBean(UserService.class);
		
		//USUARIO ADMINISTRADOR
		
		UserModel u1 = new UserModel();
		u1.setId(123);
		u1.setUserName("admin");
		u1.setPassword("{noop}admin");
		u1.setType(UserType.Administrador);
		
		u.save(u1);
		
		//USUARIO OPERADOR
		
		UserModel u2 = new UserModel();
		u2.setId(1234);
		u2.setUserName("Stiven");
		u2.setPassword("{noop}admin");
		u2.setType(UserType.Operador);
		
		u.save(u2);
		
		//ENTIDAD DE NEGOCIO
		IBusinessEntityService bs = c.getBean(BusinessEntityService.class);
		Businessentity be = new Businessentity();
		
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		Date dateBe;
		try {
			dateBe = df.parse("22/03/2020");
			long time1 = dateBe.getTime();
			Timestamp ModiDate = new Timestamp(time1);
			be.setModifieddate(ModiDate);
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		
		be = bs.save(be);
		
		//PERSON
		IPersonService p = c.getBean(PersonService.class);
		Person p1 = new Person();
		p1.setFirstname("Stiven");
		p1.setLastname("Arboleda");
		p1.setEmailpromotion(5);
		p1.setTitle("PROGRAMA STIVEN");
		
		//FECHA PARA PERSONA
		DateFormat df1 = new SimpleDateFormat("yyyy-mm-dd");
		Date date1;

		try {
			
			date1 = df1.parse("2020-07-22");
			long time1 = date1.getTime();
			Timestamp ModiDate = new Timestamp(time1);
			p1.setModifieddate(ModiDate);
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		
		be.setPerson(p1);
		p.saveCorrect(p1);
		
		
		IPersonService ps = c.getBean(PersonService.class);
		Person p2 = new Person();
		p2.setFirstname("Jonh");
		p2.setLastname("Gallego");
		p2.setEmailpromotion(55);
		p2.setTitle("PROGRAMA STIVEN");
		
		//FECHA PARA PERSONA
		DateFormat df2 = new SimpleDateFormat("yyyy-mm-dd");
		Date date2;
		try {
			date2 = df2.parse("2020-06-19");
			long time2 = date2.getTime();
			Timestamp ModiDate = new Timestamp(time2);
			p2.setModifieddate(ModiDate);
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		
		be.setPerson(p2);
		ps.saveCorrect(p2);
		
				
		//AÑADIR STATE PROVINCE
		
		IAddressService as = c.getBean(AddressService.class);
		Address address = new Address();
		
		address.setAddressline1("Carrera");
		address.setCity("Cali");
		address.setPostalcode("0032");
		address.setModifieddate(Timestamp.from(Instant.now()));
		
		Address address2 = new Address();
		
		address2.setAddressline1("Carrera");
		address2.setCity("Bogotá");
		address2.setPostalcode("00324");
		address2.setModifieddate(Timestamp.from(Instant.now()));
		
		IStateProvinceService sps = c.getBean(StateProvinceService.class);
		Stateprovince state = new Stateprovince();
		
		state.setName("Colombia");
		state.setStateprovincecode("57");
		state.setIsonlystateprovinceflag("Amarillo, azul y rojo");
		
		state = sps.saveCorrect(state);
		address.setStateprovince(state);
		address2.setStateprovince(state);
		as.save(address);
		as.save(address2);
		
		Stateprovince state1 = new Stateprovince();
		
		state1.setName("Chile");
		state1.setStateprovincecode("570");
		state1.setIsonlystateprovinceflag("rojo");
		
		state1 = sps.saveCorrect(state1);
		
		Stateprovince state2 = new Stateprovince();
		
		state2.setName("Medellin");
		state2.setStateprovincecode("22");
		state2.setIsonlystateprovinceflag("Verde");
		
		state2 = sps.saveCorrect(state2);
		
		//tercer servicio businessentityaddress
		
		IBusinessEntityAddressService bea = c.getBean(BusinessEntityAddressService.class);
		Businessentityaddress bentity = new Businessentityaddress();
		Businessentityaddress bentity2 = new Businessentityaddress();
		IAddressTypeService ats = c.getBean(AddressTypeService.class);
		Addresstype at = new Addresstype();
		at.setName("Carrera");
		at = ats.save(at);
		Addresstype at2 = new Addresstype();
		at2.setName("Avenida");
		at2 = ats.save(at2);
		Addresstype at3 = new Addresstype();
		at3.setName("Calle");
		at3 = ats.save(at3);
		Addresstype at4 = new Addresstype();
		at4.setName("Diagonal");
		at4 = ats.save(at4);
		//bentity.setAddress(address);
		//bentity.setAddresstype(at);
		//bentity.setBusinessentity(be);
		
		bentity = bea.save(bentity, address.getAddressid(), at.getAddresstypeid(), be.getBusinessentityid());
		bea.save(bentity2, address2.getAddressid(), at2.getAddresstypeid(), be.getBusinessentityid());
		
		//BUSINESS ENTITY CONTACT
		
		IBusinessEntityContactService bec = c.getBean(BusinessEntityContactService.class);
		Businessentitycontact bc = new Businessentitycontact();
		IContactTypeService cts = c.getBean(ContactTypeService.class);
		Contacttype ct = new Contacttype();
		ct = cts.save(ct);
		
		
		bec.save(bc, be.getBusinessentityid(), ct.getContacttypeid(), p1.getBusinessentityid());
	}
	
	
}
