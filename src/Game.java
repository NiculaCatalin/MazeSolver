package tema;

import java.util.Comparator;
import java.util.LinkedList;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
//import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Game {
	QueueNode Romeo;
	QueueNode Juliet;
	CityMap map;

	public static CityMap Read() {
		try {
			Scanner information = new Scanner(new File("maze.in"));
			int n, m;
			n = information.nextInt();
			m = information.nextInt();
			char[][] matrix = new char[n][m];
			int i = 0;
			String fileLine;
			fileLine = information.nextLine();
			while (i < n) {
				fileLine = information.nextLine();
				matrix[i] = fileLine.toCharArray();
				i++;
			}
			information.close();
			return new CityMap(n, m, matrix);
		} catch (FileNotFoundException exception) {
			exception.printStackTrace();
		}
		return null;

	}

	public static void Write(LinkedList<QueueNode> queue) {
		try {
			Writer file = new FileWriter("maze.out");
			QueueNode node;
			String newLine = System.getProperty("line.separator");
			while(!queue.isEmpty())
			{
				node = queue.remove();
				file.write(node.distance + " " + node.ox + " " + node.oy + newLine);
			}
			file.close();
		}

		catch (IOException exception) {
			exception.printStackTrace();
		}
	}

	public int[][] GetDistanceMatrix(QueueNode currentNode) {
		LinkedList<QueueNode> queue = new LinkedList<QueueNode>();
		int i, j, ox, oy, distance;
		int distanceMatrix[][] = new int[this.map.height][this.map.width];
		queue.add(currentNode);

		while (!queue.isEmpty()) {

			currentNode = queue.remove();
			ox = currentNode.ox;
			oy = currentNode.oy;
			distance = currentNode.distance;
			for (i = -1; i <= 1; i++)
				for (j = -1; j <= 1; j++)
					if (this.map.IsInBounds(ox + i, oy + j))
						if (this.map.IsValid(ox + i, oy + j, distanceMatrix[ox + i][oy + j])) {
							queue.add(new QueueNode(distance + 1, ox + i, oy + j));
							distanceMatrix[ox + i][oy + j] = distance + 1;
						}

		}
		return distanceMatrix;

	}

	public static void main(String[] args) {

		Game game = new Game();
		CityMap map = Read();
		game.map = map;
		game.Romeo = map.GetRomeo();
		game.Juliet = map.GetJuliet();
		int romeoDistanceMatrix[][] = game.GetDistanceMatrix(game.Romeo);
		int julietDistanceMatrix[][] = game.GetDistanceMatrix(game.Juliet);
		int i, j, minimumDistance;
		minimumDistance = game.map.width * game.map.height;
		LinkedList<QueueNode> queue = new LinkedList<QueueNode>();
		for (i = 0; i < game.map.height; i++)
			for (j = 0; j < game.map.width; j++) {

				if (romeoDistanceMatrix[i][j] == julietDistanceMatrix[i][j])
					if (romeoDistanceMatrix[i][j] < minimumDistance && romeoDistanceMatrix[i][j] != 0)
						minimumDistance = romeoDistanceMatrix[i][j];
			}
		for (i = 0; i < game.map.height; i++)
			for (j = 0; j < game.map.width; j++) {

				if (romeoDistanceMatrix[i][j] == julietDistanceMatrix[i][j])
					if (romeoDistanceMatrix[i][j] == minimumDistance)
						queue.add(new QueueNode(minimumDistance, i+1, j+1));
						
			}
		game.Write(queue);
	}

}
