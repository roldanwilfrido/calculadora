package com.appgate.numeros.services;

import com.appgate.numeros.components.UnirestWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class SesionesClientService {

	private final String sesionesServiceUrl;

	private final UnirestWrapper unirestWrapper;


	@Autowired
	public SesionesClientService(UnirestWrapper unirestWrapper,
								 @Value("${sesiones_api.url}") String sesionesServiceUrl) {
		this.unirestWrapper = unirestWrapper;
		this.sesionesServiceUrl = sesionesServiceUrl;
	}

	public void validarSesion(String sesionId) {
		unirestWrapper.llamarServicio(this.sesionesServiceUrl + "/sesiones/" + sesionId);
	}
}
