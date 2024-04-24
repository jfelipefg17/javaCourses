package services;

import models.Exam;

public interface ExamService {

  Exam findExamByName(String name);
}
