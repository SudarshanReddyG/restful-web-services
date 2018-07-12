package com.sudarshan.rest.restfulwebservices.filter;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

@RestController
public class FilteringController {
	
	@GetMapping("/fetchFilter")
	public StaticFilterBean fetchFilterBean() {
		return new StaticFilterBean("Value1", "Value2", "Value3");
	}
	
	@GetMapping("/fetchFilter-list")
	public List<StaticFilterBean> fetchFilterBeanList() {
		return Arrays.asList(new StaticFilterBean("Value11", "Value12", "Value13"), new StaticFilterBean("Value21", "Value22", "Value23"));
	}
	
	@GetMapping("/fetchDynaFilter")
	public MappingJacksonValue fetchDynamicFilterBean() {
		DynamicFilterBean dynamicFilterBean = new DynamicFilterBean("Value1", "Value2", "Value3");
		
		SimpleBeanPropertyFilter simpleBeanPropertyFilter = SimpleBeanPropertyFilter.filterOutAllExcept("field1", "field3");
		
		FilterProvider filterProvider  = new SimpleFilterProvider().addFilter("DynamicFilter", simpleBeanPropertyFilter);
		
		MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(dynamicFilterBean);
		
		mappingJacksonValue.setFilters(filterProvider);
		
		return mappingJacksonValue;
	}
	
	@GetMapping("/fetchDynaFilterList")
	public MappingJacksonValue fetchDynamicFilterBeanList() {
		List<DynamicFilterBean> dynamicFilterBeanList = Arrays.asList(new DynamicFilterBean("Value11", "Value12", "Value13"), new DynamicFilterBean("Value21", "Value22", "Value23"));
		
		SimpleBeanPropertyFilter simpleBeanPropertyFilter = SimpleBeanPropertyFilter.filterOutAllExcept("field2", "field3");
		
		FilterProvider filterProvider  = new SimpleFilterProvider().addFilter("DynamicFilter", simpleBeanPropertyFilter);
		
		MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(dynamicFilterBeanList);
		
		mappingJacksonValue.setFilters(filterProvider);
		
		return mappingJacksonValue;
	}
	
	
}
