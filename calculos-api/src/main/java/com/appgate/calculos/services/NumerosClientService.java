package com.appgate.calculos.services;

import com.appgate.calculos.components.UnirestWrapper;
import com.appgate.calculos.exceptions.NumerosApiException;
import com.appgate.calculos.models.dto.NumerosDto;
import com.appgate.calculos.utils.Constantes;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mashape.unirest.http.JsonNode;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Log4j2
public class NumerosClientService {

	private final String numerosServiceUrl;

	private final UnirestWrapper unirestWrapper;

	private final ObjectMapper mapper;


	@Autowired
	public NumerosClientService(UnirestWrapper unirestWrapper,
                                @Value("${numeros_api.url}") String numerosServiceUrl) {
		this.unirestWrapper = unirestWrapper;
		this.numerosServiceUrl = numerosServiceUrl;
		this.mapper = new ObjectMapper();
		mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
	}

	public List<NumerosDto> getNumerosXSesion(String sesionId) {
		try {
			JsonNode res = unirestWrapper.getJson(this.numerosServiceUrl + "/numeros/" + sesionId);
			log.info(Constantes.NUMEROS_X_SESION_OBTENIDOS);
			return mapper.readValue(res.getArray().toString(), new TypeReference<List<NumerosDto>>(){});
		} catch (JsonProcessingException e) {
			throw new NumerosApiException(Constantes.ERROR_OBTENIENDO_NUMEROS);
		}
	}
	public void sobreescribirLosNumerosDeUnaSesion(String sesionId, NumerosDto dto) {
		try {
			unirestWrapper
					.patch(this.numerosServiceUrl + "/numeros/" + sesionId, mapper.writeValueAsString(dto));
			log.info(Constantes.NUMEROS_ACTUALIZADOS);
		} catch (JsonProcessingException e) {
			throw new NumerosApiException(Constantes.ERROR_BLANQUEANDO_SESION);
		}
	}
}
