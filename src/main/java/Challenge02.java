import org.json.*;

public class Challenge02 {
    public Challenge02()throws Exception{

        //get the data
        Requests r = new Requests(2);
        JSONObject jsonObj = new JSONObject(r.getData());
        Long k = (Long)jsonObj.get("k");
        JSONArray jsonArr = jsonObj.getJSONArray("list");

        //search k
        int index;
        for(index = 0; index < jsonArr.length(); index++){
            if((jsonArr.get(index)).equals(k))
                break;
        }

        //post the solution
        r.postResult(String.valueOf(index));

    }
}
