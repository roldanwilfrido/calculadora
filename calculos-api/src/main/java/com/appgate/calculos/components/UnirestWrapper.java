package com.appgate.calculos.components;

import com.appgate.calculos.exceptions.NumerosApiException;
import com.appgate.calculos.exceptions.RecursoException;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

import static javax.servlet.http.HttpServletResponse.SC_OK;

@Component
public class UnirestWrapper {

    public JsonNode getJson(String url) {
        HttpResponse<JsonNode> resp;
        try {
            resp = Unirest.get(url).asJson();
        } catch(Exception e) {
            throw new RecursoException("No es posible acceder a numeros-api en el momento. Intente más tarde.");
        }
        if (resp.getStatus() != SC_OK)
            throw new NumerosApiException(resp.getBody().getObject().get("errorMessage").toString());

        return resp.getBody();
    }

    public JsonNode patch(String url, String json) {
        HttpResponse<JsonNode> resp;
        try {
            resp = Unirest.patch(url)
                    .header("content-type", MediaType.APPLICATION_JSON_VALUE)
                    .body(json).asJson();
        } catch(Exception e) {
            throw new RecursoException("No es posible acceder a numeros-api en el momento. Intente más tarde.");
        }
        if (resp.getStatus() != SC_OK)
            throw new NumerosApiException(resp.getBody().getObject().get("errorMessage").toString());

        return resp.getBody();
    }
}
