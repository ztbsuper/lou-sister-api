package ztbsuper.lousysterm.publisher.servlets;

import com.wordnik.swagger.jaxrs.config.DefaultJaxrsScanner;
import com.wordnik.swagger.models.*;
import com.wordnik.swagger.models.auth.ApiKeyAuthDefinition;
import com.wordnik.swagger.models.auth.In;
import ztbsuper.lousysterm.base.enums.HeaderKeys;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class SwaggerBootstrapServlet extends HttpServlet {

    @Override
    public void init(ServletConfig config) throws ServletException {

        InputStream resourceAsStream = getClass().getClassLoader().getResourceAsStream("application.properties");
        Properties properties = new Properties();
        try {
            properties.load(resourceAsStream);
        } catch (IOException e) {
            e.printStackTrace();
        }

        String basePath = properties.getProperty("basepath");
        String host = properties.getProperty("host");
        String apiVersion = properties.getProperty("api.version");

        Info info = new Info()
                .title("Lousysterm API")
                .description("say nothing")
                .contact(new Contact()
                        .email("ztbsuper@gmail.com"))
                .license(new License()
                        .name("Apache 2.0")
                        .url("http://www.apache.org/licenses/LICENSE-2.0.html"))
                .version(apiVersion);

        ServletContext context = config.getServletContext();
        Swagger swagger = new Swagger().info(info);
        swagger.setHost(host);
        List<Scheme> schemeList = new ArrayList<Scheme>();
        schemeList.add(Scheme.HTTP);
        swagger.setSchemes(schemeList);
        swagger.setBasePath(basePath);
        swagger.securityDefinition(HeaderKeys.ACCESS_TOKEN, new ApiKeyAuthDefinition(HeaderKeys.ACCESS_TOKEN, In.HEADER));
        context.setAttribute("swagger", swagger);

        super.init(config);
        config.getServletContext().setAttribute("scanner", new DefaultJaxrsScanner());

        config.getServletContext().setAttribute("reader", new LousystermSwaggerConfig(config));
    }
}
