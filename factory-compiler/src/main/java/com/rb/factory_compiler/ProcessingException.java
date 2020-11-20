package com.rb.factory_compiler;

import javax.lang.model.element.Element;

/**
 * @author: changZePeng
 * @date: 2020/11/9
 */
class ProcessingException extends Throwable {
    public ProcessingException(Element annotatedElement, String s, String simpleName) {
        System.out.print(String.format(s,simpleName));
    }
}
