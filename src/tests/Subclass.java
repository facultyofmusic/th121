package tests;

public class Subclass extends Super{
	
	@Override
	public void lol(){
		System.out.println("LOL IN SUB!!!");
		subOnlyMethod();
	}
	
	public void subOnlyMethod(){
		System.out.println("NOT REALLY....");
		
	}
	
	public static void main(String args[]){
		Super a = new Super(), b = new Subclass();
		a.lol();
		b.lol();
	}
}
