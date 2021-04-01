import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;


public class TicTac {
	
	static ArrayList<Integer> playerPosition = new ArrayList<Integer>();
	static ArrayList<Integer> cpuPosition = new ArrayList<Integer>();
	
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		
		char[][] ticPrint = {{' ', '|', ' ', '|', ' '},
				 {'-', '+', '-', '+', '-'},
				 {' ', '|', ' ', '|', ' '},
				 {'-', '+', '-', '+', '-'},
				 {' ', '|', ' ', '|', ' '}};
		
		printTic(ticPrint);
		
		while(true) {
			System.out.println("Enter your placement (1-9):");
			int playerPos = input.nextInt();
			while(playerPosition.contains(playerPos) || cpuPosition.contains(playerPos) ) {
				System.out.println("Position taken! Enter a correct possition: ");
				playerPos = input.nextInt();
			}
			
			switches(ticPrint, playerPos, "player");
			
			String result = checkWinner();
			if(result.length() > 0) {
				printTic(ticPrint);
				System.out.println(result);
				break;
			}
			
			Random rand = new Random();
			int cpuPos = rand.nextInt(9) + 1;
			while(playerPosition.contains(cpuPos) || cpuPosition.contains(cpuPos) ) {
				cpuPos = rand.nextInt(9) + 1;
			}
			
			switches(ticPrint, cpuPos, "cpu");
			
			printTic(ticPrint);
			
			result = checkWinner();
			if(result.length() > 0) {
				printTic(ticPrint);
				System.out.println(result);
				break;
			}

		}
		input.close();
		}
	public static void printTic(char[][] ticPrint) {
		for(char[] row : ticPrint) {
			for(char c : row) {
				System.out.print(c);
			}
			System.out.println();
		}
	}
	public static void switches(char[][] ticPrint, int pos, String user) {
		char symbol = 'X';
		if(user.equals("player")) {
			symbol = 'X';
			playerPosition.add(pos);
		} else if(user.equals("cpu")) {
			symbol = 'O';
			cpuPosition.add(pos);
		}
		
		switch(pos) {
		case 1: ticPrint[0][0] = symbol;
			break;
		case 2: ticPrint[0][2] = symbol;
			break;
		case 3: ticPrint[0][4] = symbol;
			break;
		case 4: ticPrint[2][0] = symbol;
			break;
		case 5: ticPrint[2][2] = symbol;
			break;
		case 6: ticPrint[2][4] = symbol;
			break;
		case 7: ticPrint[4][0] = symbol;
			break;
		case 8: ticPrint[4][2] = symbol;
			break;
		case 9: ticPrint[4][4] = symbol;
			break;
	}
	}
	
	public static String checkWinner() {
		
		List<Integer> topRow = Arrays.asList(1, 2, 3);
		List<Integer> midRow = Arrays.asList(4, 5, 6);
		List<Integer> botRow = Arrays.asList(7, 8, 9);
		List<Integer> leftCol = Arrays.asList(1, 4, 7);
		List<Integer> midCol = Arrays.asList(2, 5, 8);
		List<Integer> rightCol = Arrays.asList(3, 6, 9);
		List<Integer> cross1 = Arrays.asList(1, 5, 9);
		List<Integer> cross2 = Arrays.asList(7, 5, 3);
		
		List<List> winning = new ArrayList<List>();
		winning.add(topRow);
		winning.add(midRow);
		winning.add(botRow);
		winning.add(leftCol);
		winning.add(midCol);
		winning.add(rightCol);
		winning.add(cross1);
		winning.add(cross2);
	
		for(List<?> l : winning) {
			if(playerPosition.containsAll(l)) {
				return "Congratulations you won";
			} else if(cpuPosition.containsAll(l)) {
				return "CPU wins! Sorry :(";
			} else if(playerPosition.size() + cpuPosition.size() == 9) {
				return "CAT!";
			}
		}
		
		return "";
	}
	}
	
