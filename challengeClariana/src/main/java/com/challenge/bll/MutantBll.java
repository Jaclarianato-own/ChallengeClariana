package com.challenge.bll;

import java.util.ArrayList;
import java.util.List;

import com.challenge.models.Mutant;

public class MutantBll {

	public static boolean isMutant(Mutant mutant) {

		try {
			boolean isSimmetrical = validateMatrixSymmetryNxN(mutant);

			if (!isSimmetrical)
				return false;

			boolean isLetters = validateLetters(mutant);

			if (!isLetters)
				return false;

			char[][] matrix = ConvertToCharMatrix(mutant.getDna());
			
			if(null == matrix)
				return false;

			int sequences = horizontalValidation(matrix);

			if (sequences > 1)
				return true;

			sequences = verticalValidation(matrix) + sequences;

			if (sequences > 1)
				return true;

			sequences = leftDiagonal(matrix) + sequences;

			if (sequences > 1)
				return true;

			sequences = rigthDiagonal(matrix) + sequences;

			if (sequences > 1)
				return true;

			return false;

		} catch (Exception e) {
			return false;
		}

	}

	private static boolean validateLetters(Mutant mutant) {

		try {

			for (String x : mutant.getDna()) {
				char[] caracteres = x.toCharArray();

				for (char y : caracteres) {
					if (y != 'A' && y != 'T' && y != 'C' && y != 'G')
						return false;
				}
			}

			return true;

		} catch (Exception e) {
			return false;
		}

	}

	private static int leftDiagonal(char[][] matrix) {

		try {
			Integer altura = matrix.length, anchura = matrix[0].length;

			int secuencia = 0;
			for (Integer diagonal = 1 - anchura; diagonal <= altura - 1; diagonal += 1) {

				char referencia = matrix[Math.max(0, diagonal)][-Math.min(0, diagonal)];

				int cont = 1;
				for (Integer vertical = Math.max(0, diagonal), horizontal = -Math.min(0, diagonal); vertical < altura
						&& horizontal < anchura; vertical += 1, horizontal += 1) {

					if (matrix[vertical][horizontal] == referencia) {
						cont++;
					} else {
						referencia = matrix[vertical][horizontal];
						cont = 1;
					}

					if (cont == 4) {
						secuencia++;
						cont = 0;
					}
				}
			}

			return secuencia;

		} catch (Exception e) {
			return -1;
		}

	}

	private static int rigthDiagonal(char[][] matrix) {

		try {
			List<String> aux = new ArrayList<>();
			for (int k = 0; k < matrix.length * 2; k++) {

				System.out.println();

				String fila = "";
				for (int j = 0; j <= k; j++) {

					int i = k - j;

					if (i < matrix.length && j < matrix.length) {

						fila = fila + matrix[i][j];

					}
				}

				aux.add(fila);

			}

			int secuencia = getSecuenciaRigthDiagonal(aux);

			return secuencia;

		} catch (Exception e) {
			return -1;
		}

	}

	private static int getSecuenciaRigthDiagonal(List<String> aux) {

		try {
			int secuencia = 0;

			for (String x : aux) {

				if (x == "")
					continue;
				char[] f = x.toCharArray();
				char reference = f[0];

				int cont = 1;
				int r = 0;
				for (char g : f) {

					if (r == 0) {
						r++;
						continue;
					}

					if (g == reference) {
						cont++;
					} else {
						reference = g;
						cont = 1;
					}

					if (cont == 4) {
						secuencia++;
						cont = 0;
					}
				}

			}

			return secuencia;
		} catch (Exception e) {
			return -1;
		}

	}

	private static Integer verticalValidation(char[][] matrix) {

		try {
			int secuencia = 0;
			for (Integer j = 0; j < matrix[0].length; j++) {

				char referencia = matrix[0][j];
				int cont = 1;
				for (Integer i = 0; i < matrix.length; i++) {

					if (i == 0)
						continue;

					if (matrix[i][j] == referencia) {
						cont++;
					} else {
						referencia = matrix[i][j];
						cont = 1;
					}

					if (cont == 4) {
						secuencia++;
						cont = 0;
					}

				}

			}

			return secuencia;
		} catch (Exception e) {
			return -1;
		}

	}

	private static Integer horizontalValidation(char[][] matrix) {

		try {
			int secuencia = 0;
			for (Integer i = 0; i < matrix.length; i++) {

				char referencia = matrix[i][0];
				int cont = 1;
				for (Integer j = 0; j < matrix[0].length; j++) {

					if (j == 0)
						continue;

					if (matrix[i][j] == referencia) {
						cont++;
					} else {
						referencia = matrix[i][j];
						cont = 1;
					}

					if (cont == 4) {
						secuencia++;
						cont = 0;
					}

				}

			}

			return secuencia;
		} catch (Exception e) {
			return -1;
		}

	}

	private static char[][] ConvertToCharMatrix(String[] dna) {

		try {

			char[][] matrix = new char[dna.length][dna.length];

			int x = 0;
			for (String i : dna) {

				char[] file = i.toCharArray();

				int y = 0;
				for (char j : file) {
					matrix[x][y] = j;
					y++;
				}
				x++;
			}

			return matrix;

		} catch (Exception e) {
			return null;
		}

	}

	private static boolean validateMatrixSymmetryNxN(Mutant mutant) {

		try {
			int numFiles = mutant.getDna().length;

			for (String i : mutant.getDna()) {

				int numColumns = i.length();

				if (numColumns != numFiles)
					return false;

			}

			return true;
		} catch (Exception e) {
			return false;
		}

	}

}
