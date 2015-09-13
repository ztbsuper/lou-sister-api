package ztbsuper.lousysterm.publisher.servlets;

import com.wordnik.swagger.config.FilterFactory;
import com.wordnik.swagger.config.Scanner;
import com.wordnik.swagger.config.ScannerFactory;
import com.wordnik.swagger.config.SwaggerConfig;
import com.wordnik.swagger.core.filter.SwaggerSpecFilter;
import com.wordnik.swagger.jaxrs.config.DefaultJaxrsScanner;
import com.wordnik.swagger.model.ApiDescription;
import com.wordnik.swagger.models.Model;
import com.wordnik.swagger.models.Operation;
import com.wordnik.swagger.models.Swagger;
import com.wordnik.swagger.models.parameters.Parameter;
import com.wordnik.swagger.models.properties.Property;

import javax.servlet.ServletConfig;
import java.util.List;
import java.util.Map;

public class LousystermSwaggerConfig implements SwaggerConfig {
    public LousystermSwaggerConfig(ServletConfig servletConfig) {
        Scanner scanner = new DefaultJaxrsScanner();
        ScannerFactory.setScanner(scanner);


        String shouldPrettyPrint = servletConfig.getInitParameter("swagger.pretty.print");
        if (shouldPrettyPrint != null)
            scanner.setPrettyPrint(Boolean.parseBoolean(shouldPrettyPrint));
        FilterFactory.setFilter(new SwaggerSpecFilter() {
            @Override
            public boolean isOperationAllowed(Operation operation, ApiDescription api, Map<String, List<String>> params, Map<String, String> cookies, Map<String, List<String>> headers) {
                return true;
            }

            @Override
            public boolean isParamAllowed(Parameter parameter, Operation operation, ApiDescription api, Map<String, List<String>> params, Map<String, String> cookies, Map<String, List<String>> headers) {
                return true;
            }

            @Override
            public boolean isPropertyAllowed(Model model, Property property, String propertyName, Map<String, List<String>> params, Map<String, String> cookies, Map<String, List<String>> headers) {
                return true;
            }
        });

    }

    public Swagger configure(Swagger swagger) {
        return swagger;
    }
}