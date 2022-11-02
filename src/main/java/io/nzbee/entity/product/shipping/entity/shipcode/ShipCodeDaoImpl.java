package io.nzbee.entity.product.shipping.entity.shipcode;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;

import io.nzbee.util.FileStorageProperties;

@Component
public class ShipCodeDaoImpl implements IShipCodeDao {

	private final Logger LOGGER = LoggerFactory.getLogger(getClass());

	@Autowired
	private FileStorageProperties scfsp;

	@Autowired
	private IShipCodeSchemaToDTOMapper scMapper;

	@Override
	public List<ShipCodeViewDTO> getAll() {
		LOGGER.debug("called getAll()");

		try {
			System.out.println(scfsp.getShipCodeFile());
			InputStream resource = this.getFileFromResourceAsStream(scfsp.getShipCodeFile());
			
			//printInputStream(resource);
			// Resource resource = new UrlResource(filePath.toUri());
			CsvSchema bootstrapSchema = CsvSchema.emptySchema().withHeader().withColumnSeparator('\t')
					.withQuoteChar('"');

			CsvMapper mapper = new CsvMapper();
			
			MappingIterator<ShipCodeFileSchema> readValues = mapper.readerFor(ShipCodeFileSchema.class)
					.with(bootstrapSchema).readValues(resource);

			return readValues.readAll().stream().map(s -> scMapper.toDTO(s)).collect(Collectors.toList());

		} catch (IOException e) {
			LOGGER.error(e.toString());
		}
		return new ArrayList<ShipCodeViewDTO>();
	}

	
	private InputStream getFileFromResourceAsStream(String fileName) {

        // The class loader that loaded the class
        ClassLoader classLoader = getClass().getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream(fileName);

        // the stream holding the file content
        if (inputStream == null) {
            throw new IllegalArgumentException("file not found! " + fileName);
        } else {
            return inputStream;
        }

    }
}
