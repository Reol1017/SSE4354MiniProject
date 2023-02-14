import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class TestATM {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		try {
		      ProcessBuilder builder = new ProcessBuilder("java", "-jar", "D:\\code\\eclipse-workspace\\JarPackage\\ATM.jar");
		      Process process = builder.start();

		      BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
		      String line;
		      while ((line = reader.readLine()) != null) {
		        System.out.println(line);
		      }
		      reader.close();
		    } catch (Exception e) {
		      e.printStackTrace();
		    }
//		try {
//		      Process process = Runtime.getRuntime().exec(new String[] {"cmd"});
//		      process = Runtime.getRuntime().exec(new String[] {"cmd","java","-jar","D:\\code\\eclipse-workspace\\JarPackage\\ATM.jar"});
//		       process = Runtime.getRuntime().exec(new String[] {"10074"});
//		      BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
//		      BufferedReader errorReader = new BufferedReader(new InputStreamReader(process.getErrorStream()));
//		      BufferedReader inputReader = new BufferedReader(new InputStreamReader(System.in));
//		      while (true) {
//		        System.out.print("> ");
//		        String inputCommand = inputReader.readLine();
//		        process.getOutputStream().write((inputCommand + "\n").getBytes());
//		        process.getOutputStream().flush();
//		        String line;
//		        while ((line = reader.readLine()) != null) {
//		          System.out.println(line);
//		        }
//		        while ((line = errorReader.readLine()) != null) {
//		          System.out.println(line);
//		        }
//		      }
//		    } catch (Exception e) {
//		      e.printStackTrace();
//		    }
	}

}
