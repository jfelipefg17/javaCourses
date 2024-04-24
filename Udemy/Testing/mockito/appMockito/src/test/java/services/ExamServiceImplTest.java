package services;

import models.Exam;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import repositoriesDAO.ExamRepository;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;


class ExamServiceImplTest {



  @Test
  void findExamByName() {
    ExamRepository repository = mock(ExamRepository.class);
    ExamService service = new ExamServiceImpl(repository);
    List<Exam> list = Arrays.asList(new Exam(5L, "Math"), new Exam(6L, "Spanish"), new Exam(7L, "Science"));

    when(repository.findAll()).thenReturn(list);

    Exam exam = service.findExamByName("Math");



    Assertions.assertNotNull(exam);
    Assertions.assertEquals(5l, exam.getId());
    Assertions.assertEquals("Math", exam.getName());

  }
}