

public class Challenge01 {
    Challenge01()throws Exception{

        Requests r = new Requests(1);
        String data = r.getData();

        data = data.replace("\n", "");
        data = "\"" + data + "\"";

        r.postResult(data);
    }
}
