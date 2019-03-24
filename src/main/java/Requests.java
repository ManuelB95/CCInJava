import java.io.InputStream;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;


public class Requests{
    private int challNr;
    public Requests(int challNr){
        this.challNr = challNr;
    }

    public String getData() throws Exception{
        String reqestUrl = "https://cc.the-morpheus.de/challenges/" + challNr + "/";
        Scanner scanner = new Scanner(new URL(reqestUrl).openStream());

        if (!scanner.hasNextLine()) {
            System.err.println("Scanner is empty!");
            scanner.close();
            return null;
        }

        String data = "";

        while(scanner.hasNextLine()) {
            data += scanner.nextLine() + "\n";
        }
        scanner.close();
        return data;
    }


    public void postResult(String result)throws Exception{

        String postUrl = "https://cc.the-morpheus.de/solutions/" + challNr + "/";

        HttpURLConnection connection = (HttpURLConnection) new URL(postUrl).openConnection();
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setDoInput(true);
        connection.setDoOutput(true);
        connection.connect();

        PrintWriter writer = new PrintWriter(connection.getOutputStream());
        writer.print("{\"token\":" + result + "}");
        writer.close();

        System.out.println("Response Code: " + connection.getResponseCode());
        InputStream stream;
        if (connection.getResponseCode() == 200) stream = connection.getInputStream();
        else stream = connection.getErrorStream();

        System.out.println("Response Data:");
        int c;
        String s = "";
        while((c=stream.read()) != -1){
            s += (char)c;
        }
        System.out.println(s);

        connection.disconnect();
        System.out.println("=> done!");

    }
}
