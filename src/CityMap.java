package tema;

public class CityMap {
	int height;
	int width;
	char[][] matrix;

	public CityMap(int n, int m, char[][] matrix) {
		this.height = n;
		this.width = m;
		this.matrix = matrix;
	}

	public QueueNode GetRomeo() {
		int i, j;
		for (i = 0; i < this.height; i++)
			for (j = 0; j < this.width; j++)
				if (this.matrix[i][j] == 'R') {
					return new QueueNode(1, i, j);
				}
		return null;
	}

	public QueueNode GetJuliet() {
		int i, j;
		for (i = 0; i < this.height; i++)
			for (j = 0; j < this.width; j++)
				if (this.matrix[i][j] == 'J') {
					return new QueueNode(1, i, j);
				}
		return null;
	}

	public boolean IsInBounds(int a, int b) {
		if (a < 0 || b < 0 || a >= this.height || b >= this.width)
			return false;
		else
			return true;
	}

	public char GetValue(int a, int b) {
		return this.matrix[a][b];
	}

	public boolean IsValid(int a, int b, int beenThere) {
		if (this.IsInBounds(a, b))
			if (this.GetValue(a, b) != 'X' && beenThere == 0)
				return true;
		return false;
	}
}
