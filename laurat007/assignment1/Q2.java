
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author laura
 */
public class Q2 {
	
	public static final String INPUT_FILE = "in";
	
	static class MyList {
		Object obj;
		MyList next;
		
		MyList() {
			next = null;
		}
		
		void addLast(Object n) {
			if(this.obj == null){
				this.obj = n;
				return;
			}
			
			MyList aux, newNode;
			newNode = new MyList();
			newNode.obj = n;
			newNode.next = null;
			
			aux = this;
			while(aux.next != null) {
				aux = aux.next;
			}
			aux.next = newNode;
		}
		
		public String toString() {
			String result = "";
			MyList aux = this;
			while(aux.next != null) { 
				result += aux.obj + ", ";
				aux = aux.next;
			}
			return result + aux.obj;
		}
	}
	
	public static void main(String[] args) {
		MyList list = new MyList();
		int N, K;
		
		try {
			Scanner sc = new Scanner(new BufferedReader(new FileReader(
				INPUT_FILE)));
			N = sc.nextInt();
			for(int i = 0; i < N; i++) {
				list.addLast(sc.nextInt());
			}
			K = sc.nextInt();
			sc.close();
		} catch (IOException e) {
				throw new RuntimeException(e);
			}
		
		//System.out.println(list);
		MyList aux = list;
		if(K > N) {
			System.out.println("K is too large");
		}
		else {
			for(int i = 1; i < N - K; i++) {
				aux = aux.next;
			}
			System.out.println(aux.obj);
		}
	}
}
