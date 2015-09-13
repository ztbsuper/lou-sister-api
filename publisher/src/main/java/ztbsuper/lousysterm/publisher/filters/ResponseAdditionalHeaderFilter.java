package ztbsuper.lousysterm.publisher.filters;

import com.google.common.base.Joiner;
import com.google.common.collect.Lists;
import ztbsuper.lousysterm.base.enums.HeaderKeys;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.ext.Provider;
import java.io.IOException;

@Provider
public class ResponseAdditionalHeaderFilter implements ContainerResponseFilter {
    @Override
    public void filter(ContainerRequestContext request, ContainerResponseContext response) throws IOException {
        response.getHeaders().putSingle("X-Frame-Options", "SAMEORIGIN");
        response.getHeaders().putSingle("Access-Control-Allow-Origin", "*");
        response.getHeaders().putSingle("Access-Control-Allow-Credentials", "true");
        response.getHeaders().putSingle("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT");
        response.getHeaders().putSingle("Access-Control-Allow-Headers",
                Joiner.on(",").join(Lists.newArrayList(
                        "Content-Type",
                        "Accept",
                        HeaderKeys.ACCESS_TOKEN
                )));

        response.getHeaders().putSingle("Cache-Control", "no-cache, must-revalidate");
        response.getHeaders().putSingle("Pragma", "no-cache");

        MediaType mediaType = response.getMediaType();
        if (mediaType != null) {
            String contentType = mediaType.toString();
            if (!contentType.toLowerCase().contains("charset")) {
                response.getHeaders().putSingle("Content-Type", mediaType.withCharset("utf-8").toString());
            }
        }
    }
}