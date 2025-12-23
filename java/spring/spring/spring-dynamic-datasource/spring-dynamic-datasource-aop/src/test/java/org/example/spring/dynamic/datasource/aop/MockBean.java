package org.example.spring.dynamic.datasource.aop;

import lombok.RequiredArgsConstructor;

/**
 * The type Mock bean.
 */
@RequiredArgsConstructor
public class MockBean {

    private final String name;

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