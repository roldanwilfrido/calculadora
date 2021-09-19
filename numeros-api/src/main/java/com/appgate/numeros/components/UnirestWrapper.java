package com.appgate.numeros.components;

import com.appgate.numeros.exceptions.RecursoException;
import com.appgate.numeros.exceptions.SesionesApiException;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import org.springframework.stereotype.Component;

import static javax.servlet.http.HttpServletResponse.SC_OK;

@Component
public class UnirestWrapper {

    public void llamarServicio(String url) {
        HttpResponse<JsonNode> resp;
        try {
            resp = Unirest.get(url).asJson();
        } catch(Exception e) {
            throw new RecursoException("No es posible acceder a sesiones-api en el momento. Intente más tarde.");
        }
        if (resp.getStatus() != SC_OK)
            throw new SesionesApiException(resp.getBody().getObject().get("errorMessage").toString());

    }
}
