package be.kdg.reisproject.reflection;


import sun.reflect.generics.tree.FormalTypeParameter;

import java.lang.annotation.Annotation;
import java.lang.reflect.*;


/**
 * @author Lotte Vanhalst
 * @version 1.0 2/04/2019 17:46
 */
public class ReflectionTools {

    public static void classAnalysis (Class aClass){
        System.out.println("Analyse van de klasse: " + aClass.getSimpleName());
        System.out.println("=================================================================");
        System.out.println("Fully qualified name     : " + aClass.getName());
        System.out.println("Naam van de superklasse  : " + aClass.getSuperclass().getSimpleName());
        System.out.println("Naam van de package      : " + aClass.getPackage());

        StringBuilder interfacestr = new StringBuilder();
        interfacestr.append("Interfaces               : ");
        for (Class inter: aClass.getInterfaces()){
            interfacestr.append(inter.getSimpleName()).append(" ");
        }

        System.out.println(interfacestr.toString());

        StringBuilder constructorstr = new StringBuilder();
        constructorstr.append("Constructors             : ");
        for (Constructor constructor: aClass.getDeclaredConstructors()){
            constructorstr.append(constructor.toString()).append(" ");
        }
        System.out.println(constructorstr.toString());

        StringBuilder attstr = new StringBuilder();
        attstr.append("Attributen               : ");
        for (Field attribuut: aClass.getDeclaredFields()){
            attstr.append(attribuut.getName()).append(" ");
        }
        System.out.println(attstr.toString());

        StringBuilder getstr = new StringBuilder();
        getstr.append("Getters                  : ");
        for (Method methode: aClass.getDeclaredMethods()){
            if (methode.getName().startsWith("get")) {
                getstr.append(methode.getName()).append(" ");
            }
        }
        System.out.println(getstr.toString());

        StringBuilder setstr = new StringBuilder();
        setstr.append("Setters                  : ");
        for (Method methode: aClass.getDeclaredMethods()){
            if (methode.getName().startsWith("set")) {
                setstr.append(methode.getName()).append(" ");
            }
        }
        System.out.println(setstr.toString());

        StringBuilder methstr = new StringBuilder();
        methstr.append("Andere methoden          : ");
        for (Method methode: aClass.getDeclaredMethods()){
            if (!methode.getName().startsWith("get") && !methode.getName().startsWith("set")) {
                methstr.append(methode.getName()).append(" ");
            }
        }
        System.out.println(methstr.toString());
        System.out.println();

    }

    public static Object runAnnotated (Class aClass) throws IllegalAccessException, InstantiationException, InvocationTargetException {
        System.out.println("Aangemaakt object door runAnnotated:");
        Object object = aClass.newInstance();
        Object result = new Object();
        for (Method method: aClass.getMethods()) {
            if (method.getAnnotation(CanRun.class)!=null && (method.getParameterCount() == 1)){
                for (Parameter parameter: method.getParameters()) {
                    if (parameter.getType().getSimpleName().equals("String")) {
                        result = method.invoke(object, method.getAnnotation(CanRun.class).value());
                    }
                }
            }
        }
        System.out.println(object.toString());
        return object;
    }
}
