package org.example.spring.dynamic.datasource.aop;

/**
 * The type Mock bean.
 */
public record MockBean(String name) {

    /**
     * Invoke none string.
     *
     * @return the string
     */
    public String invokeNone() {
        return name;
    }

    /**
     * Invoke string.
     *
     * @return the string
     */
    @MockMergedAnnotation
    public String invoke() {
        return name;
    }

    /**
     * Invoke child string.
     *
     * @return the string
     */
    @MockMergedAnnotationChild
    public String invokeChild() {
        return name;
    }
}