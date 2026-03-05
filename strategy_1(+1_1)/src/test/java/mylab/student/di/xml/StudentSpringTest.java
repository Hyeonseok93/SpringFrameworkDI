package mylab.student.di.xml;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.support.GenericXmlApplicationContext;

public class StudentSpringTest {

    private GenericXmlApplicationContext context;

    @BeforeEach
    public void setup() {
        context = new GenericXmlApplicationContext("classpath:mylab-student-di.xml");
    }

    @Test
    public void testStudentGradeSystem() {
        Course course = context.getBean("course", Course.class);
        GradeService gradeService = context.getBean("gradeService", GradeService.class);

        assertNotNull(course);
        assertNotNull(gradeService);

        double avgScore = course.getAverageScore();
        assertEquals(85.0, avgScore, 0.01);
        System.out.println("평균 점수 검증: " + avgScore);

        String gradeS001 = gradeService.calculateGrade("S001");
        assertEquals("A", gradeS001);
        System.out.println("S001 학생 학점 검증: " + gradeS001);

        int highScorerCount = gradeService.getHighScoreStudents(90).size();
        assertEquals(1, highScorerCount);
        System.out.println("90점 이상 고득점자 수 검증: " + highScorerCount);
    }
}
