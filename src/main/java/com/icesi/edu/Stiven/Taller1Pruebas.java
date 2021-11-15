package com.icesi.edu.Stiven;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import com.icesi.edu.Stiven.model.person.Address;
import com.icesi.edu.Stiven.model.person.Businessentity;
import com.icesi.edu.Stiven.model.person.Person;
import com.icesi.edu.Stiven.model.person.Stateprovince;
import com.icesi.edu.Stiven.model.person.UserModel;
import com.icesi.edu.Stiven.model.person.UserType;
import com.icesi.edu.Stiven.service.impl.AddressService;
import com.icesi.edu.Stiven.service.impl.BusinessEntityService;
import com.icesi.edu.Stiven.service.impl.PersonService;
import com.icesi.edu.Stiven.service.impl.StateProvinceService;
import com.icesi.edu.Stiven.service.impl.UserService;
import com.icesi.edu.Stiven.service.inter.IAddressService;
import com.icesi.edu.Stiven.service.inter.IBusinessEntityService;
import com.icesi.edu.Stiven.service.inter.IPersonService;
import com.icesi.edu.Stiven.service.inter.IStateProvinceService;
import com.icesi.edu.Stiven.service.inter.IUserService;


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
		
		
		IStateProvinceService sps = c.getBean(StateProvinceService.class);
		Stateprovince state = new Stateprovince();
		
		state.setName("Colombia");
		state.setStateprovincecode("57");
		state.setIsonlystateprovinceflag("Amarillo, azul y rojo");
		
		state = sps.saveCorrect(state);
		address.setStateprovince(state);
		
		as.save(address);
		
	}
	
	
}
