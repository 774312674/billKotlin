package com.rb.factory_compiler;

import com.google.auto.service.AutoService;
import com.rb.annotation.Factory;

import java.util.LinkedHashSet;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Filer;
import javax.annotation.processing.Messager;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.TypeElement;
import javax.lang.model.util.Elements;
import javax.lang.model.util.Types;

/**
 * @author: changZePeng
 * @date: 2020/11/9
 */
@AutoService(Process.class)
public class FactoryProcessor  extends AbstractProcessor {

    private Types mTypeUtils;
    private Messager mMessager;
    private Filer mFiler;
    private Elements mElementUtils;
//    private Map<String, FactoryGroupedClasses> factoryClasses = new LinkedHashMap<>();
    @Override
    public boolean process(Set<? extends TypeElement> set, RoundEnvironment roundEnvironment) {
        //	扫描所有被@Factory注解的元素
        for (Element annotatedElement : roundEnvironment.getElementsAnnotatedWith(Factory.class)) {
            if (annotatedElement.getKind() != ElementKind.CLASS) {
//                    throw new ProcessingException(annotatedElement, "Only classes can be annotated with @%s",
//                            Factory.class.getSimpleName());
            }
            TypeElement typeElement = (TypeElement) annotatedElement;
//            FactoryAnnotatedClass annotatedClass = new FactoryAnnotatedClass(typeElement);


        }
        return false;
    }
    @Override
    public synchronized void init(ProcessingEnvironment processingEnvironment) {
        super.init(processingEnvironment);
        mTypeUtils = processingEnvironment.getTypeUtils();
        mMessager = processingEnvironment.getMessager();
        mFiler = processingEnvironment.getFiler();
        mElementUtils = processingEnvironment.getElementUtils();

    }

    @Override
    public Set<String> getSupportedAnnotationTypes() {
        Set<String> annotations = new LinkedHashSet<>();
        annotations.add(Factory.class.getCanonicalName());
        return super.getSupportedAnnotationTypes();
    }

    @Override
    public SourceVersion getSupportedSourceVersion() {
        return super.getSupportedSourceVersion();
    }
}
