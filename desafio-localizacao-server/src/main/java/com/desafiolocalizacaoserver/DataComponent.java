package com.desafiolocalizacaoserver;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

import com.desafiolocalizacaoserver.model.Employee;
import com.desafiolocalizacaoserver.model.Store;
import com.desafiolocalizacaoserver.service.EmployeeService;
import com.desafiolocalizacaoserver.service.StoreService;
import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;

@Component
public class DataComponent {

	Logger logger = LoggerFactory.getLogger(DataComponent.class);
	
	private ResourceLoader resourceLoader;

    public DataComponent(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

	@Autowired
	private EmployeeService employeeService;

	@Autowired
	private StoreService storeService;
    
    @PostConstruct
	public void setupData() throws IOException {
    	logger.info("Lendo dados do arquivo funcionarios.csv.");
		List<Object> objects = readDataFromCSV("classpath:data/funcionarios.csv", Employee.class);
		employeeService.initialStore(objects.stream().map(employee -> (Employee) employee).collect(Collectors.toList()));

		logger.info("Lendo dados do arquivo lojas.csv.");
		objects = readDataFromCSV("classpath:data/lojas.csv", Store.class);
		storeService.initialStore(objects.stream().map(store -> (Store) store).collect(Collectors.toList()));
	}

    private List<Object> readDataFromCSV(String filename, Class<?> clazz) throws IOException {
		Resource resource = resourceLoader.getResource(filename);
        InputStream inputStream = resource.getInputStream();
		CsvMapper mapper = new CsvMapper();

		CsvSchema schema = mapper.schemaFor(clazz).withHeader();
		MappingIterator<Object> values = mapper.readerFor(clazz).with(schema).readValues(inputStream);
		List<Object> list = values.readAll();
		return list;
	}

}
