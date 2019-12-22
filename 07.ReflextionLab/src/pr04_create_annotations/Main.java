package pr04_create_annotations;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Class<AnnotationTest> annotationTest = AnnotationTest.class;
        System.out.println(Arrays.toString(annotationTest.getAnnotation(Subject.class).categories()));
    }
}
