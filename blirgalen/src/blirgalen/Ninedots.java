package blirgalen;

import java.util.Arrays;
import java.util.Random;

public class Ninedots {
	
	static enum Dot{current, set, notSet};
	
	static int max = 3;
	static Dot arr[][] = new Dot[max][max];
	static int covered = 1;
	static int uncovered =0;
	static int steps=0;
	static int minSteps=9999;
	static int simulations=10000;
	

	public static void initDots() {
		for(int x=0;x<max;x++) {
			for(int y=0;y<max;y++) {
				arr[x][y]=Dot.notSet;
			}
		}
	}
	public static void printBoard() {
		for(int y=0;y<max;y++) {
			System.out.println("\n");
			for(int x=0;x<max;x++) {
				if(arr[x][y] == Dot.notSet) {
					System.out.printf("0\t");
				}else if(arr[x][y]== Dot.set) {
					System.out.printf("X\t");
				}else {
					System.out.printf("C\t");
				}
			}
		}
		
	}
	
	public static void coverDots() {
		for(int x=0;x<max;x++) {
			for(int y=0;y<max;y++) {
				if(arr[x][y] == Dot.notSet) {
					arr[x][y]=Dot.set;
				}
			}
		}
	}
	public static int[] findCurrent() {
		int pos[] = new int[2];
		for(int x=0;x<max;x++) {
			for(int y=0;y<max;y++) {
				if(arr[x][y] == Dot.current) {
					pos[0]=x;
					pos[1]=y;
					return pos;
				}
			}
		}
		throw new RuntimeException("Didn't find current");
	}
	
	public static boolean legalMove(int x, int y) {
		if((x>=0 && x<3) &&(y>=0 && y<3)) {
			return true;
		}else {
			return false;
		}
	}
	
	public static boolean allCovered() {
		for(int x=0;x<max;x++) {
			for(int y=0;y<max;y++) {
				if(arr[x][y] == Dot.notSet) {
					return false;
				}
			}
		}
		return true;
	}
	
	public static void goRight(int x, int y) {
		arr[x][y]=Dot.current;
		arr[x-1][y]=Dot.set;
	}
	public static void goLeft(int x, int y) {
		arr[x][y]=Dot.current;
		arr[x+1][y]=Dot.set;
	
	}
	public static void goUp(int x, int y) {
		arr[x][y]=Dot.current;
		arr[x][y+1]=Dot.set;
	}
	public static void goDown(int x, int y) {
		arr[x][y]=Dot.current;
		arr[x][y-1]=Dot.set;
	}
	public static void goUpRight(int x, int y) {
		arr[x][y]=Dot.current;
		arr[x-1][y+1]=Dot.set;
	}
	public static void goUpleft(int x, int y) {
		arr[x][y]=Dot.current;
		arr[x+1][y+1]=Dot.set;
	}
	public static void goDownRight(int x, int y) {
		arr[x][y]=Dot.current;
		arr[x-1][y-1]=Dot.set;
	}
	public static void goDownLeft(int x, int y) {
		arr[x][y]=Dot.current;
		arr[x+1][y-1]=Dot.set;
	}
	public static void main(String[] args) {
		for(int i=0;i<simulations;i++) {
			Random ran = new Random();
			initDots();
			arr[ran.nextInt(2)][ran.nextInt(2)] = Dot.current; //start at random pos
			//printBoard();
			//System.out.println("\nAre all covered? "+allCovered());
			while(!allCovered()) {
				int x = findCurrent()[0];
				int y = findCurrent()[1];
				double randVal = Math.random();
				//System.out.println("\n");
				//System.out.println("------------Step: "+steps+"-----------");
				if(randVal<=0.125 && legalMove(x+1,y)) {
					for(int j=1;j<3;j++) {
						if(legalMove(x+j, y)) {
							goRight(x+j, y);
						}
					}
					//System.out.println("go right");
					steps++;
				}else if((randVal<=0.25 && randVal>0.125) && (legalMove(x-1,y))) {
					for(int j=1;j<3;j++) {
						if(legalMove(x-j, y)) {
							goLeft(x-j, y);
						}
					}
					//System.out.println("go left");
					steps++;
				}else if((randVal<=0.375 && randVal>0.25) && (legalMove(x,y-1))) {
					for(int j=1;j<3;j++) {
						if(legalMove(x, y-j)) {
							goUp(x, y-j);
						}
					}
					//System.out.println("go up");
					steps++;
				}else if((randVal<=0.5 && randVal>0.375) && (legalMove(x,y+1))) {
					for(int j=1;j<3;j++) {
						if(legalMove(x, y+j)) {
							goDown(x, y+j);
						}
					}
					//System.out.println("go down");
					steps++;
				}else if((randVal<=0.612 && randVal>0.50) && (legalMove(x+1,y-1))) {
					for(int j=1;j<3;j++) {
						if(legalMove(x+j, y-j)) {
							goUpRight(x+j, y-j);
						}
					}
					//System.out.println("go upRight");
					steps++;
				}else if((randVal<=0.75 && randVal>0.612) && (legalMove(x-1,y-1))) {
					for(int j=1;j<3;j++) {
						if(legalMove(x-j, y-j)) {
							goUpleft(x-j, y-j);
						}
					}
					//System.out.println("go upLeft");
					steps++;
				}else if((randVal<=0.875 && randVal>0.75) && (legalMove(x+1,y+1))) {
					for(int j=1;j<3;j++) {
						if(legalMove(x+j, y+j)) {
							goDownRight(x+j, y+j);
						}
					}
					//System.out.println("go downRight");
					steps++;
				}else if((randVal<=1.0 && randVal>0.875) && (legalMove(x-1,y+1))) {
					for(int j=1;j<3;j++) {
						if(legalMove(x-j, y+j)) {
							goDownLeft(x-j, y+j);
						}
					}
					//System.out.println("go downLeft");
					steps++;
				}
				
				//printBoard();
			}
			if(steps<minSteps) {
				minSteps=steps;
			}
			System.out.println("\namount of steps: "+steps);
			steps=0;
		}	
		System.out.println("Best result: "+minSteps+" steps With "+simulations+" tries.");
	}
}
