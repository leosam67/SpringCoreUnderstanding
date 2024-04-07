package ru.leosam.spring;

import ru.leosam.spring.components.Component;
import ru.leosam.spring.components.Inject;

import java.io.File;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class ObjectComposer {
    private Map<String, Object> objects = new HashMap<>();
    private Map<String, Object> operations = new HashMap<>();
    private Map<String, String> alias = new HashMap<>();
    private String objectKeyFromFieldName(String fieldName) {
        String objName = fieldName.substring(0, 1).toUpperCase()
                + fieldName.substring(1);
        return objName;
    }
    public ObjectComposer(String packageName) {
        try {
            String filePackageName = "/target/classes/" + packageName.replace('.', '/');
            filePackageName = new File(".").getCanonicalPath() + filePackageName;
            File pack = new File(filePackageName);
            for (File file : pack.listFiles()) {
                if (!file.getName().endsWith("class")) continue;
                String className = file.getName().split("\\.")[0];
                Class aClass = Class.forName(packageName + "." + className);
                if (!aClass.isAnnotationPresent(Component.class)) continue;
                String componentName = aClass.getSimpleName();
                Component component = (Component) aClass.getAnnotation(Component.class);
                if (!component.value().isEmpty()) {
                    componentName = component.value();
                    alias.put(componentName, className);
                    operations.put(componentName, className);
                } else {
                    alias.put(componentName, componentName);
                }
                objects.put(componentName, aClass.getDeclaredConstructor().newInstance());
                // System.out.println("Mapped " + componentName + " to " + objects.get(componentName));
            }
            System.out.println("Operations are:");
            for(String key : operations.keySet()) {
                System.out.println(key + "\t" + operations.get(key));
            }

            for (String clz : objects.keySet()) {
                Object obj = objects.get(clz);
                Class aClass = obj.getClass();
                for(Field field : obj.getClass().getDeclaredFields()) {
                    if(field.isAnnotationPresent(Inject.class)) {
                        String injectValue = ((Inject) field.getAnnotation(Inject.class)).value();
                        field.setAccessible(true);
                        if(objects.containsKey(objectKeyFromFieldName(field.getName()))) {
                            field.set(obj, objects.get(objectKeyFromFieldName(field.getName())));
                        } else if (!injectValue.isEmpty()) {
                            field.set(obj, objects);
                        } else {
                            throw new IllegalArgumentException("NO object bo be injected to "
                                + aClass.getName() + "." + field.getName()
                                + " typed" + field.get(obj).getClass().getName());
                        }
                    } else System.out.println();
                }
            }
        } catch (Exception ex) {
            throw new IllegalArgumentException(ex);
        }
    }
    public Object getObjectByName(String name) {
        return objects.get(name);
    }
    public <T> T getObjectByNameAndType(String name, Class<T> clz) {
        return (T) objects.get(name);
    }
}
