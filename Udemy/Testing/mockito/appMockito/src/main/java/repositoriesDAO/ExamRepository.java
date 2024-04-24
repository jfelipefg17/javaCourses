package repositoriesDAO;

import models.Exam;

import java.util.List;

public interface ExamRepository {

  List<Exam> findAll();
}
