public class Main {
  public static void main(String[] args) {
    System.out.println("Hello and welcome!");

    // Código para generar e imprimir una matriz en espiral
    int n = 5; // Tamaño de la matriz
    int[][] spiralMatrix = fillSpiralMatrix(n);

    // Imprimir la matriz en espiral
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < n; j++) {
        System.out.printf("%3d ", spiralMatrix[i][j]);
      }
      System.out.println();
    }
  }

  public static int[][] fillSpiralMatrix(int n) {
    int[][] matrix = new int[n][n];
    int direction = 0; // 0 - derecha, 1 - abajo, 2 - izquierda, 3 - arriba
    int row = 0, col = 0;

    for (int num = 1; num <= n * n; num++) {

      matrix[row][col] = num;

      if (direction == 0) {
        if (col + 1 < n && matrix[row][col + 1] == 0) {
          col++;
        } else {
          direction = 1;
          row++;
        }
      } else if (direction == 1) {
        if (row + 1 < n && matrix[row + 1][col] == 0) {
          row++;
        } else {
          direction = 2;
          col--;
        }
      } else if (direction == 2) {
        if (col - 1 >= 0 && matrix[row][col - 1] == 0) {
          col--;
        } else {
          direction = 3;
          row--;
        }
      } else if (direction == 3) {
        if (row - 1 >= 0 && matrix[row - 1][col] == 0) {
          row--;
        } else {
          direction = 0;
          col++;
        }
      }
    }

    return matrix;
  }
}
