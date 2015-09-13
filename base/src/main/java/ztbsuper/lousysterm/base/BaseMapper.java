package ztbsuper.lousysterm.base;

import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import org.apache.log4j.Logger;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public abstract class BaseMapper {

    private static Logger log = Logger.getLogger(BaseMapper.class);

    protected final MapperFactory mapperFactory;

    protected BaseMapper() {
        mapperFactory = new DefaultMapperFactory.Builder().build();
    }

    protected void register(Class<?> typeA, Class<?> typeB) {
        mapperFactory.classMap(typeA, typeB)
                .byDefault()
                .register();
    }

    public <T> T map(Object obj, Class<T> targetType) {
        return mapperFactory.getMapperFacade().map(obj, targetType);
    }

    public static <S, D> D merge(S sourceObject, D destinationObject) {
        Class<?> sourceClass = sourceObject.getClass();
        Class<?> destinationClass = destinationObject.getClass();
        Field[] sourceFields = sourceClass.getDeclaredFields();
        for (Field sourceField : sourceFields) {
            sourceField.setAccessible(true);
            String fieldName = sourceField.getName();
            try {
                Object val = sourceField.get(sourceObject);
                if (val != null) {
                    Field destField = destinationClass.getDeclaredField(fieldName);
                    destField.setAccessible(true);
                    destField.set(destinationObject, val);
                    destField.setAccessible(false);
                }
            } catch (IllegalAccessException e) {
                log.info(e.getMessage());
            } catch (NoSuchFieldException e) {
                log.info(e.getMessage());
            }
            sourceField.setAccessible(false);
        }
        return destinationObject;
    }

    public <T> List<T> mapList(List<? extends Object> source, Class<T> targetType) {
        if (source == null) return null;

        List<T> result = new ArrayList<T>(source.size());
        for (Object object : source) {
            result.add(mapperFactory.getMapperFacade().map(object, targetType));
        }
        return result;
    }
}