import java.awt.*;
import java.io.*;
import java.net.Socket;

/**
 * Handles communication to/from the server for the editor
 * 
 * @author Chris Bailey-Kellogg, Dartmouth CS 10, Fall 2012
 * @author Chris Bailey-Kellogg; overall structure substantially revised Winter 2014
 * @author Travis Peters, Dartmouth CS 10, Winter 2015; remove EditorCommunicatorStandalone (use echo server for testing)
 * @author Tim Pierson Dartmouth CS 10, provided for Winter 2024
 */
public class EditorCommunicator extends Thread {
	private PrintWriter out;		// to server
	private BufferedReader in;		// from server
	protected Editor editor;		// handling communication for

	/**
	 * Establishes connection and in/out pair
	 */
	public EditorCommunicator(String serverIP, Editor editor) {
		this.editor = editor;
		System.out.println("connecting to " + serverIP + "...");
		try {
			Socket sock = new Socket(serverIP, 4242);
			out = new PrintWriter(sock.getOutputStream(), true);
			in = new BufferedReader(new InputStreamReader(sock.getInputStream()));
			System.out.println("...connected");
		}
		catch (IOException e) {
			System.err.println("couldn't connect");
			System.exit(-1);
		}
	}

	/**
	 * Sends message to the server
	 */
	public void send(String msg) {
		out.println(msg);
	}

	/**
	 * Keeps listening for and handling (your code) messages from the server
	 */
	public void run() {
		try {
			// Handle messages
			// TODO: YOUR CODE HERE
			//get line from server
			String line;
			while ((line = in.readLine()) != null){
				System.out.println(line);
				Parse parse = new Parse();
				parse.parseLine(line, editor.getSketch());
				//out prints to client
				//sout prints to server
			}
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		finally {
			System.out.println("server hung up");
		}
	}	

	// Send editor requests to the server
	// TODO: YOUR CODE HERE

	//method for add request
	public synchronized void add(Shape shape){
		send("add " + shape);
	}

	//method for delete request
	public synchronized void delete(Integer id){
		send("delete " + id);
	}


	//method for move request
	public synchronized void move(Integer id, Integer mX, Integer mY){
		send("move " + id  + " " + mX + " " + mY);
	}

	//method for recolor request
	public synchronized void recolor(Integer id, Color color){
		send("recolor " + id + " " + color.getRGB());
	}

	//method for drag request
//	public synchronized void drag(Integer id){
//		send("drag " + id);
//	}


}
