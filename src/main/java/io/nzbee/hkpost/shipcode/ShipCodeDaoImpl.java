package io.nzbee.hkpost.shipcode;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Component;
import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import io.nzbee.util.FileStorageServiceUpload;

@Component
public class ShipCodeDaoImpl implements IShipCodeDao {

	private final Logger LOGGER = LoggerFactory.getLogger(getClass());

	@Autowired
	private ShipCodeFileStorageProperties scfsp;
	
	@Autowired
	private IShipCodeSchemaToDTOMapper scMapper;

	@Override
	public List<ShipCodeViewDTO> getAll() {
		LOGGER.debug("called getAll()");

		try {
			Path filePath = Paths.get(scfsp.getShipCodeFile()).normalize();
			Resource resource = new UrlResource(filePath.toUri());
			CsvSchema bootstrapSchema = CsvSchema.emptySchema().withHeader().withColumnSeparator('\t')
					.withQuoteChar('"');

			File file = resource.getFile();
			
			CsvMapper mapper = new CsvMapper();
			MappingIterator<ShipCodeFileSchema> readValues = mapper.readerFor(ShipCodeFileSchema.class)
					.with(bootstrapSchema).readValues(file);

			return readValues.readAll().stream().map(s -> scMapper.toDTO(s)).collect(Collectors.toList()); 

		} catch (IOException e) {
			LOGGER.error(e.toString());
		}
		return new ArrayList<ShipCodeViewDTO>();
	}

//	@Override
//	public List<ShipCodeViewDTO> findAll() {
//		List<ShipCodeViewDTO> lsc = new ArrayList<ShipCodeViewDTO>();
//		lsc.add(new ShipCodeViewDTO("LEG", "EC-Get", "易寄取", "易寄取", "A", new Double(0)));
//		lsc.add(new ShipCodeViewDTO("SMP", "Smart Post", "易送遞", "易送递", "S", new Double(0)));
//		lsc.add(new ShipCodeViewDTO("LCP", "Local Courier Post","本地郵政速遞郵件", "本地邮政速递邮件", "A", new Double(0)));
//		lsc.add(new ShipCodeViewDTO("LPL", "Local Parcel", "本地包裹", "本地包裹", "A", new Double(0)));
//		lsc.add(new ShipCodeViewDTO("LRM", "Local Registered Mail", "本地掛號", "本地挂号", "A", new Double(0)));
//		return lsc;
//	}

}
